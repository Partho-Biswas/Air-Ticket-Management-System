package airticketmanagementsystem;

import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.util.List;

public class FlightInfo extends JFrame {
    
    public FlightInfo() {
        setTitle("Available Flight Information");
        getContentPane().setBackground(new Color(183, 218, 252));
        setLayout(new BorderLayout());
        
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("airticketmanagementsystem/icons/logo4.jpg"));
        setIconImage(icon.getImage());
        
        JLabel heading = new JLabel("Flight Informations", SwingConstants.CENTER);
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setForeground(Color.DARK_GRAY);
        heading.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(heading, BorderLayout.NORTH);
        
        // Create table with column names
        String[] columnNames = {"Flight Name", "Source", "Destination", "Flight Code", "Ticket Fare"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        
        // Set table font and row height
        table.setFont(new Font("Arial", Font.PLAIN, 16)); // bigger font for content
        table.setRowHeight(28); // increase row height for readability
        
        // Customize header
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("Arial", Font.BOLD, 16)); // bold headers
        tableHeader.setBackground(new Color(200, 200, 200)); // light gray header
        tableHeader.setForeground(Color.BLACK);
        
        try {
            List<String> flights = FileHandler.readData("flights.txt");
            
            for (String flight : flights) {
                String[] fields = flight.split(",");
                if (fields.length >= 5) {
                    model.addRow(new Object[]{fields[0], fields[1], fields[2], fields[3], fields[4]});
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading flight information");
        }
        
        JScrollPane jsp = new JScrollPane(table);
        Border b = BorderFactory.createLineBorder(Color.BLACK);
        jsp.setBorder(b);
        add(jsp, BorderLayout.CENTER);
        
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FlightInfo();
    }
}
