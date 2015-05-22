package genindex;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class User {

    private String job;

    private String login;

    private String password;

    public final String getLogin() {
      return login;
    }

    public final String getPassword() {
      return password;
    }

    public User(String n, String p) {
          login = n;
          password = p;
          job = "";
    }
    
    public User(String n, String p, String j) {
          login = n;
          password = p;
          job = j;
    }

    public boolean existBddAndCusto(){
        boolean find = false;
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
        } catch ( ClassNotFoundException e ) {
        }
        String url = "jdbc:mysql://192.168.24.16/td2";
        String utilisateur = "td2";
        String motDePasse = "OST";
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        try {
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
            statement = connexion.createStatement();

            
            PreparedStatement preparedStatement = connexion.prepareStatement( "SELECT login FROM user WHERE login=? AND passwd=? AND jobID=4" );

            preparedStatement.setString( 1, login );
            preparedStatement.setString( 2, password );

            resultat = preparedStatement.executeQuery();

            if(resultat.next()){
                find = true;
            }
            else find = false;
               
        } catch ( SQLException e ) {
        } finally {
            if ( resultat != null ) {
                try {
                    resultat.close();
                } catch ( SQLException ignore ) {
                }
            }
            if ( statement != null ) {
                try {
                    statement.close();
                } catch ( SQLException ignore ) {
                }
            }
            if ( connexion != null ) {
                try {
                    connexion.close();
                } catch ( SQLException ignore ) {
                }
            }
        }
        return find;
    }
    
}
