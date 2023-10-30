package com.biblio.servlet;

import com.biblio.beans.Lecteur;
import com.biblio.beans.Pret;
import com.biblio.dao.DAOFactory;
import com.biblio.dao.LecteurDAO;
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

@WebServlet(name = "Recherche2dateServlet")
public class Recherche2dateServlet extends HttpServlet {
    public static final String VUE ="/WEB-INF/recherche2date.jsp";
    public static final String DATEDEBUT = "dateDebut";
    public static final String DATEFIN = "dateFin";
    public static final String NOM = "nom";
    public static final String CONF_DAO  = "daofactory";
    public static final String PRETS = "prets";
    public static final String RESULTAT = "resultat";

    private static String resultat = "";


    PretDAO pretDAO ;
    LecteurDAO lecteurDAO;


    public void init() throws ServletException{
        this.pretDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO)).getPretDAO();
        this.lecteurDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO)).getLecteurDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom1 = getValeurParametre(request , NOM);
        String dateD = getValeurParametre(request , DATEDEBUT);
        String dateF = getValeurParametre(request , DATEFIN);

        DateTime dateDebut = DateTime.parse(dateD);
        DateTime dateFin = DateTime.parse(dateF);
        String nom = "%"+nom1+"%";
        List<Pret> prets = null ;

        if (dateDebut.isBefore(dateFin)){
            if (nom.isEmpty()){
                prets = pretDAO.recherche2Date(dateDebut , dateFin);
            }
            else {
                prets = pretDAO.recherche2Date(dateDebut ,dateFin , nom);
            }
        }
        else{
            resultat = "la date debut doit etre superieur au date fin sur le recherche entre deux date";
        }

        request.setAttribute(PRETS , prets);
        request.setAttribute(RESULTAT , resultat);
        this.getServletContext().getRequestDispatcher(VUE).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
