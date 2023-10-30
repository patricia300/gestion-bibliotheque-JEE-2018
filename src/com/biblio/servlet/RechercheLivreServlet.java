package com.biblio.servlet;

import com.biblio.beans.Livre;
import com.biblio.dao.DAOFactory;
import com.biblio.dao.LivreDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "RechercheLivreServlet")
public class RechercheLivreServlet extends HttpServlet {

    public static final String CONF_DAO_FACTORY = "daofactory" ;
    public static final String PARAM = "livre" ;
    public static final String RESULTAT = "livres";
    public static final String VUE = "/WEB-INF/resultatRechercheLivre.jsp";

    private LivreDAO livreDAO;

    public void init() throws ServletException{
        this.livreDAO = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getLivreDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = getValeurParametre(request , PARAM );
        String nom = "%"+param+"%";
        List<Livre> livres = livreDAO.recherche(nom);

        request.setAttribute(RESULTAT , livres);
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
