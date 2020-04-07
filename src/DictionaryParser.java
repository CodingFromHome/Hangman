import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DictionaryParser {
    private FileReader input;
    private BufferedReader bufRead;

    ArrayList<String> dictionaryWords = new ArrayList<String>();

    public DictionaryParser(String fileName) throws FileNotFoundException {
        input = new FileReader(fileName);
        bufRead = new BufferedReader(input);
    }

    public ArrayList<String> parse() throws IOException{
        String currentLine = null;
        while ((currentLine = bufRead.readLine()) != null) {
            String[] stringTokens = currentLine.split(" ");
            if (stringTokens.length > 0) {

                String firstToken = stringTokens[0];

                //check valid text and skip words with -,',numbers
                if (firstToken.length() > 1 && !firstToken.contains("-")) {
                    firstToken = firstToken.toLowerCase();
                    dictionaryWords.add(firstToken);
                }

                //String secondToken = stringTokens[1];
            }
        }
        return dictionaryWords;
    }
}


