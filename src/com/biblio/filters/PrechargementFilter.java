package com.biblio.filters;

import com.biblio.beans.Lecteur;
import com.biblio.beans.Livre;
import com.biblio.beans.Pret;
import com.biblio.dao.DAOFactory;
import com.biblio.dao.LecteurDAO;
import com.biblio.dao.LivreDAO;
import com.biblio.dao.PretDAO;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrechargementFilter implements Filter {
    public static final String CONG_DAO_FACTORY = "daofactory";
    public static final String ATT_SESSiON_LECTEUR = "lecteurs";
    public static final String ATT_SESSION_LIVRE = "livres";
    public static final String ATT_SESSION_PRET = "prets";

    private LecteurDAO lecteurDAO;
    private LivreDAO livreDAO;
    private PretDAO pretDAO;

    public void init(FilterConfig filterConfig) throws ServletException{
        this.lecteurDAO = ((DAOFactory) filterConfig.getServletContext().getAttribute(CONG_DAO_FACTORY)).getLecteurDAO();
        this.livreDAO = ((DAOFactory) filterConfig.getServletContext().getAttribute(CONG_DAO_FACTORY)).getLivreDAO();
        this.pretDAO = ((DAOFactory)filterConfig.getServletContext().getAttribute(CONG_DAO_FACTORY)).getPretDAO();
    }
    public void doFilter(ServletRequest req , ServletResponse res , FilterChain chain) throws ServletException , IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        /*
         *Recuperation de la liste des lecteurs existant et
         * enregistrement en session
         */
        if (session.getAttribute(ATT_SESSiON_LECTEUR) == null){
            List<Lecteur> listeLecteurs = lecteurDAO.affiched();
            Map<Long , Lecteur> lecteurMaps = new HashMap<Long, Lecteur>();
            for (Lecteur lecteur : listeLecteurs){
                lecteurMaps.put(lecteur.getIdLecteur(), lecteur);
            }
            session.setAttribute(ATT_SESSiON_LECTEUR , lecteurMaps);
        }

        /*
         *Recuperation de la liste des livres existant et
         * enregistrement en session
         */
        if (session.getAttribute(ATT_SESSION_LIVRE) == null){
            List<Livre> listeLivres = livreDAO.affiched();
            Map<Long , Livre> livreMap = new HashMap<Long, Livre>();
            for (Livre livre : listeLivres){
                livreMap.put(livre.getIdLivre(),livre);
            }
            session.setAttribute(ATT_SESSION_LIVRE , livreMap);
        }

        /*
         *Recuperation de la liste des prets existant et
         * enregistrement en session
         */
        if (session.getAttribute(ATT_SESSION_PRET) == null){
            List<Pret> listePrets = pretDAO.affiched();
            Map<Long , Pret> pretMap = new HashMap<Long, Pret>();
            for (Pret pret : listePrets){
                pretMap.put(pret.getIdPret(),pret);
            }
            session.setAttribute(ATT_SESSION_PRET , pretMap);
        }

        /*
         * Pour terminer ,poursuite de la requete en cours
         */
        chain.doFilter(request , res);
    }
    public void destroy(){

    }
}
