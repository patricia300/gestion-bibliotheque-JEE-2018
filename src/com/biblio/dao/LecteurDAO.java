package com.biblio.dao;

import com.biblio.beans.Lecteur;

import java.util.List;

public interface LecteurDAO {

    void create(Lecteur lecteur) throws DAOException;
    Lecteur search(Long idLecteur) throws DAOException;
    void update(Lecteur lecteur) throws DAOException;
    void delete(Lecteur lecteur) throws DAOException;
    List<Lecteur> affiched() throws DAOException;
    List<Lecteur> recherche(String nom) throws DAOException;
}
