package genindex;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
/**
 *
 * @author Maëva
 */
public class SexingTest extends JPanel {
    private JLabel labTitre;
    private JButton bouEspece;
    private JTextField textProt;
    private Frame_mother frame;

    
    public SexingTest (Frame_mother interfaceUti)
    {
        frame = interfaceUti;
        
        // Titre page
        labTitre = new JLabel("Création d'un test de Sexage");
        labTitre.setFont(new Font("Serif", Font.BOLD, 20));
        
        this.add(labTitre);
}
}
