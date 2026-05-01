package Views;

import javax.swing.*;
import java.awt.*;

public class Manager_view {

    public JFrame frame = new JFrame("Manager Dashboard");

    public JLabel title = new JLabel("Manager Dashboard");

    // INPUT FIELDS (TOP SECTION)
    public JLabel nameLabel = new JLabel("Full Name:");
    public JTextField nameField = new JTextField();

    public JLabel codeLabel = new JLabel("Matricule / Code:");
    public JTextField codeField = new JTextField();

    public JLabel passLabel = new JLabel("Password:");
    public JPasswordField passField = new JPasswordField();

    // BUTTONS
    public JButton addStudentBtn = new JButton("Add Student");
    public JButton removeStudentBtn = new JButton("Remove Student");

    public JButton addDriverBtn = new JButton("Add Driver");
    public JButton removeDriverBtn = new JButton("Remove Driver");

    JButton linesDashboardBtn = new JButton("Lines Dashboard");


    public JButton logoutBtn = new JButton("Logout");

    public Manager_view() {

        frame.setSize(600, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        // Title
        title.setBounds(200, 10, 300, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        // ================= TOP INPUTS =================
        nameLabel.setBounds(50, 50, 100, 25);
        nameField.setBounds(180, 50, 200, 25);

        codeLabel.setBounds(50, 80, 120, 25);
        codeField.setBounds(180, 80, 200, 25);

        passLabel.setBounds(50, 110, 100, 25);
        passField.setBounds(180, 110, 200, 25);

        // ================= BUTTONS =================
        addStudentBtn.setBounds(50, 170, 200, 30);
        removeStudentBtn.setBounds(300, 170, 200, 30);

        addDriverBtn.setBounds(50, 220, 200, 30);
        removeDriverBtn.setBounds(300, 220, 200, 30);

        linesDashboardBtn.setBounds(50, 270, 450, 30);

        logoutBtn.setBounds(200, 330, 150, 30);

        // ================= ADD COMPONENTS =================
        frame.add(title);

        frame.add(nameLabel);
        frame.add(nameField);

        frame.add(codeLabel);
        frame.add(codeField);

        frame.add(passLabel);
        frame.add(passField);

        frame.add(addStudentBtn);
        frame.add(removeStudentBtn);

        frame.add(addDriverBtn);
        frame.add(removeDriverBtn);

        frame.add(linesDashboardBtn);
        

        frame.add(logoutBtn);

        frame.setVisible(true);
    }
}