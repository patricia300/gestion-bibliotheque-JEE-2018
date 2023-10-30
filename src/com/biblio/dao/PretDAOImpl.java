package com.biblio.dao;

import com.biblio.beans.Lecteur;
import com.biblio.beans.Pret;
import org.joda.time.DateTime;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.biblio.dao.DAOUtilitaire.*;

public class PretDAOImpl implements PretDAO {
    private static final String SQL_SELECT_ALL = "SELECT * FROM prets ORDER BY idPret DESC ";
    private static final String SQL_SELECT_ALL_NON_RENDU = "SELECT * FROM prets WHERE rendu = false ORDER BY idPret DESC ";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM prets WHERE idPret = ?";
    private static final String SQL_INSERT = "INSERT INTO prets (idLecteur , IdLivre ,datePret ,dateRetour , rendu) VALUES (? , ? , ? , ? , ?)";
    private static final String SQL_UPDATE = "UPDATE prets set idLecteur = ? , idLivre = ? , dateRetour = ? , rendu = ? WHERE idPret = ?";
    private static final String SQL_DELETE_BY_Id = "DELETE FROM prets WHERE idPret = ? AND rendu = true";
    private static final String SQL_UPDATE_RENDU = "UPDATE prets set rendu = ? WHERE idPret = ?";
    private static final String SQL_RECHERECHE_ENTRE_2_DATE = "SELECT prets.idPret , prets.idLecteur , prets.idLivre , prets.datePret , prets.dateRetour , prets.rendu FROM prets , lecteurs WHERE (lecteurs.idLecteur = prets.idLecteur)  AND (datePret BETWEEN ? AND ?)  AND ((lecteurs.nomLecteur LIKE ?) OR (lecteurs.prenomLecteur LIKE ?)) ORDER BY idPret DESC";
    private static final String SQL_RECHERECHE_ENTRE_2_DATE_SANS_NOM = "SELECT * FROM prets  WHERE datePret BETWEEN ? AND ?";
    private static final String SQL_RECHERCHE = "SELECT * FROM prets WHERE idPret LIKE ? OR idLecteur LIKE ?  OR IdLivre LIKE ? OR datePret LIKE ? OR dateRetour LIKE ? ORDER BY idPret DESC";
    private static final String SQL_SELECT_LECTEUR = "SELECT * FROM prets WHERE idLecteur = ?";

    private DAOFactory daoFactory;

    public PretDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(Pret pret) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeurGenerer = null;
        try {
            connection  =daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparer(connection , SQL_INSERT ,true ,
                    pret.getLecteur().getIdLecteur(),
                    pret.getLivre().getIdLivre(),
                    new Timestamp(pret.getDatePret().getMillis()),
                    new Timestamp(pret.getDateRetour().getMillis()),
                    pret.getRendu());
            int statut = preparedStatement.executeUpdate();
            if (statut == 0){
                throw new DAOException("Echec de la creation du pret, aucune ligne ajouté à la table");
            }
            valeurGenerer = preparedStatement.getGeneratedKeys();
            if (valeurGenerer.next()){
                pret.setIdPret(valeurGenerer.getLong(1));
            }else {
                throw new DAOException("Echec de la creation en base , acune ID auto-generé retourné");
            }
        }
        catch (SQLException e){
            throw new DAOException(e);
        }
        finally {
            fermetures(valeurGenerer , preparedStatement , connection);
        }
    }

    @Override
    public Pret search(Long id) throws DAOException {
        return trouver(SQL_SELECT_BY_ID , id);
    }

    @Override
    public List<Pret> affiched() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Pret> prets = new ArrayList<Pret>();
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                prets.add(map(resultSet));
            }
        }
        catch (SQLException e){
            throw new DAOException(e);
        }
        finally {
            fermetures(resultSet, preparedStatement , connection);
        }
        return prets;
    }

    @Override
    public List<Pret> recherche2Date(DateTime dateDebut ,DateTime dateFin, String lecteur) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Pret> prets = new ArrayList<Pret>();
        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparer(connection , SQL_RECHERECHE_ENTRE_2_DATE , false ,
                    new Timestamp(dateDebut.getMillis()) ,
                    new Timestamp(dateFin.getMillis()),
                    lecteur , lecteur);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                prets.add(map(resultSet));
            }
        }
        catch (SQLException e){
            throw new DAOException(e);
        }
        finally {
            fermetures(resultSet, preparedStatement , connection);
        }
        return prets;
    }

    @Override
    public List<Pret> recherche2Date(DateTime dateDebut ,DateTime dateFin) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Pret> prets = new ArrayList<Pret>();
        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparer(connection , SQL_RECHERECHE_ENTRE_2_DATE_SANS_NOM , false ,
                    new Timestamp(dateDebut.getMillis()) ,
                    new Timestamp(dateFin.getMillis()));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                prets.add(map(resultSet));
            }
        }
        catch (SQLException e){
            throw new DAOException(e);
        }
        finally {
            fermetures(resultSet, preparedStatement , connection);
        }
        return prets;
    }
    @Override
    public List<Pret> affichedNonRendu() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Pret> prets = new ArrayList<Pret>();
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_NON_RENDU);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                prets.add(map(resultSet));
            }
        }
        catch (SQLException e){
            throw new DAOException(e);
        }
        finally {
            fermetures(resultSet, preparedStatement , connection);
        }
        return prets;
    }

    @Override
    public List<Pret> recherche(String pret) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Pret> prets = new ArrayList<Pret>();
        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparer(connection , SQL_RECHERCHE , false , pret , pret , pret , pret , pret);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                prets.add(map(resultSet));
            }
        }
        catch (SQLException e){
            throw new DAOException(e);
        }
        finally {
            fermetures(resultSet, preparedStatement , connection);
        }
        return prets;
    }

    @Override
    public List<Pret> rechercheLecteur(Long id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Pret> prets = new ArrayList<Pret>();
        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparer(connection , SQL_SELECT_LECTEUR , false , id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                prets.add(map(resultSet));
            }
        }
        catch (SQLException e){
            throw new DAOException(e);
        }
        finally {
            fermetures(resultSet, preparedStatement , connection);
        }
        return prets;
    }

    @Override
    public void delete(Pret pret) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparer(connection , SQL_DELETE_BY_Id , true , pret.getIdPret());
            int statut = preparedStatement.executeUpdate();
            if (statut == 0){
                throw new DAOException("Echec de la suppression du pret , aucune ligne de la table supprimé");
            }
            else {
                pret.setIdPret(null);
            }
        }
        catch (SQLException e){
            throw new DAOException(e);
        }
        finally {
            fermetures(preparedStatement , connection);
        }
    }

    @Override
    public void update(Pret pret) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement  = initialisationRequetePreparer(connection , SQL_UPDATE , false ,
                    pret.getLecteur().getIdLecteur() ,
                    pret.getLivre().getIdLivre()  ,
                    new Timestamp(pret.getDateRetour().getMillis()),
                    pret.getRendu() ,
                    pret.getIdPret());
            int statut = preparedStatement.executeUpdate();
            if (statut == 0){
                throw new DAOException("echec de la modification du pret,aucun ligne de la table a été modifié");
            }
        }catch (SQLException e){
            throw new DAOException(e);
        }
        finally {
            fermetures(preparedStatement ,connection);
        }

    }

    /*
     * Simple méthode utilitaire permettant de faire la correspondance (le
     * mapping) entre une ligne issue de la table des commandes (un ResultSet)
     * et un bean Commande.
     */
    private Pret map( ResultSet resultSet ) throws SQLException {
        Pret pret = new Pret();

        pret.setIdPret(resultSet.getLong("idPret"));
        LecteurDAO lecteurDAO = daoFactory.getLecteurDAO();

        pret.setLecteur( lecteurDAO.search( resultSet.getLong( "idLecteur" ) ) );
        LivreDAO livreDAO = daoFactory.getLivreDAO();

        pret.setLivre(livreDAO.search(resultSet.getLong("idLivre")));
        pret.setDatePret(new DateTime(resultSet.getTimestamp("datePret")));
        pret.setDateRetour(new DateTime(resultSet.getTimestamp("dateRetour")));
        pret.setRendu(resultSet.getBoolean("rendu"));
        return pret;
    }

    private Pret trouver(String sql ,Object... objects)throws DAOException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Pret pret = null;
        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparer(connection,sql,false,objects);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                pret = map(resultSet);
            }
        }catch (SQLException e){
            throw new DAOException(e);
        }finally {
            fermetures(resultSet , preparedStatement ,connection);
        }
        return pret;
    }
}
