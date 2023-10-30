package com.biblio.servlet;

import com.biblio.beans.Pret;
import com.biblio.dao.DAOFactory;
import com.biblio.dao.PretDAO;
import org.joda.time.DateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ListeEnRetardServlet")
public class ListeEnRetardServlet extends HttpServlet {
    public static final String VUE = "/WEB-INF/listeRetard.jsp";
    public static final String PRETS = "prets";
    public static final String CONF_DAO  = "daofactory";

    private PretDAO pretDAO;

    public void init() throws ServletException{
        this.pretDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO)).getPretDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Pret> prets = pretDAO.affichedNonRendu();
        List<Pret> pretEnRetard = new ArrayList<Pret>();
        DateTime now = new DateTime();
        for (Pret pret : prets){
            if (pret.getDateRetour().isBeforeNow()){
                pretEnRetard.add(pret);
            }
        }
        request.setAttribute(PRETS , pretEnRetard);
        this.getServletContext().getRequestDispatcher(VUE).forward(request,response);
    }
}
