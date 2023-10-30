package com.biblio.servlet;

import com.biblio.beans.Lecteur;
import com.biblio.beans.Livre;
import com.biblio.beans.Pret;
import com.biblio.dao.DAOFactory;
import com.biblio.dao.LecteurDAO;
import com.biblio.dao.LivreDAO;
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

@WebServlet(name = "RendreEnRetardServlet")
public class RendreEnRetardServlet extends HttpServlet {
    public static final String IDPRET = "idPret";
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String VUEPRET = "/WEB-INF/prets.jsp";
    public static final String PRET = "pret";
    public static final String LIVRES = "livres";
    public static final String LECTEURS = "lecteurs";
    public static final String PRETS = "prets";
    public static final String VUE = "/WEB-INF/prets.jsp";

    PretDAO pretDAO ;
    LivreDAO livreDAO;
    LecteurDAO lecteurDAO;

    public void init(){
        this.pretDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getPretDAO();
        this.livreDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getLivreDAO();
        this.lecteurDAO = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getLecteurDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = getValeurParametre(request ,  IDPRET);
        DateTime dateRendu = new DateTime();
        Pret pret;

        if (id != null){
            Long idPret = Long.parseLong(id);
            pret = pretDAO.search(idPret);
            request.setAttribute(PRET , pret);
                try {
                    if (pret != null) {
                        pret.setRendu(true);
                        pret.setDateRetour(dateRendu);
                        Livre livre = livreDAO.search(pret.getLivre().getIdLivre());
                        if (livre != null){
                            livre.setDisponible(true);
                        }
                        pretDAO.update(pret);
                        livreDAO.updateDisponible(livre);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
        }
        List<Livre> livres = livreDAO.affiched();
        List<Pret> prets = pretDAO.affiched();
        List<Pret> pretsNonRendu = pretDAO.affichedNonRendu();
        List<Lecteur> lecteursNonTrier = lecteurDAO.affiched();
        List<Lecteur> lecteurs = trierLecteur(lecteursNonTrier , pretsNonRendu);
        request.setAttribute(LECTEURS , lecteurs);
        request.setAttribute(LIVRES , livres);
        request.setAttribute(PRETS , prets);
        this.getServletContext().getRequestDispatcher(VUEPRET).forward(request,response);
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
