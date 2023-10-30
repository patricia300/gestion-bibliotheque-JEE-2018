package com.biblio.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Lecteur implements Serializable {
    @Id
    @GeneratedValue
    private Long idLecteur;

    @Column(name = "nomLecteur")
    private String nomLecteur;

    @Column(name = "prenomLecteur")
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

    public String getPrenomLecteur() {
        return prenomLecteur;
    }

    public void setPrenomLecteur(String prenomLecteur) {
        this.prenomLecteur = prenomLecteur;
    }


}
