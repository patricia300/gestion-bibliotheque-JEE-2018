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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ModificationPretServlet")
public class ModificationPretServlet extends HttpServlet {
    public  static final String PARAM_ID_PRET = "idPret";
    public  static final String VUEFORM = "/WEB-INF/modifierPret.jsp";
    public  static final String VUESUCCES = "/WEB-INF/prets.jsp";
    public  static final String ATT_PRET = "pret";
    public  static final String ATT_FORM = "form";
    public  static final String LIVRES = "livres";
    public  static final String PRETS = "prets";
    public  static final String LECTEURS = "lecteurs";
    public static final String CONF_DAO_FACTORY = "daofactory" ;

    private LecteurDAO lecteurDAO;
    private LivreDAO livreDAO;
    private PretDAO pretDAO;

    public void init() throws ServletException{
        this.lecteurDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getLecteurDAO();
        this.livreDAO = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getLivreDAO();
        this.pretDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getPretDAO();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PretForm form = new PretForm(pretDAO , lecteurDAO , livreDAO);
        Pret pret = form.modifierPret(request);
        List<Pret> prets = pretDAO.affiched();
        List<Pret> pretsNonRendu = pretDAO.affichedNonRendu();
        List<Lecteur> lecteursNonTrier = lecteurDAO.affiched();
        List<Livre> livres = livreDAO.affiched();
        List<Lecteur> lecteurs = trierLecteur(lecteursNonTrier , pretsNonRendu);

        if (form.getErreurs().isEmpty()){
            request.setAttribute(LECTEURS , lecteurs);
            request.setAttribute(LIVRES , livres);
            request.setAttribute(PRETS , prets);
            this.getServletContext().getRequestDispatcher(VUESUCCES).forward(request,response);
        }
        else{
            request.setAttribute(LECTEURS , lecteurs);
            request.setAttribute(LIVRES , livres);
            request.setAttribute(ATT_FORM , form);
            request.setAttribute(ATT_PRET,pret);
            this.getServletContext().getRequestDispatcher(VUEFORM).forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = getValeurParametre(request , PARAM_ID_PRET);
        Long idPret = Long.parseLong(id);
        Pret pret = pretDAO.search(idPret);

        List<Livre> livres = livreDAO.affiched();
        List<Pret> pretsNonRendu = pretDAO.affichedNonRendu();
        List<Lecteur> lecteursNonTrier = lecteurDAO.affiched();
        List<Lecteur> lecteurs = trierLecteur(lecteursNonTrier , pretsNonRendu);

        request.setAttribute(LECTEURS , lecteurs);
        request.setAttribute(LIVRES , livres);
        request.setAttribute(ATT_PRET , pret);

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
