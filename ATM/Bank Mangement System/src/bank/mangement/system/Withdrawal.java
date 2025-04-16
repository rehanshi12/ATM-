package bank.mangement.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Withdrawal extends JFrame implements ActionListener {
    
    JTextField amount;
    JButton withdrawal, back;
    String pinnumber;
    
    Withdrawal(String pinnumber){
        this.pinnumber = pinnumber;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 800, 800);
        add(image);
        
        JLabel text = new JLabel("Enter the amount you want to withdrawal");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System",Font.BOLD, 16 ));
        text.setBounds(140, 240, 700, 35);
        image.add(text);
        
        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 18));
        amount.setBounds(140, 290, 290, 30);
        image.add(amount);
        
        withdrawal = new JButton("Withdrawal");
        withdrawal.setBounds(310,440,150,25);
        withdrawal.addActionListener(this);
        image.add(withdrawal);
        
        back = new JButton("Back");
        back.setBounds(310,473,150,25);
        back.addActionListener(this);
        image.add(back);
        
        setSize(800, 800);
        setLocation(250,0);
        setUndecorated(true);
        setVisible(true);
        
        
    }
    
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource()==withdrawal){
            String number = amount.getText();
            Date date = new Date();
            if(number.equals("")){
                JOptionPane.showMessageDialog(null, "Please enter the amount you want to withdrawal");
            }else{
               try{ Conn conn = new Conn();
                String query = "insert into bank values('"+pinnumber+"', '"+date+"', 'Withdrawal', '"+number+"')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Rs "+number+" Withdrawal Successfully");
                setVisible(false);
                new Transaction(pinnumber).setVisible(true);
            }catch (Exception e){
                    System.out.println(e);
                    }
        } 
        }else if(ae.getSource()==back){
            setVisible(true);
             new Transaction(pinnumber).setVisible(true);
                    }
        }
   
    public static void main (String[] args){
        new Withdrawal("");
        
    }
    
    
}