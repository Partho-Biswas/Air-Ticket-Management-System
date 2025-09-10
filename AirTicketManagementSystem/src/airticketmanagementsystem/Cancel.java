package airticketmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Cancel extends JFrame implements ActionListener {

    JTextField tfpnr;
    JLabel tfname, cancellationno, lblfcode, lbldateoftravel;
    JButton fetchButton, flight;

    public Cancel() {
        getContentPane().setBackground(Color.WHITE);
        getContentPane().setBackground(new Color(183, 218, 252));
        setLayout(null);

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("airticketmanagementsystem/icons/logo4.jpg"));
        setIconImage(icon.getImage());

        Random random = new Random();

        JLabel heading = new JLabel("CANCELLATION");
        heading.setBounds(280, 20, 250, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        add(heading);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airticketmanagementsystem/icons/cancel.png"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(470, 120, 250, 250);
        add(image);

        JLabel lblnid = new JLabel("PNR Number");
        lblnid.setBounds(60, 80, 150, 25);
        lblnid.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblnid);

        tfpnr = new JTextField();
        tfpnr.setBounds(220, 80, 150, 25);
        add(tfpnr);

        fetchButton = new JButton("Show Details");
        fetchButton.setBackground(Color.BLACK);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setBounds(380, 80, 120, 25);
        fetchButton.addActionListener(this);
        add(fetchButton);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(60, 130, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblname);

        tfname = new JLabel();
        tfname.setBounds(220, 130, 150, 25);
        add(tfname);

        JLabel lblnationality = new JLabel("Cancellation No");
        lblnationality.setBounds(60, 180, 150, 25);
        lblnationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblnationality);

        cancellationno = new JLabel("" + random.nextInt(1000000));
        cancellationno.setBounds(220, 180, 150, 25);
        add(cancellationno);

        JLabel lbladdress = new JLabel("Flight Code");
        lbladdress.setBounds(60, 230, 150, 25);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbladdress);

        lblfcode = new JLabel();
        lblfcode.setBounds(220, 230, 150, 25);
        add(lblfcode);

        JLabel lblgender = new JLabel("Date");
        lblgender.setBounds(60, 280, 150, 25);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblgender);

        lbldateoftravel = new JLabel();
        lbldateoftravel.setBounds(220, 280, 150, 25);
        add(lbldateoftravel);

        flight = new JButton("Cancel");
        flight.setBackground(Color.BLACK);
        flight.setForeground(Color.WHITE);
        flight.setBounds(220, 330, 120, 25);
        flight.addActionListener(this);
        add(flight);

        setSize(800, 450);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fetchButton) {
            String pnr = tfpnr.getText();

            try {
                String record = FileHandler.findRecord("reservations.txt", pnr, 0);

                if (record != null) {
                    String[] fields = record.split(",");
                    tfname.setText(fields[3]);
                    lblfcode.setText(fields[6]);
                    lbldateoftravel.setText(fields[9]);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter correct PNR");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error fetching reservation details");
            }
        } else if (ae.getSource() == flight) {
            // Check if PNR details are loaded
            if (tfname.getText().isEmpty() || lblfcode.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fetch PNR details first using 'Show Details' button!",
                        "Information Required",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Create custom confirmation dialog with flight details
            String message = "<html><div style='text-align: center;'>"
                    + "<b>Are you sure you want to cancel this flight?</b><br><br>"
                    + "Passenger: " + tfname.getText() + "<br>"
                    + "Flight: " + lblfcode.getText() + "<br>"
                    + "Date: " + lbldateoftravel.getText() + "<br>"
                    + "Cancellation No: " + cancellationno.getText() + "<br><br>"
                    + "<font color='red'>This action cannot be undone!</font></div></html>";

            // Custom buttons
            Object[] options = {"Yes, Cancel Flight", "No, Keep Reservation"};

            int confirmation = JOptionPane.showOptionDialog(
                    this,
                    message,
                    "Confirm Flight Cancellation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    options,
                    options[1] // Default to "No" option
            );

            if (confirmation == JOptionPane.YES_OPTION) {
                String name = tfname.getText();
                String pnr = tfpnr.getText();
                String cancelno = cancellationno.getText();
                String fcode = lblfcode.getText();
                String date = lbldateoftravel.getText();

                try {
                    // Save cancellation record
                    String data = pnr + "," + name + "," + cancelno + "," + fcode + "," + date;
                    FileHandler.saveData("cancellations.txt", data);

                    // Remove from reservations
                    FileHandler.deleteRecord("reservations.txt", pnr, 0);

                    // Success message with details
                    JOptionPane.showMessageDialog(this,
                            "<html><div style='text-align: center;'>"
                            + "<b>Ticket Cancelled Successfully!</b><br><br>"
                            + "Cancellation Number: <b>" + cancelno + "</b><br>"
                            + "Passenger: " + name + "<br>"
                            + "Flight: " + fcode + "<br>"
                            + "Refund will be processed within 7-10 working days.</div></html>",
                            "Cancellation Complete",
                            JOptionPane.INFORMATION_MESSAGE);

                    setVisible(false);

                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this,
                            "Error cancelling ticket: " + e.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] args) {
        new Cancel();
    }
}
