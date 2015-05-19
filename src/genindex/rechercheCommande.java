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
//    private JTextField textRechercheNom;
    private JLabel labelRechercheNom;
    private JButton bouttonRechercher;
    private JButton bouttonAnnuler;
    private JLabel titre;
    private JTextArea InformationClient;
    private Frame_mother frame;
    private String resultSelected;
    private String monId;
    private String monNom;

    public rechercheCommande(Frame_mother interfaceUti)
    {
        frame = interfaceUti;
        
        // Titre page
        titre = new JLabel("Recherche de commandes d'un client");
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
                InterfaceUtilisateur validateur = new InterfaceUtilisateur(frame);
                frame.setFrame(validateur);
            }
        });
        
        InformationClient = new JTextArea();
        InformationClient.setEditable(false);
        this.setLayout(new GridLayout(5,1));
        this.add(titre);
        this.add(listClient);
        this.add(bouttonRechercher);
        this.add(bouttonAnnuler);
        this.add(InformationClient);

    }
    
    public void actionPerformed(ActionEvent ae)
    {
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