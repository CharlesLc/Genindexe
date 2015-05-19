package genindex;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
/**
 *
 * @author Charles
 */
public class FormulaireEspece extends JPanel implements ActionListener {
    private JTextField txtSpecie;
    private JLabel labelSpecie,title, labelChoixCat;
    private JButton buttonEnvoyer;
    private JButton buttonAnnuler; 
    private Frame_mother frame;
    private JPanel p1,p2;
    private JComboBox choixCat;
    
    private String specie, cat;
    
    public FormulaireEspece (Frame_mother interfaceUti) {
        frame = interfaceUti;
        
        // Titre page
        title = new JLabel("Ajout d'une espèce");
        title.setFont(new Font("Serif", Font.BOLD, 20));
        
        // Nom d'espèce
        labelSpecie = new JLabel("Nom d'espèce : "); 
        labelSpecie.setHorizontalAlignment(JLabel.CENTER);
        labelSpecie.setVerticalAlignment(JLabel.CENTER);
        txtSpecie = new JTextField("");
        txtSpecie.setBounds(300, 70, 200, 30);
        
        // Choix de catégorie
        labelChoixCat= new JLabel("Choisir une catégorie : ");
        choixCat = new JComboBox();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.24.16/td2", "td2", "OST");
            Statement state = con.createStatement();
            ResultSet result = state.executeQuery("SELECT name FROM specieCategory");
            while (result.next()) {
                choixCat.addItem(result.getString("name"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        // Envoyer
        buttonEnvoyer = new JButton("Envoyer");
        buttonEnvoyer.addActionListener(this);
        
        // Annuler
        buttonAnnuler = new JButton("Annuler");
        buttonAnnuler.addActionListener(this);
        
        // Construction du Panel
        p1 = new JPanel();
        p1.add(title);
        p2 = new JPanel();
        p2.setLayout(new GridLayout(3,2));
        p2.add(labelSpecie);
        p2.add(txtSpecie);
        p2.add(labelChoixCat);
        p2.add(choixCat);
        p2.add(buttonEnvoyer);
        p2.add(buttonAnnuler);
        
        this.setLayout(new GridLayout(2,2));
        this.add(p1);
        this.add(p2);
    }
    
    public JPanel getPanel(){
        return this;
    }
    
    public void actionPerformed(ActionEvent ae) {
        String specie = "";

        // Récupère la saisie de l'utilisateur
        specie = txtSpecie.getText();
        cat = choixCat.getSelectedItem().toString();

        if (ae.getSource() == buttonEnvoyer) {
            if (specie.equals("")== false) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://192.168.24.16/td2", "td2", "OST");
                    PreparedStatement recherche = con.prepareStatement("select nameSpecie from specie where nameSpecie=?");
                    recherche.setString(1, specie);
                    ResultSet resultatRecherche = recherche.executeQuery();
                    if (resultatRecherche.next()) {
                        // Déjà présent
                        JOptionPane.showMessageDialog(this, "L'espèce que vous essayez de créer existe déjà dans une catégorie", "Espèce existante", JOptionPane.ERROR_MESSAGE);

                    } else {
                        PreparedStatement ajout = con.prepareStatement("insert into specie(nameSpecie,nameCat) values(?,?)");
                        ajout.setString(1, specie);
                        ajout.setString(2, cat);
                        int resultatAjout = ajout.executeUpdate();
                        if (resultatAjout == 1) {
                            // Ajout réussi
                            txtSpecie.setText("");
                            JOptionPane.showMessageDialog(null, "Ajout réussi");
                        } else {
                            // Ajout non réussi
                            JOptionPane.showMessageDialog(null, "Ajout non réussi");
                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }else {
                JOptionPane.showMessageDialog(this, "Vous n'avez rien saisi. Veuillez réessayer", "Champ vide", JOptionPane.ERROR_MESSAGE);
            } 
        } else if (ae.getSource() == buttonAnnuler) {
            InterfaceUtilisateur secretaire = new InterfaceUtilisateur(frame);
            frame.setFrame(secretaire);
        }
    }
}
