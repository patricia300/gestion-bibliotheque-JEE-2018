package com.biblio.dao;

import com.biblio.beans.Lecteur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.biblio.dao.DAOUtilitaire.*;

public class LecteurDAOImpl implements LecteurDAO {

    private DAOFactory daoFactory;

    private static final String SQL_SELECT_PAR_ID_OU_NOM = "SELECT * FROM lecteurs WHERE idLecteur = ?";
    private static final String SQL_INSERET = "INSERT INTO lecteurs (nomLecteur , prenomLecteur) VALUES (? , ?)";
    private static final String SQL_SELECT_ALL_LECTEURS = "SELECT * FROM lecteurs ORDER BY idLecteur DESC";
    private static final String SQL_DELETE_BY_Id = "DELETE FROM lecteurs WHERE idLecteur = ?";
    private static final String SQL_UPDATE_BY_ID = "UPDATE lecteurs set nomLecteur = ? , prenomLecteur = ? WHERE idLecteur = ?";
    private static final String SQL_SEARCH = "SELECT * FROM lecteurs WHERE nomLecteur LIKE ? OR prenomLecteur LIKE ? OR idLecteur LIKE ? ORDER BY idLecteur DESC";

    /*
     * constucteurs
     */
    public LecteurDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /*
     * appel de toutes les methodes implementés
     */

    @Override
    public void create(Lecteur lecteur) throws IllegalArgumentException, DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeurGenerer = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparer(connection , SQL_INSERET , true , lecteur.getNomLecteur() , lecteur.getPrenomLecteur());
            int statut = preparedStatement.executeUpdate();
            if (statut == 0){
                throw new DAOException("echec de la création d'un lecteur,aucune ligne ajouté à la table");
            }
            valeurGenerer = preparedStatement.getGeneratedKeys();
            if (valeurGenerer.next()){
                lecteur.setIdLecteur(valeurGenerer.getLong(1));
            }
            else {
                throw new DAOException("Echec de la creation d'un lecteur , aucun ID auto-generé retourner ");
            }
        }catch (SQLException e){
            throw new DAOException(e);
        }
        finally {
            fermetures(valeurGenerer , preparedStatement , connection);
        }
    }

    /*
     * DEBUT fonction recherche lecteur par l'id ou le nomLecteur
     */

    @Override
    public Lecteur search(Long idLecteur) throws DAOException {
        return trouver(SQL_SELECT_PAR_ID_OU_NOM , idLecteur ) ;
    }

    /*
     * FIN fonction recherche lecteur par l'id ou le nomLecteur
     */

    /*
     * fonction modification de lecteur
     */

    @Override
    public void update(Lecteur lecteur) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparer(connection , SQL_UPDATE_BY_ID , true , lecteur.getNomLecteur(),lecteur.getPrenomLecteur() , lecteur.getIdLecteur());
            int resultat = preparedStatement.executeUpdate();
            if (resultat == 0){
                throw new DAOException("echec de la modification du lecteur,aucun ligne de la table a été modifié");
            }
        }catch (SQLException e){
            throw new DAOException(e);
        }finally {
            fermetures(preparedStatement , connection);
        }
    }

    /*
     * FIN fonction modification de lecteur
     */

    /*
     * fonction suppression de lecteur
     */

    @Override
    public void delete(Lecteur lecteur) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
               connection = daoFactory.getConnection();
               preparedStatement = initialisationRequetePreparer(connection,SQL_DELETE_BY_Id,true,lecteur.getIdLecteur());
               int statut = preparedStatement.executeUpdate();
               if (statut == 0){
                   throw new DAOException("Echec de la suppresion du lecteur ,aucune ligne supprimée de la table");
               }else {
                   lecteur.setIdLecteur(null);
               }
        }catch (SQLException e){
            throw new DAOException(e);
        }finally {
            fermetures(preparedStatement ,connection );
        }
    }
    /*
     *FIN  fonction suppression de lecteur
     */

    /*
     * fonction afficher tout les lecteurs
     */
    @Override
    public List<Lecteur> affiched() throws DAOException {
        Connection connection  = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List <Lecteur> lecteurs = new ArrayList<Lecteur>();
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_LECTEURS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                lecteurs.add(map(resultSet));
            }
        }catch (SQLException e){
            throw new DAOException(e.getMessage());
        }finally {
            fermetures(resultSet , preparedStatement , connection);
        }
        return lecteurs;
    }

    @Override
    public List<Lecteur> recherche(String nom) throws DAOException {
        Connection connection  = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List <Lecteur> lecteurs = new ArrayList<Lecteur>();
        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparer(connection , SQL_SEARCH , false , nom , nom ,nom);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                lecteurs.add(map(resultSet));
            }
        }catch (SQLException e){
            throw new DAOException(e.getMessage());
        }finally {
            fermetures(resultSet , preparedStatement , connection);
        }
        return lecteurs;
    }

    /*
     * FIN fonction afficher tout les lecteurs
     */

    /*
     * Simple méthode utilitaire permettant de faire la correspondance (le
     * mapping) entre une ligne issue de la table des lecteurs (un
     * ResultSet) et un bean Lecteurs.
     */

    private static Lecteur map(ResultSet resultSet) throws SQLException{
        Lecteur lecteur = new Lecteur();
        lecteur.setIdLecteur(resultSet.getLong("idLecteur"));
        lecteur.setNomLecteur(resultSet.getString("nomLecteur"));
        lecteur.setPrenomLecteur(resultSet.getString("prenomLecteur"));
        return lecteur;
    }
    private Lecteur trouver(String sql ,Object... objects)throws DAOException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Lecteur lecteur = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparer(connection,sql,false,objects);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                lecteur = map(resultSet);
            }
        }catch (SQLException e){
            throw new DAOException(e);
        }finally {
            fermetures(resultSet , preparedStatement ,connection);
        }
        return lecteur;
    }
}

