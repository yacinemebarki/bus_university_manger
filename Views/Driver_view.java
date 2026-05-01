package Views;

import javax.swing.*;
import java.awt.*;

public class Driver_view {

    public JFrame frame = new JFrame("Driver Dashboard");

    // Title
    JLabel title = new JLabel("Driver Panel");

    // 🔒 READ-ONLY INFO
    JLabel busLabel = new JLabel("Bus Code:");
    public JLabel busValue = new JLabel("-");

    JLabel timeLabel = new JLabel("Start Time:");
    public JLabel timeValue = new JLabel("-");

    JLabel destLabel = new JLabel("Destination:");
    public JLabel destValue = new JLabel("-");

    // Animation
    public JLabel routeLabel = new JLabel("Start =====> Destination");

    // Buttons
    public JButton startBtn = new JButton("Start Trip");
    public JButton problemBtn = new JButton("Report Problem");

    public Driver_view() {

        frame.setSize(500, 320);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Title
        title.setBounds(170, 10, 200, 30);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        // Bus
        busLabel.setBounds(50, 60, 120, 25);
        busValue.setBounds(200, 60, 200, 25);

        // Time
        timeLabel.setBounds(50, 90, 120, 25);
        timeValue.setBounds(200, 90, 200, 25);

        // Destination
        destLabel.setBounds(50, 120, 120, 25);
        destValue.setBounds(200, 120, 200, 25);

        // Animation
        routeLabel.setBounds(120, 160, 300, 25);
        routeLabel.setFont(new Font("Arial", Font.BOLD, 14));

        // Buttons
        startBtn.setBounds(80, 200, 150, 30);
        problemBtn.setBounds(250, 200, 150, 30);

        // Add components
        frame.add(title);

        frame.add(busLabel);
        frame.add(busValue);

        frame.add(timeLabel);
        frame.add(timeValue);

        frame.add(destLabel);
        frame.add(destValue);

        frame.add(routeLabel);

        frame.add(startBtn);
        frame.add(problemBtn);

        frame.setVisible(true);

        // Actions
        startBtn.addActionListener(e -> startAnimation());
    }

    // 🔥 Animation
    private void startAnimation() {
        new Thread(() -> {
            String base = "Start ";
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ignored) {}

                base += "=";
                routeLabel.setText(base + "=> " + destValue.getText());
            }
        }).start();
    }
}