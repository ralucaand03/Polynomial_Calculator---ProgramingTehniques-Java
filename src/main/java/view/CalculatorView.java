package view;

import controller.NumericValuesController;
import controller.OperationsController;
import model.Polynomial;
import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static controller.OperationsController.getPolynomial;

public class CalculatorView extends Component {
    private boolean isFirstTextFieldActive = true;
    private JFrame frame;
    private static JTextField firstPolynomialTextField;
    private static JTextField secondPolynomialTextField;
    public JTextField resultTextField;
    private JButton addButton;
    private JButton unused;
    private JButton subtractButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JButton equalsButton;
    private JButton derivateButton;
    private JButton integrateButton;
    private JButton buttonPlus;
    private JButton buttonMinus;
    private JButton buttonDelete;
    private JButton buttonSwap;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button0;
    private JButton buttonX;
    private JButton buttonPower;
    public CalculatorView(NumericValuesController numericValuesController, OperationsController operationsController) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Define the color variables
        Color colorButtonsRight = new Color(246, 189, 96); // Hex: #F6BD60
        Color colorButtons = new Color(113, 97, 239); // Hex: #7161EF
        Color  colorBackground= new Color(128, 207, 169); // Hex: #80CFA9
        Color colorText = new Color(233, 230, 255); // Hex: #E9E6FF

        // Set background color for the frame
        frame.getContentPane().setBackground(colorBackground);

        // Create main panel with BoxLayout for vertical arrangement
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        mainPanel.setBackground(colorBackground);

        // Create panel for the upper text and buttons
        JPanel upperPanel = new JPanel(new BorderLayout());
        upperPanel.setBackground(colorBackground);

        // Create label for the upper text
        JLabel titleLabel = new JLabel("<html><span style='font-size:24px'>ℙ𝕠𝕝𝕪𝕟𝕠𝕞𝕚𝕒𝕝 ℂ𝕒𝕝𝕔𝕦𝕝𝕒𝕥𝕠𝕣</span></html>");
        titleLabel.setForeground(colorText); // Set text color
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the text

        // Set maximum size
        Dimension maxLabelSize = new Dimension(400, Integer.MAX_VALUE);
        titleLabel.setMaximumSize(maxLabelSize);

        upperPanel.add(titleLabel, BorderLayout.NORTH);

        // Create buttons for derivate and integrate
        derivateButton = new JButton("Derivate");
        integrateButton = new JButton("Integrate");

        // Create panel for text fields
        JPanel textFieldsPanel = new JPanel();
        textFieldsPanel.setLayout(new GridLayout(3, 2, 10, 10));
        textFieldsPanel.setBackground(colorBackground);

        // Define font size for labels and text fields
        int labelFontSize = 14;
        int textFieldFontSize = 14;

        // Create labels with larger font size
        JLabel firstPolynomialLabel = new JLabel("First Polynomial");
        firstPolynomialLabel.setFont(new Font("Arial", Font.PLAIN, labelFontSize));

        JLabel secondPolynomialLabel = new JLabel("Second Polynomial");
        secondPolynomialLabel.setFont(new Font("Arial", Font.PLAIN, labelFontSize));

        JLabel resultLabel = new JLabel("Result");
        resultLabel.setFont(new Font("Arial", Font.PLAIN, labelFontSize));

        // Create text fields with larger font size
        firstPolynomialTextField = new JTextField();
        firstPolynomialTextField.setFont(new Font("Arial", Font.PLAIN, textFieldFontSize));

        secondPolynomialTextField = new JTextField();
        secondPolynomialTextField.setFont(new Font("Arial", Font.PLAIN, textFieldFontSize));

        resultTextField = new JTextField();
        resultTextField.setFont(new Font("Arial", Font.PLAIN, textFieldFontSize));

        // Set text color for the JLabels
        firstPolynomialLabel.setForeground(colorText);
        secondPolynomialLabel.setForeground(colorText);
        resultLabel.setForeground(colorText);

        // Add labels and text fields to the text fields panel
        textFieldsPanel.add(firstPolynomialLabel);
        textFieldsPanel.add(firstPolynomialTextField);
        textFieldsPanel.add(secondPolynomialLabel);
        textFieldsPanel.add(secondPolynomialTextField);
        textFieldsPanel.add(resultLabel);
        textFieldsPanel.add(resultTextField);

        // Create panel for buttons
        JPanel buttonsPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        buttonsPanel.setBackground(colorBackground);

        // Create arithmetic operation buttons
        addButton = new JButton("Add");
        subtractButton = new JButton("Subtract");
        multiplyButton = new JButton("Multiply");
        divideButton = new JButton("Divide");
        derivateButton =new JButton("Derivate");
        integrateButton = new JButton("Integrate");
        unused = new JButton(":)");
        equalsButton = new JButton("=");
        buttonPlus = new JButton("+");
        buttonMinus  = new JButton("-");
        buttonSwap = new JButton("SWAP");
        buttonDelete = new JButton("DEL");

        // Set the font for all buttons
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFontRight = new Font("Arial", Font.BOLD, 18);// Change the font family, style, and size as needed
        addButton.setFont(buttonFont);
        subtractButton.setFont(buttonFont);
        multiplyButton.setFont(buttonFont);
        divideButton.setFont(buttonFont);
        derivateButton.setFont(buttonFont);
        integrateButton.setFont(buttonFont);
        unused.setFont(buttonFont);
        equalsButton.setFont(buttonFont);
        buttonPlus.setFont(buttonFontRight);
        buttonMinus.setFont(buttonFontRight);
        buttonSwap.setFont(buttonFont);
        buttonDelete.setFont(buttonFont);

        // Set background color for the arithmetic operation buttons
        addButton.setBackground(colorButtons);
        derivateButton.setBackground(colorButtons);
        integrateButton.setBackground(colorButtons);
        subtractButton.setBackground(colorButtons);
        multiplyButton.setBackground(colorButtons);
        unused.setBackground(colorButtons);
        divideButton.setBackground(colorButtons);
        equalsButton.setBackground(colorButtons);
        buttonDelete.setBackground(colorButtonsRight);
        buttonSwap.setBackground(colorButtonsRight);
        buttonPlus.setBackground(colorButtonsRight);
        buttonMinus.setBackground(colorButtonsRight);

        // Set text color for arithmetic operation buttons
        addButton.setForeground(colorText);
        derivateButton.setForeground(colorText);
        unused.setForeground(colorText);
        integrateButton.setForeground(colorText);
        subtractButton.setForeground(colorText);
        multiplyButton.setForeground(colorText);
        divideButton.setForeground(colorText);
        equalsButton.setForeground(colorText);
        buttonDelete.setForeground(colorText);
        buttonSwap.setForeground(colorText);
        buttonPlus.setForeground(colorText);
        buttonMinus.setForeground(colorText);

        // Add arithmetic operation buttons to the buttons panel
        buttonsPanel.add(addButton);
        buttonsPanel.add(subtractButton);
        buttonsPanel.add(multiplyButton);
        buttonsPanel.add(divideButton);
        buttonsPanel.add(derivateButton);
        buttonsPanel.add(integrateButton);

        // Create panel for numeric buttons
        JPanel numericButtonsPanel = new JPanel(new GridLayout(4, 3, 10, 10));
        numericButtonsPanel.setBackground(colorBackground);

        // Set preferred size for numeric buttons
        Dimension buttonSize = new Dimension(80, 60); // Adjust width and height as needed

        // Set font size for numeric buttons
        Font numericButtonFont = new Font("Arial", Font.PLAIN, 18);

        // Create numeric buttons
        button1 = new JButton("1");
        button1.setPreferredSize(buttonSize);
        button1.setFont(numericButtonFont); // Set font size
        button2 = new JButton("2");
        button2.setPreferredSize(buttonSize);
        button2.setFont(numericButtonFont); // Set font size
        button3 = new JButton("3");
        button3.setPreferredSize(buttonSize);
        button3.setFont(numericButtonFont); // Set font size
        button4 = new JButton("4");
        button4.setPreferredSize(buttonSize);
        button4.setFont(numericButtonFont); // Set font size
        button5 = new JButton("5");
        button5.setPreferredSize(buttonSize);
        button5.setFont(numericButtonFont); // Set font size
        button6 = new JButton("6");
        button6.setPreferredSize(buttonSize);
        button6.setFont(numericButtonFont); // Set font size
        button7 = new JButton("7");
        button7.setPreferredSize(buttonSize);
        button7.setFont(numericButtonFont); // Set font size
        button8 = new JButton("8");
        button8.setPreferredSize(buttonSize);
        button8.setFont(numericButtonFont); // Set font size
        button9 = new JButton("9");
        button9.setPreferredSize(buttonSize);
        button9.setFont(numericButtonFont); // Set font size
        button0 = new JButton("0");
        button0.setPreferredSize(buttonSize);
        button0.setFont(numericButtonFont); // Set font size
        buttonX = new JButton("x");
        buttonX.setPreferredSize(buttonSize);
        buttonX.setFont(buttonFontRight); // Set font size
        buttonPower = new JButton("^");
        buttonPower.setPreferredSize(buttonSize);
        buttonPower.setFont(buttonFontRight); // Set font size

        // Set background color for the numeric buttons
        button1.setBackground(colorText);
        button2.setBackground(colorText);
        button3.setBackground(colorText);
        button4.setBackground(colorText);
        button5.setBackground(colorText);
        button6.setBackground(colorText);
        button7.setBackground(colorText);
        button8.setBackground(colorText);
        button9.setBackground(colorText);
        button0.setBackground(colorText);
        buttonX.setBackground(colorButtonsRight);
        buttonPower.setBackground(colorButtonsRight);

        // Set text color for the numeric buttons
        button1.setForeground(colorBackground);
        button2.setForeground(colorBackground);
        button3.setForeground(colorBackground);
        button4.setForeground(colorBackground);
        button5.setForeground(colorBackground);
        button6.setForeground(colorBackground);
        button7.setForeground(colorBackground);
        button8.setForeground(colorBackground);
        button9.setForeground(colorBackground);
        button0.setForeground(colorBackground);
        buttonX.setForeground(colorText);
        buttonPower.setForeground(colorText);

        // Add numeric buttons to the numeric buttons panel
        numericButtonsPanel.add(button7);
        numericButtonsPanel.add(button8);
        numericButtonsPanel.add(button9);
        numericButtonsPanel.add(buttonPlus);
        numericButtonsPanel.add(button4);
        numericButtonsPanel.add(button5);
        numericButtonsPanel.add(button6);
        numericButtonsPanel.add(buttonMinus);
        numericButtonsPanel.add(button1);
        numericButtonsPanel.add(button2);
        numericButtonsPanel.add(button3);
        numericButtonsPanel.add(buttonDelete);
        numericButtonsPanel.add(button0);
        numericButtonsPanel.add(buttonX);
        numericButtonsPanel.add(buttonPower);
        numericButtonsPanel.add(buttonSwap);

        // Add action listeners for operation buttons
        buttonPlus.addActionListener(e -> numericValuesController.appendDigit("+", getActiveTextField()));
        buttonMinus.addActionListener(e -> numericValuesController.appendDigit("-", getActiveTextField()));
        buttonDelete.addActionListener(e -> numericValuesController.deleteLastCharacter(getActiveTextField()));

        // Add action listeners for instructiona buttons
        addButton.addActionListener(e -> operationsController.addButtonClicked());
        subtractButton.addActionListener(e -> operationsController.subtractButtonClicked());
        multiplyButton.addActionListener(e -> operationsController.multiplyButtonClicked());
        divideButton.addActionListener(e -> operationsController.divideButtonClicked());
        derivateButton.addActionListener(e -> operationsController.derivateButtonClicked());
        integrateButton.addActionListener(e -> operationsController.integrateButtonClicked());

        // Add action listeners for numeric buttons
        button1.addActionListener(e -> numericValuesController.appendDigit("1", getActiveTextField()));
        button2.addActionListener(e -> numericValuesController.appendDigit("2", getActiveTextField()));
        button3.addActionListener(e -> numericValuesController.appendDigit("3", getActiveTextField()));
        button4.addActionListener(e -> numericValuesController.appendDigit("4", getActiveTextField()));
        button5.addActionListener(e -> numericValuesController.appendDigit("5", getActiveTextField()));
        button6.addActionListener(e -> numericValuesController.appendDigit("6", getActiveTextField()));
        button7.addActionListener(e -> numericValuesController.appendDigit("7", getActiveTextField()));
        button8.addActionListener(e -> numericValuesController.appendDigit("8", getActiveTextField()));
        button9.addActionListener(e -> numericValuesController.appendDigit("9", getActiveTextField()));
        button0.addActionListener(e -> numericValuesController.appendDigit("0", getActiveTextField()));
        buttonPower.addActionListener(e -> numericValuesController.appendDigit("^", getActiveTextField()));
        buttonX.addActionListener(e -> numericValuesController.appendDigit("X", getActiveTextField()));

        // Add action listener for buttonSwap
        buttonSwap.addActionListener(e -> {
            isFirstTextFieldActive = !isFirstTextFieldActive; // Toggle the value
            if (isFirstTextFieldActive) {
                firstPolynomialTextField.requestFocusInWindow(); // Set focus to the first text field
            } else {
                secondPolynomialTextField.requestFocusInWindow(); // Set focus to the second text field
            }
        });

        // Add panels to the main panel
        mainPanel.add(upperPanel);
        mainPanel.add(Box.createVerticalStrut(20)); // Add space between panels
        mainPanel.add(textFieldsPanel);
        mainPanel.add(Box.createVerticalStrut(20)); // Add space between panels
        mainPanel.add(buttonsPanel);
        mainPanel.add(Box.createVerticalStrut(20)); // Add space between panels
        mainPanel.add(numericButtonsPanel);

        // Add main panel to the frame
        frame.add(mainPanel);
        frame.setSize(500, 700);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }

    //---------------------------------------------------------------------------------------------------
    public JTextField getFirstPolynomialTextField() {
        return firstPolynomialTextField;
    }
    public void setFirstPolynomialTextField(JTextField firstPolynomialTextField) {
        this.firstPolynomialTextField = firstPolynomialTextField;
    }
    public JTextField getSecondPolynomialTextField() {
        return secondPolynomialTextField;
    }
    public void setSecondPolynomialTextField(JTextField secondPolynomialTextField) {
        this.secondPolynomialTextField = secondPolynomialTextField;
    }
    private JTextField getActiveTextField() {
        return isFirstTextFieldActive ? firstPolynomialTextField : secondPolynomialTextField;
    }
    //---------------------------------------------------------------------------------------------------

    //---------------------------------------------------------------------------------------------------
    public static Polynomial getFirstPolynomial() {
        String input = firstPolynomialTextField.getText();
        return getPolynomial(input);
    }
    public static Polynomial getSecondPolynomial() {
        String input = secondPolynomialTextField.getText();
        return getPolynomial(input);
    }
    public JTextField getResultTextField() {
        return resultTextField;
    }
    public void setResultTextField(JTextField resultTextField) {
        this.resultTextField = resultTextField;
    }
    public void setResultTextFieldString(String resultText) {
        if (resultText != null) {
            resultTextField.setText(resultText);
            Container parent = resultTextField.getParent();
            if (parent != null) {
                parent.revalidate();
                parent.repaint();
            }
        }
    }
}