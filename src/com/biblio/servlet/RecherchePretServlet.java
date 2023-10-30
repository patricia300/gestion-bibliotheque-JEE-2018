package com.biblio.servlet;

import com.biblio.beans.Pret;
import com.biblio.dao.DAOFactory;
import com.biblio.dao.PretDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RecherchePretServlet")
public class RecherchePretServlet extends HttpServlet {
    public static final String CONF_DAO_FACTORY = "daofactory" ;
    public static final String PARAM = "pret" ;
    public static final String RESULTAT = "prets";
    public static final String VUE = "/WEB-INF/resultatRecherchePret.jsp";

    private PretDAO pretDAO;

    public void init() throws ServletException{
        this.pretDAO = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getPretDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = getValeurParametre(request , PARAM );
        String nom = "%"+param+"%";
        List<Pret> prets = pretDAO.recherche(nom);

        request.setAttribute(RESULTAT , prets);
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
