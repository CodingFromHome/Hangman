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
public class Hangman_Gui extends JFrame {
    private static JPanel panel;
    private static JLabel asteriskText;
    private static JLabel label;
    private static JTextField guessLetter;
    private static JButton oK;
    private static JButton newWord;
    private static JTextArea hangMan;
    private static BufferedImage myPicture;
    private static JLabel picLabel;
    private static JPanel radioButtonGroupPanel;
    private static JRadioButton radioButtonText;
    private static JRadioButton radioButtonImage;
    private static JRadioButton radioButtonAnimation;
    private static ButtonGroup buttonGroup;
    private static JTextArea definition;
    // menubar
    static JMenuBar menuBar;
    // JMenu
    static JMenu help;
    // Menu items
    static JMenuItem about;
    private static MenuListener menuListener;

    public Hangman_Gui () {
        Hangman_Initialize();
    }
    public void Hangman_Initialize() {
        try {
            //Creating the Frame
            setTitle("Hangman");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(600, 400);



            hangMan = new JTextArea();
            definition = new JTextArea();
            MenuListener menuListener = new MenuListener();
            definition.setSize(400,200);

            radioButtonText = new JRadioButton();
            radioButtonText.setText("Text");
            radioButtonImage = new JRadioButton();
            radioButtonImage.setText("Image");
            radioButtonImage.setSelected(true);
            radioButtonAnimation = new JRadioButton();
            radioButtonAnimation.setText("Animation");
            buttonGroup = new ButtonGroup();
            //buttonGroup.("Hangman Image as");
            buttonGroup.add(radioButtonText);
            buttonGroup.add(radioButtonImage);
            buttonGroup.add(radioButtonAnimation);
            radioButtonGroupPanel = new JPanel();
            radioButtonGroupPanel.setBorder(BorderFactory.createTitledBorder("Hangman Image as"));
            radioButtonGroupPanel.add(radioButtonText);
            radioButtonGroupPanel.add(radioButtonImage);
            radioButtonGroupPanel.add(radioButtonAnimation);

            // Creating the panel at bottom and adding components
            panel = new JPanel(); // the panel is not visible in output
            asteriskText = new JLabel("");
            asteriskText.setFont(new Font("TimesRoman", Font.BOLD, 20));
            label = new JLabel("Guess a letter");
            guessLetter = new JTextField(1); // accepts upto 10 characters
            oK = new JButton("OK");
            newWord = new JButton("New Word");
            picLabel = new JLabel();
            displayHangman();
            panel.add(asteriskText); // Components Added using Flow Layout
            panel.add(label);
            panel.add(guessLetter);
            panel.add(oK);
            panel.add(newWord);
            panel.add(radioButtonGroupPanel);

            menuBar = new JMenuBar();

            // create a menu
            help = new JMenu("Help");

            // create menuitems
            about = new JMenuItem("About");

            about.addActionListener(menuListener);
            // add menu items to menu
            help.add(about);

            // add menu to menu bar
            menuBar.add(help);

            // add menubar to frame
            setJMenuBar(menuBar);

            // Text Area at the Center

            //Adding Components to the frame.
            getContentPane().add(BorderLayout.SOUTH, panel);
            getContentPane().add(BorderLayout.CENTER, hangMan);
            getContentPane().add(BorderLayout.WEST, picLabel);
            getContentPane().add(BorderLayout.EAST, definition);
            setVisible(true);

            initializeGUI();

            oK.addActionListener(new ButtonClickListener());
            newWord.addActionListener(new NewWordClickListener());
            guessLetter.addKeyListener(new LetterGuessedListener());
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private static void displayHangman() throws IOException {
        if (radioButtonText.isSelected()) {
            hangMan.setText(Hangman.hangmanTextImage());
            hangMan.setVisible(true);
            picLabel.setVisible(false);
        }
        else if (radioButtonImage.isSelected()) {
            myPicture = ImageIO.read(new File(Hangman.hangmanGuiImage()));
            picLabel.setIcon(new ImageIcon(myPicture));
            hangMan.setVisible(false);
            picLabel.setVisible(true);
        }
    }

    private class MenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            JDialog dialog = new JDialog();
            JLabel label = new JLabel("This is About dialog");
            dialog.add(label);
            dialog.setSize(300, 300);
            dialog.setVisible(true);
        }
    }

    private  class LetterGuessedListener implements KeyListener {

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

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String guessedLetter = guessLetter.getText();
            hangmanChecker(guessedLetter);
        }
    }

    private class NewWordClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            initializeGUI();
        }
    }
    private void initializeGUI () {
       try {
            Hangman.initialize();
            asteriskText.setForeground(Color.BLACK);
            asteriskText.setText(Hangman.asterisk);
            guessLetter.setText("");
            definition.setText("Definition: \n");
            Hangman.dictionaryWord.definitions.forEach((n -> definition.append(String.format("%s \n",n))));
            //for (int i = 0; i<Hangman.dictionaryWord.definitions.size();i++) {
                //String definitionString = String.format("%d. %s \n",i+1,Hangman.dictionaryWord.definitions.get(i));
               // definition.append(definitionString);

           // }
            definition.append("\n \n Root: "+Hangman.dictionaryWord.root);
           definition.setLineWrap(true);
            displayHangman();
       } catch (IOException e) {
           JOptionPane.showMessageDialog(this, e.getMessage());
       }

    }

    private void hangmanChecker (String guessedLetter) {
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
                    displayHangman();
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
        catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public void main(String args[]) {
        Hangman_Initialize();
    }
}