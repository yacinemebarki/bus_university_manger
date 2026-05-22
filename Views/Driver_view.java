package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Driver_view {

    public JFrame frame =
            new JFrame("Driver Dashboard");

    // TITLE
    JLabel title =
            new JLabel("Driver Trips Dashboard");

    // TABLE
    String[] columns = {

            "Trip ID",
            "Departure",
            "Destination",
            "Start Time",
            "Bus"

    };

    public DefaultTableModel model =
            new DefaultTableModel(columns, 0);

    public JTable tripTable =
            new JTable(model);

    JScrollPane scroll =
            new JScrollPane(tripTable);

    public Driver_view() {

        frame.setSize(1200, 620);

        frame.setLayout(null);

        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        // TITLE
        title.setBounds(430, 20, 500, 40);

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        // TABLE
        scroll.setBounds(
                40,
                100,
                1200,
                500
        );

        tripTable.setRowHeight(35);

        tripTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        tripTable.getTableHeader().setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        // ADD COMPONENTS
        frame.add(title);

        frame.add(scroll);

        frame.setVisible(true);
    }
}