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
    String myLine = null;
    ArrayList<String> dictionaryWords = new ArrayList<String>();

    public DictionaryParser(String fileName) throws FileNotFoundException {
        input = new FileReader(fileName);
        bufRead = new BufferedReader(input);
    }

    public ArrayList<String> parse() throws IOException{
        while ((myLine = bufRead.readLine()) != null) {
            String[] array1 = myLine.split(" ");
            if (array1.length > 0 && array1[0].length() > 1 && !array1[0].contains("-")) {
                String dicWord = array1[0].trim();
                dicWord = dicWord.toLowerCase();
                System.out.println(dicWord);
                dictionaryWords.add(dicWord);
            }
        }
        return dictionaryWords;
    }
}


