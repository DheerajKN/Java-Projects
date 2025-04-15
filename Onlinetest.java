import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class OnlineJavaTest extends JFrame {
    private JLabel questionLabel;
    private JRadioButton[] options;
    private JButton nextButton, bookmarkButton, resultButton;
    private ButtonGroup optionGroup;
    private int currentQuestion = 0;
    private int score = 0;
    private List<Integer> bookmarkedQuestions = new ArrayList<>();
    
    // Question data
    private final String[] questions = {
        "Which one among these is not a primitive datatype?",
        "Which class is available to all the class automatically?",
        "Which package is directly available to our class without importing it?",
        "String class is defined in which package?",
        "Which institute is best for java coaching?",
        "Which one among these is not a keyword?",
        "Which one among these is not a class?",
        "Which one among these is not a function of Object class?",
        "Which function is not present in Applet class?",
        "Which one among these is not a valid component?"
    };
    
    private final String[][] choices = {
        {"int", "Float", "boolean", "char"},
        {"Swing", "Applet", "Object", "ActionEvent"},
        {"swing", "applet", "net", "lang"},
        {"lang", "Swing", "Applet", "awt"},
        {"Dheeraj Notes", "Aptech", "SSS IT", "jtek"},
        {"class", "int", "get", "if"},
        {"Swing", "Actionperformed", "ActionEvent", "Button"},
        {"toString", "finalize", "equals", "getDocumentBase"},
        {"init", "main", "start", "destroy"},
        {"JButton", "JList", "JButtonGroup", "JTextArea"}
    };
    
    private final int[] correctAnswers = {1, 2, 3, 0, 2, 2, 1, 3, 1, 2};
    
    public OnlineJavaTest() {
        super("Online Java Test");
        initializeUI();
        setupQuestion();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        
        // Question panel
        JPanel questionPanel = new JPanel(new BorderLayout());
        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        questionPanel.add(questionLabel, BorderLayout.NORTH);
        questionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        
        // Options panel
        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        optionGroup = new ButtonGroup();
        options = new JRadioButton[4];
        
        for (int i = 0; i < options.length; i++) {
            options[i] = new JRadioButton();
            options[i].setFont(new Font("Arial", Font.PLAIN, 13));
            optionGroup.add(options[i]);
            optionsPanel.add(options[i]);
        }
        
        questionPanel.add(optionsPanel, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        nextButton = new JButton("Next");
        bookmarkButton = new JButton("Bookmark");
        resultButton = new JButton("View Result");
        
        nextButton.addActionListener(this::handleNext);
        bookmarkButton.addActionListener(this::handleBookmark);
        resultButton.addActionListener(this::showResult);
        
        buttonPanel.add(bookmarkButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(resultButton);
        
        add(questionPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void setupQuestion() {
        if (currentQuestion < questions.length) {
            questionLabel.setText("Question " + (currentQuestion + 1) + ": " + questions[currentQuestion]);
            
            for (int i = 0; i < options.length; i++) {
                options[i].setText(choices[currentQuestion][i]);
                options[i].setSelected(false);
            }
            
            // Update button states
            nextButton.setEnabled(currentQuestion < questions.length - 1);
            resultButton.setEnabled(currentQuestion == questions.length - 1);
        }
    }
    
    private void handleNext(ActionEvent e) {
        checkAnswer();
        currentQuestion++;
        setupQuestion();
    }
    
    private void handleBookmark(ActionEvent e) {
        if (!bookmarkedQuestions.contains(currentQuestion)) {
            bookmarkedQuestions.add(currentQuestion);
            JOptionPane.showMessageDialog(this, "Question " + (currentQuestion + 1) + " bookmarked!");
        } else {
            JOptionPane.showMessageDialog(this, "Question already bookmarked!");
        }
    }
    
    private void showResult(ActionEvent e) {
        checkAnswer();
        
        String result = "Test Completed!\n\n";
        result += "Your Score: " + score + " out of " + questions.length + "\n";
        result += "Percentage: " + (score * 100 / questions.length) + "%\n\n";
        
        if (score == questions.length) {
            result += "Perfect! You got all questions right!";
        } else if (score >= questions.length * 0.7) {
            result += "Good job! You passed with a good score.";
        } else if (score >= questions.length * 0.5) {
            result += "You passed, but consider reviewing Java concepts.";
        } else {
            result += "You need more practice with Java fundamentals.";
        }
        
        JOptionPane.showMessageDialog(this, result);
        System.exit(0);
    }
    
    private void checkAnswer() {
        for (int i = 0; i < options.length; i++) {
            if (options[i].isSelected() && i == correctAnswers[currentQuestion]) {
                score++;
                break;
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new OnlineJavaTest().setVisible(true);
        });
    }
}
