package com.biblio.forms;

import com.biblio.beans.Lecteur;
import com.biblio.beans.Livre;
import com.biblio.beans.Pret;
import com.biblio.dao.DAOException;
import com.biblio.dao.LecteurDAO;
import com.biblio.dao.LivreDAO;
import com.biblio.dao.PretDAO;
import org.joda.time.DateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PretForm {
    private static final String CHAMP_ID_LECTEUR = "idLecteur";
    private static final String CHAMP_ID_LIVRE = "idLivre";
    public  static final String PARAM_ID_PRET = "idPret";
    public  static final String PARAM_ID_ANCIEN_LIVRE = "ancienIdLivre";


    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();
    private PretDAO pretDAO;
    private LecteurDAO lecteurDAO;
    private LivreDAO livreDAO;

    public PretForm(PretDAO pretDAO, LecteurDAO lecteurDAO, LivreDAO livreDAO) {
        this.pretDAO = pretDAO;
        this.lecteurDAO = lecteurDAO;
        this.livreDAO = livreDAO;
    }

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public void setErreurs(String champ, String message) {
        erreurs.put(champ , message);
    }

    public Pret creerPret(HttpServletRequest request){
        String idLect = getValeurChamp(request , CHAMP_ID_LECTEUR);
        Long idLecteur = Long.parseLong(idLect);

        String idLiv = getValeurChamp(request , CHAMP_ID_LIVRE);
        Long idLivre = Long.parseLong(idLiv);

        Lecteur lecteur = lecteurDAO.search(idLecteur);
        Livre livre = livreDAO.search(idLivre);

        DateTime dateNow = new DateTime();
        DateTime datePlus7 = dateNow.plusDays(7);

        Pret pret = new Pret();
        try {
            traiterLecteur(lecteur , pret);
            traiterLivre(livre , pret);
            pret.setDatePret(dateNow);
            pret.setDateRetour(datePlus7);
            pret.setRendu(false);

            if (erreurs.isEmpty()){
                pretDAO.create(pret);
                livreDAO.updateDisponible(livre);
                livreDAO.updateNombrePret(livre);
                resultat  = "Succes de la creation du Pret";
            }
            else
            {
                resultat = "Echec de la creation du Pret";
            }
        }catch (DAOException e){
            setErreurs("imprevu" , "erreur imprevu lors de la creation");
            resultat = "echec de la creation du pret : une erreur imprevue lors de la creation du pret ,merci de réessayer dans quelques instant";
            e.printStackTrace();
        }
        return pret;
    }


    public Pret modifierPret(HttpServletRequest request){
        //recuperation lecteur
        String lecteur = getValeurChamp(request , CHAMP_ID_LECTEUR );
        Long idLecteur = Long.parseLong(lecteur);
        Lecteur lecteur1 = lecteurDAO.search(idLecteur);
        //recuperation et modification nouveau livre
        String idLiv = getValeurChamp(request , CHAMP_ID_LIVRE);
        Long idLivre = Long.parseLong(idLiv);
        Livre livre = livreDAO.search(idLivre);
        livre.setDisponible(false);
        livreDAO.updateNombrePret(livre);
        livreDAO.update(livre);
        //recuperation et modification ancien livre
        String ancienId = getValeurChamp(request , PARAM_ID_ANCIEN_LIVRE);
        Long ancienIdLivre = Long.parseLong(ancienId);
        Livre ancienLivre = livreDAO.search(ancienIdLivre);
        ancienLivre.setDisponible(true);
        livreDAO.updateNombrePretM_1(ancienLivre);
        livreDAO.update(ancienLivre);

        String idP = getValeurChamp(request , PARAM_ID_PRET);
        Long idPret = Long.parseLong(idP);

        Pret pret = pretDAO.search(idPret);
        pret.setLecteur(lecteur1);
        pret.setLivre(livre);

        pretDAO.update(pret);
        return pret;
    }

    private void traiterLecteur(Lecteur lecteur , Pret pret){
        if(lecteur == null){
            setErreurs(CHAMP_ID_LECTEUR , "Lecteur inconnu , il est null alors");
        }
        pret.setLecteur(lecteur);
    }
    private void traiterLivre(Livre livre , Pret pret){
        if(livre == null){
            setErreurs(CHAMP_ID_LIVRE , "Livre inconnu , il est null alors");
        }
        livre.setDisponible(false);
        pret.setLivre(livre);
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */

    private static String getValeurChamp(HttpServletRequest request, String nomChamp){
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0){
            return null;
        }else {
            return valeur.trim();
        }
    }
}