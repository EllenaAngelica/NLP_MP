
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cornelius
 */
public class TrieNode {

    private TrieNode[] children;
    private boolean endOfWord;

    public TrieNode() {
        this.children = new TrieNode[28];
    }

    public TrieNode[] getArr() {
        return children;
    }

    public boolean isEndOfWord() {
        return endOfWord;
    }
    
    public void setEndOfWord(){
        this.endOfWord=true;
    }
}
