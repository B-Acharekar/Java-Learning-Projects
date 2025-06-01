package BasicCalculator;

import java.awt.*;
import javax.swing.*;

public class CalculatorUI extends JFrame {
    private final JTextField display;
    private String currentOperator = "";
    private double firstNumber = 0;
    private boolean startNewNumber = true;

    public CalculatorUI() {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 32));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 5, 5));
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 24));
            button.addActionListener(e -> handleInput(text));
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void handleInput(String input) {
        if ("0123456789".contains(input)) {
            if (startNewNumber) {
                display.setText(input);
                startNewNumber = false;
            } else {
                display.setText(display.getText() + input);
            }
        } else if ("+-*/".contains(input)) {
            firstNumber = Double.parseDouble(display.getText());
            currentOperator = input;
            startNewNumber = true;
        } else if (input.equals("=")) {
            double secondNumber = Double.parseDouble(display.getText());
            double result = Calculator.calculate(firstNumber, secondNumber, currentOperator);
            display.setText(String.valueOf(result));
            startNewNumber = true;
        } else if (input.equals("C")) {
            display.setText("0");
            firstNumber = 0;
            currentOperator = "";
            startNewNumber = true;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculatorUI::new);
    }
}
