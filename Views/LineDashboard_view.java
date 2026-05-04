package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LineDashboard_view {

    public JFrame frame = new JFrame("Lines Dashboard");

    // 🔹 INPUT FIELDS
    public JTextField codeField = new JTextField();
    public JTextField nameField = new JTextField();
    public JTextField destinationField = new JTextField();
    public JTextField distanceField = new JTextField();

    // 🔹 SEARCH
    public JTextField searchField = new JTextField();
    public JButton searchBtn = new JButton("Search");

    // 🔹 BUTTONS
    public JButton addBtn = new JButton("Add");
    public JButton updateBtn = new JButton("Update");
    public JButton removeBtn = new JButton("Remove");

    // 🔹 TABLE
    public JTable table;
    public DefaultTableModel model;

    // 🔹 TOTAL
    public JLabel totalLabel = new JLabel("Total Lines: 0");

    public LineDashboard_view() {

        frame.setSize(850, 500);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ===== TITLE =====
        JLabel title = new JLabel("Lines Management");
        title.setBounds(320, 10, 250, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(title);

        // ===== INPUT FIELDS =====
        addField("Code", codeField, 20, 50);
        addField("Name", nameField, 220, 50);

        addField("Destination", destinationField, 420, 50);
        addField("Distance", distanceField, 620, 50);

        // ===== BUTTONS =====
        addBtn.setBounds(220, 100, 100, 30);
        updateBtn.setBounds(340, 100, 100, 30);
        removeBtn.setBounds(460, 100, 100, 30);

        frame.add(addBtn);
        frame.add(updateBtn);
        frame.add(removeBtn);

        // ===== SEARCH =====
        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setBounds(580, 100, 60, 25);

        searchField.setBounds(640, 100, 120, 25);
        searchBtn.setBounds(760, 100, 80, 25);

        frame.add(searchLabel);
        frame.add(searchField);
        frame.add(searchBtn);

        // ===== TABLE =====
        String[] columns = {
                "Line Code", "Name", "Destination", "Distance (km)"
        };

        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(20, 150, 800, 260);
        frame.add(scroll);

        // ===== TOTAL =====
        totalLabel.setBounds(20, 420, 200, 25);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        frame.add(totalLabel);

        // ===== CLICK ROW → FILL FIELDS =====
        table.getSelectionModel().addListSelectionListener(e -> fillFields());

        frame.setVisible(true);
    }

    // 🔹 Helper to create fields
    private void addField(String label, JTextField field, int x, int y) {
        JLabel l = new JLabel(label + ":");
        l.setBounds(x, y, 80, 25);

        field.setBounds(x + 80, y, 120, 25);

        frame.add(l);
        frame.add(field);
    }

    // 🔹 Fill inputs when row selected
    private void fillFields() {
        int row = table.getSelectedRow();

        if (row != -1) {
            codeField.setText(model.getValueAt(row, 0).toString());
            nameField.setText(model.getValueAt(row, 1).toString());
            destinationField.setText(model.getValueAt(row, 2).toString());
            distanceField.setText(model.getValueAt(row, 3).toString());
        }
    }

    // 🔹 Update total lines
    public void updateTotal() {
        totalLabel.setText("Total Lines: " + model.getRowCount());
    }
}