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
   private JTextArea textFonctionUser, textNomUser, textPrenomUser;
   
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
           
           //Panels
           //Premier panel: il comprend les informations de l'utilisateur
           JPanel infoUser = new JPanel();
           infoUser.setLayout(new GridLayout(2,3));
           infoUser.add(labFonctionUser);
           infoUser.add(textFonctionUser);
           infoUser.add(labNomUser);
           infoUser.add(textNomUser);
           infoUser.add(labPrenomUser);
           infoUser.add(textPrenomUser);
           
           //Second panel: les actions que peut faire la secrétaire spécifiquement
           //ce panel sera mis en visible(false) si l'utilisateur est un validateur 
           //ou un technicien
           JPanel panelSecretaire = new JPanel();
           panelSecretaire.setLayout(new GridLayout(1,4));
           panelSecretaire.add(bouNouvClient);
           panelSecretaire.add(bouNouvCommande);
           panelSecretaire.add(bouNouvEspece);
           panelSecretaire.add(bouNouvCategorie);
           panelSecretaire.isVisible();
           
           //Panel regroupant les autres panels
           JPanel panelGeneral = new JPanel();
           panelGeneral.setLayout(new GridLayout(1,2));
           panelGeneral.add(infoUser);
           panelGeneral.add(panelSecretaire);           
           
           //this setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           this.add(panelGeneral);
           this.pack();
           this.setVisible(true);          
   }
   
   public static void main (String[] args)
   {
       new InterfaceUtilisateur();
   }  
    
}
