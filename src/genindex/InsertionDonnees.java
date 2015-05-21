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
public class InsertionDonnees extends JPanel implements ActionListener{
    
    private JComboBox listeMicroplaque;
    private JLabel labelRechercheMicroplaque;
    
    private JButton bouttonRechercher;
    private JButton bouttonAnnuler;
    
    private JPanel panelEnTete;
    private JPanel panelR;
    private JPanel panelResultat;
    private JLabel vide;
    
    private JTextField titre;
    
    private int microplaque;
    private int nbPlace;
    
    private Frame_mother frame;
    
    
    
    // Déclaration connexion
    private String messages = "";
    private Connection con = null;
    private PreparedStatement recherche = null;
    private ResultSet resultatRecherche = null;
    
    public InsertionDonnees (Frame_mother interfaceUti)
    {
        
        messages = "";
        
        // Titre
        titre = new JTextField("Insertion données microplaques");
        titre.setEditable(false);
        titre.setFont(new Font("Serif", Font.BOLD, 20));
        
        // Recherche de microplaques
        labelRechercheMicroplaque = new JLabel("Choisir une microplaque");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://192.168.24.16/td2","td2","OST");
            recherche = con.prepareStatement("select microplateID,maxPlaces from microplate where microplateID IN (select microplateID from result where readNumber = 0)");
            resultatRecherche = recherche.executeQuery();  
              
            listeMicroplaque = new JComboBox();
            listeMicroplaque.setPreferredSize(new Dimension(160, 30));        
            while ( resultatRecherche.next()) {
                listeMicroplaque.addItem(resultatRecherche.getString("microplateID"));
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
        listeMicroplaque.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent ae) {
                microplaque = Integer.parseInt(listeMicroplaque.getSelectedItem().toString());
                
                setPanel(panelR);
                addPanel();
                
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://192.168.24.16/td2","td2","OST");
                    recherche = con.prepareStatement("select maxPlaces from microplate where microplateID=?");
                    recherche.setInt(1,microplaque);
                    resultatRecherche = recherche.executeQuery(); 
                    if ( resultatRecherche.next()) {
                        resultatAffichage(microplaque,Integer.parseInt(resultatRecherche.getString("maxPlaces")));
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
        });
        
        // Rechercher
        bouttonRechercher = new JButton("Comparer les résultats");
        bouttonRechercher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VisuFirstRead ref = new VisuFirstRead(frame,microplaque);
                frame.setFrame(ref);
            }
        });

        // Annuler
        bouttonAnnuler = new JButton("Annuler");
        bouttonAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InterfaceUtilisateur technicien = new InterfaceUtilisateur(frame,"","");
                frame.setFrame(technicien);
            }
        });
        
        // Panel de page
        this.setLayout(new GridLayout(2,2));
        
        
        // Panel d'entête 
        panelEnTete = new JPanel(new GridLayout(4,2));
        panelEnTete.add(titre);
        vide = new JLabel("");
        panelEnTete.add(vide);
        panelEnTete.add(labelRechercheMicroplaque);
        panelEnTete.add(listeMicroplaque);
        panelEnTete.add(bouttonRechercher);
        panelEnTete.add(bouttonAnnuler);
        this.add(panelEnTete);
        
        panelR = new JPanel(new GridLayout(1,2));
        this.add(panelR);
        
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        
    }
    
    public void resultatAffichage(int microplaque, int place)
    {
        messages = "";
        
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://192.168.24.16/td2","td2","OST");
            recherche = con.prepareStatement("select scrapieTID, sexingTID, sampleID from microplate natural join result natural join sample where microplateID =?");
            recherche.setInt(1, microplaque);
            resultatRecherche = recherche.executeQuery();        
            while ( resultatRecherche.next()) {
                if(resultatRecherche.getString("scrapieTID") == null)
                {
                    panelR.add(new AffichageResultat(Integer.parseInt(resultatRecherche.getString("sampleID")),true));
                }
                else
                    panelR.add(new AffichageResultat(Integer.parseInt(resultatRecherche.getString("sampleID")),false));
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
        this.validate();
    }
    
    public void setPanel(JPanel p)
    {
        this.remove(p);
    }
    
    public void addPanel()
    {
        panelR = new JPanel(new GridLayout(1,2));
        this.add(panelR);
        
    }
    
    
    
    
}
