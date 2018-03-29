
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
     * Return true/false
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
    public String cekReduplikasi(String kata) throws IOException{
        String res = "";
        if(kata.contains("-")){
            int idxHubung = kata.indexOf("-");
            System.out.println(idxHubung);
            String temp1 = kata.substring(0, idxHubung);
            String temp2 = kata.substring(idxHubung+1);
            System.out.println(temp1);
            System.out.println(temp2);
            if(temp1.equalsIgnoreCase(temp2)){
                res = temp1;
            }
            else if(this.cekAdaDiLexicon(temp1) && !this.cekAdaDiLexicon(temp2)){
                res = temp1;
            }
            else if(!this.cekAdaDiLexicon(temp1) && this.cekAdaDiLexicon(temp2)){
                res = temp2;
            }
        }
        else{
            res = kata;
        }
        return res;
    }
}
