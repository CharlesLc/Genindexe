/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genindex;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

/**
 *
 * @author Valentin
 */
public class FormulaireClient extends JPanel implements ActionListener{
    
    private JTextField textNom;
    private JLabel labelNom;
    private JTextField textVille;
    private JLabel labelVille;
    private JButton buttonEnvoyer;
    private JButton buttonAnnuler; 
    private Frame_mother frame;
    private JTextField titre;
    private JPanel p1;
    //private JPanel panelClient; 
    
    private String nom, ville;
    
    public FormulaireClient (Frame_mother interfaceUti) {
        
        // Titre page
        titre = new JTextField("Création d'un client");
        titre.setEditable(false);
        titre.setFont(new Font("Serif", Font.BOLD, 20));
        
        frame = interfaceUti;
        // NOM
        labelNom = new JLabel("Nom"); 
        labelNom.setHorizontalAlignment(JLabel.CENTER);
        labelNom.setVerticalAlignment(JLabel.CENTER);
        textNom = new JTextField(20);
        //JScrollPane scrollPane = new JScrollPane(textNom); 
        
        // VILLE
        labelVille = new JLabel("Ville"); 
        labelVille.setHorizontalAlignment(JLabel.CENTER);
        labelVille.setVerticalAlignment(JLabel.CENTER);
        textVille = new JTextField(20);
        //JScrollPane scrollPane2 = new JScrollPane(textVille); 
        
        // ENVOYER
        buttonEnvoyer = new JButton("Envoyer");
        buttonEnvoyer.addActionListener(this);
        
        // ANNULER
        buttonAnnuler = new JButton("Annuler");
        buttonAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InterfaceUtilisateur secretaire = new InterfaceUtilisateur(frame,"","");
                frame.setFrame(secretaire);
            }
        });
                
        //panelClient = new JPanel();
        this.setLayout(new GridLayout(2,2));
        this.add(titre);
        p1 = new JPanel();
        p1.setLayout(new GridLayout(3,2));
        p1.add(labelNom);
        p1.add(textNom);
        p1.add(labelVille);
        p1.add(textVille);
        p1.add(buttonEnvoyer);
        p1.add(buttonAnnuler);
        this.add(p1);
        
    }
    
    public JPanel getPanel(){
        return this;
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        //if (ae.getSource()== buttonEnvoyer)
        //{
        //   Login lo = new Login();
        //    fo.setFrame(lo);
        //   
        //}
        nom = textNom.getText();
        ville = textVille.getText();
        
        if (nom.equals("")== false && ville.equals("")== false  ) {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://192.168.24.16/td2","td2","OST");
                PreparedStatement recherche = con.prepareStatement("select name from customer where name=? and town=?");
                recherche.setString(1, nom);
                recherche.setString(2, ville);
                ResultSet resultatRecherche = recherche.executeQuery();
                if (resultatRecherche.next())
                {
                    // Déjà présent
                    JOptionPane.showMessageDialog(null,"Le client existe déjà");


                } else
                {
                    PreparedStatement ajout = con.prepareStatement("insert into customer(name,town) values(?,?)");
                    ajout.setString(1, nom);
                    ajout.setString(2, ville);
                    int resultatAjout = ajout.executeUpdate();
                    if (resultatAjout == 1)
                    {
                        // Ajout réussi
                        textVille.setText("");
                        textNom.setText("");
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
        }else {
            JOptionPane.showMessageDialog(this, "Vous n'avez pas tout saisi. Veuillez réessayer", "Champ vide", JOptionPane.ERROR_MESSAGE);
        }
    }   
        
}

    
    
    
    
    
    
