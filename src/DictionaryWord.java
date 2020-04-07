import java.util.ArrayList;

public class DictionaryWord {
    public enum WordType { VERB, NOUN, ADVERB, ADJECTIVE}

    public String word;
    public ArrayList<String> definitions;
    public WordType wordTyoe;
    public String root;
}
