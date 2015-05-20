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
 * @author Valentin
 */
public class visuListes  extends JPanel implements ActionListener{
    
    private JButton bouListCustomer;
    private Frame_mother frame;
    private JTextField titre;
    private JPanel panelPrincipal;
    private JPanel panelBoutons;
    private JTextField listName;
    private JTextArea theTextArea;
    private JScrollPane theJScrollPane;
    private JLabel labelListeClient,labelListeAnalyses,labelListeEspeces,labelListeCategories,labelListeEchantillon,labelListeCommande;
    private JButton bouListClient,bouListAnalyses,bouListEspeces,bouListCategories,bouListEchantillon,bouListCommande;
    private JButton bouttonAnnuler;
    
    public visuListes(Frame_mother interfaceUti){
    
        frame = interfaceUti;
        
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(3,1));
        
        panelBoutons = new JPanel();
        panelBoutons.setLayout(new GridLayout(7,2));
        
        listName = new JTextField();
        listName.setEditable(false);
        listName.setHorizontalAlignment(JTextField.CENTER);
        listName.setFont(new Font("Serif", Font.BOLD, 16));
        
        theTextArea = new JTextArea();
        theJScrollPane = new JScrollPane(theTextArea);
        
        // Titre page
        titre = new JTextField("Visualisation des listes");
        titre.setEditable(false);
        titre.setFont(new Font("Serif", Font.BOLD, 20)); 
        
        /////// LABELS
        //Liste Client
        labelListeClient = new JLabel("Liste des clients");
        //Liste Analyses
        labelListeAnalyses = new JLabel("Liste des analyses");
        //Liste Espèces
        labelListeEspeces = new JLabel("Liste des espèces");
        //Liste Echantillon
        labelListeCategories = new JLabel("Liste des catégories d'espèces");
        //Liste Echantillon
        labelListeEchantillon = new JLabel("Liste des échantillons");
        //Liste Commande
        labelListeCommande = new JLabel("Liste des commandes");
        
        /////// BOUTONS/////////////////////////////////////////////////
        bouListClient = new JButton ("Afficher");
        bouListClient.addActionListener(this);
        
        bouListAnalyses = new JButton ("Afficher");
        bouListAnalyses.addActionListener(this);
        
        bouListEspeces = new JButton ("Afficher");
        bouListEspeces.addActionListener(this);
        
        bouListCategories = new JButton ("Afficher");
        bouListCategories.addActionListener(this);
        
        bouListEchantillon = new JButton ("Afficher");
        bouListEchantillon.addActionListener(this);
        
        bouListCommande = new JButton ("Afficher");
        bouListCommande.addActionListener(this);
        
        // ANNULER
        bouttonAnnuler = new JButton("Annuler");
        bouttonAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InterfaceUtilisateur secretaire = new InterfaceUtilisateur(frame,"","");
                frame.setFrame(secretaire);
            }
        });
        ///////////////////////////////////////////////////////////////

        panelBoutons.add(labelListeClient);
        panelBoutons.add(bouListClient);
        panelBoutons.add(labelListeAnalyses);
        panelBoutons.add(bouListAnalyses);
        panelBoutons.add(labelListeEspeces);
        panelBoutons.add(bouListEspeces);
        panelBoutons.add(labelListeCategories);
        panelBoutons.add(bouListCategories);
        panelBoutons.add(labelListeEchantillon);
        panelBoutons.add(bouListEchantillon);
        panelBoutons.add(labelListeCommande);
        panelBoutons.add(bouListCommande);
        panelBoutons.add(bouttonAnnuler);
        panelBoutons.add(listName);

        panelPrincipal.add(titre);
        panelPrincipal.add(panelBoutons);     
        panelPrincipal.add(theJScrollPane);
        this.add(panelPrincipal);
    }

    public void actionPerformed(ActionEvent ae){     

        // Déclaration connexion
        String messages = "";
        Connection con = null;
        PreparedStatement recherche = null;
        ResultSet resultatRecherche = null;
        PreparedStatement recherche2 = null;
        ResultSet resultatRecherche2 = null;        

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://192.168.24.16/td2","td2","OST");   
            if(ae.getSource() == bouListClient ){
                listName.setText("Clients");
                recherche = con.prepareStatement("select  customerID,name,town,login from customer ");
                resultatRecherche = recherche.executeQuery();  
                if (resultatRecherche.next())
                {
                    theTextArea.setText(" ID : " + resultatRecherche.getString("customerID") + "   Nom : " + resultatRecherche.getString("name") + "   Ville : " + resultatRecherche.getString("town") + "\n");
                    while ( resultatRecherche.next()) {
                        theTextArea.append(" ID : " + resultatRecherche.getString("customerID") + "   Nom : " + resultatRecherche.getString("name") + "   Ville : " + resultatRecherche.getString("town") + "\n");
                    }
                }
            }
            else if(ae.getSource() == bouListAnalyses ){
                listName.setText("Analyses");
                recherche = con.prepareStatement("select scrapieTID,nameT from scrapieTest");
                resultatRecherche = recherche.executeQuery();  
                if (resultatRecherche.next())
                {
                    theTextArea.setText("Scrapie test : \n");
                    theTextArea.append("  -Scrapie ID : " + resultatRecherche.getString("scrapieTID") + "   Nom : " + resultatRecherche.getString("nameT") + "\n");
                    while ( resultatRecherche.next()) {
                        theTextArea.append("  -Scrapie ID : " + resultatRecherche.getString("scrapieTID") + "   Nom : " + resultatRecherche.getString("nameT") + "\n");
                    }
                }
                recherche = con.prepareStatement("select sexingTID,nameT from sexingTest");
                resultatRecherche = recherche.executeQuery();
                if (resultatRecherche.next())
                {
                    theTextArea.append("Sexing test : \n");
                    theTextArea.append("  -Sexing ID : " + resultatRecherche.getString("sexingTID") + "   Nom : " + resultatRecherche.getString("nameT") + "\n");
                    while ( resultatRecherche.next()) {
                        theTextArea.append("  -Sexing ID : " + resultatRecherche.getString("sexingTID") + "   Nom : " + resultatRecherche.getString("nameT") + "\n");
                    }
                }
            }
            else if(ae.getSource() == bouListEspeces ){
                listName.setText("Espèces");
                recherche = con.prepareStatement("select specieID,nameSpecie,nameCat from specie ");
                resultatRecherche = recherche.executeQuery();  
                if (resultatRecherche.next())
                {
                    theTextArea.setText(" ID : " + resultatRecherche.getString("specieID") + "   Nom : " + resultatRecherche.getString("nameSpecie") + "   Catégorie : " + resultatRecherche.getString("nameCat") + "\n");
                    while ( resultatRecherche.next()) {
                        theTextArea.append(" ID : " + resultatRecherche.getString("specieID") + "   Nom : " + resultatRecherche.getString("nameSpecie") + "   Catégorie : " + resultatRecherche.getString("nameCat") + "\n");
                    }
                }
            }
            else if(ae.getSource() == bouListCategories ){
                listName.setText("Catégories d'espèces");
                recherche = con.prepareStatement("select name from specieCategory ");
                resultatRecherche = recherche.executeQuery();  
                if (resultatRecherche.next())
                {
                    theTextArea.setText(" Nom : " + resultatRecherche.getString("name") + "\n");
                    while ( resultatRecherche.next()) {
                        theTextArea.append(" Nom : " + resultatRecherche.getString("name") + "\n");
                    }
                }
            }
            else if(ae.getSource() == bouListEchantillon ){
                listName.setText("Echantillons");
                recherche = con.prepareStatement("select sampleID,orderID,specieID,scrapieTID,sexingTID from sample ");
                resultatRecherche = recherche.executeQuery();  
                if (resultatRecherche.next())
                {
                    theTextArea.setText(" ID : " + resultatRecherche.getString("sampleID") + "   Nom : " + resultatRecherche.getString("orderID") + "   Ville : " + resultatRecherche.getString("specieID"));
                    if(resultatRecherche.getString("scrapieTID") != null){
                        theTextArea.append( "   Test : Scrapie\n");
                    }
                    if(resultatRecherche.getString("sexingTID") != null){
                        theTextArea.append( "   Test : Sexing\n");
                    }
                    while ( resultatRecherche.next()) {
                        theTextArea.append(" ID : " + resultatRecherche.getString("sampleID") + "   Nom : " + resultatRecherche.getString("orderID") + "   Ville : " + resultatRecherche.getString("specieID"));
                        if(resultatRecherche.getString("scrapieTID") != null){
                            theTextArea.append( "   Test : Scrapie\n");
                        }
                        if(resultatRecherche.getString("sexingTID") != null){
                            theTextArea.append( "   Test : Sexing\n");
                        }
                    }
                }
            }
            else if(ae.getSource() == bouListCommande ){
                listName.setText("Commandes");
                recherche = con.prepareStatement("select orderID,customerID,status from orders");
                resultatRecherche = recherche.executeQuery(); 

                if (resultatRecherche.next())
                {
                    recherche2 = con.prepareStatement("select name from customer where customerID=?");
                    recherche2.setString(1, resultatRecherche.getString("customerID"));
                    resultatRecherche2 = recherche2.executeQuery(); 
                    if (resultatRecherche2.next()) {
                        theTextArea.setText(" ID : " + resultatRecherche.getString("orderID") + "   Client : " + resultatRecherche2.getString("name") + "   Statut : " + resultatRecherche.getString("status") + "\n");
                    }
                    while ( resultatRecherche.next()) {
                        recherche2 = con.prepareStatement("select name from customer where customerID=?");
                        recherche2.setString(1, resultatRecherche.getString("customerID")); 
                        resultatRecherche2 = recherche2.executeQuery(); 
                        if (resultatRecherche2.next()) {
                            theTextArea.append(" ID : " + resultatRecherche.getString("orderID") + "   Client : " + resultatRecherche2.getString("name") + "   Statut : " + resultatRecherche.getString("status") + "\n");
                        }
                    }
                }
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
}