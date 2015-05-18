package genindex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*
import java.sql.Statement;
import java.sql.ResultSet;
*/

public class BDD {

    public BDD() {
        String messages = "";
        try {
            messages=messages+"Chargement du driver...\n";
            Class.forName( "com.mysql.jdbc.Driver" );
            messages=messages+"Driver chargé !\n";
        } catch ( ClassNotFoundException e ) {
            messages=messages+"Erreur lors du chargement : le driver n'a pas été trouvé dans le classpath ! \n"
                    + e.getMessage() +"\n";
        }
        System.out.println(messages);
    }
    
    public Connection connexion(){

        String messages = "";
        
        /* Connexion à la base de données */
        String url = "jdbc:mysql://192.168.24.16/td2";
        String utilisateur = "td2";
        String motDePasse = "OST";
        Connection connexion = null;

        /*
        Statement statement = null;
        ResultSet resultat = null;
        */

        try {
            messages=messages+"Connexion à la base de données...\n";
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
            messages=messages+"Connexion réussie !\n";
        } catch ( SQLException e ) {
            messages=messages+"Erreur lors de la connexion : "
                    + e.getMessage() +"\n";
        }
        System.out.println(messages+"\n");
        return connexion;
    }
}

