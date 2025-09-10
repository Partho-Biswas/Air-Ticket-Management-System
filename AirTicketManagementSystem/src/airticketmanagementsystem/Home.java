package airticketmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener {

    public Home() {
        setLayout(new BorderLayout());
        ImageIcon originalIcon = new ImageIcon(ClassLoader.getSystemResource("airticketmanagementsystem/icons/front.png"));
        Image originalImage = originalIcon.getImage();

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int width = getWidth();
                int height = getHeight();
                g.drawImage(originalImage, 0, 0, width, height, this);
            }
        };
        backgroundPanel.setLayout(null);

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("airticketmanagementsystem/icons/logo4.jpg"));
        setIconImage(icon.getImage());

        JLabel heading = new JLabel("WELCOME TO️");
        heading.setBounds(450, 40, 1000, 220);
        heading.setForeground(Color.DARK_GRAY);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 90));
        backgroundPanel.add(heading);
        
        JLabel dash = new JLabel("----------");
        dash.setBounds(1160, 40, 1000, 800);
        dash.setForeground(new Color(100, 33, 20));
        dash.setFont(new Font("Tahoma", Font.ITALIC | Font.BOLD, 100));
        backgroundPanel.add(dash);
        
        JLabel heading2 = new JLabel(" MONEY");
        heading2.setBounds(1150, 40, 1000, 1000);
        heading2.setForeground(new Color(255, 45, 75));
        heading2.setFont(new Font("Tahoma", Font.ITALIC | Font.BOLD, 100));
        backgroundPanel.add(heading2);
        
        JLabel cr1 = new JLabel("Partho Biswas");
        cr1.setHorizontalAlignment(JLabel.CENTER);
        cr1.setBounds(1100, 350, 1000, 1000);
        cr1.setForeground(new Color(17, 214, 241));
        cr1.setFont(new Font("Tahoma", Font.PLAIN | Font.BOLD, 32));
        backgroundPanel.add(cr1);
        
        JLabel cr2 = new JLabel("Apurbo Chandra Paul");
        cr2.setHorizontalAlignment(JLabel.CENTER);
        cr2.setBounds(1200, 380, 1000, 1000);
        cr2.setForeground(new Color(68, 7, 14));
        cr2.setFont(new Font("Tahoma", Font.PLAIN | Font.BOLD, 30));
        backgroundPanel.add(cr2);
        
        JLabel cr3 = new JLabel("Ripon Al Mamun");
        cr3.setHorizontalAlignment(JLabel.CENTER);
        cr3.setBounds(1220, 410, 1000, 1000);
        cr3.setForeground(new Color(230, 11, 12));
        cr3.setFont(new Font("Tahoma", Font.PLAIN | Font.BOLD, 30));
        backgroundPanel.add(cr3);

        add(backgroundPanel, BorderLayout.CENTER);

        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);
        customizeMenuBar(menubar);

        // Add all menu items directly to the menu bar
        JMenuItem flightDetails = createMenuButton("Flight Details");
        flightDetails.addActionListener(this);
        menubar.add(flightDetails);

        JMenuItem customerDetails = createMenuButton("Add Customer");
        customerDetails.addActionListener(this);
        menubar.add(customerDetails);

        JMenuItem bookFlight = createMenuButton("Book Flight");
        bookFlight.addActionListener(this);
        menubar.add(bookFlight);

        // NEW: Add Boarding Pass option
        JMenuItem boardingPass = createMenuButton("Boarding Pass");
        boardingPass.addActionListener(this);
        menubar.add(boardingPass);

        JMenuItem journeyDetails = createMenuButton("Journey Details");
        journeyDetails.addActionListener(this);
        menubar.add(journeyDetails);

        JMenuItem ticketCancellation = createMenuButton("Cancel Ticket");
        ticketCancellation.addActionListener(this);
        menubar.add(ticketCancellation);

        JMenuItem customerList = createMenuButton("Customer List");
        customerList.addActionListener(this);
        menubar.add(customerList);

        JMenuItem passengerList = createMenuButton("Passenger List");
        passengerList.addActionListener(this);
        menubar.add(passengerList);

        // Add glue to push the exit button to the right
        menubar.add(Box.createHorizontalGlue());

        // Create exit button with proper sizing to match menu bar
        JButton exitButton = createMenuStyleExitButton();
        exitButton.addActionListener(this);

        // Add the exit button directly to the menu bar
        menubar.add(exitButton);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    private JMenuItem createMenuButton(String text) {
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.setFont(new Font("Tahoma", Font.BOLD, 14));
        menuItem.setBackground(new Color(60, 63, 65));
        menuItem.setForeground(Color.WHITE);
        menuItem.setBorder(BorderFactory.createEmptyBorder(12, 15, 12, 15));

        // Add hover effect
        menuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuItem.setBackground(new Color(75, 110, 175));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuItem.setBackground(new Color(60, 63, 65));
            }
        });

        return menuItem;
    }

    private JButton createMenuStyleExitButton() {
        JButton exitButton = new JButton("EXIT");

        exitButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        exitButton.setBackground(new Color(220, 80, 70));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);

        exitButton.setPreferredSize(new Dimension(100, 40));
        exitButton.setMaximumSize(new Dimension(100, 40));
        exitButton.setMinimumSize(new Dimension(100, 40));

        exitButton.setMargin(new Insets(8, 15, 8, 15));

        exitButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 60, 50), 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));

        // Add hover effects
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitButton.setBackground(new Color(200, 70, 60));
                exitButton.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(160, 50, 40), 2),
                        BorderFactory.createEmptyBorder(5, 15, 5, 15)
                ));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitButton.setBackground(new Color(220, 80, 70));
                exitButton.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(180, 60, 50), 2),
                        BorderFactory.createEmptyBorder(5, 15, 5, 15)
                ));
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                exitButton.setBackground(new Color(180, 60, 50));
            }
        });

        return exitButton;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String text = ae.getActionCommand();

        if (text.equals("Add Customer")) {
            new AddCustomer();
        } else if (text.equals("Flight Details")) {
            new FlightInfo();
        } else if (text.equals("Book Flight")) {
            new BookFlight();
        } else if (text.equals("Boarding Pass")) {
            new BoardingPass(); // NEW: Open boarding pass
        } else if (text.equals("Journey Details")) {
            new JourneyDetails();
        } else if (text.equals("Cancel Ticket")) {
            new Cancel();
        } else if (text.equals("Customer List")) {
            new CustomerList();
        } else if (text.equals("Passenger List")) {
            new PassengerList();
        } else if (text.equals("EXIT")) {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to exit?", "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    private void customizeMenuBar(JMenuBar menuBar) {
        // Set menu bar properties - increased height to 50px
        menuBar.setPreferredSize(new Dimension(getWidth(), 50));
        menuBar.setBackground(new Color(60, 63, 65));
        menuBar.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        // Apply the changes
        SwingUtilities.updateComponentTreeUI(menuBar);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Home();
    }
}
