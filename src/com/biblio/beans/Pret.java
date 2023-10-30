package com.biblio.beans;

import java.io.Serializable;
import java.sql.Date;
import org.joda.time.DateTime;

public class Pret implements Serializable {

   private Long idPret;
   private Lecteur lecteur ;
   private Livre livre;
   private DateTime datePret;
   private DateTime dateRetour;
   private Boolean rendu = false;

   public Boolean getRendu() {
      return rendu;
   }

   public void setRendu(Boolean rendu) {
      this.rendu = rendu;
   }

   public Long getIdPret() {
      return idPret;
   }

   public void setIdPret(Long idPret) {
      this.idPret = idPret;
   }

   public Lecteur getLecteur() {
      return lecteur;
   }

   public void setLecteur(Lecteur lecteur) {
      this.lecteur = lecteur;
   }

   public Livre getLivre() {
      return livre;
   }

   public void setLivre(Livre livre) {
      this.livre = livre;
   }

   public DateTime getDatePret() {
      return datePret;
   }

   public void setDatePret(DateTime datePret) {
      this.datePret = datePret;
   }

   public DateTime getDateRetour() {
      return dateRetour;
   }

   public void setDateRetour(DateTime dateRetour) {
      this.dateRetour = dateRetour;
   }
}
