import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Hangman {
	private static ArrayList<String> words = new ArrayList<String>();
	public static String word;
	public static String asterisk;
	public static int count = 0;
	public static String wordsFile = "";

	public Hangman() {

	}

	private static void initializeWords() {
		words.add("terminator");
		words.add("banana");
		words.add("computer");
		words.add("cow");
		words.add("rain");
		words.add("water");
	}


	public static List<String> readFileInList()
	{

		List<String> lines = Collections.emptyList();
		try
		{
			lines = Files.readAllLines(Paths.get(wordsFile), StandardCharsets.UTF_8);
		}

		catch (IOException e)
		{
			// do something
			e.printStackTrace();
		}
		return lines;
	}

	public static void initialize() {
		word = words.get((int) (Math.random() * words.size()));
		asterisk = new String(new char[word.length()]).replace("\0", "*");
		count = 0;
	}

	public static void main(String[] args) {
		int i = 0, j;
		String arg;
		char flag;
		initializeWords();

		try {
			while (i < args.length && args[i].startsWith("-")) {
				arg = args[i++];
				// use this type of check for arguments that require arguments
				if (arg.equals("-wordsfile")) {
					if (i < args.length) {
						wordsFile = args[i++];
						//words = readFileInList();
						DictionaryParser dictionaryParser = new DictionaryParser(wordsFile);
						words = dictionaryParser.parse();
					} else
						System.err.println("-output requires a filename");
				}
				// use this type of check for a series of flag arguments
				else {
					for (j = 1; j < arg.length(); j++) {
						flag = arg.charAt(j);
						switch (flag) {
							case 'c':
								runHangman();
								break;
							case 'g':
								runHangmanGui();
								break;
							default:
								System.err.println("ParseCmdLine: illegal option " + flag);
								break;
						}
					}
				}
			}
		}
		catch (IOException e) {

		}
	}
	public static void runHangmanGui() {
		Hangman_Gui gu = new Hangman_Gui();
	}
	public static void runHangman() {
		Scanner sc = new Scanner(System.in);
		boolean bContinue = true;
		initialize();

		while(bContinue) {
			while (count < 7 && asterisk.contains("*")) {
				System.out.println("Guess any letter in the word");
				System.out.println(asterisk);
				String guess = sc.next();
				hang(guess);
			}
			System.out.println("Do you want to continue(y/n) ?");
			String sContinue = sc.next();
			if (!sContinue.equalsIgnoreCase("y")) {
				bContinue = false;
			}
			else {
				initialize();
			}
		}
		sc.close();
	}

	public static String getnewAsterisk(String guess) {
		String newasterisk = "";
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == guess.charAt(0)) {
				newasterisk += guess.charAt(0);
			} else if (asterisk.charAt(i) != '*') {
				newasterisk += word.charAt(i);
			} else {
				newasterisk += "*";
			}
		}

		return newasterisk;
	}

	public static void hang(String guess) {
		String newasterisk = getnewAsterisk(guess);
		if (asterisk.equals(newasterisk)) {
			count++;
			System.out.println(hangmanTextImage());
		} else {
			asterisk = newasterisk;
		}
		if (asterisk.equals(word)) {
			System.out.println("Correct! You win! The word was " + word);
		}
	}

	public static String hangmanTextImage() {

		String hangmanImage = new String("");
		if (count == 1) {
			hangmanImage += "Wrong guess, try again\n";
			hangmanImage += " \n";
			hangmanImage += " \n";
			hangmanImage += " \n";
			hangmanImage += " \n";
			hangmanImage += " \n";
			hangmanImage += "___|___";
			hangmanImage += "\n";
		}
		if (count == 2) {
			hangmanImage += "Wrong guess, try again\n";
			hangmanImage += "   |\n";
			hangmanImage += "   |\n";
			hangmanImage += "   |\n";
			hangmanImage += "   |\n";
			hangmanImage += "   |\n";
			hangmanImage += "   |\n";
			hangmanImage += "   |\n";
			hangmanImage += "___|___";
		}
		if (count == 3) {
			hangmanImage += "Wrong guess, try again\n";
			hangmanImage += "   ____________\n";
			hangmanImage += "   |\n";
			hangmanImage += "   |\n";
			hangmanImage += "   |\n";
			hangmanImage += "   |\n";
			hangmanImage += "   |\n";
			hangmanImage += "   |\n";
			hangmanImage += "   |\n";
			hangmanImage += "___|___";
		}
		if (count == 4) {
			hangmanImage += "Wrong guess, try again\n";
			hangmanImage += "   ____________\n";
			hangmanImage += "   |          _|_\n";
			hangmanImage += "   |         /   \\\n";
			hangmanImage += "   |        |     |\n";
			hangmanImage += "   |         \\_ _/\n";
			hangmanImage += "   |\n";
			hangmanImage += "   |\n";
			hangmanImage += "   |\n";
			hangmanImage += "___|___";
		}
		if (count == 5) {
			hangmanImage += "Wrong guess, try again\n";
			hangmanImage += "   ____________\n";
			hangmanImage += "   |          _|_\n";
			hangmanImage += "   |         /   \\\n";
			hangmanImage += "   |        |     |\n";
			hangmanImage += "   |         \\_ _/\n";
			hangmanImage += "   |           |\n";
			hangmanImage += "   |           |\n";
			hangmanImage += "   |\n";
			hangmanImage += "___|___";
		}
		if (count == 6) {
			hangmanImage += "Wrong guess, try again\n";
			hangmanImage += "   ____________\n";
			hangmanImage += "   |          _|_\n";
			hangmanImage += "   |         /   \\\n";
			hangmanImage += "   |        |     |\n";
			hangmanImage += "   |         \\_ _/\n";
			hangmanImage += "   |           |\n";
			hangmanImage += "   |           |\n";
			hangmanImage += "   |          / \\\n ";
			hangmanImage += "___|___      /   \\\n";
		}
		if (count == 7) {
			hangmanImage += "GAME OVER!\n";
			hangmanImage += "   ____________";
			hangmanImage += "   |          _|_\n";
			hangmanImage += "   |         /   \\\n";
			hangmanImage += "   |        |     |\n";
			hangmanImage += "   |         \\_ _/\n";
			hangmanImage += "   |          _|_\n";
			hangmanImage += "   |         / | \\\n";
			hangmanImage += "   |          / \\ \n";
			hangmanImage += "___|___      /   \\\n";
			hangmanImage += "The word was " + word;
		}

		return hangmanImage;
	}

	public static String hangmanGuiImage() {
		return "hm_"+count+".png";
	}
}