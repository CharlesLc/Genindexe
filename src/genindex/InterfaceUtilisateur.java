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
   private JButton bouVisuCommande, bouNouvScrapie, bouNouvSexing, bouSecondRead;//Boutons du validateur
   private JButton bouMicroplaques, bouVisuResultats, bouPremierRead; //Boutons du technicien
   private JButton bouLogout;
   private JPanel panLogout;
   private JLabel labFonctionUser;
   private JTextArea textFonctionUser;
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
        bouNouvScrapie.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            ScrapieTest scrapieteste = new ScrapieTest(frame);
            frame.setFrame(scrapieteste);}
        });
        
        bouNouvSexing = new JButton ("Créer Test de Sexage");
        bouSecondRead = new JButton ("Effectuer Seconde Lecture");

        bouMicroplaques = new JButton ("Faire Microplaque");
        bouVisuResultats = new JButton ("Visualiser Resultats");
        bouPremierRead = new JButton ("Effectuer Première Lecture");

        //Labels
        labFonctionUser = new JLabel ("Fonction: ");
        labFonctionUser.setHorizontalAlignment(JLabel.CENTER);
        labFonctionUser.setVerticalAlignment(JLabel.CENTER);

        tf1 = new JTextField();
        pass1 = new JPasswordField();

        //TextArea
        textFonctionUser = new JTextArea(3, 20);
        textFonctionUser.setEditable(false);
       
       //Panels
       //Premier panel: il comprend les informations de l'utilisateur
       JPanel infoUser = new JPanel();
       infoUser.setLayout(new GridLayout(3,2));
       infoUser.add(labFonctionUser);
       infoUser.add(textFonctionUser);

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
       panelValidateur.setLayout(new GridLayout(4,1));
       panelValidateur.add(bouVisuCommande);
       panelValidateur.add(bouNouvScrapie);
       panelValidateur.add(bouNouvSexing);
       panelValidateur.add(bouSecondRead);

       //Quatrieme panel: les actions que peut faire le Technicien
       //ce panel sera mis en visible(false) si l'utilisateur est une secrétaire
       //ou un technicien
       JPanel panelTechnicien = new JPanel();
       panelTechnicien.setLayout(new GridLayout(4,1));
       panelTechnicien.add(bouMicroplaques);
       panelTechnicien.add(bouVisuResultats);
       panelTechnicien.add(bouPremierRead);

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
                textFonctionUser.setText("Secretaire");
            //le jobid de Technicien est 3
            //login:technician  mdp:technician
            }else if (result.equals("Technician")){
                panelTechnicien.setVisible(true);
                textFonctionUser.setText("Technicien");
            }
             //le jobid de Validateur est 2 
             //login:validator  mdp:validator
            else if (result.equals("Validator")){
                panelValidateur.setVisible(true);
                textFonctionUser.setText("Validateur");
            }
            }
    catch (Exception ex)
    {
        System.out.println(ex);
    }

       //Bouton Logout
        bouLogout = new JButton ("Déconnection");
        bouLogout.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            Login connectionPanel = new Login(frame);
            frame.setFrame(connectionPanel);}
        });
        JPanel panLogout = new JPanel();
        panLogout.setLayout(new GridLayout(3,3));        
        panLogout.add(bouLogout);
        
        //Panel regroupant les autres panels
        JPanel panelGeneral = new JPanel();
        panelGeneral.setLayout(new GridLayout(5,1));
        panelGeneral.add(infoUser);
        panelGeneral.add(panelSecretaire);           
        panelGeneral.add(panelTechnicien);
        panelGeneral.add(panelValidateur);
        panelGeneral.add(panLogout);
        
       //this setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.add(panelGeneral);
       //this.pack();
       this.setVisible(true); 
           
   }
   
   
    
   public JPanel getPanel(){
        return this;
    } 
   
}
