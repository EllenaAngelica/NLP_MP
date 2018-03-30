
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CorneliusDavid
 */
public class Tester {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("list_kata.txt")));
        BufferedReader prefix=new BufferedReader(new InputStreamReader(new FileInputStream("prefiks.txt")));
        BufferedReader sufix=new BufferedReader(new InputStreamReader(new FileInputStream("sufiks.txt")));
        BufferedReader prefix1=new BufferedReader(new InputStreamReader(new FileInputStream("prefiks_1.txt")));
        BufferedReader prefix2=new BufferedReader(new InputStreamReader(new FileInputStream("prefiks_2.txt")));
        BufferedReader prefix3=new BufferedReader(new InputStreamReader(new FileInputStream("prefiks_3.txt")));
        BufferedReader prefix4=new BufferedReader(new InputStreamReader(new FileInputStream("prefiks_4.txt")));
        BufferedReader prefix5=new BufferedReader(new InputStreamReader(new FileInputStream("prefiks_5.txt")));
        BufferedReader prefix6=new BufferedReader(new InputStreamReader(new FileInputStream("prefiks_6.txt")));
        //BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("list_kata.txt")));
        String input;
        while((input=br.readLine())!=null && input.length()!=0){
            Trie.getInstance().insert(input);
        }
        ArrayList<String> pref= new ArrayList<String>();
        ArrayList<String> pref1= new ArrayList<String>();
        ArrayList<String> pref2= new ArrayList<String>();
        ArrayList<String> pref3= new ArrayList<String>();
        ArrayList<String> pref4= new ArrayList<String>();
        ArrayList<String> pref5= new ArrayList<String>();
        ArrayList<String> pref6= new ArrayList<String>();
        ArrayList<String> suf= new ArrayList<String>();
        while((input=prefix.readLine())!=null && input.length()!=0){
            pref.add(input);
        }
        while((input=prefix1.readLine())!=null && input.length()!=0){
            pref1.add(input);
        }
        while((input=prefix2.readLine())!=null && input.length()!=0){
            pref2.add(input);
        }
        while((input=prefix3.readLine())!=null && input.length()!=0){
            pref3.add(input);
        }
        while((input=prefix4.readLine())!=null && input.length()!=0){
            pref4.add(input);
        }
        while((input=prefix5.readLine())!=null && input.length()!=0){
            pref5.add(input);
        }
        while((input=prefix6.readLine())!=null && input.length()!=0){
            pref6.add(input);
        }
        while((input=sufix.readLine())!=null && input.length()!=0){
            suf.add(input);
        }
        ArrayList[] preArr = {pref1, pref2, pref3, pref4, pref5, pref6};
        //MorphologicalParser parser = new MorphologicalParser(pref,suf);
        MorphologicalParser parser = new MorphologicalParser(preArr,suf);
        
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            ArrayList<String> result = new ArrayList<>();
            result.addAll(parser.cekBerimbuhan(sc.nextLine()));
            for(int i = 0; i < result.size(); i++){
                System.out.println(result.get(i));
            }
            result.clear();
            //System.out.println(Trie.getInstance().search(result));
        }
    }
}
