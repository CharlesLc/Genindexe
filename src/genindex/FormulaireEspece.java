package genindex;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 *
 * @author Charles
 */
public class FormulaireEspece 
        extends JPanel
        implements ActionListener
{
    private JTextField txtSpecie;
    private JLabel labelSpecie;
    private JButton buttonEnvoyer;
    private JButton buttonAnnuler; 
    private Frame_mother frame;
    private JPanel actualPanel; //Correspond au panel actuel qui sera remove quand on appuie sur un bouton qui envoie ailleurs
    
    public FormulaireEspece (Frame_mother interfaceUti) {
        actualPanel=this;
        frame = interfaceUti;
        
        // NOM D'ESPECE
        labelSpecie = new JLabel("Nom d'espèce :"); 
        labelSpecie.setHorizontalAlignment(JLabel.CENTER);
        labelSpecie.setVerticalAlignment(JLabel.CENTER);
        txtSpecie = new JTextField();
        txtSpecie.setBounds(300, 70, 200, 30);
        
        // ENVOYER
        buttonEnvoyer = new JButton("Envoyer");
        buttonEnvoyer.addActionListener(this);
        
        // ANNULER
        buttonAnnuler = new JButton("Annuler");
        buttonAnnuler.addActionListener(this);
        
        //panelClient = new JPanel();
        this.setLayout(new GridLayout(3,1));
        this.add(labelSpecie);
        this.add(txtSpecie);
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
                if (txtSpecie.getText() == "")
                    {
                        JOptionPane.showMessageDialog(this,"Vous n'avez rien saisi. Veuillez réessayer", "Champ vide", JOptionPane.ERROR_MESSAGE);
                    }
//                else if (isUnique(txtSpecie)= FALSE) //TODO: isUnique à faire
//                    {
//                        JOptionPane.showMessageDialog(this,"L'espèce que vous essayez de créer existe déjà", "Espèce existante", JOptionPane.ERROR_MESSAGE);
//                    }
                else
                    {
                        //TODO : Ajout de l'espèce à la BDD
                    }
            }
        
        else if (ae.getSource()== buttonAnnuler)
            {
                InterfaceUtilisateur secretaire = new InterfaceUtilisateur(frame);
                frame.setFrame(secretaire,actualPanel);
            }
    }
}