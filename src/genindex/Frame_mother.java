/*
 * 
 */
package genindex;

/**
 *
 * @author Charles
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Frame_mother 
        extends JFrame
{
    private JFrame f;
    //private FormulaireClient p;
    private Login l;
    private JPanel j;

    public Frame_mother(){
        f = new JFrame ("Genindex");
        f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        
        j = new FormulaireClient(this);
        
        f.add(j);
        //Login login = new Login(f);
        
        f.pack();
        f.setVisible(true);
    }
    
    public void setFrame (JPanel newPan)
    {
        f.remove(j);
        j = newPan;
        f.add(j);
        
    }
    
    
}
