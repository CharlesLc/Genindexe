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
    //private FormulaireClient p;
    private Login l;
   // private JPanel j;
    private JPanel h;

    public Frame_mother(){
        f = new JFrame ("Genindex");
        f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        
        h = new InterfaceUtilisateur(this);
        
        f.add(h);
        //Login login = new Login(f);
        
        f.pack();
        f.setVisible(true);
    }
    
    public void setFrame (JPanel newPan)
    {
        f.remove(h);
        h = newPan;
        f.add(h);
        
    }
    
    
}
