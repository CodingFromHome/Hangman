import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryParserTest {

    @org.junit.jupiter.api.Test
    void parseTest() {
        try {
            DictionaryParser dictionaryParser = new DictionaryParser("OxfordEnglishDictionary.txt");
            assertEquals(13665,dictionaryParser.parse().size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @org.junit.jupiter.api.Test
    void parseExceptionTest() {
        try {
            DictionaryParser dictionaryParser = new DictionaryParser("OxfordEnglishDictionary.txt");
            //assertThrows(FileNotFoundException,)
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}