package genindex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login 
        extends JPanel
        //implements ActionListener
{
    private JLabel l1, l2, l3;
    private JTextField tf1;
    private JButton btn1;
    private JPasswordField p1;
    private JLabel l, l0;
 
    public Login (){
        /*
        setTitle("Login Form in Windows Form");
        setVisible(true);
        setSize(800, 800);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        */
        l1 = new JLabel("Login Form in Windows Form:");
        l1.setForeground(Color.blue);
        l1.setFont(new Font("Serif", Font.BOLD, 20));
        l2 = new JLabel("Enter Email:");
        l3 = new JLabel("Enter Password:");
        tf1 = new JTextField();
        p1 = new JPasswordField();
        btn1 = new JButton("Submit");
        l = new JLabel();
        l0 = new JLabel("you are succefully logged in..");
        l1.setBounds(100, 30, 400, 30);
        l2.setBounds(80, 70, 200, 30);
        l3.setBounds(80, 110, 200, 30);
        tf1.setBounds(300, 70, 200, 30);
        p1.setBounds(300, 110, 200, 30);
        btn1.setBounds(150, 160, 100, 30);

        this.add(l1);
        this.add(l2);
        this.add(tf1);
        this.add(l3);
        this.add(p1);
        this.add(btn1);

        //btn1.addActionListener(this);
    }
    /*
        public void actionPerformed(ActionEvent e){
        showData();
        }
     */   
    public void showData(){
        JFrame f1 = new JFrame();

        String str1 = tf1.getText();
        char[] p = p1.getPassword();
        String str2 = new String(p);
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdatabase","root","password");
            PreparedStatement ps = con.prepareStatement("select name from reg where email=? and pass=?");
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