package com.biblio.forms;

import com.biblio.beans.Livre;
import com.biblio.dao.DAOException;
import com.biblio.dao.LivreDAO;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class LivreForm {

    private static final String CHAMP_ID_LIVRE = "idLivre";
    private static final String CHAMP_DESIGN = "designation";
    private static final String CHAMP_AUTEUR = "auteur";
    private static final String CHAMP_DATE_ED = "dateEdition";
    private static final String CHAMP_DISPONIBLE = "disponible";

    private String resultat ;
    private Map<String , String> erreurs = new HashMap<String, String>();
    private LivreDAO livreDAO;

    public LivreForm(LivreDAO livreDAO) {
        this.livreDAO = livreDAO;
    }

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public void setErreurs(String champ , String message) {
        erreurs.put(champ , message);
    }


    public Livre creerLivre(HttpServletRequest request){
        String designation = getValeurChamp(request , CHAMP_DESIGN);
        String auteur = getValeurChamp(request , CHAMP_AUTEUR);
        String dateEdition =  getValeurChamp(request,CHAMP_DATE_ED);
        Livre livre = new Livre();

        try {
            traiterDesignation(designation , livre);
            traiterAuteur(auteur ,livre);
            livre.setDisponible(true);
            livre.setDateEdition(dateEdition);

            if (erreurs.isEmpty()){
                livreDAO.create(livre);
                resultat  = "Succes de la creation du livre";
            }
            else
            {
                resultat = "echec de la creation du livre";
            }
        }catch (DAOException e){
            setErreurs("imprevu" , "erreur imprevu lors de la creation");
            resultat = "echec de la creation du livre : une erreur imprevue lors de la creation du livre ,merci de réessayer dans quelques instant";
            e.printStackTrace();
        }
        return livre;
    }
    public Livre modifierLivre(HttpServletRequest request){
        String id  = getValeurChamp(request , CHAMP_ID_LIVRE);
        String designation = getValeurChamp(request , CHAMP_DESIGN);
        String auteur = getValeurChamp(request , CHAMP_AUTEUR);
        String dateEdition =  getValeurChamp(request,CHAMP_DATE_ED);
        String disponible = getValeurChamp(request , CHAMP_DISPONIBLE);
        Long idLivre = Long.parseLong(id);
        Livre livre = new Livre();
        try {
            livre.setIdLivre(idLivre);
            traiterDesignation(designation , livre);
            traiterAuteur(auteur ,livre);
            traiterDisponible(disponible , livre);
            livre.setDateEdition(dateEdition);
            if (erreurs.isEmpty()){
                livreDAO.update(livre);
                resultat  = "Succes de la modification du livre";
            }
            else
            {
                resultat = "echec de la modification du livre";
            }
        }catch (DAOException e){
            setErreurs(CHAMP_DESIGN , e.getMessage());
            setErreurs(CHAMP_AUTEUR , e.getMessage());
            setErreurs("imprevu" , "erreur imprevu lors de la modification");
            resultat = "echec de la modification du livre : une erreur imprevue lors de la modification du livre ,merci de réessayer dans quelques instant";
            e.printStackTrace();
        }
        return livre;
    }

    private void validationDesign(String design ) throws FormValidationException{
        if (design == null && design.length()< 5 ){
                throw new FormValidationException("Le designation doit contenir au moins 5 caracteres");
        }
    }

    private void validationAuteur(String auteur ) throws FormValidationException{
        if (auteur == null && auteur.length()< 5 ){
            throw new FormValidationException("Le nom de l'auteur doit contenir au moins 5 caracteres");
        }
    }

    private void validationDisponible(String disponible) throws FormValidationException{
        if (!disponible.matches("oui|non")){
            throw new FormValidationException("la disponibilité doit être oui ou non");
        }
    }

   private void traiterDisponible(String dispo , Livre livre){
        try {
            validationDisponible(dispo);
            if (dispo.equals("oui")){
                livre.setDisponible(true);
            }
            if (dispo.equals("non")){
                livre.setDisponible(false);
            }
        }
        catch (FormValidationException e){
            setErreurs(CHAMP_DISPONIBLE , e.getMessage());
        }

        //livre.setDisponible(dispo);
    }

    private void traiterDesignation(String design , Livre livre){
        try {
            validationDesign(design);
        }catch (FormValidationException e){
            setErreurs(CHAMP_DESIGN , e.getMessage());
        }
        livre.setDesignation(design);
    }

    private void traiterAuteur(String auteur , Livre livre){
        try {
            validationAuteur(auteur);
        }catch (FormValidationException e){
            setErreurs(CHAMP_AUTEUR , e.getMessage());
        }
        livre.setAuteur(auteur);
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



