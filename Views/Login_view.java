package Views;

import javax.swing.*;
import java.awt.*;

public class Login_view {

    public JFrame frame = new JFrame("Login");

    public JLabel titleLabel = new JLabel("Login Page");

    JLabel userLabel = new JLabel("Name:");
    public JTextField nameField = new JTextField();

    JLabel passLabel = new JLabel("Password:");
    public JPasswordField passField = new JPasswordField();

    JLabel roleLabel = new JLabel("Role:");
    public JComboBox<String> roleBox = new JComboBox<>(
            new String[]{"Student", "Driver", "Manager"}
    );

    public JButton loginBtn = new JButton("Login");

    public Login_view() {

        frame.setSize(400, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        // Title
        titleLabel.setBounds(150, 20, 200, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Username
        userLabel.setBounds(50, 70, 100, 25);
        nameField.setBounds(150, 70, 180, 25);

        // Password
        passLabel.setBounds(50, 110, 100, 25);
        passField.setBounds(150, 110, 180, 25);

        // Role
        roleLabel.setBounds(50, 150, 100, 25);
        roleBox.setBounds(150, 150, 180, 25);

        // Button
        loginBtn.setBounds(150, 200, 100, 30);

        // Add components
        frame.add(titleLabel);
        frame.add(userLabel);
        frame.add(nameField);
        frame.add(passLabel);
        frame.add(passField);
        frame.add(roleLabel);
        frame.add(roleBox);
        frame.add(loginBtn);

        frame.setVisible(true);
    }
}