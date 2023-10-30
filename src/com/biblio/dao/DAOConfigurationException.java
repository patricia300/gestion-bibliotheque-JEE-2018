package com.biblio.dao;

public class DAOConfigurationException extends RuntimeException{
    /*
     * constructeurs
     */
    public DAOConfigurationException(String message) {
        super(message);
    }

    public DAOConfigurationException(String message, Throwable cause) {
        super( message, cause );
    }

    public DAOConfigurationException(Throwable cause) {
        super(cause);
    }
}
