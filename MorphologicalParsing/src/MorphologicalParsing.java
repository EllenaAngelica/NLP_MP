
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


/**
 * Tugas Pengolahan Bahasa Alami - Tugas 2
 * Morphological Parsing
 * "Buatlah program untuk melakukan morphological parsing untuk mengenali kata berimbuhan dan
kata ulang dalam bahasa Indonesia. Gunakanlah method search yang dibuat kelompok sebelumnya
untuk mencari apakah kata ada dalam lexicon. Program menerima input sebuah file teks berisi kata-
kata dan melakukan morphological parsing terhadap semua kata pada teks. Keluaran berupa list
hasil setiap kata dalam sebuah file teks lainnya."
 * @author :
 * Muhammad Adam Nur Mishwari   (2015730013)
 * Ferdinandus Renaldi          (2015730028)
 * Ellena Angelica              (2015730029)
 * Evelyn Wijaya                (2015730030)
 */
public class MorphologicalParsing {
    public static void main(String[] args) throws IOException {
        // Input
        BufferedReader brMP = new BufferedReader(new InputStreamReader(new FileInputStream("input_file.txt")));
        String inputMP;
        while((inputMP=brMP.readLine())!=null && inputMP.length()!=0){
            Trie.getInstance().insert(inputMP);
        }
    }
}
