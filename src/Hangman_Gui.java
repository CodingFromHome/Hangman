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
    private static BufferedImage myPicture;
    private static JLabel picLabel;
    private static JRadioButton radioButtonText;
    private static JRadioButton radioButtonImage;
    private static JRadioButton radioButtonAnimation;
    private static ButtonGroup buttonGroup;


    public Hangman_Gui () {
        Hangman_Initialize();
    }
    public static void Hangman_Initialize() {
        try {
        //Creating the Frame
        frame = new JFrame("Hangman");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        hangMan = new JTextArea();

        radioButtonText = new JRadioButton();
        radioButtonText.setText("Text");
        radioButtonImage = new JRadioButton();
        radioButtonImage.setText("Image");
        radioButtonAnimation = new JRadioButton();
        radioButtonAnimation.setText("Animation");
        buttonGroup = new ButtonGroup();
        //buttonGroup.("Hangman Image as");
        buttonGroup.add(radioButtonText);
        buttonGroup.add(radioButtonImage);
        buttonGroup.add(radioButtonAnimation);


            //Creating the panel at bottom and adding components
        panel = new JPanel(); // the panel is not visible in output
        asteriskText = new JLabel("");
        asteriskText.setFont(new Font("TimesRoman", Font.BOLD, 20));
        label = new JLabel("Guess a letter");
        guessLetter = new JTextField(1); // accepts upto 10 characters
        oK = new JButton("OK");
        newWord = new JButton("New Word");
        myPicture = ImageIO.read(new File(Hangman.hangmanGuiImage()));
        picLabel = new JLabel(new ImageIcon(myPicture));
        panel.add(asteriskText); // Components Added using Flow Layout
        panel.add(label);
        panel.add(guessLetter);
        panel.add(oK);
        panel.add(newWord);
        panel.add(radioButtonText);
        panel.add(radioButtonImage);
        panel.add(radioButtonAnimation);


        // Text Area at the Center


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
            JOptionPane.showMessageDialog(frame, e.getMessage());
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
       try {
            Hangman.initialize();
            asteriskText.setForeground(Color.BLACK);
            asteriskText.setText(Hangman.asterisk);
            guessLetter.setText("");
            myPicture = ImageIO.read(new File(Hangman.hangmanGuiImage()));
            picLabel.setIcon(new ImageIcon(myPicture));
       } catch (IOException e) {
           JOptionPane.showMessageDialog(frame, e.getMessage());
       }

    }

    private static void hangmanChecker (String guessedLetter) {
        try {
            if (guessedLetter.length() != 1) {
                hangMan.setText("Guess a letter ");
                return;
            }
            if (Hangman.count < 7 && Hangman.asterisk.contains("*")) {
                asteriskText.setText(Hangman.asterisk);
                String newasterisk = Hangman.getnewAsterisk(guessedLetter);

                if (Hangman.asterisk.equals(newasterisk)) {
                    Hangman.count++;
                    myPicture = ImageIO.read(new File(Hangman.hangmanGuiImage()));
                    picLabel.setIcon(new ImageIcon(myPicture));
                    if (Hangman.count == 7) {
                        hangMan.setText("Game over! The word was " + Hangman.word);
                    } else {
                        hangMan.setText("Wrong guess, try again");
                    }
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
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage());
        }
    }

    public static void main(String args[]) {
        Hangman_Initialize();

    }
}