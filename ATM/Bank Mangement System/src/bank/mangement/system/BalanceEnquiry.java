package bank.mangement.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class BalanceEnquiry extends JFrame implements ActionListener {

    
    JButton b1;
    JLabel text;
    String pinnumber;

    BalanceEnquiry(String pinnumber) {
        this.pinnumber = pinnumber;

        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 800, 800);
        
        add(image);
        
        b1 = new JButton("BACK");
        b1.setBounds(310,475,150,25);
        b1.addActionListener(this);
        image.add(b1);
        
        int balance = 0;
        Conn c1 = new Conn();
        try {
           
            ResultSet rs = c1.s.executeQuery("select * from bank where pin = '" + pinnumber + "'");
            while (rs.next()) {
                // Ensure these column names match your database table structure
               if(rs.getString("type").equals("Deposit")){
                   balance += Integer.parseInt(rs.getString("amount"));
                }else {
                   balance -= Integer.parseInt(rs.getString("amount"));
               }
            }  
        } catch (Exception e) {
            System.out.println(e);
        }

        
        text = new JLabel("Your Current Account Balance is Rs " + balance);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 14));
        text.setBounds(140, 300, 400, 35);
        image.add(text);
        
       setSize(800, 800);
        setLocation(250, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transaction(pinnumber).setVisible(true);
    }

    public static void main(String[] args) {
        
        new BalanceEnquiry("");
    }
}