/*
Time Complexity: Insert -> O(n) where n is the length of the word to be inserted
                 Search -> O(n) where n is the length of the word to be searched
                 StartWith -> O(n) where n is the length of prefix
Space complexity: O(n) where n is the length of the longest word in the input string 
*/

class Trie {
    private TrieNode root;
    private final int size = 26;
    class TrieNode{
        boolean isWord;
        TrieNode[] children = new TrieNode[size];
        
        public TrieNode(){
            this.isWord = false;
            for(int i = 0 ; i < size; i++){
                this.children[i] = null;
            }
        }
    }
    
    public Trie() {
        root = new TrieNode();
    }
    
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
    
    public boolean search(String word) {
        TrieNode node = root;
        for(char c: word.toCharArray()){
            int index = c-'a';
            if(node.children[index] != null){
                node = node.children[index];
            }else{
                return false;
            }
        }
        return node.isWord;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for(char c: prefix.toCharArray()){
            int index = c-'a';
            if(node.children[index] != null){
                node = node.children[index];
            }else{
                return false;
            }
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */