package bank.mangement.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener{
    
    JButton deposit, cashwithdrawal, fastcash, ministatement, pinchange, balanceEnquiry, exit;
    String pinnumber;
    FastCash(String pinnumber){
        this.pinnumber = pinnumber;
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,800,800);
        add(image);
        
        JLabel text = new JLabel("Select Withdrawal Amount");
        text.setBounds(165, 240, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);
        
        deposit = new JButton("Rs 100");
        deposit.setBounds(110,370,150,25);
        deposit.addActionListener(this);
        image.add(deposit);
        
        cashwithdrawal = new JButton("Rs 500");
        cashwithdrawal.setBounds(310,370,150,25);
        cashwithdrawal.addActionListener(this);
        image.add(cashwithdrawal);
        
        fastcash = new JButton("Rs 1000");
        fastcash.setBounds(110,405,150,25);
        fastcash.addActionListener(this);
        image.add(fastcash);
        
        ministatement = new JButton("Rs 2000");
        ministatement.setBounds(310,405,150,25);
        ministatement.addActionListener(this);
        image.add(ministatement);
        
        pinchange = new JButton("Rs 5000");
        pinchange.setBounds(110,440,150,25);
        pinchange.addActionListener(this);
        image.add(pinchange);
        
        balanceEnquiry = new JButton("Rs 10000");
        balanceEnquiry.setBounds(310,440,150,25);
        balanceEnquiry.addActionListener(this);
        image.add(balanceEnquiry);
        
        exit = new JButton("BACK");
        exit.setBounds(310,475,150,25);
        exit.addActionListener(this);
        image.add(exit);
        
        setSize(800, 800);
        setLocation(250,0);
        setUndecorated(true);
        setVisible(true);
        
        
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == exit){
            setVisible(false);
            new Transaction(pinnumber).setVisible(true);
        }else {
           String amount = ((JButton)ae.getSource()).getText().substring(3);
           Conn c = new Conn();
           try{
               ResultSet rs = c.s.executeQuery("Select * from bank where pin =  '"+pinnumber+"'");
               int balance = 0;
               while(rs.next()) {
                   if (rs.getString("type").equals("Deposit")){
                       balance += Integer.parseInt(rs.getString("amount"));
                   } else{
                       balance -= Integer.parseInt(rs.getString("amount"));
                   }
                       
                       
               }
           
               if (ae.getSource() != exit && balance < Integer.parseInt(amount)){
                   JOptionPane.showMessageDialog(null, "Insufficient Balance");
                   return;
               }
               
               Date date = new Date();
               String query = "insert into bank values('"+pinnumber+"' , '"+date+"' , 'withdraw' , '"+amount+"')";
                  c.s.executeUpdate(query);
                  JOptionPane.showMessageDialog(null, "Rs "+amount+ " Debited Successfully");
                  
                  setVisible(false);
                  new Transaction(pinnumber).setVisible(true);
                  
               
           } catch(Exception e){
               System.out.println(e);
        
           }
         
         }
        
    }
    public static void main(String[] args){
        new FastCash("");
}
}