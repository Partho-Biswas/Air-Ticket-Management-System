package airticketmanagementsystem;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;

public class BookFlight extends JFrame implements ActionListener{
    
    JTextField tfnid, dcdate;
    JLabel tfname, tfnationality, tfaddress, labelgender, labelfname, labelfcode, labelTicketFare;
    JButton bookflight, fetchButton, flight;
    Choice source, destination;
    
    public BookFlight() {
        getContentPane().setBackground(new Color(183, 218, 252));
        setLayout(null);
        
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("airticketmanagementsystem/icons/logo4.jpg"));
        setIconImage(icon.getImage());
        
        JLabel heading = new JLabel("Book Flight");
        heading.setBounds(420, 20, 500, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        heading.setForeground(Color.DARK_GRAY);
        add(heading);
        
        JLabel lblnid = new JLabel("NID:");
        lblnid.setBounds(60, 80, 150, 25);
        lblnid.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblnid);
        
        tfnid = new JTextField();
        tfnid.setBounds(280, 80, 150, 25);
        add(tfnid);
        
        fetchButton = new JButton("Fetch User");
        fetchButton.setBackground(Color.BLACK);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setBounds(440, 80, 120, 25);
        fetchButton.addActionListener(this);
        add(fetchButton);
        
        JLabel lblname = new JLabel("Name:");
        lblname.setBounds(60, 130, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblname);
        
        tfname = new JLabel();
        tfname.setBounds(280, 130, 150, 25);
        add(tfname);
        
        JLabel lblnationality = new JLabel("Nationality:");
        lblnationality.setBounds(60, 180, 150, 25);
        lblnationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblnationality);
        
        tfnationality = new JLabel();
        tfnationality.setBounds(280, 180, 150, 25);
        add(tfnationality);
        
        JLabel lbladdress = new JLabel("Address:");
        lbladdress.setBounds(60, 230, 150, 25);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbladdress);
        
        tfaddress = new JLabel();
        tfaddress.setBounds(280, 230, 150, 25);
        add(tfaddress);
        
        JLabel lblgender = new JLabel("Gender:");
        lblgender.setBounds(60, 280, 150, 25);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblgender);
        
        labelgender = new JLabel("Gender");
        labelgender.setBounds(280, 280, 150, 25);
        add(labelgender);
        
        JLabel lblsource = new JLabel("Source:");
        lblsource.setBounds(60, 330, 150, 25);
        lblsource.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblsource);
        
        source = new Choice();
        source.setBounds(280, 330, 150, 25);       
        add(source);
        
        JLabel lbldest = new JLabel("Destination:");
        lbldest.setBounds(60, 380, 150, 25);
        lbldest.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbldest);
        
        destination = new Choice();
        destination.setBounds(280, 380, 150, 25);       
        add(destination);
        
        // Load flight sources and destinations
        try {
            java.util.List<String> flights = FileHandler.readData("flights.txt");
            Set<String> sources = new HashSet<>();
            Set<String> destinations = new HashSet<>();
            
            for (String flight : flights) {
                String[] fields = flight.split(",");
                if (fields.length >= 3) {
                    sources.add(fields[1]);
                    destinations.add(fields[2]);
                }
            }
            
            for (String src : sources) {
                source.add(src);
            }
            
            for (String dest : destinations) {
                destination.add(dest);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        flight = new JButton("Fetch Flights:");
        flight.setBackground(Color.BLACK);
        flight.setForeground(Color.WHITE);
        flight.setBounds(440, 380, 120, 25);
        flight.addActionListener(this);
        add(flight);
        
        JLabel lblfname = new JLabel("Flight Name:");
        lblfname.setBounds(60, 430, 150, 25);
        lblfname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblfname);
        
        labelfname = new JLabel();
        labelfname.setBounds(280, 430, 150, 25);
        add(labelfname);
        
        JLabel lblfcode = new JLabel("Flight Code:");
        lblfcode.setBounds(60, 480, 150, 25);
        lblfcode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblfcode);
        
        labelfcode = new JLabel();
        labelfcode.setBounds(280, 480, 150, 25);
        add(labelfcode);
        
        JLabel lblTicketFare = new JLabel("Ticket Fare: ");
        lblTicketFare.setBounds(60, 530, 150, 25);
        lblTicketFare.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblTicketFare);
        
        labelTicketFare = new JLabel();
        labelTicketFare.setBounds(280, 530, 150, 25);
        add(labelTicketFare);      
        
        JLabel lbldate = new JLabel("Date of Travel (dd-MM-yyyy):");
        lbldate.setBounds(60, 580, 250, 25);
        lbldate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbldate);
        
        dcdate = new JTextField();
        dcdate.setBounds(280, 580, 150, 25);
        add(dcdate);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airticketmanagementsystem/icons/details.png"));
        Image i2 = i1.getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(i2);
        JLabel lblimage = new JLabel(image);
        lblimage.setBounds(550, 80, 500, 550);
        add(lblimage);
        
        bookflight = new JButton("Book Flight");
        bookflight.setBackground(new Color(50, 222, 110));
        bookflight.setForeground(Color.DARK_GRAY);
        bookflight.setFont(new Font("Tahoma", Font.BOLD, 20));
        bookflight.setBounds(500, 580, 150, 40);
        bookflight.addActionListener(this);
        add(bookflight);
        
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    // Helper method to validate date format
    private boolean isValidDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fetchButton) {
            String nid = tfnid.getText();
            
            try {
                String record = FileHandler.findRecord("passengers.txt", nid, 4);
                
                if (record != null) {
                    String[] fields = record.split(",");
                    tfname.setText(fields[0]); 
                    tfnationality.setText(fields[1]); 
                    tfaddress.setText(fields[3]);
                    labelgender.setText(fields[5]);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter correct NID Number");                
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error fetching passenger details");
            }
        } else if (ae.getSource() == flight) {
            String src = source.getSelectedItem();
            String dest = destination.getSelectedItem();
            try {
                java.util.List<String> flights = FileHandler.readData("flights.txt");
                boolean found = false;
                
                for (String flight : flights) {
                    String[] fields = flight.split(",");
                    if (fields.length >= 4 && fields[1].equals(src) && fields[2].equals(dest)) {
                        labelfname.setText(fields[0]); 
                        labelfcode.setText(fields[3]); 
                        labelTicketFare.setText(fields[4]); 
                        found = true;
                        break;
                    }
                }
                
                if (!found) {
                    JOptionPane.showMessageDialog(null, "No Flights Found");                
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error fetching flight details");
            }
        } else {
            Random random = new Random();
            
            String nid = tfnid.getText();
            String name = tfname.getText(); 
            String nationality = tfnationality.getText();
            String flightname = labelfname.getText(); 
            String flightcode = labelfcode.getText();
            String ticketFare = labelTicketFare.getText();
            String src = source.getSelectedItem(); 
            String des = destination.getSelectedItem();
            String ddate = dcdate.getText();
            
            // Validate date format
            if (!isValidDate(ddate)) {
                JOptionPane.showMessageDialog(null, "Please enter date in dd-MM-yyyy format");
                return;
            }
            
            // Validate inputs
            if (nid.isEmpty() || name.isEmpty() || flightname.isEmpty() || 
                flightcode.isEmpty() || ticketFare.isEmpty() || ddate.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please complete all booking details");
                return;
            }
            
            try {
                String pnr = "PNR-" + random.nextInt(1000000);
                String ticketNo = "TIC-" + random.nextInt(10000);
                
                // Format: PNR,TicketNo,NID,Name,Nationality,FlightName,FlightCode,Source,Destination,Date
                String data = pnr + "," + ticketNo + "," + nid + "," + name + "," + 
                             nationality + "," + flightname + "," + flightcode + "," + ticketFare + "," +
                             src + "," + des + "," + ddate;
                
                FileHandler.saveData("reservations.txt", data);
                
                JOptionPane.showMessageDialog(null, "Ticket Booked Successfully. PNR: " + pnr);
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error booking flight");
            }
        }
    }

    public static void main(String[] args) {
        new BookFlight();
    }
}