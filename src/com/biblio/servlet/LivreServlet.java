package com.biblio.servlet;


import com.biblio.beans.Livre;
import com.biblio.dao.DAOFactory;
import com.biblio.dao.LivreDAO;
import com.biblio.forms.LivreForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "LivreServlet")
public class LivreServlet extends HttpServlet {
    public static final String CONF_DAO  = "daofactory";
    public static final String ATT_LIVRE = "livre";
    public static final String ATT_FORM = "form";
    public static final String LIVRES = "livres";
    public static final String VUE = "/WEB-INF/livre.jsp";

    private LivreDAO livreDAO ;

    public void init() throws ServletException{
        this.livreDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO)).getLivreDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LivreForm livreForm = new LivreForm(livreDAO);
        Livre livre = livreForm.creerLivre(request);
        List<Livre> livres = livreDAO.affiched();
        request.setAttribute(ATT_FORM , livreForm);
        request.setAttribute(ATT_LIVRE , livre);
        request.setAttribute(LIVRES, livres);
      this.getServletContext().getRequestDispatcher(VUE).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Livre> livres = livreDAO.affiched();
        request.setAttribute(LIVRES, livres);
        this.getServletContext().getRequestDispatcher("/WEB-INF/livre.jsp").forward(request,response);
    }
}
