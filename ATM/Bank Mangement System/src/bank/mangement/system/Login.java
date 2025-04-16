package bank.mangement.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*; // for actionlistener
import java.sql.*;

public class Login extends JFrame implements ActionListener  {

    JButton login , signup, clear;
     JTextField   cardTextField;
     JPasswordField pinTextField;
    Login() {
        setTitle("Automated Teller Machine");

        setLayout(null);

        // Load and resize the logo image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(70, 10, 100, 100);
        add(label);

        // Add welcome text
        JLabel text = new JLabel("Welcome to ATM");
        text.setFont(new Font("Osward", Font.BOLD, 38));
        text.setBounds(200, 40, 400, 40);
        add(text);
       
        JLabel cardno = new JLabel("Card no :");
        cardno.setFont(new Font("Raleway", Font.BOLD, 28));
        cardno.setBounds(120,150 , 400 , 30);
        add(cardno);
       
          cardTextField = new JTextField();
        cardTextField.setBounds(300, 150, 230, 30);
         cardTextField .setFont(new Font("Arial", Font.BOLD, 14));
        add(cardTextField);
       
        JLabel pin = new JLabel("Pin :");
        pin.setFont(new Font("Raleway", Font.BOLD, 28));
        pin.setBounds(120, 220 , 250, 30);
        add(pin);
       
        pinTextField = new JPasswordField();
        pinTextField.setBounds(300, 220, 230, 30);
        pinTextField .setFont(new Font("Arial", Font.BOLD, 14));

        add(pinTextField);
       
       
         login = new JButton("SIGN IN" );
        login.setBounds(300,300,100,30);
        login.addActionListener(this);
        add(login);

         clear = new JButton("CLEAR");
        clear.setBounds(430,300,100,30);
          clear.addActionListener(this);
        add(clear);
       
         signup = new JButton("SIGN UP");
        signup.setBounds(350,350 ,100 ,30);//left to right length , up to down length, button length left to right,button length up to down
        signup.addActionListener(this);
        add(signup);
        // Set background color
        getContentPane().setBackground(Color.white);

        // Set frame size, visibility, and location
        setSize(800, 500);
        setVisible(true);
        setLocation(300, 200);

       
    }
 public void actionPerformed(ActionEvent ae){
          if (ae.getSource() == clear) {
           cardTextField.setText("");
           pinTextField.setText("");
        } else if (ae.getSource() == login) {
            Conn conn = new Conn();
            String cardnumber = cardTextField.getText();
            String pinnumber = pinTextField.getText();
            String query = "select * from login where cardnumber = '" + cardnumber + "' and pinnumber = '" + pinnumber + "'";
           
            try {
               
          ResultSet rs =  conn.s.executeQuery(query);
                if(rs.next()){
                setVisible(false);
                new Transaction(pinnumber).setVisible(true);
             }  else {
                JOptionPane.showMessageDialog(null, "Incorrect Card Number or Pin");
               }
                }
                catch(Exception e){
                     System.out.println(e);  
            }
           
        } else if (ae.getSource() == signup) {
            setVisible(false);
            new SignupOne().setVisible(true);
        }
 }
    public static void main(String[] args) {
        new Login();
    }
}