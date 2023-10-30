package com.biblio.dao;

import com.biblio.beans.Livre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.biblio.dao.DAOUtilitaire.fermetures;
import static com.biblio.dao.DAOUtilitaire.initialisationRequetePreparer;

public class LivreDAOImpl implements LivreDAO {
    private static final String SQL_INSERT = "INSERT into livres (designation,auteur,disponible,dateEdition,nombrePret) VALUES (? , ? , ? , ? ,?)";
    private static final String SQL_SELECT_ALL = "SELECT * FROM livres ORDER BY idLivre DESC";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM livres WHERE idLivre = ?";
    private static final String SQL_DELETE_BY_Id = "DELETE FROM livres WHERE idLivre = ?";
    private static final String SQL_UPDATE = "UPDATE livres set designation = ? , auteur = ? , disponible = ? , dateEdition = ? WHERE idLivre = ?";
    private static final String SQL_UPDATE_DISPONIBLE = "UPDATE livres SET disponible = ? WHERE idLivre = ?";
    private static final String SQL_PLUS_1_NOMBRE_PRET ="UPDATE livres SET nombrePret = nombrePret + 1 WHERE idLivre = ?";
    private static final String SQL_MOINS_1_NOMBRE_PRET ="UPDATE livres SET nombrePret = nombrePret - 1 WHERE idLivre = ?";
    private static final String SQL_SELECT_RECHERCHE = "SELECT * FROM livres WHERE idLivre LIKE ? OR designation LIKE ? OR auteur LIKE ?  OR dateEdition LIKE ? OR nombrePret LIKE ? ORDER BY idLivre DESC";


    private DAOFactory daoFactory;

    public LivreDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(Livre livre) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeurGenerer = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparer(connection,SQL_INSERT,true,livre.getDesignation(),livre.getAuteur(),livre.getDisponible(),livre.getDateEdition() , livre.getNombrePret());
            int statut = preparedStatement.executeUpdate();
            if (statut == 0){
                throw new DAOException("echec de la creation d'un livre , aucune livre ajouté à la table");
            }
            valeurGenerer = preparedStatement.getGeneratedKeys();
            if(valeurGenerer.next()){
                livre.setIdLivre(valeurGenerer.getLong(1));
            }
            else{
                throw new DAOException("Echec de la creation d'un livre aucune , aucun ID auto-generé retourner");
            }
        }catch (SQLException e){
            throw new DAOException(e);
        }finally {
            fermetures(valeurGenerer ,preparedStatement , connection);
        }

    }

    @Override
    public Livre search(Long id) throws DAOException {
        return trouver(SQL_SELECT_BY_ID , id);
    }

    @Override
    public List<Livre> affiched() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null ;
        ResultSet resultSet = null;
        List<Livre> livres = new ArrayList<Livre>();
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                livres.add(map(resultSet));
            }
        }catch (SQLException e){
            throw new DAOException(e.getMessage());
        }finally {
            fermetures(resultSet , preparedStatement , connection);
        }
        return livres;
    }

    @Override
    public List<Livre> recherche(String nom) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null ;
        ResultSet resultSet = null;
        List<Livre> livres = new ArrayList<Livre>();
        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparer(connection,SQL_SELECT_RECHERCHE , false , nom , nom , nom , nom, nom);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                livres.add(map(resultSet));
            }
        }catch (SQLException e){
            throw new DAOException(e.getMessage());
        }finally {
            fermetures(resultSet , preparedStatement , connection);
        }
        return livres;
    }

    @Override
    public void delete(Livre livre) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparer(connection , SQL_DELETE_BY_Id , true , livre.getIdLivre());
            int statut = preparedStatement.executeUpdate();
            if (statut == 0){
                throw new DAOException("Echec de la suppression , aucune ligne supprimée de la table ");
            }
            else {
                livre.setIdLivre(null);
            }
        }catch (SQLException e){
            throw new DAOException(e);
        }finally {
            fermetures(preparedStatement , connection);
        }
    }

    @Override
    public void update(Livre livre) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement  = initialisationRequetePreparer(connection , SQL_UPDATE , false , livre.getDesignation() , livre.getAuteur() , livre.getDisponible() , livre.getDateEdition() , livre.getIdLivre());
            int statut = preparedStatement.executeUpdate();
            if (statut == 0){
                throw new DAOException("echec de la modification du livre,aucun ligne de la table a été modifié");
            }
        }catch (SQLException e){
            throw new DAOException(e);
        }
        finally {
            fermetures(preparedStatement ,connection);
        }

    }

    @Override
    public void updateDisponible(Livre livre) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement  = initialisationRequetePreparer(connection , SQL_UPDATE_DISPONIBLE , false ,  livre.getDisponible() , livre.getIdLivre());
            int statut = preparedStatement.executeUpdate();
            if (statut == 0){
                throw new DAOException("echec de la modification du disponiblité du livre,aucun ligne de la table a été modifié");
            }
        }catch (SQLException e){
            throw new DAOException(e);
        }
        finally {
            fermetures(preparedStatement ,connection);
        }

    }

    @Override
    public void updateNombrePret(Livre livre) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement  = initialisationRequetePreparer(connection , SQL_PLUS_1_NOMBRE_PRET , false ,livre.getIdLivre());
            int statut = preparedStatement.executeUpdate();
            if (statut == 0){
                throw new DAOException("echec de l'ajout plus 1 au nombrePret,aucun ligne de la table a été modifié");
            }
        }catch (SQLException e){
            throw new DAOException(e);
        }
        finally {
            fermetures(preparedStatement ,connection);
        }

    }

    @Override
    public void updateNombrePretM_1(Livre livre) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement  = initialisationRequetePreparer(connection , SQL_MOINS_1_NOMBRE_PRET , false ,livre.getIdLivre());
            int statut = preparedStatement.executeUpdate();
            if (statut == 0){
                throw new DAOException("echec de l'ajout moins 1 au nombrePret,aucun ligne de la table a été modifié");
            }
        }catch (SQLException e){
            throw new DAOException(e);
        }
        finally {
            fermetures(preparedStatement ,connection);
        }
    }

    private Livre trouver(String sql , Object... objects)throws DAOException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Livre livre = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparer(connection,sql,false,objects);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                livre = map(resultSet);
            }
        }catch (SQLException e){
            throw new DAOException(e);
        }finally {
            fermetures(resultSet , preparedStatement ,connection);
        }
        return livre;
    }

    private static Livre map(ResultSet resultSet) throws SQLException {
        Livre livre = new Livre();
        livre.setIdLivre(resultSet.getLong("idLivre"));
        livre.setDesignation(resultSet.getString("designation"));
        livre.setAuteur(resultSet.getString("auteur"));
        livre.setDisponible(resultSet.getBoolean("disponible"));
        livre.setDateEdition(resultSet.getString("dateEdition"));
        livre.setNombrePret(resultSet.getInt("nombrePret"));
        return livre;
    }

}
