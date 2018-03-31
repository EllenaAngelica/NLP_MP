
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
    ArrayList<String> pref1;
    ArrayList<String> pref2;
    ArrayList<String> pref3;
    ArrayList<String> pref4;
    ArrayList<String> pref5;
    ArrayList<String> pref6;
    ArrayList<String> suf;
    ArrayList<String> hasilList;

    public MorphologicalParser(ArrayList<String> pref, ArrayList<String> suf) {
        this.pref = pref;
        this.suf = suf;
        pref.sort(new StringComparator());
        Collections.reverse(pref);
        suf.sort(new StringComparator());
        Collections.reverse(suf);
        hasilList = new ArrayList<>();
    }

    public MorphologicalParser(ArrayList[] preArr, ArrayList<String> suf) {
        this.suf = suf;
        this.pref1 = preArr[0];
        this.pref2 = preArr[1];
        this.pref3 = preArr[2];
        this.pref4 = preArr[3];
        this.pref5 = preArr[4];
        this.pref6 = preArr[5];
        suf.sort(new StringComparator());
        Collections.reverse(suf);
        pref1.sort(new StringComparator());
        Collections.reverse(pref1);
        pref2.sort(new StringComparator());
        Collections.reverse(pref2);
        pref3.sort(new StringComparator());
        Collections.reverse(pref3);
        pref4.sort(new StringComparator());
        Collections.reverse(pref4);
        pref5.sort(new StringComparator());
        Collections.reverse(pref5);
        pref6.sort(new StringComparator());
        Collections.reverse(pref6);
        hasilList = new ArrayList<>();
    }

    public boolean isVokal(char in) {
        if (in == 'a' || in == 'e' || in == 'i' || in == 'u' || in == 'o') {
            return true;
        }
        return false;

    }
    
    /**
     * Buat ngecek lexicon biar ga harus nulis Trie.blablabla
     * @param s inputnya
     * @return kalo true berarti di lexicon ada sm di hasilListnya belum ada yg sama
     */
    private boolean cekLexicon(String s) {
        return (Trie.getInstance().search(s) && !hasilList.contains(s));
    }

    public ArrayList<String> cekBerimbuhan(String input) {
        hasilList.clear();
        String in = input;
        String prefix = "";
        
        String prefixTemp="";
        String wordParse = "";
        String hasil = "";

        String infix = "";
        String suffix = "";
        
        

        ArrayList<String> prefixList = new ArrayList<>();
        //ArrayList<String> hasilList = new ArrayList<>();

        //kalo misalnya kata tersebut udh kata dasar
        if (Trie.getInstance().search(in)) {
            hasilList.add(in);
        }
        for (int i = 0; i < in.length(); i++) {
            hasil += in.substring(i, i + 1);
            /**
             * Cek prefiksnya dari string hasil
             */
            if (i == 0) {
                if (hasil.equals(pref1.get(0))) {
                    //prefix = pref1.get(0);
                    prefixList.add(pref1.get(0));
                }
            }
            if (i == 1) {
                for (int j = 0; j < pref2.size(); j++) {
                    if (hasil.equals(pref2.get(j))) {
                        prefixList.add(pref2.get(j));
                        break;
                    }
                }
            }
            if (i == 2) {
                for (int j = 0; j < pref3.size(); j++) {
                    if (hasil.equals(pref3.get(j))) {
                        prefixList.add(pref3.get(j));
                        break;
                    }
                }
            }
            if (i == 3) {
                for (int j = 0; j < pref4.size(); j++) {
                    if (hasil.equals(pref4.get(j))) {
                        prefixList.add(pref4.get(j));
                        break;
                    }
                }
            }
            if (i == 4) {
                for (int j = 0; j < pref5.size(); j++) {
                    if (hasil.equals(pref5.get(j))) {
                        prefixList.add(pref5.get(j));
                        break;
                    }
                }
            }
            if (i == 5) {
                for (int j = 0; j < pref6.size(); j++) {
                    if (hasil.equals(pref6.get(j))) {
                        prefixList.add(pref6.get(j));
                        break;
                    }
                }
            }

        }
        for (int i = 0; i < prefixList.size(); i++) {
            String temp;
            if (prefixList.get(i).length() == 1) {
                temp = hasil.substring(1); //BELUM NGECEK SUFIKS. KALO SUFIKS UDAH MASUK TOLONG GANTI UPPERBOUNDNYA
                if (cekLexicon(temp)) {
                    hasilList.add(temp);
                }
            }
            if (prefixList.get(i).length() == 2) {
                temp = hasil.substring(2);
                if (temp.length() > 2) { // ini cuma supaya kalo disubstring g error
                    if (prefixList.get(i).equals("me") || prefixList.get(i).equals("pe")) {
                        
                              
                        if (cekLexicon(temp)) {
                            hasilList.add(temp);
                            
                        }
                        else {prefixTemp=temp;
                        if(prefixList.get(i).equals("me")){
                        prefix="me";}
                        else{
                            prefix="pe";
                        }
                        }
                        if (temp.substring(0, 1).equals("m")) {
                            prefix+="m";
                            if (cekLexicon(temp.substring(1))) {
                                hasilList.add(temp.substring(1));                                
                            }                            
                            else{
                                prefixTemp=temp.substring(1);
                            }
                            String temp2 = "p" + temp.substring(1);
                            if (cekLexicon(temp2)) {
                                prefixTemp=temp2;
                                hasilList.add(temp2);
                            }
                        }
                        if (temp.substring(0, 1).equals("n")) {
                            prefix+="n";
                            if (cekLexicon(temp.substring(1))) {
                                hasilList.add(temp.substring(1));
                            }
                            
                                prefixTemp=temp.substring(1);
                            
                            String temp2 = "t" + temp.substring(1);
                            if (cekLexicon(temp2)) {
                                hasilList.add(temp2);
                            }
                        }
                        if (temp.substring(0, 2).equals("ng")) {
                            prefix+="g";
                            
                            if (cekLexicon(temp.substring(2))) {
                                hasilList.add(temp.substring(2));
                            }
                            
                                prefixTemp=temp.substring(2);
                            
                            //ini kalo cuma satu suku kata
                            //harusnya kalo cuma satu suku kata si katanya paling banyak ada tiga huruf
                            if (temp.substring(2, 3).equals("e")) {
                                prefix+="e";
                                String temp2 = temp.substring(3);
                                //System.out.println("isi temp2 "+temp2);
                                //if(temp2.length() < 6){
                                    if(cekLexicon(temp2)){
                                        hasilList.add(temp2);
                                        
                                    }
                                    
                                        prefixTemp=temp2;
                                    
                                //}
                            }
                            //System.out.println(" "+prefixTemp);

                            
                            String temp2 = "k" + temp.substring(2);
                            //System.out.println("DEBUG : " + temp2);
                            if (cekLexicon(temp2)) {
                                prefixTemp=temp2;
                                hasilList.add(temp2);
                            }

                        }
                        if (temp.substring(0, 2).equals("ny")) {
                            prefix+="ny";
                            if (cekLexicon(temp.substring(2))) {
                                hasilList.add(temp.substring(2));
                            }
                            else{
                                        prefixTemp=temp.substring(2);
                                    }
                            String temp2 = "s" + temp.substring(2);
                            //System.out.println("DEBUG : " + temp2);
                            if (cekLexicon(temp2)) {
                                hasilList.add(temp2);
                            }
                        }
                    } else if (prefixList.get(i).equals("be") || prefixList.get(i).equals("te")) {
                        if(prefixList.get(i).equals("be"))
                        {prefix="be";}
                        else{
                            prefix="te";
                        }
                        if (temp.substring(0, 1).equals("r")) {
                            prefix+="r";
                            if (cekLexicon(temp.substring(1))) {
                                hasilList.add(temp.substring(1));
                            }
                            else{
                                prefixTemp=temp.substring(1);
                            }
                        } else {
                            if (temp.length() > 2 && temp.substring(1, 3).equals("er")) {
                                if (cekLexicon(temp.substring(3))) {
                                    hasilList.add(temp.substring(3));
                                }
                                else{
                                    prefixTemp=temp.substring(3);
                                }
                            }
                        }
                    } else {
                        if (cekLexicon(temp)) {
                            hasilList.add(temp);
                        }
                    }
                    // KATA KHUSUS YG DIUBAH JADI L
                    // KALO TERNYATA ADA ATURAN YG BIKIN KATA BISA BERUBAH JADI L NANTI GANTI
                    // KATA KHUSUS KAYAKNYA HARUS HARDCODE
                    if (temp.equals("lajar")) {
                        if (cekLexicon("ajar")) {
                            hasilList.add("ajar");
                        }
                    }
                    if (temp.equals("lunjur")) {
                        if (cekLexicon("unjur")) {
                            hasilList.add("unjur");
                        }
                    }
                }
            }
            //System.out.println(prefix);
            if (prefixList.get(i).length() == 3) {
                temp = hasil.substring(3);
                if (temp.length() > 2) {
                    // ini kyknya ga perlu dikasih if yg ini
                    // kyknya bisa langsung cek lexicon aja
                    if (prefixList.get(i).equals("ber") || prefixList.get(i).equals("ter")) {
                        //System.out.println("Masuk ber atau ter");
                        if (cekLexicon(temp)) {
                            hasilList.add(temp);
                        }
                    } else {
                        if (cekLexicon(temp)) {
                            hasilList.add(temp);
                        }
                    }
                }
            }
            if (prefixList.get(i).length() == 4) {
                temp = hasil.substring(4);
                if (temp.length() > 2) {
                    if (cekLexicon(temp)) {
                        hasilList.add(temp);
                    }
                }
            }
            if (prefixList.get(i).length() == 5) {
                temp = hasil.substring(5);
                if (temp.length() > 2) {
                    if (cekLexicon(temp)) {
                        hasilList.add(temp);
                    }
                }
            }
            if (prefixList.get(i).length() == 6) {
                temp = hasil.substring(6);
                if (temp.length() > 2) {
                    if (cekLexicon(temp)) {
                        hasilList.add(temp);
                    }
                }
            }
        }
        //kalo misalnya kata tersebut punya imbuhan
        /*else{
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
        }*/

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
        System.out.println("Prefix "+prefix);
        System.out.println("Prefixtemp "+prefixTemp);
        
        if(prefixTemp.length()!=0){
            for(int i = 0;i<suf.size();i++){
                if(prefixTemp.endsWith(suf.get(i))){
                    String temp = prefixTemp.substring(0,prefixTemp.length()-suf.get(i).length());
                    System.out.println(temp);
                    if (temp.length() > 2) {
                        if (cekLexicon(temp)) {
                            for(int j =0;j<hasilList.size();j++){
                                if(hasilList.get(j).length()>temp.length()){
                                    hasilList.clear();
                                    hasilList.add(temp);
                                    break;
                                }
                            }
                            //System.out.println("hasil "+temp);
                            break;
                        }
                    }
                }
            }
        }
        return hasilList;
    }

}

class StringComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return Integer.compare(o1.length(), o2.length());
    }
}
