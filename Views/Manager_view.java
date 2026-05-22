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

    public JButton logoutBtn = new JButton("Logout");
    public JPanel contentPanel = new JPanel();
    public LeftMenu menu = new LeftMenu();

    public Manager_view() {

        frame.setSize(1200, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Title
        title.setBounds(220, 10, 300, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        // ================= TOP INPUTS =================
        nameLabel.setBounds(40, 60, 120, 25);
        nameField.setBounds(170, 60, 240, 25);

        codeLabel.setBounds(40, 100, 120, 25);
        codeField.setBounds(170, 100, 240, 25);

        passLabel.setBounds(40, 140, 120, 25);
        passField.setBounds(170, 140, 240, 25);

        // ================= BUTTONS =================
        addStudentBtn.setBounds(40, 190, 200, 35);
        removeStudentBtn.setBounds(260, 190, 200, 35);

        addDriverBtn.setBounds(40, 245, 200, 35);
        removeDriverBtn.setBounds(260, 245, 200, 35);

        logoutBtn.setBounds(170, 310, 200, 40);

        // ================= ADD COMPONENTS =================
        contentPanel.setLayout(null);
        contentPanel.add(title);

        contentPanel.add(nameLabel);
        contentPanel.add(nameField);

        contentPanel.add(codeLabel);
        contentPanel.add(codeField);

        contentPanel.add(passLabel);
        contentPanel.add(passField);

        contentPanel.add(addStudentBtn);
        contentPanel.add(removeStudentBtn);

        contentPanel.add(addDriverBtn);
        contentPanel.add(removeDriverBtn);

        contentPanel.add(logoutBtn);

        frame.setLayout(new BorderLayout());

        frame.add(menu, BorderLayout.WEST);

        frame.add(contentPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}