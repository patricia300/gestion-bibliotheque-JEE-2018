package com.biblio.dao;

import com.biblio.beans.Pret;
import org.joda.time.DateTime;

import java.util.List;

public interface PretDAO {
    void create(Pret pret) throws DAOException;
    Pret search(Long id) throws DAOException;
    List<Pret> affiched() throws DAOException;
    List<Pret> recherche(String pret) throws DAOException;
    List<Pret> rechercheLecteur(Long id) throws DAOException;
    void delete(Pret pret) throws DAOException;
    void update(Pret pret) throws DAOException;
    List<Pret> affichedNonRendu() throws DAOException;
    List<Pret> recherche2Date(DateTime dateDebut , DateTime dateFin , String lecteur) throws DAOException;
    List<Pret> recherche2Date(DateTime dateDebut , DateTime dateFin) throws DAOException;

}
