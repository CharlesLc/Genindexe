
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

/**
 *
 * @author MOoh'
 */
public class ScrapieTest extends JPanel implements ActionListener{
   
    private JTextField titre;
    private JLabel nomTest,titre2;
    private JTextField textNomTest;
    private JLabel nomEspece;
    private JTextField textNomEspece;
    private JComboBox listeEspece;
    private JLabel position;
    private JTextField textPosition;
    private JLabel valeur;
    private JTextField textValeur;
    
    private JButton bouttonEnvoyer;
    private JButton bouttonAnnuler;
    
    private Frame_mother frame;
    private JPanel p;
    private String resultSelected;
    private String monId;
    private String monNom;
    private int pos;
    private int val;
    private int id;
    
    
    public ScrapieTest (Frame_mother interfaceUti)
    {
        // Récupération de la frame
        frame = interfaceUti;
        
        // Déclaration connexion
        String messages = "";
        Connection con = null;
        PreparedStatement recherche = null;
        ResultSet resultatRecherche = null;
        
        // Titre page 
        titre = new JTextField("Création d'un Scrapie Test");
        titre.setEditable(false);
        titre.setFont(new Font("Serif", Font.BOLD, 20));
        titre2 = new JLabel("");

        
        // Formulaire
        nomTest = new JLabel("Nom du test");
        textNomTest = new JTextField(20);
        textNomTest.setText("Scrapie Test");
        
        // Faire une liste déroulante      
        nomEspece = new JLabel("Choix de l'espèce");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://192.168.24.16/td2","td2","OST");
            recherche = con.prepareStatement("select specieID, nameSpecie from specie where specieID IN( select specieID from specie where specieID NOT IN (select specieID from scrapieTest))");
            resultatRecherche = recherche.executeQuery();  
  
            listeEspece = new JComboBox();
            listeEspece.setPreferredSize(new Dimension(160, 30));        
            while ( resultatRecherche.next()) {
                listeEspece.addItem(resultatRecherche.getString("specieID") + " " + resultatRecherche.getString("nameSpecie"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }finally {
            messages=messages+"Fermeture de l'objet ResultSet.\n";
            if ( resultatRecherche != null ) {
                try {
                    resultatRecherche.close();
                } catch ( SQLException ignore ) {
                }
            }
            messages=messages+"Fermeture de l'objet Statement.\n";
            if ( recherche != null ) {
                try {
                    recherche.close();
                } catch ( SQLException ignore ) {
                }
            }
            messages=messages+"Fermeture de l'objet Connection.\n";
            if ( con != null ) {
                try {
                    con.close();
                } catch ( SQLException ignore ) {
                }
            }
        }
       
        position = new JLabel("Position");
        textPosition = new JTextField(20);
        
        valeur = new JLabel("Valeur");
        textValeur = new JTextField(20);
        
        // Rechercher
        bouttonEnvoyer = new JButton("Ajouter");
        bouttonEnvoyer.addActionListener(this);

        // ANNULER
        bouttonAnnuler = new JButton("Annuler");
        bouttonAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InterfaceUtilisateur validateur = new InterfaceUtilisateur(frame,"","");
                frame.setFrame(validateur);
            }
        });
        
        // Ajout des éléments au panel
        p = new JPanel();
        p.setLayout(new GridLayout(6,2));
        p.add(titre);
        p.add(titre2);
        p.add(nomTest);
        p.add(textNomTest);
        p.add(nomEspece);
        p.add(listeEspece);
        p.add(position);
        p.add(textPosition);
        p.add(valeur);
        p.add(textValeur);
        p.add(bouttonEnvoyer);
        p.add(bouttonAnnuler);
        this.setLayout(new GridLayout(1,1));
        
        this.add(p);
        
        
        
        
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        resultSelected = listeEspece.getSelectedItem().toString();
        String[] tabSplitResult = resultSelected.split(" ");
        monId = tabSplitResult[0]; 
        monNom = textNomTest.getText();
        id=Integer.parseInt(monId);
        pos=Integer.parseInt(textPosition.getText());
        val=Integer.parseInt(textValeur.getText());
        
        String IDrawData = "";
        

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.24.16/td2","td2","OST");
            PreparedStatement insertionRaw = con.prepareStatement("insert into rawData (position,value) values (?,?)", Statement.RETURN_GENERATED_KEYS);
            insertionRaw.setInt(1, pos);
            insertionRaw.setInt(2,val);
            int resultatRecherche = insertionRaw.executeUpdate();
            if (resultatRecherche==1)
            {
                System.out.println("If Select Raw");   
                // Recupère l'identifiant de la commande que l'on vient d'ajouter dans la base de donnée
                ResultSet resIDrawData = insertionRaw.getGeneratedKeys();
                while (resIDrawData.next()) {
                    IDrawData = resIDrawData.getString(1);
                }
                PreparedStatement insertionScrapie = con.prepareStatement("insert into scrapieTest (nameT,specieID,scrapieRawID) values (?,?,?)");
                insertionScrapie.setString(1,monNom);
                insertionScrapie.setInt(2,id);
                insertionScrapie.setString(3,IDrawData);
                int resultatInsertScrapie=insertionScrapie.executeUpdate();
                if (resultatInsertScrapie==1)
                {
                   JOptionPane.showMessageDialog(null,"Bien ajouté"); 
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Pas reussi à ajouter le Scrapie Test");
                }
            } 
            else
            {    
                    JOptionPane.showMessageDialog(null,"Pas reussi à ajouter la Raw Data");
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }
}
