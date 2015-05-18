package genindex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class BDD {

    public String exemple() {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        String data = "";
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/jst_test","root","");

            // Connexion réussie

            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM table1");

            // parcours de l'ensemble des reponses
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");

                data += "ID: " + id + ", Nom: " + nom + "<br/>";
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore
                stmt = null;
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqlEx) {
                } // ignore
                conn = null;
            }
        }
        return data;
    }

}

