/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genindex;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Valentin
 */
public class FormulaireClient extends JPanel implements ActionListener{
    
    private JTextField textNom;
    private JLabel labelNom;
    private JTextField textVille;
    private JLabel labelVille;
    private JButton buttonEnvoyer;
    private JButton buttonAnnuler; 
    private Frame_mother frame;
    //private JPanel panelClient; 
    
    private String nom, ville;
    
    public FormulaireClient (Frame_mother interfaceUti) {
        
        frame = interfaceUti;
        // NOM
        labelNom = new JLabel("Nom"); 
        labelNom.setHorizontalAlignment(JLabel.CENTER);
        labelNom.setVerticalAlignment(JLabel.CENTER);
        textNom = new JTextField(20);
        //JScrollPane scrollPane = new JScrollPane(textNom); 
        
        // VILLE
        labelVille = new JLabel("Ville"); 
        labelVille.setHorizontalAlignment(JLabel.CENTER);
        labelVille.setVerticalAlignment(JLabel.CENTER);
        textVille = new JTextField(20);
        //JScrollPane scrollPane2 = new JScrollPane(textVille); 
        
        // ENVOYER
        buttonEnvoyer = new JButton("Envoyer");
        buttonEnvoyer.addActionListener(this);
        
        // ANNULER
        buttonAnnuler = new JButton("Annuler");
        buttonAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InterfaceUtilisateur secretaire = new InterfaceUtilisateur(frame);
                frame.setFrame(secretaire);
            }
        });
                
        //panelClient = new JPanel();
        this.setLayout(new GridLayout(3,2));
        this.add(labelNom);
        this.add(textNom);
        this.add(labelVille);
        this.add(textVille);
        this.add(buttonEnvoyer);
        this.add(buttonAnnuler);
        
    }
    
    public JPanel getPanel(){
        return this;
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        //if (ae.getSource()== buttonEnvoyer)
        //{
        //   Login lo = new Login();
        //    fo.setFrame(lo);
        //   
        //}
        nom = textNom.getText();
        textNom.setText("");
        
        ville = textVille.getText();
        textVille.setText("");
        
        
        
    }
}
    
    
    
    
    
    
