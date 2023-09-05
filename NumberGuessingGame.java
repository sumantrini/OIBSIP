import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame {
    private int targetNumber;
    private int attemptsLeft;
    private int rounds;
    private int score;

    private JLabel statusLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JLabel roundLabel;
    private JLabel scoreLabel;

    public NumberGuessingGame() {
        targetNumber = generateRandomNumber();
        attemptsLeft = 10;
        rounds = 1;
        score = 0;

        setTitle("Number Guessing Game");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        statusLabel = new JLabel("Guess a number between 1 and 100.");
        guessField = new JTextField();
        guessButton = new JButton("Guess");
        roundLabel = new JLabel("Round: " + rounds);
        scoreLabel = new JLabel("Score: " + score);

        add(statusLabel);
        add(guessField);
        add(guessButton);
        add(roundLabel);
        add(scoreLabel);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (attemptsLeft > 0) {
                    int guess = Integer.parseInt(guessField.getText());
                    checkGuess(guess);
                } else {
                    statusLabel.setText("No attempts left. Start a new round.");
                }
            }
        });
    }

    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    private void checkGuess(int guess) {
        if (guess == targetNumber) {
            statusLabel.setText("Correct! You guessed the number.");
            score += attemptsLeft;
            scoreLabel.setText("Score: " + score);
            nextRound();
        } else if (guess < targetNumber) {
            statusLabel.setText("Try a higher number. Attempts left: " + (--attemptsLeft));
        } else {
            statusLabel.setText("Try a lower number. Attempts left: " + (--attemptsLeft));
        }
    }

    private void nextRound() {
        if (rounds < 5) {
            rounds++;
            targetNumber = generateRandomNumber();
            attemptsLeft = 10;
            roundLabel.setText("Round: " + rounds);
            statusLabel.setText("Guess a number between 1 and 100.");
        } else {
            statusLabel.setText("Game over. Final score: " + score);
            guessButton.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGame().setVisible(true);
            }
        });
    }
}
