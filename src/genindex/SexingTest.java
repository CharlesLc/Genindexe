package genindex;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
/**
 * @author Maëva
 */
public class SexingTest extends JPanel implements ActionListener {
    private JLabel labTitre, labChoix, labNomTest, labPositionMale, labValeurMale, labPositionFemelle, labValeurFemelle;
    private JComboBox choixEspece;
    private JTextField textNomTest, textPositionMale, textValeurMale, textPositionFemelle, textValeurFemelle;
    private Frame_mother frame;
    private JButton bouAnnuler, bouEnvoyer;
    private JPanel panTitre, panTest;
    private String resultSelected;
    private String monId;
    private String monNom;
    private int posMale, posFemelle;
    private int valMale, valFemelle;
    private int id;
    
    public SexingTest (Frame_mother interfaceUti)
    {
        frame = interfaceUti;
        
        // Titre page
        labTitre = new JLabel("Création d'un test de Sexage");
        labTitre.setFont(new Font("Serif", Font.BOLD, 20));
        
        // Formulaire
        labNomTest = new JLabel("Nom du test");
        textNomTest = new JTextField(20);
        
        labPositionMale = new JLabel("Position");
        textPositionMale = new JTextField(20);
        labValeurMale = new JLabel("Valeur");
        textValeurMale = new JTextField(20);
        
        labPositionFemelle = new JLabel("Position");
        textPositionFemelle = new JTextField(20);
        labValeurFemelle = new JLabel("Valeur");
        textValeurFemelle = new JTextField(20);
               
        // Déclaration connexion
        String messages = "";
        Connection con = null;
        PreparedStatement recherche = null;
        ResultSet resultatRecherche = null;
        
        //Choix de l'espèce
        labChoix= new JLabel("Choix de l'espèce");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://192.168.24.16/td2","td2","OST");
            recherche = con.prepareStatement("select specieID, nameSpecie from specie where specieID IN( select specieID from specie where specieID NOT IN (select specieID from sexingTest))");
            resultatRecherche = recherche.executeQuery();  
  
            choixEspece = new JComboBox();
            choixEspece.setPreferredSize(new Dimension(160, 30));        
            while ( resultatRecherche.next()) {
                choixEspece.addItem(resultatRecherche.getString("specieID") + " " + resultatRecherche.getString("nameSpecie"));
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
             
        
        // Envoyer
        bouEnvoyer = new JButton("Envoyer");
        bouEnvoyer.addActionListener(this);
        
        // Annuler
        bouAnnuler = new JButton("Annuler");
        bouAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InterfaceUtilisateur validateur = new InterfaceUtilisateur(frame,"","");
                frame.setFrame(validateur);
            }
        });
        
        //Panels
        panTest = new JPanel();
        panTest.setLayout(new GridLayout(7,2));
        panTest.add(labNomTest);
        panTest.add(textNomTest);
        panTest.add(labChoix);
        panTest.add(choixEspece);
        panTest.add(labPositionMale);
        panTest.add(textPositionMale);
        panTest.add(labValeurMale);
        panTest.add(textValeurMale);
        panTest.add(labPositionFemelle);
        panTest.add(textPositionFemelle);
        panTest.add(labValeurFemelle);
        panTest.add(textValeurFemelle);
        panTest.add(bouEnvoyer);
        panTest.add(bouAnnuler);
        this.setLayout(new GridLayout(2,1));
        this.add(labTitre);
        this.add(panTest);
        
        }
    
    public JPanel getPanel(){
        return this;
    }
    
     public void actionPerformed(ActionEvent ae)
    {
        resultSelected = choixEspece.getSelectedItem().toString();
        String[] tabSplitResult = resultSelected.split(" ");
        monId = tabSplitResult[0]; 
        monNom = tabSplitResult[1];
        id=Integer.parseInt(monId);
        posMale=Integer.parseInt(textPositionMale.getText());
        posFemelle=Integer.parseInt(textPositionFemelle.getText());
        valMale=Integer.parseInt(textValeurMale.getText());
        valFemelle=Integer.parseInt(textValeurFemelle.getText());
        

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.24.16/td2","td2","OST");
            PreparedStatement insertionRaw = con.prepareStatement("insert into rawData (position,value) values (?,?)");
            insertionRaw.setInt(1, posMale);
            insertionRaw.setInt(2,valMale);
            insertionRaw.setInt(3, posFemelle);
            insertionRaw.setInt(4,valFemelle);

            int resultatRecherche = insertionRaw.executeUpdate();
            if (resultatRecherche==1)
            {
                System.out.println("If Select Raw");
                PreparedStatement rechercheId = con.prepareStatement("select rawDataID from rawData where position=? and value=?");
                rechercheId.setInt(1,posMale);
                rechercheId.setInt(2,valMale);
                rechercheId.setInt(3,posFemelle);
                rechercheId.setInt(4,valFemelle);
                ResultSet resultatrechRaw=rechercheId.executeQuery();
                if(resultatrechRaw.next())
                {
                    System.out.println("If Insert Sexing");
                    PreparedStatement insertionSexing = con.prepareStatement("insert into sexingTest (nameT,specieID,maleRawID,femaleRawID) values (?,?,?,?)");
                    insertionSexing.setString(1,monNom);
                    insertionSexing.setInt(2,id);
                    insertionSexing.setInt(3,resultatrechRaw.getInt("rawDataID"));
                    insertionSexing.setInt(4,resultatrechRaw.getInt("rawDataID"));
                    int resultatInsertSexing=insertionSexing.executeUpdate();
                    if (resultatInsertSexing==1)
                    {
                       JOptionPane.showMessageDialog(null,"Bien ajouté"); 
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Pas reussi à ajouter le Sexing Test");
                    }   
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Pas reussi à trouver la Raw Data correspondante");
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
     
