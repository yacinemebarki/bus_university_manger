package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LineDashboard_view {

    public JFrame frame = new JFrame("Line Dashboard");

    // 🔹 INPUT FIELDS
    JLabel codeLabel = new JLabel("Line Code:");
    public JTextField codeField = new JTextField();

    JLabel nameLabel = new JLabel("Name:");
    public JTextField nameField = new JTextField();

    JLabel timeLabel = new JLabel("Start Time:");
    public JTextField timeField = new JTextField();

    JLabel startLabel = new JLabel("Start Point:");
    public JTextField startField = new JTextField();

    JLabel endLabel = new JLabel("End Point:");
    public JTextField endField = new JTextField();

    JLabel distLabel = new JLabel("Distance (km):");
    public JTextField distField = new JTextField();

    JLabel busCountLabel = new JLabel("Bus Count:");
    public JTextField busCountField = new JTextField();

    // 🔹 SEARCH
    JLabel searchLabel = new JLabel("Search (Start/End):");
    public JTextField searchField = new JTextField();
    public JButton searchBtn = new JButton("Search");

    // 🔹 BUTTONS
    public JButton addBtn = new JButton("Add Line");
    public JButton removeBtn = new JButton("Remove Line");
    public JButton updateBtn = new JButton("Update Line");

    // 🔹 TOTAL LINES
    public JLabel totalLabel = new JLabel("Total Lines: 0");

    // 🔹 TABLE
    public JTable table;
    public DefaultTableModel model;

    public LineDashboard_view() {

        frame.setSize(900, 520);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ===== INPUTS =====
        codeLabel.setBounds(20, 20, 100, 25);
        codeField.setBounds(120, 20, 120, 25);

        nameLabel.setBounds(260, 20, 100, 25);
        nameField.setBounds(320, 20, 120, 25);

        timeLabel.setBounds(460, 20, 100, 25);
        timeField.setBounds(540, 20, 100, 25);

        startLabel.setBounds(20, 60, 100, 25);
        startField.setBounds(120, 60, 120, 25);

        endLabel.setBounds(260, 60, 100, 25);
        endField.setBounds(320, 60, 120, 25);

        distLabel.setBounds(460, 60, 100, 25);
        distField.setBounds(560, 60, 80, 25);

        busCountLabel.setBounds(660, 60, 100, 25);
        busCountField.setBounds(760, 60, 80, 25);
        updateBtn.setBounds(430, 100, 150, 30);

        // ===== BUTTONS =====
        addBtn.setBounds(120, 100, 120, 30);
        removeBtn.setBounds(260, 100, 150, 30);

        // ===== SEARCH =====
        searchLabel.setBounds(450, 100, 150, 25);
        searchField.setBounds(600, 100, 150, 25);
        searchBtn.setBounds(760, 100, 100, 30);

        // ===== TOTAL =====
        totalLabel.setBounds(20, 130, 200, 25);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));

        // ===== TABLE =====
        String[] columns = {
                "LineCode", "Name", "Start Time",
                "Start Point", "End Point",
                "Distance (km)", "Bus Count"
        };

        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(20, 160, 850, 300);

        // ===== ADD COMPONENTS =====
        frame.add(codeLabel); frame.add(codeField);
        frame.add(nameLabel); frame.add(nameField);
        frame.add(timeLabel); frame.add(timeField);

        frame.add(startLabel); frame.add(startField);
        frame.add(endLabel); frame.add(endField);
        frame.add(distLabel); frame.add(distField);
        frame.add(busCountLabel); frame.add(busCountField);

        frame.add(addBtn);
        frame.add(removeBtn);
        frame.add(updateBtn);

        frame.add(searchLabel);
        frame.add(searchField);
        frame.add(searchBtn);

        frame.add(totalLabel);
        frame.add(scroll);

        frame.setVisible(true);
    }

    // 🔥 Update total lines
    public void updateTotalLines() {
        totalLabel.setText("Total Lines: " + model.getRowCount());
    }
}