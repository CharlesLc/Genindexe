package genindex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login 
        extends JPanel
        implements ActionListener
{
    private JLabel l1, l2, l3;
    private JTextField tf1;
    private JButton btn1;
    private JPasswordField pass1;
    private JLabel l, l0;
    private Frame_mother frame;
    private InterfaceUtilisateur IntUti;
    private String str1,str2;
 
    public Login (Frame_mother h){
      
        frame=h;
        l1 = new JLabel("Formulaire de connexion");
        l1.setForeground(Color.blue);
        l1.setFont(new Font("Serif", Font.BOLD, 20));
        l2 = new JLabel("Login :");
        l3 = new JLabel("Password :");
        tf1 = new JTextField();
        pass1 = new JPasswordField();
        btn1 = new JButton("Terminer");
        l = new JLabel();
        l0 = new JLabel("Connexion réussie");
        
        JPanel p1=new JPanel();
        p1.add(l1);
        p1.setLayout(new GridLayout(1,1));
        JPanel p2=new JPanel();
        p2.add(l2);
        p2.add(tf1);
        p2.setLayout(new GridLayout(2,1));
        JPanel p3=new JPanel();
        p3.add(l3);
        p3.add(pass1);
        p3.setLayout(new GridLayout(2,1));
        JPanel p4=new JPanel();
        p4.add(btn1);
        p4.setLayout(new GridLayout(1,1));
        
        
        this.setLayout(new GridLayout(4,1));
        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.add(p4);

        btn1.addActionListener(this);
    }
    
        public void actionPerformed(ActionEvent e){
        showData();
        }
        
    public void showData(){
        JFrame f1 = new JFrame();

        str1 = tf1.getText();
        char[] p = pass1.getPassword();
        str2 = new String(p);
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.24.16/td2","td2","OST");
            PreparedStatement ps = con.prepareStatement("select jobid from user where login=? and passwd=?");
            ps.setString(1, str1);
            ps.setString(2, str2);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                IntUti=new InterfaceUtilisateur(frame,str1,str2);
                frame.setFrameLogin(IntUti,this);
            } else
            {
                JOptionPane.showMessageDialog(null,
                   "Login ou mot de passe incorrecte.. Recommencez s'il vous plaît.");
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }
    public String retourneLogin()
    {
        return (str1);
    }
    public String retournePasswd()
    {
        return (str2);
    }

}