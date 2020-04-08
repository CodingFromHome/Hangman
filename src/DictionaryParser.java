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
    private BufferedReader bufRead;

    ArrayList<String> dictionaryWords = new ArrayList<String>();


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
    public ArrayList<String> parse() throws IOException{
        String currentLine = null;

        // Filtering only nouns form the dictionary words
        while ((currentLine = bufRead.readLine()) != null ) {

            if (currentLine.contains(" n. ")) {
                String[] stringTokens = currentLine.split("( n. |\\.|\\[)");

                // Skip empty lines in the file
                if (stringTokens.length > 0) {

                    String firstToken = stringTokens[0];
                    // Check valid text and skip words that contain one of (-, numbers,')
                    if (firstToken.length() > 1 && !firstToken.contains("-")) {
                        DictionaryWord dictionaryWord = new DictionaryWord();
                        dictionaryWord.word = stringTokens[0].trim();
                        dictionaryWord.word = dictionaryWord.word.toLowerCase();
                        dictionaryWord.wordType = DictionaryWord.WordTypes.NOUN;
                        dictionaryWords.add(dictionaryWord.word);
                    }

                }
            }
        }
        return dictionaryWords;
    }
}


