/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genindex;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Ju'
 */
public class FormulaireCommande extends JPanel implements ActionListener{
    
    private final JLabel Client;
    private final JComboBox ListClient;
    private final JLabel Categorie;
    private final JTextField TextCategorie;
    private final JLabel Espece;
    private final JTextField TextEspece;
    private final JLabel Analyse;
    private final JTextField TextAnalyse;
    private final JLabel Statut;
    private final JLabel AfficheStatut;
    
    private final JButton Envoyer;
    private final JButton Annuler; 
    private final Frame_mother frame; 
  
    public FormulaireCommande (Frame_mother interfaceUti) {
    
    frame = interfaceUti;
    
    JPanel p1 = new JPanel();
    p1.setLayout(new BoxLayout(p1, BoxLayout.LINE_AXIS));
    Client = new JLabel(" Client : ", JLabel.CENTER);
    Client.setPreferredSize(new Dimension(120, 30));
    // Affiche une liste déroulante contenant les nom des client présent dans la base de donnée
    ListClient = new JComboBox();
    ListClient.setPreferredSize(new Dimension(160, 30));
    try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.24.16/td2","td2","OST");
            Statement state = con.createStatement();
            ResultSet result = state.executeQuery("SELECT name FROM customer");
            while(result.next()){
                ListClient.addItem(result.getString("name"));
            } 
        }
    catch (Exception ex)
        {
            System.out.println(ex);
        }

    p1.add(Client);
    p1.add(ListClient);
    
    JPanel p2 = new JPanel();
    p2.setLayout(new BoxLayout(p2, BoxLayout.LINE_AXIS));
    Categorie = new JLabel(" Categorie : ", JLabel.CENTER);
    Categorie.setPreferredSize(new Dimension(120, 30));
    TextCategorie = new JTextField("Entrer la catégorie");
    TextCategorie.setPreferredSize(new Dimension(160, 30));
    p2.add(Categorie);
    p2.add(TextCategorie);
    
    JPanel p3 = new JPanel();
    p3.setLayout(new BoxLayout(p3, BoxLayout.LINE_AXIS));
    Espece = new JLabel(" Espece : ", JLabel.CENTER);
    Espece.setPreferredSize(new Dimension(120, 30));
    TextEspece = new JTextField("Entrer l'espèce");
    TextEspece.setPreferredSize(new Dimension(160, 30));
    p3.add(Espece);
    p3.add(TextEspece);
    
    JPanel p4 = new JPanel();
    p4.setLayout(new BoxLayout(p4, BoxLayout.LINE_AXIS));
    Analyse = new JLabel(" Analyse : ", JLabel.CENTER);
    Analyse.setPreferredSize(new Dimension(120, 30));
    TextAnalyse = new JTextField("Entrer l'analyse");
    TextAnalyse.setPreferredSize(new Dimension(160, 30));
    p4.add(Analyse);
    p4.add(TextAnalyse);
    
    JPanel p5 = new JPanel();
    p5.setLayout(new BoxLayout(p5, BoxLayout.LINE_AXIS));
    Statut = new JLabel(" Statut : ", JLabel.CENTER);
    AfficheStatut = new JLabel ("Affichier le statut en cours");
    // A voir si on rajoute un truc pour les echantillons
    
    JPanel p6 = new JPanel();
    p6.setLayout(new BoxLayout(p6, BoxLayout.LINE_AXIS));
    Envoyer = new JButton("Envoyer");
    Annuler = new JButton("Annuler");
    Annuler.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            InterfaceUtilisateur secretaire = new InterfaceUtilisateur(frame);
            frame.setFrame(secretaire);
        }
    });
   
    p6.add(Envoyer);
    p6.add(Annuler);
    
    JPanel global = new JPanel(); 
    global.setLayout(new BoxLayout(global, BoxLayout.PAGE_AXIS));
    global.add(p1);
    global.add(p2);
    global.add(p3);
    global.add(p4);
    global.add(p5);
    global.add(p6);
    
    this.add(global);
    
    }
    
    public JPanel getPanel(){
        return this;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
