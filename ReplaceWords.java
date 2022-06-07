import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/*
public class ReplaceWords {
    private Set<String> set;
    public String replaceWords(List<String> dictionary, String sentence) {
        set = new HashSet<>();
        for(String s: dictionary) set.add(s);
        StringBuilder sb = new StringBuilder();
        for(String s: sentence.split(" ")){
            String res = s;
            for(int i = 0 ; i < s.length() ; i++){
                if(set.contains(s.substring(0, i+1))){
                    res = s.substring(0, i+1);
                    break;
                }
            }
            
            sb.append(res);
            sb.append(" ");
        }
        
        return sb.toString().trim();
    }

}
*/

class ReplaceWords {
    static class Trie{
        public Trie() {
        root = new TrieNode();
    }
    
   
    class TrieNode{
        boolean isWord;
        TrieNode[] children = new TrieNode[26];
    }
    
    private TrieNode root;
    public void insert(String word) {
        TrieNode node = root;
        for(char c: word.toCharArray()){
            int index = c-'a';
            if(node.children[index] == null){
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isWord = true;
    }
    
    public String getSmallestPrefix(String word) {
        TrieNode node = root;
        StringBuilder sb = new StringBuilder();
        int wordIndex = 0;
        for(char c: word.toCharArray()){
            int index = c-'a';
            if(node.isWord){
                return word.substring(0,wordIndex);
            }
            else if(node.children[index] == null){
               return word;
            }
            else{
                sb.append(c);
                wordIndex++;
                node = node.children[index];
            }
        }
        return sb.toString();
    } 
    }
    
    public static String replaceWords(List<String> dictionary, String sentence) {
        Trie t = new Trie();
        for(String word: dictionary){
            t.insert(word);
        }
        StringBuilder sb = new StringBuilder();
        for(String s: sentence.split(" ")){
            sb.append(t.getSmallestPrefix(s));
            sb.append(" ");
        }
        return sb.toString().trim();
    }
    public static void main(String[] args) {
        List<String> dictionary = new ArrayList<>();
        dictionary.add("cat");
        dictionary.add("bat");
        dictionary.add("rat");

        String sentence = "the cattle was rattled by the battery";

        System.out.println(replaceWords(dictionary, sentence));
    }
}