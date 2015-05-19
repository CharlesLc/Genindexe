/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genindex;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author MOoh'
 */
public class rechercheCommande extends JPanel implements ActionListener{
        
    private JTextField textRechercheNom;
    private JLabel labelRechercheNom;
    private JButton bouttonRechercher;
    private JButton bouttonAnnuler;
    private JLabel titre;
    private Frame_mother frame;
    private JPanel actualPanel; //Correspond au panel actuel qui sera remove quand on appuie sur un bouton qui envoie ailleurs
    
    public rechercheCommande(Frame_mother interfaceUti)
    {
        actualPanel=this;
        frame = interfaceUti;
        
        // Titre page
        titre = new JLabel("Recherche de commandes d'un client");
        titre.setFont(new Font("Serif", Font.BOLD, 20));
       
        // Recherche client
        labelRechercheNom = new JLabel("Saisir le nom du client");
        textRechercheNom = new JTextField(20);

         // Rechercher
        bouttonRechercher = new JButton("Rechercher");
        bouttonRechercher.addActionListener(this);

        // ANNULER
        bouttonAnnuler = new JButton("Annuler");
        bouttonAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InterfaceUtilisateur validateur = new InterfaceUtilisateur(frame);
                frame.setFrame(validateur,actualPanel);
            }
        });
       
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        
    }
}
