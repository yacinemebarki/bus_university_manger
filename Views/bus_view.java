package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class bus_view {

    public JFrame frame=new JFrame("Bus Management");

    public JLabel title=new JLabel("Bus Management");

    // INPUTS
    public JLabel matriculeLabel=new JLabel("Matricule:");
    public JTextField matriculeField=new JTextField();

    public String[] workStatusOptions={"ON_WORK", "OFF_WORK"};
    public JComboBox<String> workStatusCombo=new JComboBox<>(workStatusOptions);

    public String[] problemStatusOptions={"OK", "PROBLEM"};
    public JComboBox<String> problemStatusCombo=new JComboBox<>(problemStatusOptions);

    // BUTTONS
    public JButton addBusBtn=new JButton("Add Bus");
    public JButton removeBusBtn=new JButton("Remove Bus");
    public JButton searchBtn=new JButton("Search");
    public JButton statusBtn=new JButton("Change Status");
    // CAPACITY
    public JLabel capacityLabel = new JLabel("Capacity:");
    public JTextField capacityField = new JTextField();

    // TABLE
    public String[] columns={"Matricule","capacity" ,"Work Status", "Problem Status"};
    public DefaultTableModel lines=new DefaultTableModel(columns, 0);
    public JTable table = new JTable(lines) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    public JScrollPane scroll=new JScrollPane(table);


    public String[] searchOptions={"By Matricule","By Work Status","By Problem Status","By Capacity"};

    public JComboBox<String> searchTypeCombo=new JComboBox<>(searchOptions);

    public JPanel contentPanel=new JPanel();
    public LeftMenu menu=new LeftMenu();

    public bus_view() {

        frame.setSize(900, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        // ================= TITLE =================
        title.setBounds(280, 10, 300, 30);
        title.setFont(new Font("Arial", Font.BOLD, 22));

        // ================= INPUT SECTION =================
        matriculeLabel.setBounds(50, 60, 100, 25);
        matriculeField.setBounds(150, 60, 200, 25);

        workStatusCombo.setBounds(380, 60, 150, 25);
        problemStatusCombo.setBounds(550, 60, 150, 25);

        capacityLabel.setBounds(720, 60, 80, 25);
        capacityField.setBounds(800, 60, 70, 25);

        // ================= BUTTONS =================
        addBusBtn.setBounds(50, 110, 150, 30);
        removeBusBtn.setBounds(220, 110, 150, 30);
        searchBtn.setBounds(390, 110, 150, 30);
        statusBtn.setBounds(560, 110, 150, 30);
        searchTypeCombo.setBounds(390,150,200,25);
        

        // ================= TABLE =================
        scroll.setBounds(50, 170, 650, 300);

        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(44, 62, 80));
        table.getTableHeader().setForeground(Color.WHITE);

        table.setSelectionBackground(new Color(100, 149, 237));
        table.setSelectionForeground(Color.WHITE);

        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.getTableHeader().setReorderingAllowed(false);

        // CENTER TEXT
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // ================= ADD COMPONENTS =================
        contentPanel.setLayout(null);
        contentPanel.add(title);

        contentPanel.add(matriculeLabel);
        contentPanel.add(matriculeField);

        contentPanel.add(workStatusCombo);
        contentPanel.add(problemStatusCombo);
        contentPanel.add(searchTypeCombo);
        contentPanel.add(addBusBtn);
        contentPanel.add(removeBusBtn);
        contentPanel.add(searchBtn);
        contentPanel.add(statusBtn);

        contentPanel.add(scroll);
        contentPanel.add(capacityLabel);
        contentPanel.add(capacityField);

        frame.add(menu,BorderLayout.WEST);
        frame.add(contentPanel,BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
