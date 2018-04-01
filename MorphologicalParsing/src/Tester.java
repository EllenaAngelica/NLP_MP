
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class Tester {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader infix=new BufferedReader(new InputStreamReader(new FileInputStream("infiks.txt")));
        BufferedReader prefix=new BufferedReader(new InputStreamReader(new FileInputStream("prefiks.txt")));
        BufferedReader sufix=new BufferedReader(new InputStreamReader(new FileInputStream("sufiks.txt")));
        BufferedReader prefix1=new BufferedReader(new InputStreamReader(new FileInputStream("prefiks_1.txt")));
        BufferedReader prefix2=new BufferedReader(new InputStreamReader(new FileInputStream("prefiks_2.txt")));
        BufferedReader prefix3=new BufferedReader(new InputStreamReader(new FileInputStream("prefiks_3.txt")));
        BufferedReader prefix4=new BufferedReader(new InputStreamReader(new FileInputStream("prefiks_4.txt")));
        BufferedReader prefix5=new BufferedReader(new InputStreamReader(new FileInputStream("prefiks_5.txt")));
        BufferedReader prefix6=new BufferedReader(new InputStreamReader(new FileInputStream("prefiks_6.txt")));
        
        BufferedReader csvReader = new BufferedReader(new FileReader("kata_dasar_kbbi.csv")); 
        String line="";
        while ((line = csvReader.readLine()) != null) {

                String in = line;
                Trie.getInstance().insert(in);
            }
        String input;
        ArrayList<String> pref= new ArrayList<String>();
        ArrayList<String> pref1= new ArrayList<String>();
        ArrayList<String> pref2= new ArrayList<String>();
        ArrayList<String> pref3= new ArrayList<String>();
        ArrayList<String> pref4= new ArrayList<String>();
        ArrayList<String> pref5= new ArrayList<String>();
        ArrayList<String> pref6= new ArrayList<String>();
        ArrayList<String> suf= new ArrayList<String>();
        ArrayList<String> inf= new ArrayList<String>();
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
        while((input=infix.readLine())!=null && input.length()!=0){
            inf.add(input);
        }
        ArrayList[] preArr = {pref1, pref2, pref3, pref4, pref5, pref6};
        MorphologicalParser parser = new MorphologicalParser(preArr,suf,inf);
        BufferedReader inputtext=new BufferedReader(new InputStreamReader(new FileInputStream("inputantext.txt")));
       Scanner sc=new Scanner(inputtext);
       //Scanner sc=new Scanner(System.in);
       
       ArrayList<String> hasilPenelusuran = new ArrayList<String>();
        while(sc.hasNextLine()){
            ArrayList<String> result = new ArrayList<>();
            String temp = sc.nextLine().toLowerCase();
            
            if(temp.startsWith("-")){
                temp=temp.substring(1);
            }
            
            String kataAwal="Kata awal: "+temp+" kata dasar: ";
           
            temp=hilangkanSimbol(temp); 
            
            //System.out.println(temp+" kata");
            
            result.addAll(parser.cekBerimbuhan(temp,0));
           
            for(int i = 0; i < result.size(); i++){
                String simbol=" | ";
                if(i==result.size()-1){
                    simbol="";
                }
                kataAwal+=result.get(i)+simbol;
                //System.out.println(result.get(i));
               
            }
            result.clear();
            hasilPenelusuran.add(kataAwal);
        }
            File newTextFile = new File("hasilparser.txt");
            
            FileWriter fw = new FileWriter(newTextFile);
            
            for(int i =0;i<hasilPenelusuran.size();i++){
                fw.write(hasilPenelusuran.get(i));
                fw.write(System.getProperty("line.separator"));
            }
            fw.close();
    }
    private static String hilangkanSimbol(String input){
        String output=input;
            output=output.replaceAll("[^- a-zA-Z]*", "");
            return output;
    }
}
