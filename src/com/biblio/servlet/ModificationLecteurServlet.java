package com.biblio.servlet;

import com.biblio.beans.Lecteur;
import com.biblio.dao.DAOException;
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

@WebServlet(name = "ModificationLecteurServlet")
public class ModificationLecteurServlet extends HttpServlet {

    public static final String PARAM_ID_LECTEUR = "idLecteur";
    public static final String PARAM_NOM_LECTEUR = "nomLecteur";
    public static final String PARAM_PRENOM_LECTEUR = "prenomLecteur";
    public static final  String ATT_LECTEUR = "lecteur";
    public static final  String ATT_FORM = "form";
    public static final String CONF_DAO_FACTORY = "daofactory" ;
    public static final String LECTEURS = "lecteurs";
    public  static final String VUEFORM = "/WEB-INF/modifierLecteur.jsp";
    public  static final String VUESUCCES = "/WEB-INF/lecteur.jsp";

    private LecteurDAO lecteurDAO;

    public void init() throws ServletException{
        this.lecteurDAO = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getLecteurDAO();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LecteurForm form = new LecteurForm(lecteurDAO);
        Lecteur lecteur = form.updateLecteur(request);
        List<Lecteur> lecteurs = lecteurDAO.affiched();
        if (form.getErreurs().isEmpty()){
            if (lecteur.getIdLecteur() != null && lecteurs!= null){
                request.setAttribute(LECTEURS , lecteurs);
            }
            this.getServletContext().getRequestDispatcher(VUESUCCES).forward(request,response);
        }
        else{
            request.setAttribute(ATT_FORM , form);
            request.setAttribute(ATT_LECTEUR, lecteur);
            this.getServletContext().getRequestDispatcher(VUEFORM).forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idLecteur = getValeurParametre(request,PARAM_ID_LECTEUR);
        Long id = Long.parseLong(idLecteur);
        String nomLecteur = getValeurParametre(request,PARAM_NOM_LECTEUR);
        String prenomLecteur = getValeurParametre(request,PARAM_PRENOM_LECTEUR);

        Lecteur lecteur = new Lecteur();

        lecteur.setIdLecteur(id);
        lecteur.setNomLecteur(nomLecteur);
        lecteur.setPrenomLecteur(prenomLecteur);
        request.setAttribute(ATT_LECTEUR,lecteur);
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
