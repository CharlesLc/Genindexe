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
 
    public Login (Frame_mother h){
      
        
        l1 = new JLabel("Login Form");
        l1.setForeground(Color.blue);
        l1.setFont(new Font("Serif", Font.BOLD, 20));
        l2 = new JLabel("Enter Login :");
        l3 = new JLabel("Enter Password :");
        tf1 = new JTextField();
        pass1 = new JPasswordField();
        btn1 = new JButton("Submit");
        l = new JLabel();
        l0 = new JLabel("you are succefully logged in..");
        
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

        String str1 = tf1.getText();
        char[] p = pass1.getPassword();
        String str2 = new String(p);
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
                f1.setVisible(true);
                f1.setSize(600, 600);
                f1.setLayout(null);
                
                l0.setForeground(Color.blue);
                l0.setFont(new Font("Serif", Font.BOLD, 30));
                l.setBounds(60, 50, 400, 30);
                l0.setBounds(60, 100, 400, 40);

                f1.add(l);
                f1.add(l0);
                l.setText("Welcome " + rs.getString(1));
                l.setForeground(Color.red);
                l.setFont(new Font("Serif", Font.BOLD, 30));

            } else
            {
                JOptionPane.showMessageDialog(null,
                   "Incorrect email-Id or password..Try Again with correct detail");
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }

}