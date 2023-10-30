package com.biblio.servlet;


import com.biblio.beans.Livre;
import com.biblio.dao.DAOException;
import com.biblio.dao.DAOFactory;
import com.biblio.dao.LivreDAO;
import com.biblio.forms.LivreForm;

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

@WebServlet(name = "UpdateLecteurServlet")
public class ModificationLivreServlet extends HttpServlet {

    public static final String PARAM_ID_LIVRE = "idLivre";
    public static final String CONF_DAO_FACTORY = "daofactory" ;
    public static final String LIVRES = "livres";
    public static final String ATT_FORM = "form";
    public static final String ATT_LIVRE = "livre";
    public  static final String VUEFORM = "/WEB-INF/modifierLivre.jsp";
    public  static final String VUESUCCES = "/WEB-INF/livre.jsp";

    private LivreDAO livreDAO;

    public void init() throws ServletException{
        this.livreDAO = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getLivreDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LivreForm form = new LivreForm(livreDAO);
        Livre livre = form.modifierLivre(request);
        List<Livre> livres = livreDAO.affiched();

        if (form.getErreurs().isEmpty()){
            if (livre.getIdLivre() != null && livres != null){
               request.setAttribute(LIVRES , livres);
            }
            this.getServletContext().getRequestDispatcher(VUESUCCES).forward(request,response);
        }
        else{
            request.setAttribute(ATT_FORM , form);
            request.setAttribute(ATT_LIVRE,livre);
            this.getServletContext().getRequestDispatcher(VUEFORM).forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idLivre = getValeurParametre(request,PARAM_ID_LIVRE);
        Long id = Long.parseLong(idLivre);
        Livre livre = livreDAO.search(id);
        request.setAttribute(ATT_LIVRE,livre);
        this.getServletContext().getRequestDispatcher(VUEFORM).forward(request,response);
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
