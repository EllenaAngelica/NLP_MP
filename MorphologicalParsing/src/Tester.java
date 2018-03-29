
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
        //BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("list_kata.txt")));
        String input;
        while((input=br.readLine())!=null && input.length()!=0){
            Trie.getInstance().insert(input);
        }
        ArrayList<String> pref= new ArrayList<String>();
        ArrayList<String> suf= new ArrayList<String>();
        while((input=prefix.readLine())!=null && input.length()!=0){
            pref.add(input);
        }
        while((input=sufix.readLine())!=null && input.length()!=0){
            suf.add(input);
        }
        MorphologicalParser parser = new MorphologicalParser(pref,suf);
        
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            String result = parser.cekBerimbuhan(sc.nextLine());
            System.out.println(Trie.getInstance().search(result));
        }
    }
}
