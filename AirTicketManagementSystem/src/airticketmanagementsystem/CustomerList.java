package airticketmanagementsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CustomerList extends JFrame {
    
    public CustomerList() {
        setTitle("Customer List - Flight Booking System");
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("airticketmanagementsystem/icons/logo4.jpg"));
        setIconImage(icon.getImage());
        
        JLabel heading = new JLabel("CUSTOMERS WITH BOOKED FLIGHTS", SwingConstants.CENTER);
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setForeground(Color.DARK_GRAY);
        heading.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(heading, BorderLayout.NORTH);
        
        // Create table with column names
        String[] columnNames = {"Name", "NID", "PNR-Number" , "Phone", "Nationality", "Flight", "Ticket Fare", "Source", "Destination", "Date"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        
        // Style the table
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(60, 63, 65));
        table.getTableHeader().setForeground(Color.WHITE);
        
        try {
            // Read all reservations
            List<String> reservations = FileHandler.readData("reservations.txt");
            
            for (String reservation : reservations) {
                String[] reservationFields = reservation.split(",");
                if (reservationFields.length >= 10) {
                    String pnr = reservationFields[0];
                    String nid = reservationFields[2];
                    String name = reservationFields[3];
                    String nationality = reservationFields[4];
                    String flightName = reservationFields[5];
                    String TicketFare = reservationFields[7];
                    String source = reservationFields[8];
                    String destination = reservationFields[9];
                    String date = reservationFields[10];
                    
                    // Find customer details using nid
                    String customerRecord = FileHandler.findRecord("passengers.txt", nid, 4);
                    if (customerRecord != null) {
                        String[] customerFields = customerRecord.split(",");
                        if (customerFields.length >= 6) {
                            String phone = customerFields[2];
                            
                            // Add row to table
                            model.addRow(new Object[]{
                                name, nid, pnr, phone, nationality, 
                                flightName, TicketFare, source, destination, date
                            });
                        }
                    }
                    
                }
            }
            
            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "No customers with booked flights found.");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading customer data: " + e.getMessage());
        }
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        add(scrollPane, BorderLayout.CENTER);
        
        // Add buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        // Refresh button
        JButton refreshButton = new JButton("Refresh Data");
        refreshButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        refreshButton.setBackground(new Color(60, 63, 65));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.addActionListener(e -> {
            dispose();
            new CustomerList();
        });
        
        // Close button
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        closeButton.setBackground(new Color(220, 80, 70));
        closeButton.setForeground(Color.WHITE);
        closeButton.addActionListener(e -> dispose());
        
        buttonPanel.add(refreshButton);
        buttonPanel.add(Box.createHorizontalStrut(10)); // Add space between buttons
        buttonPanel.add(closeButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
        
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new CustomerList();
    }
}