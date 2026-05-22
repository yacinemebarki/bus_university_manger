package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class Student_view {

    public JFrame frame = new JFrame("traget page");
    public JLabel title = new JLabel("Find Your Transport");

    // search bar

    public JTextField searchField = new JTextField();
    public JButton searchBtn = new JButton("Search");
    public JButton clearBtn = new JButton("X"); // clear button

    // line fields

    public JLabel title_bus = new JLabel("all traget");

    public String[] columns = { "Start", "Matricule", "Depart time", "Direction", "Status" };
    public DefaultTableModel lines = new DefaultTableModel(columns, 0);
    public JTable table = new JTable(lines) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    public JScrollPane scroll = new JScrollPane(table);

    public Student_view() {
        // frame costumization

        frame.setSize(1200, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Title
        title.setBounds(320, 10, 260, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        // search bar customization

        searchField.setBounds(50, 60, 260, 32);
        searchField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchField.setBackground(Color.WHITE);

        searchBtn.setBounds(330, 60, 100, 32);
        searchBtn.setFont(new Font("Arial", Font.BOLD, 14));

        clearBtn.setBounds(440, 60, 55, 32);
        clearBtn.setFont(new Font("Arial", Font.BOLD, 16));
        clearBtn.setForeground(Color.RED);

        // table

        title_bus.setBounds(50, 110, 300, 30);
        title_bus.setFont(new Font("Arial", Font.BOLD, 17));

        scroll.setBounds(50, 150, 820, 320);

        table.setRowHeight(32);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
        table.getTableHeader().setBackground(new Color(44, 62, 80));
        table.getTableHeader().setForeground(Color.WHITE);

        table.setSelectionBackground(new Color(100, 149, 237));
        table.setSelectionForeground(Color.WHITE);

        table.setShowGrid(false);
        table.setGridColor(new Color(220, 220, 220));
        table.setShowVerticalLines(false);

        table.setShowHorizontalLines(true);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        table.getTableHeader().setReorderingAllowed(false);
        table.setIntercellSpacing(new Dimension(0, 0));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // add compunent
        frame.add(title);

        frame.add(searchField);
        frame.add(searchBtn);
        frame.add(clearBtn);

        frame.add(title_bus);
        frame.add(scroll);
        frame.setVisible(true);
    }
}
