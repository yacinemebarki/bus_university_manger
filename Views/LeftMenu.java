package Views;
import java.awt.*;
import javax.swing.*;

public class LeftMenu extends JPanel{
    
    
    public JButton personBtn=new JButton("Person");
    public JButton lineBtn=new JButton("Line");
    public JButton busBtn=new JButton("Bus");
    public JButton problemBtn=new JButton("Problem");

    public LeftMenu(){

        setLayout(new GridLayout(4,1,10,10));
        setPreferredSize(new Dimension(150, 0));
        setBackground(new Color(44, 62, 80));

        styleButton(personBtn);
        styleButton(lineBtn);
        styleButton(busBtn);
        styleButton(problemBtn);

        add(personBtn);
        add(lineBtn);
        add(busBtn);
        add(problemBtn);
    }
    private void styleButton(JButton btn) {
        btn.setFocusPainted(false);
        btn.setBackground(new Color(52, 73, 94));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(new Color(41, 128, 185));
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(new Color(52, 73, 94));
            }
        });
    }

}
