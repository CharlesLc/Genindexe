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
    //private JPanel l;
   // private JPanel j;
    private JPanel h;
    private String login,psswd;

    public Frame_mother(){
        f = new JFrame ("Genindex");
        f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        
        l = new Login(this);
        
        //h = new FormulaireEspece(this);
        
        f.add(l);

        
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
    
    public void setFrameLogin (JPanel newPan,Login logout) //JPanel ancienPan)
    {
            l = logout;
            f.remove(l);
            h=newPan;
            f.add(newPan);
            //f.validate(); // Permet de voir le nouveau panel SANS re-size de fenêtre
            f.pack(); // Permet de voir le nouveau panel AVEC re-size de fenêtre
        }
    
    public void setFrame (JPanel newPan)
    {
        if (newPan instanceof InterfaceUtilisateur)
        {
            f.remove(h);
            login=l.retourneLogin();
            psswd=l.retournePasswd();
            h=new InterfaceUtilisateur(this,login,psswd);
            f.add(h);
            f.pack(); // Permet de voir le nouveau panel AVEC re-size de fenêtre
        }
        else
        {
            f.remove(h);
            h=newPan;
            f.add(h);
            f.pack(); // Permet de voir le nouveau panel AVEC re-size de fenêtre
        }
        
    
    
    }
}
