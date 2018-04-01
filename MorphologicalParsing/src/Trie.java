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
    public static String hasilKata="";
    
    private Trie(){
        this.root=new TrieNode(null);
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
                cur.getArr()[idx]=new TrieNode(cur);
            }
            cur=cur.getArr()[idx];
        }
        cur.setEndOfWord();
    }
    
    public boolean search(String word){
        hasilKata="";
        word=word.toLowerCase();
        int len=word.length();
        
        boolean adaSpasi=false;
                
        TrieNode cur=root;
        for (int i = 0; i < len; i++) {
            char c=word.charAt(i);
            int idx=c=='-'?26:c-'a';
            if(c==' '){
                idx=27;
            }
            if(adaSpasi){
                hasilKata+=c;
            }
            
            if(cur.getArr()[idx]==null){
                if(cur.getArr()[27]!=null){
                                            
                        cur=cur.getArr()[27];
                        if(cur.getArr()[idx]!=null){
                            cur=cur.getArr()[idx];
                            adaSpasi=true;
                            hasilKata=word.substring(0,idx-1)+" "+c;
                        }
                        else{
                            return false;
                        }
                    }
                    else{
                        return false;
                    }
            }
            else{
                cur=cur.getArr()[idx];
            }
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
