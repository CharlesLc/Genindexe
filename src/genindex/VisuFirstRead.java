/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genindex;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author MOoh'
 */
public class VisuFirstRead extends JPanel implements ActionListener {
    
    private JLabel titre;
    
    private Frame_mother frame;
    
    // Déclaration connexion
    private String messages = "";
    private Connection con = null;
    private PreparedStatement recherche = null;
    private ResultSet resultatRecherche = null;
    
    
    public VisuFirstRead(Frame_mother InterfaceUti, int microplaqueID)
    {
        // Forme du panel
        this.setLayout(new GridLayout(9,2));
        
        // Titre
        this.add(titre = new JLabel("Récap des résultats"));
        
        // Résultats et ref
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://192.168.24.16/td2","td2","OST");
            recherche = con.prepareStatement("select scrapieTID, sexingTID, sampleID from microplate natural join result natural join sample where microplateID =?");
            recherche.setInt(1, microplaqueID);
            resultatRecherche = recherche.executeQuery();        
            while ( resultatRecherche.next()) {
                if(resultatRecherche.getString("scrapieTID") == null)
                {
                    this.add(new AffichageResultat(Integer.parseInt(resultatRecherche.getString("sampleID")),true));
                }
                else
                    this.add(new AffichageResultat(Integer.parseInt(resultatRecherche.getString("sampleID")),false));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }finally {
            messages=messages+"Fermeture de l'objet ResultSet.\n";
            if ( resultatRecherche != null ) {
                try {
                    resultatRecherche.close();
                } catch ( SQLException ignore ) {
                }
            }
            messages=messages+"Fermeture de l'objet Statement.\n";
            if ( recherche != null ) {
                try {
                    recherche.close();
                } catch ( SQLException ignore ) {}
            }
            messages=messages+"Fermeture de l'objet Connection.\n";
            if ( con != null ) {
                try {
                    con.close();
                } catch ( SQLException ignore ) {}
            }
        }
        
        
        
        
    }
    
    
    public void actionPerformed(ActionEvent ae)
    {
        
    }
    
    
    
}
