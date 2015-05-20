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
    private final JTextField titre;
    private final Frame_mother frame; 
    
    private String cat, esp, ana, SelectClient;
  
    public FormulaireCommande (Frame_mother interfaceUti) {
    
    // Titre page
    titre = new JTextField("Création d'une commande");
    titre.setEditable(false);
    titre.setFont(new Font("Serif", Font.BOLD, 20));    
        
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
    Envoyer.addActionListener(this);
    Annuler = new JButton("Annuler");
    Annuler.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            InterfaceUtilisateur secretaire = new InterfaceUtilisateur(frame,"","");
            frame.setFrame(secretaire);
        }
    });
   
    p6.add(Envoyer);
    p6.add(Annuler);
    
    JPanel global = new JPanel(); 
    global.setLayout(new BoxLayout(global, BoxLayout.PAGE_AXIS));
    global.add(titre);
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
        
        SelectClient = ListClient.getSelectedItem().toString(); // recupère le client sectionner dans la liste déroulante
        cat = TextCategorie.getText();
        esp = TextEspece.getText();
        ana = TextAnalyse.getText();
        String IDClient = "";
        String IDEspece = "";
        String IDScrapieTest = "";
        String IDSexingTest = "";
        String IDCommande = "";
        
        try
        {
            // Connexion à la base de donnée
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.24.16/td2","td2","OST");
            
            // Récupère l'ID du client sélectionnée dans la liste déroulante de la table customer de la base de donnée 
            PreparedStatement rechercheIDClient = con.prepareStatement("Select customerID From customer Where name=?");
            rechercheIDClient.setString(1, SelectClient);
            ResultSet resultatRechercheIDClient = rechercheIDClient.executeQuery();
            if (resultatRechercheIDClient.next())
            {
                IDClient = resultatRechercheIDClient.getString("customerID");
            }
            
            // Vérifie si l'espèce et la catégorie sont cohérent
            PreparedStatement recherche = con.prepareStatement("Select * From specie Where nameSpecie=? and nameCat=?");
            recherche.setString(1, esp);
            recherche.setString(2, cat);
            ResultSet resultatRecherche = recherche.executeQuery();
            if (resultatRecherche.next())
            {
                IDEspece = resultatRecherche.getString("specieID");
                
                //Vérifie si l'analyse donnée exsiste dans la table scrapieTest pour l'espèce donnée
                PreparedStatement rechercheAnalyse = con.prepareStatement("Select * From scrapieTest Where nameT=? and specieId=?");
                rechercheAnalyse.setString(1, ana);
                rechercheAnalyse.setString(2, IDEspece);
                ResultSet resultatRechercheAnalyse = rechercheAnalyse.executeQuery();
                
                //Vérifie si l'analyse donnée exsiste dans la table sexingTest pour l'espèce donnée
                PreparedStatement rechercheAnalyse2 = con.prepareStatement("Select * From sexingTest Where nameT=? and specieId=?");
                rechercheAnalyse2.setString(1, ana);
                rechercheAnalyse2.setString(2, IDEspece);
                ResultSet resultatRechercheAnalyse2 = rechercheAnalyse2.executeQuery(); 
                boolean Analyse = resultatRechercheAnalyse.next();
                
                // Si l'analyse existe dans l'une des deux tables alors
                if (Analyse || resultatRechercheAnalyse2.next()) 
                {
                    if (Analyse) 
                    {
                        IDScrapieTest = resultatRechercheAnalyse.getString("scrapieTID");
                        IDSexingTest = "NULL";
                    }
                    else {
                        IDSexingTest = resultatRechercheAnalyse2.getString("sexingTID");
                        IDScrapieTest = "NULL";
                    }
                    
                    // Ajout d'une nouvelle commande dans la table order
                    PreparedStatement ajoutOrder = con.prepareStatement("insert into orders (customerID, status) values(?,?);", Statement.RETURN_GENERATED_KEYS);
                    ajoutOrder.setString(1, IDClient);
                    ajoutOrder.setString(2, "In Analysis");
                    int resultatAjoutOrder = ajoutOrder.executeUpdate();
                    
                    // Recupère l'identifiant de la commande que l'on vient d'ajouter dans la base de donnée
                    ResultSet resIDOrder = ajoutOrder.getGeneratedKeys();
                    while (resIDOrder.next()) {
                        IDCommande = resIDOrder.getString(1);
                    }
                    
                    // Ajout dans la table order réussi
                    if (resultatAjoutOrder == 1)
                    {
                        //Ajout de l'analyse dans la table sample
                        PreparedStatement ajoutSample = con.prepareStatement("insert into sample (orderID, specieID, scrapieTID, sexingTID) values(?,?,?,?)");
                        ajoutSample.setString(1, IDCommande);
                        ajoutSample.setString(2, IDEspece);
                        if (IDSexingTest == "NULL") {
                            ajoutSample.setString(3, IDScrapieTest);
                            ajoutSample.setNull(4, java.sql.Types.INTEGER);
                        }
                        else {
                            ajoutSample.setNull(3, java.sql.Types.INTEGER);
                            ajoutSample.setString(4, IDSexingTest);
                        }
                        int resultatAjoutSample = ajoutSample.executeUpdate();
                        
                        //vérifie si l'ajout dans les tables order et semple à bien été fait 
                        if (resultatAjoutSample == 1)
                        {
                            // Ajout réussi
                            TextCategorie.setText("Entrer la catégorie");
                            TextEspece.setText("Entrer l'espèce");
                            TextAnalyse.setText("Entrer l'analyse");
                            JOptionPane.showMessageDialog(null,"Ajout réussi");
                        }
                        else 
                        {
                            // Ajout non réussi
                            JOptionPane.showMessageDialog(null,"Ajout non réussi");
                        } 
                    } 
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"L'analyse et l'espèce ne sont pas compatible");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null,"L'espèce et la catégorie ne sont pas compatible");
            }
            
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        
    }
}
