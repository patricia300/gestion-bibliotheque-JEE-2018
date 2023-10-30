package com.biblio.beans;

import java.io.Serializable;

public class Livre implements Serializable {
    private Long idLivre;
    private String designation;
    private boolean disponible = true;
    private String Auteur;
    private int nombrePret =0 ;

    public int getNombrePret() {
        return nombrePret;
    }

    public void setNombrePret(int nombrePret) {
        this.nombrePret = nombrePret;
    }

    public Long getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(Long idLivre) {
        this.idLivre = idLivre;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getAuteur() {
        return Auteur;
    }

    public void setAuteur(String auteur) {
        Auteur = auteur;
    }

    public String getDateEdition() {
        return dateEdition;
    }

    public void setDateEdition(String dateEdition) {
        this.dateEdition = dateEdition;
    }

    private String dateEdition;
}
