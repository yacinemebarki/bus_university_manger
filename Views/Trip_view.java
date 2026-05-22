package Views;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Trip_view {

    public JFrame frame = new JFrame("Trip Dashboard");

    // LEFT MENU
    public LeftMenu menu = new LeftMenu();

    // CENTER PANEL
    public JPanel centerPanel = new JPanel(null);

    // TITLE
    public JLabel title = new JLabel("Find Your Transport");

    // SEARCH TYPE (COMBOBOX)
    String[] types = { "Trip ID", "Driver ID", "Matricule", "Status", "station" };
    public JComboBox<String> searchBox = new JComboBox<>(types);

    public JTextField searchField = new JTextField();
    public JButton searchBtn = new JButton("Search");
    public JButton clearBtn = new JButton("X");
    public JButton addBtn = new JButton("ajouter");

    // TABLE TITLE
    public JLabel title_bus = new JLabel("All Trips");

    // TABLE
    public String[] columns = {
            "Trip ID", "Start", "Driver ID", "Matricule",
            "Depart Time", "Direction", "Status"
    };

    public DefaultTableModel model = new DefaultTableModel(columns, 0);

    public JTable table = new JTable(model) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public JScrollPane scroll = new JScrollPane(table);

    public Trip_view() {

        // ===== FRAME =====
        frame.setSize(1200, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // ===== LEFT MENU =====
        frame.add(menu, BorderLayout.WEST);

        // ===== CENTER PANEL =====
        centerPanel.setBackground(new Color(245, 246, 250));
        centerPanel.setLayout(null);

        // ===== TITLE =====
        title.setBounds(30, 10, 500, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        // ===== SEARCH TYPE (COMBOBOX) =====
        searchBox.setBounds(30, 50, 150, 30);
        searchBox.setFont(new Font("Arial", Font.PLAIN, 12));
        searchBox.setBackground(Color.WHITE);
        searchBox.setFocusable(false);

        // ===== SEARCH FIELD =====
        searchField.setBounds(200, 50, 250, 30);
        searchField.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // ===== BUTTONS =====
        searchBtn.setBounds(470, 50, 110, 30);
        searchBtn.setBackground(new Color(52, 152, 219));
        searchBtn.setForeground(Color.WHITE);
        searchBtn.setFocusPainted(false);

        clearBtn.setBounds(590, 50, 60, 30);
        clearBtn.setForeground(Color.RED);
        clearBtn.setFocusPainted(false);
        addBtn.setBounds(670, 50, 120, 30);
        addBtn.setBackground(new Color(52, 152, 219));
        addBtn.setForeground(Color.WHITE);
        addBtn.setFocusPainted(false);

        // ===== TABLE TITLE =====
        title_bus.setBounds(30, 100, 300, 30);
        title_bus.setFont(new Font("Arial", Font.BOLD, 16));

        // ===== TABLE =====
        scroll.setBounds(30, 140, 900, 380);

        table.setRowHeight(28);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(44, 62, 80));
        table.getTableHeader().setForeground(Color.WHITE);

        table.setSelectionBackground(new Color(100, 149, 237));
        table.setSelectionForeground(Color.WHITE);

        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.getTableHeader().setReorderingAllowed(false);

        // CENTER TEXT IN TABLE
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(center);
        }

        // ===== ADD COMPONENTS =====
        centerPanel.add(title);
        centerPanel.add(searchBox);
        centerPanel.add(searchField);
        centerPanel.add(searchBtn);
        centerPanel.add(clearBtn);
        centerPanel.add(addBtn);
        centerPanel.add(title_bus);
        centerPanel.add(scroll);

        frame.add(centerPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}