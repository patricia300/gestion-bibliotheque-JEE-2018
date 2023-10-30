package com.biblio.servlet;

import com.biblio.beans.Lecteur;
import com.biblio.dao.DAOFactory;
import com.biblio.dao.LecteurDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "RechercheLecteurServlet")
public class RechercheLecteurServlet extends HttpServlet {
    public static final String CONF_DAO_FACTORY = "daofactory" ;
    public static final String RESULTAT = "lecteurs";
    public static final String PARAM ="lecteur";
    public static final String VUE = "/WEB-INF/resultatRechercheLecteur.jsp";

    private LecteurDAO lecteurDAO;

    public void init() throws ServletException{
        this.lecteurDAO = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getLecteurDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = getValeurParametre(request , PARAM );
        String nom = "%"+param+"%";
        List<Lecteur> lecteurs = lecteurDAO.recherche(nom);

        request.setAttribute(RESULTAT , lecteurs);
        this.getServletContext().getRequestDispatcher(VUE).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private static String getValeurParametre(HttpServletRequest req , String nomChamp){
        String champ = req.getParameter(nomChamp);
        if (champ == null || champ.trim().length() ==0){
            return null;
        }else {
            return champ;
        }
    }
}
