package com.biblio.dao;

import java.sql.*;
import java.util.Objects;

public class DAOUtilitaire {
    /*
     *constructeurs privée
     */
    private DAOUtilitaire() {
    }

    /*
     * Initialise la requête préparée basée sur la connexion passée en argument,
     * avec la requête SQL et les objets donnés.
     */

    public static PreparedStatement initialisationRequetePreparer(Connection connection , String sql , boolean returnGeneratedKeys, Object... objects) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(sql,returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);

        for (int i = 0 ; i < objects.length ; i++){
            preparedStatement.setObject(i+1,objects[i]);
        }
        return preparedStatement;
    }

    /*
     * fermeture de toutes les ressources
     */

    /* fermeture du ResultSet*/

    public static void fermeture(ResultSet resultSet){
        if (resultSet != null){
            try {
                resultSet.close();
            }catch (SQLException e){
                System.out.println("echec de la fermeture du ResultSet" + e.getMessage());
            }
        }
    }

    /* fermeture du statement*/

    public static void fermeture(Statement statement){
        if ( statement != null){
            try {
                statement.close();
            }catch (SQLException e){
                System.out.println("echec de la fermeture Statement" + e.getMessage());
            }
        }
    }

    /* fermeture du Connection*/

    public static void fermeture(Connection connection){
        if (connection != null){
            try {
                connection.close();
            }catch (SQLException e){
                System.out.println("echec de la fermeture du Connection" + e.getMessage());
            }
        }
    }

    /* fermeture du Connection et statement*/

    public static void fermetures( Statement statement,Connection connection ){
        fermeture(statement);
        fermeture(connection);
    }

    /* fermeture du ResultSet , Connection et statement*/

    public static void fermetures(ResultSet resultSet  , Statement statement , Connection connection){
        fermeture(resultSet);
        fermeture(statement);
        fermeture(connection);
    }


}
