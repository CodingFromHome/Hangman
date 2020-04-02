//Usually you will require both swing and awt packages
// even if you are working with just swings.
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// Swing UI for Hangman
// Learning to use Swing
public class Hangman_Gui {
    private static JFrame frame;
    private static JPanel panel;
    private static JLabel asteriskText;
    private static JLabel label;
    private static JTextField guessLetter;
    private static JButton oK;
    private static JButton newWord;
    private static JTextArea hangMan;
    public void Hangman_Gui () {

    }
    public static void Hangman_Initialize() {
        try {
        //Creating the Frame
        frame = new JFrame("Hangman");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        //Creating the panel at bottom and adding components
        panel = new JPanel(); // the panel is not visible in output
        asteriskText = new JLabel("");
        asteriskText.setFont(new Font("TimesRoman", Font.BOLD, 20));
        label = new JLabel("Guessa a letter");
        guessLetter = new JTextField(1); // accepts upto 10 characters
        oK = new JButton("OK");
        newWord = new JButton("New Word");

            BufferedImage myPicture = ImageIO.read(new File("hangmn.png"));

        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        panel.add(asteriskText); // Components Added using Flow Layout
        panel.add(label);
        panel.add(guessLetter);
        panel.add(oK);
        panel.add(newWord);


        // Text Area at the Center
        hangMan = new JTextArea();

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, hangMan);
        frame.getContentPane().add(BorderLayout.NORTH, picLabel);
        frame.setVisible(true);

        initializeGUI();

        oK.addActionListener(new ButtonClickListener());
        newWord.addActionListener(new NewWordClickListener());
        guessLetter.addKeyListener(new LetterGuessedListener());
        }
        catch (IOException e) {

        }
    }

    private static class LetterGuessedListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent ke) {
            String guessLetter = String.valueOf(ke.getKeyChar());
            hangmanChecker(guessLetter);
        }

        @Override
        public void keyPressed(KeyEvent ke) {
        }

        @Override
        public void keyReleased(KeyEvent ke) {
        }
    }

    private static class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String guessedLetter = guessLetter.getText();
            hangmanChecker(guessedLetter);
        }
    }

    private static class NewWordClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            initializeGUI();
        }
    }
    private static void initializeGUI () {
        Hangman.initialize();
        asteriskText.setForeground(Color.BLACK);
        asteriskText.setText(Hangman.asterisk);
        guessLetter.setText("");
    }

    private static void hangmanChecker (String guessedLetter) {

        if (guessedLetter.length() != 1) {
            hangMan.setText("Guess a letter ");
            return;
        }
        if (Hangman.count < 7 && Hangman.asterisk.contains("*")) {
            asteriskText.setText(Hangman.asterisk);
            String newasterisk = Hangman.getnewAsterisk(guessedLetter);

            if (Hangman.asterisk.equals(newasterisk)) {
                Hangman.count++;
                hangMan.setText(Hangman.hangmanGuiImage());
                asteriskText.setForeground(Color.RED);
            } else {
                Hangman.asterisk = newasterisk;
                asteriskText.setText(Hangman.asterisk);
                asteriskText.setForeground(Color.GREEN);
            }
            if (Hangman.asterisk.equals(Hangman.word)) {
                hangMan.setText("Correct! You win! The word was " + Hangman.word);
            }
        }
    }

    public static void main(String args[]) {
        Hangman_Initialize();

    }
}