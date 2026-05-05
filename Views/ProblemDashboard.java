package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class ProblemDashboard {

    public JFrame frame = new JFrame("Problem Dashboard");

    public String[] ProblemType = {"Driver Fatigue","Full Bus", "Vehicle Problem","Road Problem"};
    public JComboBox<String> ProblemBox = new JComboBox<>(ProblemType);

    public String[] searchType = {"By name","By Type", "Both"};
    public JComboBox<String> seachBox = new JComboBox<>(searchType);
    

    public JLabel title = new JLabel("Problem Manager");

    public JTextField nameField = new JTextField(15);
    public JLabel name = new JLabel("Line Name:");

    public JLabel totalLabel = new JLabel("Total: 0");
    public JLabel driverLabel = new JLabel("Driver: 0");
    public JLabel busLabel = new JLabel("Bus: 0");
    public JLabel vehicleLabel = new JLabel("Vehicle: 0");
    public JLabel roadLabel = new JLabel("Road: 0");

    public JButton searchBtn = new JButton("Search");

    String[] columns = {"Problem id","trip id", "line_name","Driver ID", "Problem Type", "Date", "Time", "Message"};
    public DefaultTableModel model = new DefaultTableModel(columns, 0);
    public JTable table = new JTable(model);
    public JScrollPane scroll = new JScrollPane(table);

    public JPanel panel = new JPanel(new BorderLayout());
    public LeftMenu menu = new LeftMenu();

    public JPanel topPanel = new JPanel(new BorderLayout());
    public JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    public JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

    public ProblemDashboard() {

        // ================= FRAME =================
        frame.setSize(1200, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // ================= TITLE =================
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(new Color(44, 62, 80));

        topPanel.add(title, BorderLayout.NORTH);

        // ================= STATS CARDS =================
        statsPanel.add(createCard(totalLabel));
        statsPanel.add(createCard(driverLabel));
        statsPanel.add(createCard(busLabel));
        statsPanel.add(createCard(vehicleLabel));
        statsPanel.add(createCard(roadLabel));

        topPanel.add(statsPanel, BorderLayout.CENTER);

        // ================= FILTERS =================
        filterPanel.add(name);
        filterPanel.add(nameField);
        filterPanel.add(new JLabel("Problem Type:"));
        filterPanel.add(ProblemBox);
        filterPanel.add(new JLabel("search by"));
        filterPanel.add(seachBox);
        filterPanel.add(searchBtn);
        
        

        // ================= TABLE =================
        table.setRowHeight(25);
        scroll.setPreferredSize(new Dimension(700, 400));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(filterPanel, BorderLayout.NORTH);
        centerPanel.add(scroll, BorderLayout.CENTER);

        // ================= FINAL LAYOUT =================
        panel.setLayout(new BorderLayout());
        panel.add(menu, BorderLayout.WEST);
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    // ================= CARD DESIGN =================
    private JPanel createCard(JLabel label) {

        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(150, 60));
        card.setLayout(new BorderLayout());

        card.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));

        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 13));

        card.add(label, BorderLayout.CENTER);

        return card;
    }
}
