package airticketmanagementsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddCustomer extends JFrame implements ActionListener{
    
    JTextField tfname, tfphone, tfnid, tfnationality, tfaddress;
    JRadioButton rbmale, rbfemale;
    
    public AddCustomer() {
        getContentPane().setBackground(Color.WHITE);
        getContentPane().setBackground(new Color(183, 218, 252));
        setLayout(null);
        
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("airticketmanagementsystem/icons/logo4.jpg"));
        setIconImage(icon.getImage());
        
        JLabel heading = new JLabel("ADD CUSTOMER DETAILS");
        heading.setBounds(250, 20, 500, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        heading.setForeground(Color.DARK_GRAY);
        add(heading);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(60, 80, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(220, 80, 150, 25);
        add(tfname);
        
        JLabel lblnationality = new JLabel("Nationality");
        lblnationality.setBounds(60, 130, 150, 25);
        lblnationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblnationality);
        
        tfnationality = new JTextField();
        tfnationality.setBounds(220, 130, 150, 25);
        add(tfnationality);
        
        JLabel lblnid = new JLabel("NID Number");
        lblnid.setBounds(60, 180, 150, 25);
        lblnid.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblnid);
        
        tfnid = new JTextField();
        tfnid.setBounds(220, 180, 150, 25);
        add(tfnid);
        
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(60, 230, 150, 25);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbladdress);
        
        tfaddress = new JTextField();
        tfaddress.setBounds(220, 230, 150, 25);
        add(tfaddress);
        
        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(60, 280, 150, 25);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblgender);
        
        ButtonGroup gendergroup = new ButtonGroup();
        
        rbmale = new JRadioButton("Male");
        rbmale.setBounds(220, 280, 70, 25);
        rbmale.setBackground(Color.WHITE);
        add(rbmale);
        
        rbfemale = new JRadioButton("Female");
        rbfemale.setBounds(300, 280, 70, 25);
        rbfemale.setBackground(Color.WHITE);
        add(rbfemale);
        
        gendergroup.add(rbmale);
        gendergroup.add(rbfemale);       
        
        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(60, 330, 150, 25);
        lblphone.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblphone);
        
        tfphone = new JTextField();
        tfphone.setBounds(220, 330, 150, 25);
        add(tfphone);
        
        JButton save = new JButton("SAVE");
        save.setBackground(Color.BLACK);
        save.setForeground(Color.WHITE);
        save.setBounds(220, 380, 150, 30);
        save.addActionListener(this);
        add(save);
        
        ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("airticketmanagementsystem/icons/custom.png"));
        Image img = image.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT);
        ImageIcon img2 = new ImageIcon(img);
        JLabel lblimage = new JLabel(img2);
        lblimage.setBounds(450, 80, 380, 400);
        add(lblimage);
                
        setSize(900, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String name = tfname.getText();
        String nationality = tfnationality.getText();
        String phone = tfphone.getText();
        String address = tfaddress.getText();
        String nid = tfnid.getText();
        String gender = null;
        if (rbmale.isSelected()) {
            gender = "Male";
        } else if (rbfemale.isSelected()) {
            gender = "Female";
        } else {
            JOptionPane.showMessageDialog(null, "Please select gender");
            return;
        }
        
        // Validate inputs
        if (name.isEmpty() || nationality.isEmpty() || phone.isEmpty() || 
            address.isEmpty() || nid.isEmpty() || gender == null) {
            JOptionPane.showMessageDialog(null, "Please fill all fields");
            return;
        }
        
        // Check if nid already exists
        String existingRecord = FileHandler.findRecord("passengers.txt", nid, 4);
        if (existingRecord != null) {
            JOptionPane.showMessageDialog(null, "nid number already exists");
            return;
        }
        
        try {
            // Format: name,nationality,phone,address,nid,gender
            String data = name + "," + nationality + "," + phone + "," + 
                         address + "," + nid + "," + gender;
            
            FileHandler.saveData("passengers.txt", data);
            
            JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");
            setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving customer details");
        }
    }

    public static void main(String[] args) {
        new AddCustomer();
    }
}


