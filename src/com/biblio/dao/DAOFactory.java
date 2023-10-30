package com.biblio.dao;



import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {


    private static final String FICHIER_PROPERTIES = "/com/biblio/dao/dao.proprieties";
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_USER = "utilisateur";
    private static final String PROPERTY_MOT_DE_PASSE = "motDePasse";

    private String url;
    private String username;
    private String password;

     DAOFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /*
     * cherger le driver jdbc et retourner une instance factory
     * recuperer les informations à la connexion au base de donnnée
     */

    public static DAOFactory getInstance() throws DAOConfigurationException {

        Properties properties = new Properties();
        String driver;
        String url;
        String utilisateur;
        String mot_de_passe;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProprieties = classLoader.getResourceAsStream(FICHIER_PROPERTIES);

        if (fichierProprieties == null){
            throw new DAOConfigurationException("le fichier proprieties" + FICHIER_PROPERTIES + "est introuvable");
        }
        try {

            properties.load(fichierProprieties);
            url  =  properties.getProperty(PROPERTY_URL);
            driver = properties.getProperty(PROPERTY_DRIVER);
            utilisateur = properties.getProperty(PROPERTY_USER);
            mot_de_passe = properties.getProperty(PROPERTY_MOT_DE_PASSE);


        } catch (IOException e){
            throw new DAOConfigurationException("impossible de chargé le fichier propierties" + FICHIER_PROPERTIES,e);
        }

        try {
            Class.forName(driver);
        }catch (ClassNotFoundException e){
            throw new DAOConfigurationException("le driver est introuvable dans le Classpath " ,e);
        }

        DAOFactory daoFactory = new DAOFactory(url,utilisateur,mot_de_passe);
        return  daoFactory;
    }


    /*
     * methode chargé de fournir une connexion à la base de données
     */

        /*package*/ Connection getConnection () throws SQLException{
        return DriverManager.getConnection(url,username,password);
    }

    /*
     * methode de recuperation de toutes les implementations DAO
     */

    public LecteurDAO getLecteurDAO(){
        return new LecteurDAOImpl( this );
    }
    public LivreDAO getLivreDAO(){
        return new LivreDAOImpl(this);
    }
    public PretDAO getPretDAO(){
        return new PretDAOImpl(this);
    }
}
