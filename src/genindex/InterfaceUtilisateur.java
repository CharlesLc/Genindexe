package genindex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//package genindex;

/**
 * 18/05/2015
 * @author Maëva
 */
public class InterfaceUtilisateur  extends JFrame{
   private JButton bouNouvClient, bouNouvCommande, bouNouvEspece, bouNouvCategorie;
   private JLabel labFonctionUser, labNomUser, labPrenomUser;
   
   public InterfaceUtilisateur ()
   {
           //Nom de la page
           this.setTitle("Utilisateur");
           
           //Boutons
           bouNouvClient = new JButton ("Créer Nouveau Client");
           bouNouvCommande = new JButton ("Créer Nouvelle Commande");
           bouNouvEspece = new JButton ("Créer Nouvelle Espece");
           bouNouvCategorie = new JButton ("Créer Nouvelle Catégorie Espece");
           
           //Labels
           labFonctionUser = new JLabel ("Fonction: ");
           labNomUser = new JLabel ("Nom: ");
           labPrenomUser = new JLabel ("Prénom: ");
           
           //this setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           this.pack();
           this.setVisible(true);          
   }
   
   public static void main (String[] args)
   {
       new InterfaceUtilisateur();
   }  
    
}
