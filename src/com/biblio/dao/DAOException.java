package com.biblio.dao;

public class DAOException extends RuntimeException{
    /*
     * constructeurs
     */
    public DAOException(String message) {
        super( message );
    }
    public DAOException(String message , Throwable cause) {
        super( message , cause );
    }
    public DAOException(Throwable cause) {
        super( cause );
    }
}
