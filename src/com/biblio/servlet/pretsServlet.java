package com.biblio.servlet;

import com.biblio.beans.Lecteur;
import com.biblio.beans.Livre;
import com.biblio.beans.Pret;
import com.biblio.dao.DAOFactory;
import com.biblio.dao.LecteurDAO;
import com.biblio.dao.LivreDAO;
import com.biblio.dao.PretDAO;
import com.biblio.forms.PretForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "pretsServlet")
public class pretsServlet extends HttpServlet {
    public static final String CONF_DAO  = "daofactory";
    public static final String ATT_FORM = "form";
    public static final String ATT_PRET = "pret";
    public static final String LIVRES = "livres";
    public static final String LECTEURS = "lecteurs";
    public static final String PRETS = "prets";
    public static final String VUE = "/WEB-INF/prets.jsp";

    private PretDAO pretDAO;
    private LecteurDAO lecteurDAO;
    private LivreDAO livreDAO;

    public void init() throws ServletException{
        this.pretDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO)).getPretDAO();
        this.lecteurDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO)).getLecteurDAO();
        this.livreDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO)).getLivreDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PretForm form = new PretForm(pretDAO ,lecteurDAO , livreDAO);
        Pret pret = form.creerPret(request);
        List<Pret> prets = pretDAO.affiched();
        List<Pret> pretsNonRendu = pretDAO.affichedNonRendu();
        List<Lecteur> lecteursNonTrier = lecteurDAO.affiched();
        List<Livre> livres = livreDAO.affiched();

        List<Lecteur> lecteurs = trierLecteur(lecteursNonTrier , pretsNonRendu);

        request.setAttribute(ATT_FORM , form);
        request.setAttribute(ATT_PRET , pret);
        request.setAttribute(PRETS , prets);
        request.setAttribute(LECTEURS , lecteurs);
        request.setAttribute(LIVRES , livres);

        this.getServletContext().getRequestDispatcher(VUE).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Livre> livres = livreDAO.affiched();
        List<Pret> prets = pretDAO.affiched();
        List<Pret> pretsNonRendu = pretDAO.affichedNonRendu();
        List<Lecteur> lecteursNonTrier = lecteurDAO.affiched();
        List<Lecteur> lecteurs = trierLecteur(lecteursNonTrier , pretsNonRendu);
        request.setAttribute(LECTEURS , lecteurs);
        request.setAttribute(LIVRES , livres);
        request.setAttribute(PRETS , prets);
        this.getServletContext().getRequestDispatcher(VUE).forward(request,response);
    }

    private static List<Lecteur> trierLecteur(List<Lecteur> lecteurs , List<Pret> prets){
        List<Lecteur> lecteurList = new ArrayList<Lecteur>();
        for (Lecteur lecteur : lecteurs ){
            int i = 0;
            for (Pret pret : prets){
                if (pret.getLecteur().getIdLecteur().equals(lecteur.getIdLecteur())){
                    i++;
                }
            }
            if (i <= 2 ){
               lecteurList.add(lecteur);
            }
        }
        return  lecteurList;
    }

}
