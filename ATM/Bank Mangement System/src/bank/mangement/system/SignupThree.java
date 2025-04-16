package bank.mangement.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SignupThree extends JFrame implements ActionListener {
    JRadioButton r1, r2, r3, r4;
    JCheckBox c1, c2, c3, c4, c5, c6, c7;
    JButton submit, cancel;
    String formno;

    SignupThree(String formno) {
        this.formno = formno;
        setLayout(null);

        JLabel l1 = new JLabel("PAGE 3 : Account Details");
        l1.setFont(new Font("Raleway", Font.BOLD, 22));
        l1.setBounds(240, 40, 400, 40);
        add(l1);

        JLabel type = new JLabel("Account Type");
        type.setFont(new Font("Raleway", Font.BOLD, 22));
        type.setBounds(100, 140, 200, 30);
        add(type);

        r1 = new JRadioButton("Saving Account");
        r1.setBounds(100, 180, 150, 20);
        r1.setBackground(Color.white);
        r1.setFont(new Font("Raleway", Font.BOLD, 16));
        add(r1);

        r2 = new JRadioButton("Fixed Deposit Account");
        r2.setBounds(350, 180, 250, 20);
        r2.setBackground(Color.white);
        r2.setFont(new Font("Raleway", Font.BOLD, 16));
        add(r2);

        r3 = new JRadioButton("Current Account");
        r3.setBounds(100, 220, 250, 20);
        r3.setBackground(Color.white);
        r3.setFont(new Font("Raleway", Font.BOLD, 16));
        add(r3);

        r4 = new JRadioButton("Recurring Deposit Account");
        r4.setBounds(350, 220, 250, 20);
        r4.setBackground(Color.white);
        r4.setFont(new Font("Raleway", Font.BOLD, 16));
        add(r4);

        ButtonGroup groupaccount = new ButtonGroup();
        groupaccount.add(r1);
        groupaccount.add(r2);
        groupaccount.add(r3);
        groupaccount.add(r4);

        JLabel card = new JLabel("Card Number");
        card.setFont(new Font("Raleway", Font.BOLD, 22));
        card.setBounds(100, 270, 200, 30);
        add(card);

        JLabel number = new JLabel("XXXX-XXXX-XXXX-4184");
        number.setFont(new Font("Raleway", Font.BOLD, 22));
        number.setBounds(330, 270, 300, 30);
        add(number);

        JLabel carddetail = new JLabel("Your 16 Digit Card Number");
        carddetail.setFont(new Font("Raleway", Font.BOLD, 12));
        carddetail.setBounds(100, 300, 300, 20);
        add(carddetail);

        JLabel pin = new JLabel("PIN:");
        pin.setFont(new Font("Raleway", Font.BOLD, 22));
        pin.setBounds(100, 350, 200, 30);
        add(pin);

        JLabel pnumber = new JLabel("XXXX");
        pnumber.setFont(new Font("Raleway", Font.BOLD, 22));
        pnumber.setBounds(330, 350, 300, 30);
        add(pnumber);

        JLabel pindetail = new JLabel("Your 4 Digit Password");
        pindetail.setFont(new Font("Raleway", Font.BOLD, 12));
        pindetail.setBounds(100, 370, 300, 30);
        add(pindetail);

        JLabel services = new JLabel("Services Required");
        services.setFont(new Font("Raleway", Font.BOLD, 22));
        services.setBounds(100, 420, 300, 30);
        add(services);

        c1 = new JCheckBox("ATM Card");
        c1.setFont(new Font("Raleway", Font.BOLD, 18));
        c1.setBounds(100, 460, 200, 30);
        c1.setBackground(Color.white);
        add(c1);

        c2 = new JCheckBox("Internet Banking");
        c2.setFont(new Font("Raleway", Font.BOLD, 18));
        c2.setBounds(350, 460, 200, 30);
        c2.setBackground(Color.white);
        add(c2);

        c3 = new JCheckBox("Mobile Banking");
        c3.setFont(new Font("Raleway", Font.BOLD, 16));
        c3.setBounds(100, 490, 200, 30);
        c3.setBackground(Color.white);
        add(c3);

        c4 = new JCheckBox("Email & SMS Alert");
        c4.setFont(new Font("Raleway", Font.BOLD, 16));
        c4.setBounds(350, 490, 200, 30);
        c4.setBackground(Color.white);
        add(c4);

        c5 = new JCheckBox("Cheque Book");
        c5.setFont(new Font("Raleway", Font.BOLD, 16));
        c5.setBounds(100, 520, 200, 30);
        c5.setBackground(Color.white);
        add(c5);

        c6 = new JCheckBox("E Statement");
        c6.setFont(new Font("Raleway", Font.BOLD, 18));
        c6.setBounds(350, 520, 200, 30);
        c6.setBackground(Color.white);
        add(c6);

        c7 = new JCheckBox("I hereby declare that the above details are correct to the best of my knowledge.");
        c7.setFont(new Font("Raleway", Font.BOLD, 15));
        c7.setBounds(100, 600, 700, 30);
        c7.setBackground(Color.white);
        add(c7);

        submit = new JButton("Submit");
        submit.setFont(new Font("Raleway", Font.BOLD, 15));
        submit.setBounds(250, 650, 120, 30);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setFont(new Font("Raleway", Font.BOLD, 15));
        cancel.setBounds(420, 650, 120, 30);
        cancel.addActionListener(this);
        add(cancel);

        setSize(850, 820);
        setVisible(true);
        setLocation(350, 0);
        getContentPane().setBackground(Color.white);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String accountType = null;
            if (r1.isSelected()) {
                accountType = "Saving Account";
            } else if (r2.isSelected()) {
                accountType = "Fixed Deposit Account";
            } else if (r3.isSelected()) {
                accountType = "Current Account";
            } else if (r4.isSelected()) {
                accountType = "Recurring Deposit Account";
            }

            Random random = new Random();
            String cardnumber = "" + Math.abs(random.nextLong() % 90000000L + 5040936000000000L);
            String pinnumber = String.format("%04d", random.nextInt(10000));

            StringBuilder facility = new StringBuilder();
            if (c1.isSelected()) {
                facility.append("ATM Card");
            }
            if (c2.isSelected()) {
                facility.append(" Internet Banking");
            }
            if (c3.isSelected()) {
                facility.append(" Mobile Banking");
            }
            if (c4.isSelected()) {
                facility.append(" Email & SMS Alert");
            }
            if (c5.isSelected()) {
                facility.append(" Cheque Book");
            }
            if (c6.isSelected()) {
                facility.append(" E Statement");
            }

            try {
                if (accountType == null) {
                    JOptionPane.showMessageDialog(null, "Account Type is required");
                } else {
                    Conn c = new Conn();
                    String query1 = "insert into signupthree values('" + formno + "', '" + accountType + "', '" + cardnumber + "', '" + pinnumber + "', '" + facility + "')";
                    String query2 = "insert into login values('" + formno + "', '" + cardnumber + "', '" + pinnumber + "')";
                    c.s.executeUpdate(query1);
                    c.s.executeUpdate(query2);
                    JOptionPane.showMessageDialog(null, "Card Number: " + cardnumber + "\nPin: " + pinnumber);

                    setVisible(false);
                new Deposit(pinnumber).setVisible(false);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
            new Login ().setVisible(true);
        }
    }

    public static void main(String args[]) {
        new SignupThree("");
    }
}
