package genindex;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

/**
 * 18/05/2015
 * @author Maëva
 */
public class InterfaceUtilisateur  extends JPanel{
   private JButton bouNouvClient, bouNouvCommande, bouNouvEspece, bouNouvCategorie;
   private JButton bouVisuCommande, bouNouvScrapie, bouNouvSexing;
   private JLabel labFonctionUser, labNomUser, labPrenomUser;
   private JTextArea textFonctionUser, textNomUser, textPrenomUser;
   private Frame_mother frame;
   private JPasswordField pass1;
   private JTextField tf1;
   private JPanel actualPanel; //Correspond au panel actuel qui sera remove quand on appuie sur un bouton qui envoie ailleurs
   
   public InterfaceUtilisateur (Frame_mother formulaireClient)
   {
           actualPanel=this;
           frame = formulaireClient;
           //Boutons
           bouNouvClient = new JButton ("Créer Nouveau Client");
           bouNouvClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FormulaireClient nouveauClient = new FormulaireClient(frame);
                frame.setFrame(nouveauClient,actualPanel);}
            });
           
           bouNouvCommande = new JButton ("Créer Nouvelle Commande");
           bouNouvCommande.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FormulaireCommande nouvelleCommande = new FormulaireCommande(frame);
                frame.setFrame(nouvelleCommande,actualPanel);}
            });
           
           bouNouvEspece = new JButton ("Créer Nouvelle Espece");
           bouNouvCategorie = new JButton ("Créer Nouvelle Catégorie Espece");
           
           bouVisuCommande = new JButton ("Visualiser la Commande");
           bouNouvScrapie = new JButton ("Créer Test de Tremblement");
           bouNouvSexing = new JButton ("Créer Test de Sexage");
           
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
           
           tf1 = new JTextField();
           pass1 = new JPasswordField();
           
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
           
           //Troisieme panel: les actions que peut faire le validateur
           //ce panel sera mis en visible(false) si l'utilisateur est une secrétaire
           //ou un technicien
           JPanel panelTechnicien = new JPanel();
           panelTechnicien.setLayout(new GridLayout(3,1));
           panelTechnicien.add(bouVisuCommande);
           panelTechnicien.add(bouNouvScrapie);
           panelTechnicien.add(bouNouvSexing);
           
           panelSecretaire.setVisible(true);
           panelTechnicien.setVisible(false);
           
           
           String str1 = tf1.getText();
        char[] p = pass1.getPassword();
        String str2 = new String(p);
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.24.16/td2","td2","OST");
            PreparedStatement ps = con.prepareStatement("select jobid from user where login=? and passwd=?");
            ps.setString(1, str1);
            ps.setString(2, str2);
            ResultSet rs = ps.executeQuery();
            //le jobid de Secretaire est 1
            if (rs.isFirst()){
                panelSecretaire.setVisible(true);
            //le jobid de Technicien est 3
            }else if (rs.isLast()){
                panelTechnicien.setVisible(true);
            }
             //faire un else avec le validateur (son jobid est 2)           
            }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
           
           
           //Panel regroupant les autres panels
           JPanel panelGeneral = new JPanel();
           panelGeneral.setLayout(new GridLayout(2,1));
           panelGeneral.add(infoUser);
           panelGeneral.add(panelSecretaire);           
           panelGeneral.add(panelTechnicien);
           
           //this setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           this.add(panelGeneral);
           //this.pack();
           this.setVisible(true); 
           
   }
   
   
    
   public JPanel getPanel(){
        return this;
    } 
   
}
