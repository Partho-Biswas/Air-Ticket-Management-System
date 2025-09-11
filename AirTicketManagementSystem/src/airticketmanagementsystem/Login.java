package airticketmanagementsystem;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public final class Login extends JFrame implements ActionListener {
    JButton submit, reset, close;
    JTextField tfusername;
    JPasswordField tfpassword;
    JCheckBox showPassword;
    JLabel forgotPassword; // ✅ Forgot password label

    public Login() {
        setTitle("Air Ticket Management System");
        setLayout(null);

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("airticketmanagementsystem/icons/logo4.jpg"));
        setIconImage(icon.getImage());

        // Background GIF
        ImageIcon bgIcon = new ImageIcon(ClassLoader.getSystemResource("airticketmanagementsystem/icons/front.gif"));
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 400, 280); // Increased height for forgot password
        setContentPane(background);
        background.setLayout(null);

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font inputFont = new Font("Arial", Font.PLAIN, 14);

        JLabel lblusername = new JLabel("Username:");
        lblusername.setForeground(Color.WHITE);
        lblusername.setFont(labelFont);
        lblusername.setBounds(40, 40, 100, 25);
        background.add(lblusername);

        tfusername = new JTextField();
        tfusername.setFont(inputFont);
        tfusername.setBounds(150, 40, 200, 25);
        tfusername.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        background.add(tfusername);

        JLabel lblpassword = new JLabel("Password:");
        lblpassword.setForeground(Color.WHITE);
        lblpassword.setFont(labelFont);
        lblpassword.setBounds(40, 80, 100, 25);
        background.add(lblpassword);

        tfpassword = new JPasswordField();
        tfpassword.setFont(inputFont);
        tfpassword.setBounds(150, 80, 200, 25);
        tfpassword.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        background.add(tfpassword);

        showPassword = new JCheckBox("Show Password");
        showPassword.setBounds(150, 110, 150, 20);
        showPassword.setForeground(Color.CYAN);
        showPassword.setOpaque(false);
        background.add(showPassword);

        showPassword.addActionListener(e -> {
            if (showPassword.isSelected()) {
                tfpassword.setEchoChar((char) 0);
            } else {
                tfpassword.setEchoChar('•');
            }
        });

        forgotPassword = new JLabel("<HTML><U><B>Forgot Password?</B></U></HTML>");
        forgotPassword.setForeground(Color.YELLOW);
        forgotPassword.setFont(new Font("Arial", Font.PLAIN, 12));
        forgotPassword.setBounds(150, 135, 150, 20);
        forgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        background.add(forgotPassword);

        // Forgot Password Click Action
        forgotPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                resetPassword();
            }
        });

        // Styled buttons
        submit = createStyledButton("Login", new Color(46, 204, 113));
        submit.setBounds(260, 180, 100, 30);
        background.add(submit);

        reset = createStyledButton("Reset", new Color(52, 152, 219));
        reset.setBounds(150, 180, 100, 30);
        background.add(reset);

        close = createStyledButton("Close", new Color(231, 76, 60));
        close.setBounds(40, 180, 100, 30);
        background.add(close);

        setSize(400, 280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // ✅ Reset Password Function
    private void resetPassword() {
        String username = JOptionPane.showInputDialog(this, "Enter your username:");

        if (username != null && !username.trim().isEmpty()) {
            try {
                List<String> users = FileHandler.readData("users.txt");
                boolean found = false;
                List<String> updatedUsers = new ArrayList<>();

                for (String user : users) {
                    String[] fields = user.split(",");
                    if (fields.length >= 2 && fields[0].equals(username)) {
                        found = true;
                        String newPassword = JOptionPane.showInputDialog(this, "Enter new password for " + username + ":");
                        if (newPassword != null && !newPassword.trim().isEmpty()) {
                            updatedUsers.add(username + "," + newPassword);
                            JOptionPane.showMessageDialog(this, "Password updated successfully!");
                        } else {
                            JOptionPane.showMessageDialog(this, "Password not changed.");
                            updatedUsers.add(user); // keep old
                        }
                    } else {
                        updatedUsers.add(user);
                    }
                }

                if (!found) {
                    JOptionPane.showMessageDialog(this, "Username not found!");
                } else {
                    FileHandler.overwriteData("users.txt", updatedUsers);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error while resetting password");
            }
        }
    }

    // Helper method for styled buttons
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });

        button.addActionListener(this);
        return button;
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String username = tfusername.getText();
            String password = new String(tfpassword.getPassword());

            try {
                List<String> users = FileHandler.readData("users.txt");
                boolean authenticated = false;

                for (String user : users) {
                    String[] fields = user.split(",");
                    if (fields.length >= 2 && fields[0].equals(username) && fields[1].equals(password)) {
                        authenticated = true;
                        break;
                    }
                }

                if (authenticated) {
                    new Home();
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error during authentication");
            }
        } else if (ae.getSource() == close) {
            setVisible(false);
        } else if (ae.getSource() == reset) {
            tfusername.setText("");
            tfpassword.setText("");
        }
    }

    public static void main(String[] args) {
        List<String> users = FileHandler.readData("users.txt");
        if (users.isEmpty()) {
            FileHandler.saveData("users.txt", "admin,admin");

            FileHandler.saveData("flights.txt", "Biman Bangladesh Airlines,Dhaka,Dubai,AI-201, 50000.00");
            FileHandler.saveData("flights.txt", "US-Bangla Airlines,Cox's Bazar,London,AI-101, 85000.00");
            FileHandler.saveData("flights.txt", "Air Astra,Dhaka,Singapore,6E-1865, 65800.00");
            FileHandler.saveData("flights.txt", "NovoAir,Chittagong,Moscow,SG-115, 49000.00");
            FileHandler.saveData("flights.txt", "GMG Airlines,Sylhet,Thailand,JB-007, 35000.00");
        }

        new Login();
    }
}
