/*
 * 
 */
package genindex;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Frame_mother 
        extends JFrame
{
    private JFrame f;

    public Frame_mother(){
        f = new JFrame ("Genindex");
        f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        
        f.pack();
        f.setVisible(true);
    }
    
    
}
