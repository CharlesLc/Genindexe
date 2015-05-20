/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genindex;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author MOoh'
 */
public class rechercheCommande extends JPanel implements ActionListener{
        
    private JComboBox listClient;
    private JLabel labelRechercheNom;
    private JButton bouttonRechercher;
    private JButton bouttonAnnuler;
    private JTextField titre;
    private JTextArea InformationClient;
    private JScrollPane ScrollInformationClient;
    private JPanel panRecherche;
    private Frame_mother frame;
    private String resultSelected;
    private String monId;
    private String monNom;
    private int nbAnalyseFinie;
    private int nbAnalyses;
    private int pourcentage;

    public rechercheCommande(Frame_mother interfaceUti)
    {
        frame = interfaceUti;
        
        // Titre page
        titre = new JTextField("Visualisation des listes");
        titre.setEditable(false);
        titre.setFont(new Font("Serif", Font.BOLD, 20)); 
       
        // Recherche client
        labelRechercheNom = new JLabel("Saisir le nom du client");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.24.16/td2","td2","OST");
            PreparedStatement recherche = con.prepareStatement("select customerID,name from customer");
            ResultSet resultatRecherche = recherche.executeQuery();  
  
            listClient = new JComboBox();
            listClient.setPreferredSize(new Dimension(160, 30));        
            while ( resultatRecherche.next()) {
                listClient.addItem(resultatRecherche.getString("customerID") + " " + resultatRecherche.getString("name"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        // Rechercher
        bouttonRechercher = new JButton("Rechercher");
        bouttonRechercher.addActionListener(this);

        // ANNULER
        bouttonAnnuler = new JButton("Annuler");
        bouttonAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InterfaceUtilisateur validateur = new InterfaceUtilisateur(frame,"","");
                frame.setFrame(validateur);
            }
        });
        
        InformationClient = new JTextArea();
        InformationClient.setEditable(false);
        ScrollInformationClient = new JScrollPane(InformationClient);
        
        panRecherche = new JPanel();
        panRecherche.setLayout(new GridLayout(4,1));
        panRecherche.add(titre);
        panRecherche.add(listClient);
        panRecherche.add(bouttonRechercher);
        panRecherche.add(bouttonAnnuler);
        
        this.setLayout(new GridLayout(2,1));
        this.add(panRecherche);
        this.add(ScrollInformationClient);

    }
    
    public void actionPerformed(ActionEvent ae)
    {
        InformationClient.setText("");
        resultSelected = listClient.getSelectedItem().toString();
        String[] tabSplitResult = resultSelected.split(" ");
        monId = tabSplitResult[0]; 
        monNom = tabSplitResult[1]; 
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.24.16/td2","td2","OST");
            PreparedStatement recherche = con.prepareStatement("select orderID,status from orders natural join customer where customerID=?");
            recherche.setString(1, monId);
            ResultSet resultatRecherche = recherche.executeQuery();
            if (resultatRecherche.next())
            {
                InformationClient.append("Client : " + monNom + "\t ID Commande : " + resultatRecherche.getString("orderID") + "\t Statut : " + resultatRecherche.getString("status") + "\n");
                while ( resultatRecherche.next()) {
                    InformationClient.append("Client : " + monNom + "\t ID Commande : " + resultatRecherche.getString("orderID") + "\t Statut : " + resultatRecherche.getString("status") + "\n");
                }
                
                PreparedStatement analyseOk = con.prepareStatement("select * from orders natural join customer where customerID=? and status='completed'");
                analyseOk.setString(1, monId);
                PreparedStatement analyses = con.prepareStatement("select * from orders natural join customer where customerID=?");
                analyses.setString(1, monId);
                ResultSet analyseFinie = analyseOk.executeQuery();
                if (analyseFinie.last()) 
                {
                    nbAnalyseFinie = analyseFinie.getRow();
                }
                ResultSet analyseTout = analyses.executeQuery();
                if (analyseTout.last()) 
                {
                    nbAnalyses = analyseTout.getRow();
                }
                
                InformationClient.append("Nb analyses terminées : " + nbAnalyseFinie + "\n");
                pourcentage = nbAnalyseFinie*100/nbAnalyses;
                InformationClient.append("Nb analyses : " + nbAnalyses + "\t\t " + pourcentage + "% terminé \n");



                
            } else
            {
               InformationClient.setText("Client : " + monNom + "\t *Pas de commande*");
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }
}