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
   private JButton bouNouvClient, bouNouvCommande, bouNouvEspece, bouNouvCategorie;//Boutons de la secretaire
   private JButton bouVisuCommande, bouNouvScrapie, bouNouvSexing;//Boutons du validateur
   private JButton bouMicroplaques, bouVisuResultats; //Boutons du technicien
   private JLabel labFonctionUser, labNomUser, labPrenomUser;
   private JTextArea textFonctionUser, textNomUser, textPrenomUser;
   private Frame_mother frame;
   private JPasswordField pass1;
   private JTextField tf1;
   private String str1,str2;
  
   public InterfaceUtilisateur (Frame_mother formulaireClient,String log,String psswd)
    {
        str1=log;
        str2=psswd;
        frame = formulaireClient;
        //Boutons
        bouNouvClient = new JButton ("Créer Nouveau Client");
        bouNouvClient.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            FormulaireClient nouveauClient = new FormulaireClient(frame);
            frame.setFrame(nouveauClient);}
        });

        bouNouvCommande = new JButton ("Créer Nouvelle Commande");
        bouNouvCommande.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            FormulaireCommande nouvelleCommande = new FormulaireCommande(frame);
            frame.setFrame(nouvelleCommande);}
        });

        bouNouvEspece = new JButton ("Créer Nouvelle Espece");
        bouNouvEspece.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            FormulaireEspece nouvelleEspece = new FormulaireEspece(frame);
            frame.setFrame(nouvelleEspece);
        } 
        });

        bouNouvCategorie = new JButton ("Créer Nouvelle Catégorie Espece");
        bouNouvCategorie.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            FormulaireCategories nouvelleCategorie = new FormulaireCategories(frame);
            frame.setFrame(nouvelleCategorie);
        } 
        });

        bouVisuCommande = new JButton ("Visualiser la Commande");
        bouVisuCommande.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            rechercheCommande visuCommande = new rechercheCommande(frame);
            frame.setFrame(visuCommande);}
        });
        bouNouvScrapie = new JButton ("Créer Test de Tremblement");
        bouNouvSexing = new JButton ("Créer Test de Sexage");

        bouMicroplaques = new JButton ("Faire Microplaque");
        bouVisuResultats = new JButton ("Visualiser Resultats");

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
       JPanel panelValidateur = new JPanel();
       panelValidateur.setLayout(new GridLayout(3,1));
       panelValidateur.add(bouVisuCommande);
       panelValidateur.add(bouNouvScrapie);
       panelValidateur.add(bouNouvSexing);

       //Quatrieme panel: les actions que peut faire le Technicien
       //ce panel sera mis en visible(false) si l'utilisateur est une secrétaire
       //ou un technicien
       JPanel panelTechnicien = new JPanel();
       panelTechnicien.setLayout(new GridLayout(3,1));
       panelTechnicien.add(bouMicroplaques);
       panelTechnicien.add(bouVisuResultats);

       //Parametres de visibilité des panels fonction
       panelSecretaire.setVisible(false);
       panelTechnicien.setVisible(false);
       panelValidateur.setVisible(false);


       //Récupération de la fonction de l'utilisateur -- ne fonctionne pas
        try
            {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.24.16/td2","td2","OST");
            PreparedStatement ps = con.prepareStatement("SELECT jobname FROM job NATURAL JOIN user WHERE login=? and passwd=?");
            ps.setString(1, str1);
            ps.setString(2, str2);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String result = rs.getString("jobname");
            //le jobid de Secretaire est 1
            //login:secretary  mdp: secretary
            if (result.equals("Secretary")){
                System.out.println("Secretaire");
                panelSecretaire.setVisible(true);
            //le jobid de Technicien est 3
            //login:technician  mdp:technician
            }else if (result.equals("Technician")){
                panelTechnicien.setVisible(true);
            }
             //le jobid de Validateur est 2 
             //login:validator  mdp:validator
            else if (result.equals("Validator")){
                panelValidateur.setVisible(true);
            }
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
       panelGeneral.add(panelValidateur);

       //this setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.add(panelGeneral);
       //this.pack();
       this.setVisible(true); 
           
   }
   
   
    
   public JPanel getPanel(){
        return this;
    } 
   
}
