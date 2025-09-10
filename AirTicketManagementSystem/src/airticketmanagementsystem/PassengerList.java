package airticketmanagementsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PassengerList extends JFrame {
    
    public PassengerList() {
        setTitle("Passenger List - Flight Booking System");
        getContentPane().setBackground(new Color(183, 218, 252));
        setLayout(new BorderLayout());
        
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("airticketmanagementsystem/icons/logo4.jpg"));
        setIconImage(icon.getImage());
        
        JLabel heading = new JLabel("ALL PASSENGERS LIST", SwingConstants.CENTER);
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setForeground(Color.DARK_GRAY);
        heading.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(heading, BorderLayout.NORTH);
        
        // Create table with column names
        String[] columnNames = {"Name", "NID Number", "Phone", "Nationality", "Address", "Gender"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        
        JTable table = new JTable(model);
        
        // Style the table
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(60, 63, 65));
        table.getTableHeader().setForeground(Color.WHITE);
        
        // Enable sorting
        table.setAutoCreateRowSorter(true);
        
        try {
            // Read all passengers
            List<String> passengers = FileHandler.readData("passengers.txt");
            
            for (String passenger : passengers) {
                String[] fields = passenger.split(",");
                if (fields.length >= 6) {
                    String name = fields[0];
                    String nationality = fields[1];
                    String phone = fields[2];
                    String address = fields[3];
                    String nid = fields[4];
                    String gender = fields[5];
                    
                    // Add row to table
                    model.addRow(new Object[]{
                        name, nid, phone, nationality, address, gender
                    });
                }
            }
            
            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "No passengers found in the system.");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading passenger data: " + e.getMessage());
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
            new PassengerList();
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
        new PassengerList();
    }
}