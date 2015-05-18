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
    
    private JTextArea textNom;
    private JLabel labelNom;
    private JTextArea textVille;
    private JLabel labelVille;
    private JButton buttonEnvoyer;
    private JButton buttonAnnuler; 
    private Frame_mother fo;
    //private JPanel panelClient; 
    
    public FormulaireClient (Frame_mother f) {
        
        fo = f;
        // NOM
        labelNom = new JLabel("Nom"); 
        labelNom.setHorizontalAlignment(JLabel.CENTER);
        labelNom.setVerticalAlignment(JLabel.CENTER);
        textNom = new JTextArea(5, 20);
        //JScrollPane scrollPane = new JScrollPane(textNom); 
        textNom.setEditable(false);
        // VILLE
        labelVille = new JLabel("Ville"); 
        labelVille.setHorizontalAlignment(JLabel.CENTER);
        labelVille.setVerticalAlignment(JLabel.CENTER);
        textVille = new JTextArea(5, 20);
        //JScrollPane scrollPane2 = new JScrollPane(textVille); 
        textVille.setEditable(false);
        // ENVOYER
        buttonEnvoyer = new JButton("Envoyer");
        buttonEnvoyer.addActionListener(this);
        // ANNULER
        buttonAnnuler = new JButton("Annuler");
        
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
        if (ae.getSource()== buttonEnvoyer)
        {
            Login lo = new Login();
            fo.setFrame(lo);
            
        }
        
    }
}
    
    
    
    
    
    
