package bank.mangement.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PinChange extends JFrame implements ActionListener {
    JPasswordField pinField, newPinField, reenterPinField;
    JButton changeButton, backButton;
    String pinnumber;

    PinChange(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("CHANGE YOUR PIN");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(210, 280, 500, 35);
        image.add(text);

        JLabel pinText = new JLabel("Current PIN:");
        pinText.setForeground(Color.WHITE);
        pinText.setFont(new Font("System", Font.BOLD, 16));
        pinText.setBounds(165, 320, 180, 25);
        image.add(pinText);

        pinField = new JPasswordField();
        pinField.setFont(new Font("Raleway", Font.BOLD, 25));
        pinField.setBounds(330, 320, 180, 25);
        image.add(pinField);

        JLabel newPinText = new JLabel("New PIN:");
        newPinText.setForeground(Color.WHITE);
        newPinText.setFont(new Font("System", Font.BOLD, 16));
        newPinText.setBounds(165, 360, 180, 25);
        image.add(newPinText);

        newPinField = new JPasswordField();
        newPinField.setFont(new Font("Raleway", Font.BOLD, 25));
        newPinField.setBounds(330, 360, 180, 25);
        image.add(newPinField);

        JLabel reenterPinText = new JLabel("Re-Enter New PIN:");
        reenterPinText.setForeground(Color.WHITE);
        reenterPinText.setFont(new Font("System", Font.BOLD, 16));
        reenterPinText.setBounds(165, 400, 180, 25);
        image.add(reenterPinText);

        reenterPinField = new JPasswordField();
        reenterPinField.setFont(new Font("Raleway", Font.BOLD, 25));
        reenterPinField.setBounds(330, 400, 180, 25);
        image.add(reenterPinField);

        changeButton = new JButton("CHANGE");
        changeButton.setBounds(355, 485, 150, 30);
        changeButton.addActionListener(this);
        image.add(changeButton);

        backButton = new JButton("BACK");
        backButton.setBounds(355, 520, 150, 30);
        backButton.addActionListener(this);
        image.add(backButton);

        setSize(800, 800);
        setLocation(300, 0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == changeButton) {
            try {
                String currentPin = new String(pinField.getPassword());
                String newPin = new String(newPinField.getPassword());
                String reenterPin = new String(reenterPinField.getPassword());

                if (!newPin.equals(reenterPin)) {
                    JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                    return;
                }

                if (newPin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter new PIN");
                    return;
                }

                if (reenterPin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please re-enter new PIN");
                    return;
                }

                Conn conn = new Conn();
                String query1 = "UPDATE bank SET pin = ? WHERE pin = ?";
                String query2 = "UPDATE login SET pinnumber = ? WHERE pinnumber = ?";
                String query3 = "UPDATE signupthree SET pinnumber = ? WHERE pinnumber = ?";

                PreparedStatement ps1 = conn.c.prepareStatement(query1);
                ps1.setString(1, reenterPin);
                ps1.setString(2, currentPin);
                ps1.executeUpdate();

                PreparedStatement ps2 = conn.c.prepareStatement(query2);
                ps2.setString(1, reenterPin);
                ps2.setString(2, currentPin);
                ps2.executeUpdate();

                PreparedStatement ps3 = conn.c.prepareStatement(query3);
                ps3.setString(1, reenterPin);
                ps3.setString(2, currentPin);
                ps3.executeUpdate();

                JOptionPane.showMessageDialog(null, "PIN changed successfully");

                setVisible(false);
                new Transaction(pinnumber).setVisible(true);

            } catch (SQLException e) {
                System.out.println("Error in SQL execution: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new Transaction(pinnumber).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new PinChange("").setVisible(true);
    }
}
