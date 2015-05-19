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
public class FormulaireCategories extends JPanel implements ActionListener{
    
    private JLabel titre;
    private JTextField textAjoutCategorie;
    private JLabel labelAjoutCategorie;
    private JButton bouttonAjouter;
    private JButton bouttonAnnuler;
    private Frame_mother frame;
    private String nomCategorie;
    
    public FormulaireCategories(Frame_mother interfaceUti)
    {
        frame = interfaceUti;
        
        // Titre page
        titre = new JLabel("Ajout d'une catégorie");
        titre.setFont(new Font("Serif", Font.BOLD, 20));
        
        // Recherche client
        labelAjoutCategorie = new JLabel("Saisir une espèce ");
        textAjoutCategorie = new JTextField(20);

         // Rechercher
        bouttonAjouter = new JButton("Ajouter");
        bouttonAjouter.addActionListener(this);

        // ANNULER
        bouttonAnnuler = new JButton("Annuler");
        bouttonAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InterfaceUtilisateur secretaire = new InterfaceUtilisateur(frame);
                frame.setFrame(secretaire);
            }
        });
        
        this.setLayout(new GridLayout(3,2));
        this.add(labelAjoutCategorie);
        this.add(textAjoutCategorie);
        this.add(bouttonAjouter);
        this.add(bouttonAnnuler);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        nomCategorie = textAjoutCategorie.getText();
        
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.24.16/td2","td2","OST");
            PreparedStatement recherche = con.prepareStatement("select name from specieCategory where name=?");
            recherche.setString(1, nomCategorie);
            ResultSet resultatRecherche = recherche.executeQuery();
            if (resultatRecherche.next())
            {
                // Déjà présent
                JOptionPane.showMessageDialog(null,"Cette catégorie existe déjà");
               

            } else
            {
                PreparedStatement ajout = con.prepareStatement("insert into specieCategory(name) values(?)");
                ajout.setString(1, nomCategorie);
                int resultatAjout = ajout.executeUpdate();
                if (resultatAjout == 1)
                {
                    // Ajout réussi
                    textAjoutCategorie.setText("");
                    JOptionPane.showMessageDialog(null,"Ajout réussi");
                }
                else 
                {
                    // Ajout non réussi
                    JOptionPane.showMessageDialog(null,"Ajout non réussi");
                } 
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    
    }       
}
