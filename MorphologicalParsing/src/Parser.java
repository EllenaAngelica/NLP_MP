
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * Parser
 * @author :
 * Muhammad Adam Nur Mishwari   (2015730013)
 * Ferdinandus Renaldi          (2015730028)
 * Ellena Angelica              (2015730029)
 * Evelyn Wijaya                (2015730030)
 */
public class Parser {
    
    private MorphologicalParser morpParser;
    
    public Parser(){
        
    }
    public Parser(MorphologicalParser mp){
        this.morpParser = mp;
    }
    
    /**
     * 
     * @param kata
     * @return true / false
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public boolean cekAdaDiLexicon(String kata) throws FileNotFoundException, IOException{
        // Load Lexicon
       /* BufferedReader brLexicon=new BufferedReader(new InputStreamReader(new FileInputStream("list_kata.txt")));
        String inputLexicon;
        while((inputLexicon=brLexicon.readLine())!=null && inputLexicon.length()!=0){
            Trie.getInstance().insert(inputLexicon);
        }*/
        // Search in Lexicon
        return Trie.getInstance().search(kata);
    }
    /**
     * 
     * @param kata
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private boolean cekUlangSemu(String kata) throws FileNotFoundException, IOException{
        boolean res = false;
        BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("ulang_semu.txt")));
        String input;
        while((input=br.readLine())!=null && input.length()!=0){
            if(kata.equalsIgnoreCase(input)){
                res = true;
            }
        }
        return res;
    }
    /**
     * 
     * @param kata
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private boolean cekUlangSebagian(String kata) throws FileNotFoundException, IOException{
        boolean res = false;
        BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("ulang_sebagian.txt")));
        String input;
        while((input=br.readLine())!=null && input.length()!=0){
            if(kata.equalsIgnoreCase(input)){
                res = true;
            }
        }
        return res;
    }
    
    public String cekPengulangan(String kata) throws IOException{
        String hasil="";
        if(cekUlangSemu(kata)){
            return kata+" kata ulang semu ";
        }
        if(kata.contains("-")){
            String[] pecah = kata.split("-");
            String depan = pecah[0];
            String belakang = pecah[1];
            ArrayList<String> depanList = new ArrayList<String>();
            ArrayList<String> belakangList = new ArrayList<String>();
            if(!depan.equals("")){
                depanList.addAll(morpParser.cekBerimbuhan(depan, 0));
            }
            if(!belakang.equals("")){
                belakangList.addAll(morpParser.cekBerimbuhan(belakang, 0));
            }
            for(int i = 0; i < depanList.size(); i++){
                for(int j = 0; j < belakang.length(); j++){
                    if(depanList.get(i).equals(belakangList.get(j))){
                        return depan+" kata ulang penuh ";
                    }
                }
            }
            if(depan.equals(belakang)){
                return depan+" kata ulang penuh ";
            }
            char[] isiCharDepan = depan.toCharArray();
            char[] isiCharBelakang = belakang.toCharArray();
            boolean[] sama = new boolean[isiCharDepan.length];
            int jumlahSama=0;
            int jumlahBeda=0;
            for(int i =0;i<sama.length;i++){
                if(isiCharDepan[i]==isiCharBelakang[i]){
                    sama[i]=true;
                    jumlahSama++;
                }
                else{
                    sama[i]=false;
                    jumlahBeda++;
                }
            }
            
            if(jumlahBeda<jumlahSama && isiCharDepan.length==isiCharBelakang.length){
                return depan+" kata ulang berubah bunyi ";
            }
            
            
        }
        else{
            if(kata.charAt(0)==kata.charAt(2)&&kata.charAt(1)=='e'){
                if((kata.charAt(0)=='j'||kata.charAt(0)=='t')&&!kata.endsWith("an")){
                    return kata.substring(2)+" kata ulang sebagian ";
                }
                else if(kata.endsWith("an")){
                    return kata.substring(2,kata.length()-2)+" kata ulang sebagian ";
                }
                
            }
        }
        return hasil;
    }
    /**
     * Untuk mengecek apakah kata termasuk reduplikasi
     * Jenis - jenis reduplikasi :
     * 1. Kata ulang sebagian
     * 2. Kata ulang penuh
     * 3. Kata ulang berubah bunyi
     * 4. Kata ulang berimbuhan
     * 5. Kata ulang semu
     * @param kata
     * @return
     * @throws IOException 
     */
    public String cekReduplikasi(String kata) throws IOException{
        String res = "";
        if(!kata.contains("-") && this.cekUlangSebagian(kata)){ // kata ulang sebagian
            if(this.cekAdaDiLexicon(kata.substring(2))){
                res = kata.substring(2);
            }
            else if(this.cekAdaDiLexicon(kata.substring(2, kata.length()-2))){
                res = kata.substring(2, kata.length()-2);
            }
        }
        else if(this.cekUlangSemu(kata)){ // kata ulang semu
            res = kata;
        }
        else if(kata.contains("-")){
            int idxHubung = kata.indexOf("-");
            String temp1 = kata.substring(0, idxHubung);
            String temp2 = kata.substring(idxHubung+1);
            if(temp1.equalsIgnoreCase(temp2)){ // kata ulang penuh
                res = temp1;
            }
            else if(this.cekAdaDiLexicon(temp1) && !this.cekAdaDiLexicon(temp2)){
                res = temp1;
            }
            else if(!this.cekAdaDiLexicon(temp1) && this.cekAdaDiLexicon(temp2)){
                res = temp2;
            }
            else{
                res = temp1;
            }
        }
        else{
            res = kata;
        }
        return res;
    }
    /**
     * 
     * @param kata
     * @return 
     */
    public String morphologicalParsing(String kata){
        String res = "";
        return res;
    }
}
