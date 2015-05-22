package genindex;

import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Customer {
  /**
   * The different orders for the customer
   */
  private List<Order> orders;

  /**
   * Name of the customer
   */
  private String name;

  /**
   * Address (only the town) from the customer
   */
  private String town;
  
  private String login;

  public final String getName() {
    return name;
  }

  public final String getTown() {
    return town;
  }

  /**
   * Constructor for the customer
   * 
   * @param n name of the customer
   * @param t town for the customer
   */
  public Customer(){
      
  }
  
  public Customer(String n, String t) {
	name = n;
	town = t;
	orders = new ArrayList<Order>();
  }
  
  public Customer(String n, String t, String l) {
	name = n;
	town = t;
        login = l;
	orders = new ArrayList<Order>();
  }

  /**
   * Adds an order for this customer
   * 
   * @param theOrder the order to be added
   */
  public void addOrder(Order theOrder) {
	orders.add(theOrder);
  }

  public final List<Order> getOrders() {
    return orders;
  }
  
  public void updateBdd(){
      try {
            Class.forName( "com.mysql.jdbc.Driver" );
        } catch ( ClassNotFoundException e ) {
        }
        String url = "jdbc:mysql://192.168.24.16/td2";
        String utilisateur = "td2";
        String motDePasse = "OST";
        Connection connexion = null;
        Statement statement = null;
        int resultat = 0;
        try {
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
            statement = connexion.createStatement();

            
            PreparedStatement preparedStatement = connexion.prepareStatement( "UPDATE customer SET name=?, town=? WHERE login=? " );

            preparedStatement.setString( 1, name );
            preparedStatement.setString( 2, town );
            preparedStatement.setString( 3, login);

            resultat = preparedStatement.executeUpdate();
               
        } catch ( SQLException e ) {
        } finally {
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
  }
        
    public int findWithLog(String l){
        int TID=0;
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


                PreparedStatement preparedStatement = connexion.prepareStatement( "Select customerID, name, town from customer where login=?" );

                preparedStatement.setString( 1, l );

                resultat = preparedStatement.executeQuery();
                
                while ( resultat.next() ) {
                    TID = resultat.getInt("customerID");
                    String Tname = resultat.getString( "name" );
                    String Ttown = resultat.getString( "town" );
                    name = Tname;
                    town = Ttown;
                }

            } catch ( SQLException e ) {
            } finally {
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
            return TID;
      }

}
