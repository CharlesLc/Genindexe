/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genindex;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author MOoh'
 */
public class AffichageResultat extends JPanel{
    
    // Titre
    private JLabel echantillon;
    private JLabel vide;
    
    // Scrapie
    private JLabel valeur1;
    private JTextField texteValeur1;
    private JLabel position1;
    private JTextField textePosition1;
    
    // Sexing 
    private JLabel valeur2;
    private JTextField texteValeur2;
    private JLabel position2;
    private JTextField textePosition2;
    
    public AffichageResultat(int echantillonID, boolean cache)
    {
        // Déclaration du panel
        this.setLayout(new GridLayout(5,2));
        
        //Titre 
        echantillon = new JLabel("Echantillon" + echantillonID);
        vide = new JLabel("");
        
        this.add(echantillon);
        this.add(vide);
        
        // Scrapie 
        valeur1 = new JLabel("Valeur");
        texteValeur1 = new JTextField(20);      
        position1 = new JLabel("Position");
        textePosition1 = new JTextField(20);

        this.add(valeur1);
        this.add(texteValeur1);
        this.add(position1);
        this.add(textePosition1);
        
        // Sexing 
        valeur2 = new JLabel("Valeur");
        valeur2.setVisible(cache);
        texteValeur2 = new JTextField(20);  
        texteValeur2.setVisible(cache);
        position2 = new JLabel("Position");
        position2.setVisible(cache);
        textePosition2 = new JTextField(20);
        textePosition2.setVisible(cache);
        
        this.add(valeur2);
        this.add(texteValeur2);
        this.add(position2);
        this.add(textePosition2);
    }  
}
