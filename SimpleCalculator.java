/*
This Code is the property of Abdelrahman Salah a Student at The British University in Egypt.
This is a simple calculator which can add, subtract, multiply, divide, and clear (might add more features later on).
*/

import javax.swing.*;
import java.awt.*;

public class SimpleCalculator extends JFrame {

    private final JTextField display;
    private double num1 =0, num2=0;
    private char operator;

    public SimpleCalculator() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display
        display = new JTextField(20);
        display.setEditable(false);
        display.setPreferredSize(new Dimension(350, 150));
        add(display, BorderLayout.NORTH);
        display.setFont(new Font("Dialog", Font.PLAIN, 20));

        // Num buttons
        JPanel numPanel = new JPanel(new GridLayout(4,4, 5, 5)); // add gap between buttons
        JButton[] numButton = new JButton[10];
        for (int i=0; i < 10; i++)
        {
            final int number = i;
            numButton[i] = new JButton(String.valueOf(i));
            numButton[i].addActionListener(e -> display.setText(display.getText() + number));
            if(i == 9) // Check if 9 is the last number
                numPanel.add(new JPanel()); // add an empty component to the panel
            numPanel.add(numButton[i]);
            numButton[i].setPreferredSize(new Dimension(50, 50));
        }

        // Operator buttons (C, +, -, *, /)
        JPanel opButton = new JPanel(new GridLayout(4,2, 5, 5)); // add gap between buttons
        JButton addbtn = new JButton("+");
        JButton subtractbtn = new JButton("-");
        JButton multiplybtn = new JButton("*");
        JButton dividebtn = new JButton("/");
        JButton clearbtn = new JButton("C");
        JButton equalbtn = new JButton("=");

        JButton[] operatorButtons = {addbtn, subtractbtn, multiplybtn, dividebtn, equalbtn, clearbtn};

        for (JButton button : operatorButtons) {
            button.setPreferredSize(new Dimension(50, 50));
            opButton.add(button);
        }

        // Actions
        addbtn.addActionListener(e -> setOperator('+'));
        subtractbtn.addActionListener(e -> setOperator('-'));
        multiplybtn.addActionListener(e -> setOperator('*'));
        dividebtn.addActionListener(e -> setOperator('/'));
        equalbtn.addActionListener(e -> equal());
        clearbtn.addActionListener(e -> clear());

        add(numPanel, BorderLayout.CENTER);
        add(opButton, BorderLayout.EAST);

        pack();
        setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // setOperator method
    private void setOperator(char op) {
        num1 = Double.parseDouble(display.getText());
        operator = op;
        display.setText("");
    }

    private void equal() {
        num2 = Double.parseDouble(display.getText());
        double result = 0;
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0)
                    result = num1 / num2;
                else
                    display.setText("Invalid, can't divide by zero");
                break;
        }

        // Check if the outcome is int or double
        if (result == (int) result)
            display.setText(String.valueOf((int) result)); // Convert to int if result is an integer
        else
            display.setText(String.valueOf(result)); // if not keep it as it (a double)
    }

    private void clear() {
        num1 = 0;
        num2 = 0;
        operator = '\u0000';  //clear code using null
        display.setText("");
    }

    public static void main (String[] args)
    {
        SimpleCalculator calculator = new SimpleCalculator();
    }
}
