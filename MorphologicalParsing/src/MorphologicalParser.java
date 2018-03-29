
import java.util.ArrayList;

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
    }
    
    public String cekBerimbuhan(String input){
         String hasil="";   
         String in = input;
        for(int i =0;i<pref.size();i++){
            if(in.startsWith(pref.get(i))){
                in=in.substring(pref.get(i).length()); 
                System.out.println(in);
                break;
            }
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
