package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LineDashboard_view {

    public JFrame frame = new JFrame("Lines Dashboard");

    // INPUTS
    public JTextField codeField = new JTextField();
    public JTextField nameField = new JTextField();
    public JTextField destinationField = new JTextField();
    public JTextField distanceField = new JTextField();

    // SEARCH
    public JTextField searchField = new JTextField();
    public JButton searchBtn = new JButton("Search");

    // BUTTONS
    public JButton addBtn = new JButton("Add Line");
    public JButton updateBtn = new JButton("Update Line");
    public JButton removeBtn = new JButton("Remove Line");

    // TABLE
    public DefaultTableModel model;
    public JTable table;
    public JScrollPane scroll;

    // TOTAL
    public JLabel totalLabel = new JLabel("Total Lines: 0");

    public JPanel contentPanel = new JPanel();
    public LeftMenu menu = new LeftMenu();

    public LineDashboard_view() {

        frame.setSize(1000, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        contentPanel.setLayout(null);

        // ===== TITLE =====
        JLabel title = new JLabel("Lines Management");
        title.setBounds(350, 10, 250, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        contentPanel.add(title);

        // ===== INPUTS =====
        addField("Code", codeField, 20, 60);
        addField("Name", nameField, 250, 60);
        addField("Destination", destinationField, 480, 60);
        addField("Distance", distanceField, 710, 60);

        // ===== BUTTONS =====
        addBtn.setBounds(250, 110, 120, 30);
        updateBtn.setBounds(390, 110, 120, 30);
        removeBtn.setBounds(530, 110, 120, 30);

        contentPanel.add(addBtn);
        contentPanel.add(updateBtn);
        contentPanel.add(removeBtn);

        // ===== SEARCH =====
        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setBounds(680, 110, 60, 25);
        searchField.setBounds(740, 110, 120, 25);
        searchBtn.setBounds(870, 110, 100, 25);

        contentPanel.add(searchLabel);
        contentPanel.add(searchField);
        contentPanel.add(searchBtn);

        // ===== TABLE =====
        String[] columns = {"Code", "Name", "Destination", "Distance"};

        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        scroll = new JScrollPane(table);
        scroll.setBounds(20, 160, 940, 300);

        contentPanel.add(scroll);

        // ===== TOTAL =====
        totalLabel.setBounds(20, 470, 200, 25);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        contentPanel.add(totalLabel);

        // ===== LAYOUT =====
        frame.add(menu, BorderLayout.WEST);
        frame.add(contentPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    // helper
    private void addField(String label, JTextField field, int x, int y) {
        JLabel l = new JLabel(label + ":");
        l.setBounds(x, y, 80, 25);
        field.setBounds(x + 70, y, 120, 25);

        contentPanel.add(l);
        contentPanel.add(field);
    }

    // update total
    public void updateTotal() {
        totalLabel.setText("Total Lines: " + model.getRowCount());
    }
}