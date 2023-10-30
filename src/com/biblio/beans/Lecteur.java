package com.biblio.beans;

import java.io.Serializable;

public class Lecteur implements Serializable {
    private Long idLecteur;
    private String nomLecteur;

    public String getPrenomLecteur() {
        return prenomLecteur;
    }

    public void setPrenomLecteur(String prenomLecteur) {
        this.prenomLecteur = prenomLecteur;
    }

    private String prenomLecteur;

    public Long getIdLecteur() {
        return idLecteur;
    }

    public void setIdLecteur(Long idLecteur) {
        this.idLecteur = idLecteur;
    }

    public String getNomLecteur() {
        return nomLecteur;
    }

    public void setNomLecteur(String nomLecteur) {
        this.nomLecteur = nomLecteur;
    }


}
