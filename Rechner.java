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
    // Tasten-Panel erstellen
    private void initButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 2, 2));
        buttonPanel.setBackground(new Color(88, 108, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] buttons = {
                "", "", "", "*",
                "7", "8", "9", "-",
                "4", "5", "6", "+",
                "1", "2", "3", "/",
                "C", "0", "<",  "="
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.setBackground(new Color(245, 245, 245));
            btn.setForeground(Color.BLACK);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setFocusPainted(false);

            btn.addActionListener(e -> handleButton(text));
            buttonPanel.add(btn);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }
    // Logik für Tasten
    private void handleButton(String text) {
        switch (text) {
            case "C":                // Alles löschen
                display.setText("");
                num1 = num2 = 0;
                operator = ' ';
                operatorPressed = false;
                break;
            case "<":                 // Letzte Zahl löschen
                String current = display.getText();
                if (!current.isEmpty()) {
                    display.setText(current.substring(0, current.length() - 1));
                }
                break;
            case "+": case "-": case "*": case "/":           // Operatoren
                if (!display.getText().isEmpty()) {
                    try {
                        num1 = Double.parseDouble(display.getText());
                        operator = text.charAt(0);
                        operatorPressed = true;
                        display.setText("");
                    } catch (NumberFormatException ex) {
                        showError("Invalid input");
                    }
                }
                break;
            case "=":            // Ergebnis berechnen
                if (!display.getText().isEmpty() && operatorPressed) {
                    try {
                        num2 = Double.parseDouble(display.getText());
                        double result = calculate(num1, num2, operator);
                        display.setText(String.valueOf(result));
                        num1 = result;    // Ergebnis für nächste Berechnung speichern
                        operatorPressed = false;
                    } catch (NumberFormatException ex) {
                        showError("Invalid input");
                    }
                }
                break;
            default:        // Zahlen
                display.setText(display.getText() + text);
        }
    }
    // Berechnungslogik
    private double calculate(double a, double b, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/':
                if (b != 0) return a / b;
                showError("Division by zero is not allowed");
                return 0;
            default: return 0;
        }
    }
    // Fehlermeldung anzeigen
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    // Hauptmethode
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Rechner gui = new Rechner();
            gui.setVisible(true);
        });
    }
}