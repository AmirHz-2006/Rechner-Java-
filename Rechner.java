import javax.swing.*;
import java.awt.*;

public class Rechner extends JFrame {
    private JTextField display;
    private double num1, num2;
    private char operator;
    private boolean operatorPressed = false;

    public Rechner() {
        // Fenster-Einstellungen
        setTitle("Rechner");
        setSize(350, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(0, 0));
        setLocationRelativeTo(null);
        setResizable(false);

        initDisplayPanel();                // Anzeige-Panel initialisieren
        initButtonPanel();                 // Tasten-Panel initialisieren
    }
    // Anzeige-Panel erstellen
    private void initDisplayPanel() {
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBackground(new Color(88, 108, 0));
        displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 2, 10));

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 35));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setForeground(Color.WHITE);
        display.setBackground(new Color(88, 108, 0));
        display.setPreferredSize(new Dimension(0, 60));
        display.setBorder(BorderFactory.createLineBorder(new Color(88, 108, 0)));
        displayPanel.add(display, BorderLayout.CENTER);

        add(displayPanel, BorderLayout.NORTH);
    }
}