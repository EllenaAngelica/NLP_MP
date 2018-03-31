/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cornelius
 */
public class Trie {
    private TrieNode root;
    private static Trie instance;
    
    private Trie(){
        this.root=new TrieNode();
    }
    
    public void insert(String word){
        word=word.toLowerCase();
        int len=word.length();
        TrieNode cur=root;
        for (int i = 0; i < len; i++) {
            char c=word.charAt(i);
            int idx=c=='-'?26:c-'a';
            if(c==' '){
                idx=27;
            }
            if(cur.getArr()[idx]==null){
                cur.getArr()[idx]=new TrieNode();
            }
            cur=cur.getArr()[idx];
        }
        cur.setEndOfWord();
    }
    
    public boolean search(String word){
        word=word.toLowerCase();
        int len=word.length();
        TrieNode cur=root;
        for (int i = 0; i < len; i++) {
            char c=word.charAt(i);
            int idx=c=='-'?26:c-'a';
            if(cur.getArr()[idx]==null){
                return false;
            }
            cur=cur.getArr()[idx];
        }
        return cur.isEndOfWord();
    }
    
    public static Trie getInstance(){
        if(instance==null){
            return instance=new Trie();
        }else{
            return instance;
        }
    }
}