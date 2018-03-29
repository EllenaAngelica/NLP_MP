
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Parser
 * @author :
 * Muhammad Adam Nur Mishwari   (2015730013)
 * Ferdinandus Renaldi          (2015730028)
 * Ellena Angelica              (2015730029)
 * Evelyn Wijaya                (2015730030)
 */
public class Parser {
    /**
     * 
     * @param kata
     * @return true / false
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public boolean cekAdaDiLexicon(String kata) throws FileNotFoundException, IOException{
        // Load Lexicon
        BufferedReader brLexicon=new BufferedReader(new InputStreamReader(new FileInputStream("list_kata.txt")));
        String inputLexicon;
        while((inputLexicon=brLexicon.readLine())!=null && inputLexicon.length()!=0){
            Trie.getInstance().insert(inputLexicon);
        }
        // Search in Lexicon
        return Trie.getInstance().search(kata);
    }
    public void morphologicalParsing(){
    
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
        if(!kata.contains("-")){ // kata ulang sebagian
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
        }
        else{
            res = kata;
        }
        return res;
    }
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
}
