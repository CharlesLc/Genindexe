package genindex;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * 18/05/2015
 * @author Maëva
 */
public class InterfaceUtilisateur  extends JPanel{
   private JButton bouNouvClient, bouNouvCommande, bouNouvEspece, bouNouvCategorie;
   private JLabel labFonctionUser, labNomUser, labPrenomUser;
   private JTextArea textFonctionUser, textNomUser, textPrenomUser;
   
   public InterfaceUtilisateur (Frame_mother h)
   {
           //Boutons
           bouNouvClient = new JButton ("Créer Nouveau Client");
           bouNouvCommande = new JButton ("Créer Nouvelle Commande");
           bouNouvEspece = new JButton ("Créer Nouvelle Espece");
           bouNouvCategorie = new JButton ("Créer Nouvelle Catégorie Espece");
           
           //Labels
           labFonctionUser = new JLabel ("Fonction: ");
           labFonctionUser.setHorizontalAlignment(JLabel.CENTER);
           labFonctionUser.setVerticalAlignment(JLabel.CENTER);
           
           labNomUser = new JLabel ("Nom: ");
           labNomUser.setHorizontalAlignment(JLabel.CENTER);
           labNomUser.setVerticalAlignment(JLabel.CENTER);
           
           labPrenomUser = new JLabel ("Prénom: ");
           labPrenomUser.setHorizontalAlignment(JLabel.CENTER);
           labPrenomUser.setVerticalAlignment(JLabel.CENTER);
           
           //TextArea
           textFonctionUser = new JTextArea(3, 20);
           textFonctionUser.setEditable(false);
           textNomUser = new JTextArea(3, 20);
           textNomUser.setEditable(false);
           textPrenomUser = new JTextArea(3, 20);
           textPrenomUser.setEditable(false);
           
           //Panels
           //Premier panel: il comprend les informations de l'utilisateur
           JPanel infoUser = new JPanel();
           infoUser.setLayout(new GridLayout(3,2));
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
           panelSecretaire.setLayout(new GridLayout(4,1));
           panelSecretaire.add(bouNouvClient);
           panelSecretaire.add(bouNouvCommande);
           panelSecretaire.add(bouNouvEspece);
           panelSecretaire.add(bouNouvCategorie);
           panelSecretaire.isVisible();
           
           //Panel regroupant les autres panels
           JPanel panelGeneral = new JPanel();
           panelGeneral.setLayout(new GridLayout(2,1));
           panelGeneral.add(infoUser);
           panelGeneral.add(panelSecretaire);           
           
           //this setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           this.add(panelGeneral);
           //this.pack();
           this.setVisible(true);          
   }
   
   public JPanel getPanel(){
        return this;
    } 
    
}
