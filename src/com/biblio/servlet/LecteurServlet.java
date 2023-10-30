package com.biblio.servlet;

import com.biblio.beans.Lecteur;
import com.biblio.dao.DAOFactory;
import com.biblio.dao.LecteurDAO;
import com.biblio.forms.LecteurForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "LecteurServlet")
public class LecteurServlet extends HttpServlet {
    public static final String CONF_DAO_FACTORY = "daofactory" ;
    public static final String ATT_FORM = "form";
    public static final String ATT_LECTEUR = "lecteur";
    public static final String LECTEURS = "lecteurs";
    public static final String VUE = "/WEB-INF/lecteur.jsp";

    private LecteurDAO lecteurDAO;

    public void init() throws ServletException{
        this.lecteurDAO = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getLecteurDAO();
    }

    //doPost

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LecteurForm form = new LecteurForm(lecteurDAO);
        Lecteur lecteur = form.createLecteur(request);
        List<Lecteur> lecteurs = lecteurDAO.affiched();
        request.setAttribute(ATT_LECTEUR , lecteur);
        request.setAttribute(ATT_FORM,form);
        request.setAttribute(LECTEURS , lecteurs);

        this.getServletContext().getRequestDispatcher(VUE).forward(request,response);

    }

    //doGet

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Lecteur> lecteurs = lecteurDAO.affiched();
        request.setAttribute(LECTEURS , lecteurs);
        this.getServletContext().getRequestDispatcher(VUE).forward(request,response);
    }

}
