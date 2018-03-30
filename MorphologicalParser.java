
import java.util.ArrayList;
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

    public MorphologicalParser(ArrayList<String> pref, ArrayList<String> suf) {
        this.pref = pref;
        this.suf = suf;
        pref.sort(new StringComparator());
        Collections.reverse(pref);
        suf.sort(new StringComparator());
        Collections.reverse(suf);
    }

    public boolean isVokal(char in) {
        if (in == 'a' || in == 'e' || in == 'i' || in == 'u' || in == 'o') {
            return true;
        }
        return false;

    }

    public ArrayList<String> cekBerimbuhan(String input) {
        String in = input;
        String prefix = "";
        String wordParse = "";
                
        String infix = "";
        String suffix = "";

        ArrayList<String> hasilList = new ArrayList<>();

        //kalo misalnya kata tersebut udh kata dasar
        if(Trie.getInstance().search(in)){
            hasilList.add(in);
        }
        //kalo misalnya kata tersebut punya imbuhan
        else{
            //cek prefix(kalo imbuhannya tidak menyebabkan perubahan huruf pada kata dasar
            //DI, SE (buat yang 2 huruf imbuhanya)
            if(in.startsWith("di") || in.startsWith("se") || in.startsWith("ke")){
                prefix = in.substring(0, 2);
                wordParse = in.substring(2,in.length());
                hasilList.add(prefix+" "+wordParse);
            }
            //cek prefix buat 3 huruf 
            else if(in.startsWith("per") || in.startsWith("ter")){
                prefix = in.substring(0,3);
                wordParse = in.substring(3,in.length());
                hasilList.add(prefix+" "+wordParse);
            }
            
            //cek imbuhan me-
            else if(in.startsWith("me")){
                wordParse = in.substring(2,in.length());
                hasilList.add("me "+wordParse);
                
                //cek imbuhan men-
                if(in.startsWith("men")){
                    wordParse = in.substring(3,in.length());
                    hasilList.add("me "+wordParse);
                    
                    //cek imbuhan meng- dan meny-
                    if(in.startsWith("meng")){
                        wordParse = in.substring(4,in.length());
                        hasilList.add("me "+wordParse);
                    }
                    
                    else if(in.startsWith("meny")){
                        wordParse = in.substring(4,in.length());                    
                        hasilList.add("me s"+wordParse);
                    }
                }
                //cek imbuhan mem-
                else if(in.startsWith("mem")){
                    //ganti jadi p,k,t
                    if(isVokal(in.substring(3, in.length()).charAt(0))){
                        wordParse = in.substring(3,in.length());
                        hasilList.add("me p"+wordParse);
                    
                        wordParse = in.substring(3,in.length());
                        hasilList.add("me t"+wordParse);
                    
                        wordParse = in.substring(3,in.length());
                        hasilList.add("me k"+wordParse);
                    }
                    else{
                        wordParse = in.substring(3,in.length());
                        hasilList.add("me "+wordParse);
                    }
                }            
            }
            
            //imbuhan dengan awalan pe-
            else if(in.startsWith("pe")){
                wordParse = in.substring(2,in.length());
                hasilList.add("pe "+wordParse);
                
                //cek imbuhan pen-
                if(in.startsWith("pen")){
                    wordParse = in.substring(3,in.length());
                    hasilList.add("pe "+wordParse);
                    
                    //cek imbuhan meng- dan meny-
                    if(in.startsWith("peng")){
                        wordParse = in.substring(4,in.length());
                        hasilList.add("pe "+wordParse);
                    }
                    
                    else if(in.startsWith("peny")){
                        wordParse = in.substring(4,in.length());                    
                        hasilList.add("pe s"+wordParse);
                    }
                }
                //cek imbuhan pem-
                else if(in.startsWith("pem")){
                    //ganti jadi p,k,t
                    wordParse = in.substring(3,in.length());
                    hasilList.add("pe p"+wordParse);
                    
                    wordParse = in.substring(3,in.length());
                    hasilList.add("pe t"+wordParse);
                    
                    wordParse = in.substring(3,in.length());
                    hasilList.add("pe k"+wordParse);
                    
                    //buat kata yg awalnya tidak diawali p,t,k
                    wordParse = in.substring(3,in.length());
                    hasilList.add("pe "+wordParse);
                }
                
                //cek imbuhan per-
                else if(in.startsWith("per")){
                    wordParse = in.substring(3,in.length());
                    hasilList.add("pe "+wordParse);
                    
                    //ganti jadi r
                    wordParse = in.substring(2,in.length());
                    hasilList.add("pe r"+wordParse);
                }
            }
        }
        
        //akhiran
//        if (in.endsWith("an")) {
//            in = in.substring(0, in.length() - 2);
//            if (!isVokal(in.charAt(in.length() - 1)) && in.charAt(in.length() - 1) == 'k') {
//                in = in.substring(0, in.length() - 1);
//                hasSuffix = true;
//            }
//        } 
//        else if (in.endsWith("i")) {
//            char temp = in.charAt(in.length() - 2);
//            if (!isVokal(temp) && !(temp == 'k' || temp == 'l' || temp == 't' || temp == 'g' || temp == 'w')) {
//                in = in.substring(0, in.length() - 1);
//                hasSuffix = true;
//            }
//        }


        //prefiks
        //kalo ga ada prefiks langsung masukin
        //kalo udh bisa ngedetect suffix sm infix nanti benerin lg
        
        
//        if (Trie.getInstance().search(in)) {
//            hasilList.add(in);
//            return hasilList;
//        } 
//        else {
//            if (in.startsWith("be") || in.startsWith("te")) {
//                hasil = in.substring(2);
//                if (Trie.getInstance().search(hasil)) {
//                    hasilList.add(hasil);
//                    return hasilList;
//                }
//                if (hasil.startsWith("l") || hasil.startsWith("r")) {
//                    hasil = hasil.substring(1);
//                    if (Trie.getInstance().search(hasil)) {
//                        hasilList.add(hasil);
//                        return hasilList;
//                    }
//                }
//            } 
//            else {
//                for (int i = 0; i < in.length(); i++) {
//                    hasil += in.substring(i, i + 1);
//                    if (prefixChecked) {
//                        panjangKata++;
//                    }
//                    //cek prefix ada di list prefiks atau ngga
//                    if (!hasPrefix && preIndex < 2) {
//                        for (int j = 0; j < pref.size(); j++) {
//                            if (hasil.equals(pref.get(j))) {
//                                hasPrefix = true;
//                                prefix = prefix.concat(hasil);
//                                break;
//                            }
//                        }
//                    }
//                    if (hasPrefix && !prefixChecked) {
//                        /**
//                         *
//                         * MASIH NGACO TOLONG BENERIN CONTOH KATA YG GA
//                         * KEDETECT: menari, mendusta, menampilkan KALO MASUKIN
//                         * KATA (MISALNYA) mencari, YANG MUNCUL CUMA ari
//                         *
//                         */
//                        if (preIndex >= 2) {
//                            //prefixnya ga ngerubah huruf pertama
//                            if ((prefix.equals("me") || prefix.equals("pe"))) {
//                                if ((in.charAt(preIndex) == 'm') && flagPrefixM) {
//                                    listHurufPertama.add("b");
//                                    listHurufPertama.add("f");
//                                    listHurufPertama.add("v");
//                                    listHurufPertama.add("m");
//                                    listHurufPertama.add("p");
//                                    listHurufPertama.add("t");
//                                    listHurufPertama.add("k");
//                                    prefixChecked = true;
//                                    ubahHuruf = true;
//                                } else {
//                                    flagPrefixM = false;
//                                    if (preIndex == 3) {
//                                        if (in.charAt(preIndex) == 'g') {
//                                            listHurufPertama.add("a");
//                                            listHurufPertama.add("e");
//                                            listHurufPertama.add("g");
//                                            listHurufPertama.add("h");
//                                            listHurufPertama.add("i");
//                                            listHurufPertama.add("u");
//                                            listHurufPertama.add("o");
//                                            listHurufPertama.add("k");
//                                            listHurufPertama.add("g");
//                                            ubahHuruf = true;
//                                        } else if (in.charAt(preIndex) == 'y') {
//                                            listHurufPertama.add("s");
//                                            ubahHuruf = true;
//                                        }
//                                        prefixChecked = true;
//                                    }
//                                    //preIndex++;
//                                }
//                            } else {
//                                if (preIndex > 3) {
//                                    prefixChecked = true;
//                                }
//                            }
//                        }
//                        preIndex++;
//                    }
//                }
//
//                //kalo ga ada prefiks langsung masukin
//                //kalo udh bisa ngedetect suffix sm infix nanti benerin lg
//                if (Trie.getInstance().search(hasil)) {
//                    hasilList.add(hasil);
//                }
//                if (!hasPrefix) {
//                    //System.out.println(hasil);
//                } else {
//                    String temp = hasil.substring(preIndex, hasil.length());
//                    //System.out.println(temp); //buat ngecek isi temp
//                    if (ubahHuruf) {
//                        for (int i = 0; i < listHurufPertama.size(); i++) {
//                            String temp2 = listHurufPertama.get(i) + temp;
//                            //System.out.println(temp2);
//                            if (Trie.getInstance().search(temp2)) {
//                                hasilList.add(temp2);
//                                //break;
//                            }
//                        }
//                    }
//                    if (Trie.getInstance().search(temp)) {
//                        hasilList.add(temp);
//                    }
//                }
//
//            }
//
//            return hasilList;
//        }
        return hasilList;
    }

}

class StringComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return Integer.compare(o1.length(), o2.length());
    }
}
