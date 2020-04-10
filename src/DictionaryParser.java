import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is the main method which makes use of addNum method.
 */
public class DictionaryParser {
    private FileReader input;
    private static BufferedReader bufRead;
    private static ArrayList<DictionaryWord> dictionaryWords = new ArrayList<DictionaryWord>();


    public DictionaryParser(String fileName) throws FileNotFoundException {
        input = new FileReader(fileName);
        bufRead = new BufferedReader(input);
    }

    /**
     * This parses the 'Oxford dictinary' file format into word and definiitons.
     * @return ArrayList<String> List of words (noun)
     * @exception IOException On input error.
     * @see IOException
     */
    public static ArrayList<DictionaryWord> parse() throws IOException{
        String currentLine = null;

        // Filtering only nouns form the dictionary words
        while ((currentLine = bufRead.readLine()) != null ) {

            if (currentLine.contains(" n. ")) {
                String[] stringTokens = currentLine.split("( n. |\\[)");

                // Skip empty lines in the file
                if (stringTokens.length > 1) {

                    String firstToken = stringTokens[0].trim();
                    // Check valid text and skip words that contain one of (-, numbers,')
                    if (firstToken.length() > 1 && !firstToken.contains("-") && !firstToken.contains(" ")) {
                        DictionaryWord dictionaryWord = new DictionaryWord();
                        dictionaryWord.word = stringTokens[0].trim();
                        dictionaryWord.word = dictionaryWord.word.toLowerCase();
                        dictionaryWord.wordType = DictionaryWord.WordTypes.NOUN;
                        for (int i = 1;i<stringTokens.length;i++) {
                            String[] definitionTokens = stringTokens[i].split("\\d+");
                            for (int j = 0;j<definitionTokens.length;j++) {
                                if (!definitionTokens[j].contains("(") && !definitionTokens[j].contains(")")) {
                                    dictionaryWord.definitions.add(definitionTokens[j]);

                                }
                            }
                        }
                        String rootPart = stringTokens[stringTokens.length-1];
                        dictionaryWord.root = rootPart.substring(0,rootPart.length()-1);
                        dictionaryWords.add(dictionaryWord);
                    }

                }
            }
        }
        return dictionaryWords;
    }
}


