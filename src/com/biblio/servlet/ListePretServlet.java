package com.biblio.servlet;

import com.biblio.beans.Lecteur;
import com.biblio.beans.Pret;
import com.biblio.dao.DAOFactory;
import com.biblio.dao.LecteurDAO;
import com.biblio.dao.PretDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ListePretServlet")
public class ListePretServlet extends HttpServlet {
    public static final String VUE = "/WEB-INF/listePret.jsp";
    public static final String ID_LECTEUR = "idLecteur";
    public static final String LECTEUR = "lecteur";
    public static final String PRETS = "prets";
    public static final String CONF_DAO  = "daofactory";
    public static final String NONRENDU = "nbNonRendu";

    private PretDAO pretDAO;
    private LecteurDAO lecteurDAO;

    public void init() throws ServletException{
        this.pretDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO)).getPretDAO();
        this.lecteurDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO)).getLecteurDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = getValeurParametre(request , ID_LECTEUR);
        int nbNonRendu = 0;
        Long idLecteur = Long.parseLong(id);
        Lecteur lecteur = lecteurDAO.search(idLecteur);
        List<Pret> prets = pretDAO.rechercheLecteur(idLecteur);
        for (Pret pret : prets){
            if (pret.getRendu().equals(false)){
                nbNonRendu++;
            }
        }
        request.setAttribute(NONRENDU , nbNonRendu);
        request.setAttribute(PRETS , prets);
        request.setAttribute(LECTEUR,lecteur);
        this.getServletContext().getRequestDispatcher(VUE).forward(request,response);
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

