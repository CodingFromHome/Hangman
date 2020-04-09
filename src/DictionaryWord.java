import java.util.ArrayList;

public class DictionaryWord {
    public enum WordTypes { VERB, NOUN, ADVERB, ADJECTIVE}

    public String word;
    public ArrayList<String> definitions = new ArrayList<String>();
    public WordTypes wordType;
    public String root;

}
