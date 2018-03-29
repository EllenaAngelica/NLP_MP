
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author WIN 10
 */
public class MorphologicalParser {
    ArrayList<String> pref;
    ArrayList<String> suf;
    public MorphologicalParser(ArrayList<String> pref,ArrayList<String> suf){
        this.pref=pref;
        this.suf=suf;
        pref.sort(new StringComparator());
        Collections.reverse(pref);
        suf.sort(new StringComparator());
        Collections.reverse(suf);
    }
    
    public String cekBerimbuhan(String input){
         String hasil="";   
         String in = input;
         if(Trie.getInstance().search(in)){
             return in;
         }
        for(int i =0;i<pref.size();i++){
          
            if(in.startsWith(pref.get(i))){
                //System.out.println(pref.get(i));
                if("meng".equals(pref.get(i))){
                    //System.out.println(" "+in.charAt(pref.get(i).length()));
                    if(in.charAt(pref.get(i).length())=='e'){
                        String temp = in.substring(pref.get(i).length()+1);
                        if(Trie.getInstance().search(temp)){
                            in=temp;
                        }
                        else{
                            in = "k"+in.substring(pref.get(i).length());
                        }
                        
                    }
                    else{
                        in=in.substring(pref.get(i).length()); 
                    }
                }
                else if("men".equals(pref.get(i))){
                     if(in.charAt(pref.get(i).length())=='e'){
                        in="t"+in.substring(pref.get(i).length()); 
                    }
                }
                else{
                    in=in.substring(pref.get(i).length()); 
                }
                
                System.out.println(in);
                break;
            }
        }
        if(Trie.getInstance().search(in)){
             return in;
         }
        for(int i =0;i<suf.size();i++){
            
            if(in.endsWith(suf.get(i))){
                
                in = in.substring(0, (in.length()-suf.get(i).length()));
                System.out.println(in);
                break;
            }
        }
        hasil=in;
        if(hasil!=""){
                System.out.println(hasil);
                return hasil;
        }
        return input;
    }
    
    
}
class StringComparator implements Comparator<String>{
   @Override
   public int compare(String o1, String o2){
      return Integer.compare(o1.length(), o2.length());
   }
}