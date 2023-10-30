package com.biblio.forms;

import com.biblio.beans.Lecteur;
import com.biblio.dao.LecteurDAO;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public final class LecteurForm {
    private static final String CHAMP_ID_LECTEUR = "idLecteur";
    private static final String CHAMP_NOM_LECTEUR = "nomLecteur";
    private static final String CHAMP_PRENOM_LECTEUR = "prenomLecteur";

    private String resultat;
    private Map<String,String> erreurs = new HashMap<String,String>();
    private LecteurDAO lecteurDAO;

    public LecteurForm(LecteurDAO lecteurDAO){
        this.lecteurDAO = lecteurDAO;
    }
    public String getResultat() {
        return resultat;
    }
    public Map<String, String> getErreurs() {
        return erreurs;
    }


    public Lecteur createLecteur(HttpServletRequest request){
        String nom = getValeurChamp(request , CHAMP_NOM_LECTEUR);
        String prenom = getValeurChamp(request , CHAMP_PRENOM_LECTEUR);
        Lecteur lecteur = new Lecteur();
        try {
            traiterNomPrenom(nom,prenom, lecteur);
            if (erreurs.isEmpty()){
                lecteurDAO.create(lecteur);
                resultat = "succès de l'ajout du lecteur";
            }
        }catch (Exception e){
            setErreurs(CHAMP_NOM_LECTEUR , e.getMessage());
            setErreurs(CHAMP_PRENOM_LECTEUR , e.getMessage());
        }
        return lecteur;
    }
    public Lecteur updateLecteur(HttpServletRequest request){
        String id = getValeurChamp(request , CHAMP_ID_LECTEUR);
        Long idLecteur = Long.parseLong(id);
        String nom = getValeurChamp(request , CHAMP_NOM_LECTEUR);
        String prenom = getValeurChamp(request , CHAMP_PRENOM_LECTEUR);
        Lecteur lecteur = new Lecteur();
        try {
            lecteur.setIdLecteur(idLecteur);
            traiterNomPrenom(nom,prenom, lecteur);
            if (erreurs.isEmpty()){
                lecteurDAO.update(lecteur);
                resultat = "succès du modification du lecteur";
            }
        }catch (Exception e){
            setErreurs(CHAMP_NOM_LECTEUR , e.getMessage());
            setErreurs(CHAMP_PRENOM_LECTEUR , e.getMessage());
        }
        return lecteur;
    }

    /* Validation du nom */
    private void validationNom( String nom ) throws FormValidationException {
        if ( nom != null && nom.length() < 3 ) {
            throw new FormValidationException( "Le nom du lecteur doit contenir au moins 3 caractères." );
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */

    private void setErreurs (String champ , String message){
        erreurs.put(champ , message) ;
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

    private void traiterNomPrenom(String nom , String prenom , Lecteur lecteur){
        try {
            validationNom(nom);
            validationNom(prenom);
        }catch (FormValidationException e){
            setErreurs(CHAMP_NOM_LECTEUR ,  e.getMessage());
        }
        lecteur.setNomLecteur(nom);
        lecteur.setPrenomLecteur(prenom);
    }
}
