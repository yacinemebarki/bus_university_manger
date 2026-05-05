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
    public LeftMenu menu=new LeftMenu();

    public Manager_view() {

        frame.setSize(900, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        

        logoutBtn.setBounds(200, 330, 150, 30);

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