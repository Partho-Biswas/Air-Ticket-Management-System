package airticketmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class BoardingPass extends JFrame implements ActionListener{
    
    JTextField tfpnr;
    JLabel tfname, tfnationality, lblsrc, lbldest, labelfname, labelfcode, labeldate, labelTicketFare;
    JButton fetchButton;
    
    public BoardingPass() {
        getContentPane().setBackground(Color.WHITE);
        getContentPane().setBackground(new Color(183, 218, 252));
        setLayout(null);
        
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("airticketmanagementsystem/icons/logo4.jpg"));
        setIconImage(icon.getImage());
        
        JLabel heading = new JLabel("Boarding Pass");
        heading.setBounds(380, 10, 450, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        add(heading);
        
        JLabel lblaadhar = new JLabel("PNR DETAILS: ");
        lblaadhar.setBounds(60, 100, 150, 25);
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblaadhar);
        
        tfpnr = new JTextField();
        tfpnr.setBounds(210, 100, 150, 25);
        add(tfpnr);
        
        fetchButton = new JButton("Enter");
        fetchButton.setBackground(Color.BLACK);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setBounds(380, 100, 120, 25);
        fetchButton.addActionListener(this);
        add(fetchButton);
        
        JLabel lblname = new JLabel("NAME: ");
        lblname.setBounds(60, 140, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(lblname);
        
        tfname = new JLabel();
        tfname.setBounds(210, 140, 150, 25);
        tfname.setFont(new Font("Tahoma", Font.BOLD, 19));
        add(tfname);
        
        JLabel lblnationality = new JLabel("NATIONALITY:");
        lblnationality.setBounds(60, 180, 150, 25);
        lblnationality.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(lblnationality);
        
        tfnationality = new JLabel();
        tfnationality.setBounds(210, 180, 150, 25);
        tfnationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(tfnationality);
        
        JLabel lbladdress = new JLabel("SOURCE:");
        lbladdress.setBounds(60, 260, 150, 25);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(lbladdress);
        
        lblsrc = new JLabel();
        lblsrc.setBounds(210, 260, 150, 25);
        lblsrc.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblsrc);
        
        JLabel lblgender = new JLabel("DESTINATION:");
        lblgender.setBounds(430, 260, 150, 25);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(lblgender);
        
        lbldest = new JLabel();
        lbldest.setBounds(550, 260, 150, 25);
        lbldest.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbldest);
        
        JLabel lblfname = new JLabel("FLIGHT NAME:");
        lblfname.setBounds(60, 220, 150, 25);
        lblfname.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(lblfname);
        
        labelfname = new JLabel();
        labelfname.setBounds(210, 220, 200, 25);
        labelfname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(labelfname);
        
        JLabel lblTicketFare = new JLabel("TICKET FARE(TK ) :");
        lblTicketFare.setBounds(60, 300, 150, 25);
        lblTicketFare.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(lblTicketFare);
        
        labelTicketFare = new JLabel();
        labelTicketFare.setBounds(200, 300, 150, 25);
        labelTicketFare.setForeground(Color.red);
        labelTicketFare.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelTicketFare);
        
        JLabel lblfcode = new JLabel("FLIGHT CODE:");
        lblfcode.setBounds(430, 220, 150, 25);
        lblfcode.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(lblfcode);
        
        labelfcode = new JLabel();
        labelfcode.setBounds(550, 220, 150, 25);
        labelfcode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(labelfcode);
        
        JLabel lbldate = new JLabel("DATE:");
        lbldate.setBounds(430, 300, 150, 25);
        lbldate.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(lbldate);
        
        labeldate = new JLabel();
        labeldate.setBounds(550, 300, 150, 25);
        labeldate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(labeldate);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airticketmanagementsystem/icons/usb.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 150, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(i2);
        JLabel lblimage = new JLabel(image);
        lblimage.setBounds(650, 0, 300, 400);
        add(lblimage);
        
        setSize(1000, 450);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String pnr = tfpnr.getText();

        try {
            String record = FileHandler.findRecord("reservations.txt", pnr, 0);
            
            if (record != null) {
                String[] fields = record.split(",");
                tfname.setText(fields[3]); 
                tfnationality.setText(fields[4]); 
                lblsrc.setText(fields[8]); 
                lbldest.setText(fields[9]);  
                labelfname.setText(fields[5]);  
                labelTicketFare.setText(fields[7]);  
                labelfcode.setText(fields[6]);  
                labeldate.setText(fields[10]); 
            } else {
                JOptionPane.showMessageDialog(null, "Please enter correct PNR");                
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching reservation details");
        }
    }

    public static void main(String[] args) {
        new BoardingPass();
    }
}