package com.biblio.dao;

import com.biblio.beans.Livre;

import java.util.List;

public interface LivreDAO {

    void create(Livre livre) throws DAOException;
    Livre search(Long id) throws DAOException;
    List<Livre> affiched() throws DAOException;
    List<Livre> recherche(String nom) throws DAOException;
    void delete(Livre livre) throws DAOException;
    void update(Livre livre) throws DAOException;
    void updateDisponible(Livre livre) throws DAOException;
    void updateNombrePret(Livre livre) throws DAOException;
    void updateNombrePretM_1(Livre livre) throws DAOException;
}
