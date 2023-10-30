package com.biblio.config;

import com.biblio.dao.DAOFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class initialisationDAOFactory implements ServletContextListener {
    private static final String ATT_DAO_FACTORY = "daofactory";
    private DAOFactory daoFactory;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        /* recuperation du ServeltCOntext lors du chargement de l'application */
        ServletContext servletContext = event.getServletContext();
        /*Instaciation du DAOFactory*/
        this.daoFactory = DAOFactory.getInstance();
        /*Enregistrement dans un attribut ayant pour porter toutes l'application*/
        servletContext.setAttribute(ATT_DAO_FACTORY,this.daoFactory);
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
