package airticketmanagementsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class JourneyDetails extends JFrame implements ActionListener{
    JTable table;
    JTextField pnr;
    JButton show;
    
    public JourneyDetails() {
        
        getContentPane().setBackground(Color.WHITE);
        getContentPane().setBackground(new Color(183, 218, 252));
        setLayout(null);
        
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("airticketmanagementsystem/icons/logo4.jpg"));
        setIconImage(icon.getImage());
        
        JLabel heading = new JLabel("Journey Details", SwingConstants.CENTER);
        heading.setBounds(500, 20, 500, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        heading.setForeground(Color.DARK_GRAY);
        heading.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(heading, BorderLayout.NORTH);
        
        JLabel lblpnr = new JLabel("PNR Details: ");
        lblpnr.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblpnr.setBounds(50, 100, 150, 25);
//        lblpnr.setBounds(50, 50, 100, 25);
        add(lblpnr);
        
        pnr = new JTextField();
        pnr.setBounds(160, 100, 120, 25);
        add(pnr);
        
        show = new JButton("Show Details");
        show.setBackground(Color.BLACK);
        show.setForeground(Color.WHITE);
        show.setBounds(290, 100, 120, 25);
        show.addActionListener(this);
        add(show);
        
        table = new JTable();
        
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(5, 150, 1475, 250);
        jsp.setBackground(Color.WHITE);
        add(jsp);
        
        // Set table font and row height
        table.setFont(new Font("Arial", Font.PLAIN, 16)); // bigger font for content
        table.setRowHeight(28); // increase row height for readability
        
        // Customize header
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("Arial", Font.BOLD, 16)); // bold headers
        tableHeader.setBackground(new Color(140, 200, 200)); // light gray header
        tableHeader.setForeground(Color.BLACK);
        
        setSize(1500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        try {
            String pnrText = pnr.getText();
            String record = FileHandler.findRecord("reservations.txt", pnrText, 0);
            
            if (record == null) {
                JOptionPane.showMessageDialog(null, "No Information Found");
                return;
            }
            
            String[] columnNames = {"PNR", "Ticket No", "NID", "Name", "Nationality", 
                                   "Flight Name", "Flight Code", "Ticket Fare", "Source", "Destination", "Date"};
            String[] fields = record.split(",");
            
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            model.addRow(fields);
            table.setModel(model);
            
        } catch(Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading journey details");
        }
    }

    public static void main(String[] args) {
        new JourneyDetails();
    }
}