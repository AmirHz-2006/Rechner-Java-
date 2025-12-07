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