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
public class FormulaireCommande extends JPanel{
    
    private JLabel Client;
    private  JComboBox ListClient,ListCategorie,ListEspece,ListAnalyse;
    private JLabel Categorie;
    private JLabel Espece;
    private JLabel Analyse;
    private JLabel Statut;
    private JLabel AfficheStatut;
    
    private JButton Envoyer;
    private JButton Annuler; 
    private JTextField titre;
    private Frame_mother frame; 
    
    private JPanel p1,p2;
    
    private String cat, esp, ana;
    private String SelectClient,SelectCategorie,SelectEspece,SelectAnalyse;
  
    public FormulaireCommande (Frame_mother interfaceUti) {
    
    // Titre page
    titre = new JTextField("Création d'une commande");
    titre.setEditable(false);
    titre.setFont(new Font("Serif", Font.BOLD, 20));    
        
    frame = interfaceUti;
    
    p1 = new JPanel();
    p1.setLayout(new GridLayout(5,1));
    Client = new JLabel(" Client : ", JLabel.CENTER);
    Client.setPreferredSize(new Dimension(120, 30));
    // Affiche une liste déroulante contenant les nom des client présent dans la base de donnée
    ListClient = new JComboBox();
    ListClient.setPreferredSize(new Dimension(160, 30));
    
    p1.add(Client);
    p1.add(ListClient);
    
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


    
    
        // Affiche une liste déroulante contenant les catégories d'espèces présentes dans la base de donnée
        ListCategorie = new JComboBox();
        ListCategorie.setPreferredSize(new Dimension(160, 30));
        p1.add(ListCategorie);
        ListEspece = new JComboBox();
        ListEspece.setPreferredSize(new Dimension(160, 30));
        p1.add(ListEspece);
        ListAnalyse = new JComboBox();
        ListAnalyse.setPreferredSize(new Dimension(160, 30));
        p1.add(ListAnalyse);
        
        // Affiche une liste déroulante contenant les catégories d'espèces présentes dans la base de donnée
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.24.16/td2","td2","OST");
            Statement state = con.createStatement();
            ResultSet result = state.executeQuery("select name from specieCategory ");
            ListCategorie.addItem("Selectionner Catégorie");
            while(result.next()){
                ListCategorie.addItem(result.getString("name"));
            } 
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    
            
        ListCategorie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                SelectCategorie = ListCategorie.getSelectedItem().toString();
                System.out.println(SelectCategorie);
                
                p1.remove(ListEspece);
                ListEspece = new JComboBox();
                ListEspece.setPreferredSize(new Dimension(160, 30));
                p1.add(ListEspece);
                p1.remove(ListAnalyse);
                ListAnalyse = new JComboBox();
                ListAnalyse.setPreferredSize(new Dimension(160, 30));
                p1.add(ListAnalyse);

                
                // Affiche une liste déroulante contenant les espèces présentes dans la base de donnée
                try
                {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://192.168.24.16/td2","td2","OST");
                    Statement state = con.createStatement();
                    System.out.println("2");
                    ResultSet result = state.executeQuery("select nameSpecie,specieID from specie where nameCat = \"" + SelectCategorie + "\"");
                    System.out.println("3");
                    ListEspece.addItem("Selectionner Espèce");
                    System.out.println("4");
                    //ListEspece.setSelectedItem("Selectionner Espèce");
                    while(result.next()){
                        System.out.println("#");
                        ListEspece.addItem(result.getString("nameSpecie"));
                    }           
                }
                catch (Exception ex)
                {
                    System.out.println(ex);
                }

                ListEspece.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        SelectEspece = ListEspece.getSelectedItem().toString();
                        System.out.println(SelectEspece);


                        p1.remove(ListAnalyse);
                        ListAnalyse = new JComboBox();
                        ListAnalyse.setPreferredSize(new Dimension(160, 30));
                        p1.add(ListAnalyse);
                        // Affiche une liste déroulante contenant les analyses présentes dans la base de donnée
                        try
                        {
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.24.16/td2","td2","OST");
                            Statement state = con.createStatement();
                            ResultSet result = state.executeQuery("select nameT from scrapieTest where specieID = (select specieID from specie where nameSpecie = \"" + SelectEspece + "\")");
                            //ListAnalyse.addItem("");
                            //ListAnalyse.removeAllItems();
                            ListAnalyse.addItem("Selectionner Analyse");
                            //ListAnalyse.setSelectedItem("Selectionner Analyse");

                            while(result.next()){
                                ListAnalyse.addItem(result.getString("nameT"));
                            } 
                            result = state.executeQuery("select nameT from sexingTest where specieID = (select specieID from specie where nameSpecie = \"" + SelectEspece + "\")");
                            while(result.next()){
                                ListAnalyse.addItem(result.getString("nameT"));
                            } 
                        }
                        catch (Exception ex)
                        {
                            System.out.println(ex);
                        }
                    }
                });
            }
        });

    p2 = new JPanel();
    p2.setLayout(new BoxLayout(p2, BoxLayout.LINE_AXIS));
    Envoyer = new JButton("Envoyer");
    Envoyer.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            SelectClient = ListClient.getSelectedItem().toString(); // recupère le client sectionnée dans la liste déroulante
            cat = ListCategorie.getSelectedItem().toString(); // recupère la catégorie sectionnée dans la liste déroulante
            esp = ListEspece.getSelectedItem().toString(); // recupère l'espèce sectionnée dans la liste déroulante
            ana = ListAnalyse.getSelectedItem().toString(); // recupère l'analyse sectionnée dans la liste déroulante
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
    });


    Annuler = new JButton("Annuler");
    Annuler.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            InterfaceUtilisateur secretaire = new InterfaceUtilisateur(frame,"","");
            frame.setFrame(secretaire);
        }
    });
   
    p2.add(Envoyer);
    p2.add(Annuler);
    
    JPanel global = new JPanel(); 
    global.setLayout(new BoxLayout(global, BoxLayout.PAGE_AXIS));
    global.add(titre);
    global.add(p1);

    global.add(p2);

    this.add(global);
    
    }
    
    public JPanel getPanel(){
        return this;
    }

}
