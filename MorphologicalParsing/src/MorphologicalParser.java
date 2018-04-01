
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    String hasilTanpaAkhiran = "";

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
     *
     * @param s inputnya
     * @return kalo true berarti di lexicon ada sm di hasilListnya belum ada yg
     * sama
     */
    private boolean cekLexicon(String s) {
        return (Trie.getInstance().search(s) && !hasilList.contains(s));
    }

    public ArrayList<String> cekBerimbuhan(String input, int count) {
        boolean ketemu = false;
        hasilList.clear();
        String in = input;
        String prefix = "";

        String prefixTemp = "";
        String prefixTemp2 = "";
        String wordParse = "";
        String hasil = "";

        int counter = count;

        String infix = "";
        String suffix = "";

        ArrayList<String> prefixList = new ArrayList<>();
        //ArrayList<String> hasilList = new ArrayList<>();

        //kalo misalnya kata tersebut udh kata dasar
        if (Trie.getInstance().search(in)) {
            hasilList.add(in);
            //return hasilList;
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
                this.cekSufiks(temp, suf);
                prefixTemp = temp;
                prefixTemp2 = hasil;

            } else if (prefixList.get(i).length() == 2) {
                temp = hasil.substring(2);
                prefixTemp = temp;
                if (temp.length() > 2) { // ini cuma supaya kalo disubstring g error
                    if (prefixList.get(i).equals("me") || prefixList.get(i).equals("pe")) {

                        if (cekLexicon(temp)) {
                            hasilList.add(temp);
                            ketemu = true;

                        } else {
                            prefixTemp = temp;
                            if (prefixList.get(i).equals("me")) {
                                prefix = "me";
                            } else {
                                prefix = "pe";
                            }
                        }
                        if (temp.substring(0, 1).equals("m")) {
                            prefix += "m";
                            if (cekLexicon(temp.substring(1))) {
                                ketemu = true;
                                hasilList.add(temp.substring(1));
                            } //else {
                            prefixTemp = temp.substring(1);
                            this.cekSufiks(prefixTemp, suf);
                            //}
                            String temp2 = "p" + temp.substring(1);
                            prefixTemp2 = temp2;
                            if (cekLexicon(temp2)) {
                                ketemu = true;
                                hasilList.add(temp2);
                            }
                            this.cekSufiks(temp2, suf);
                        }
                        /*else if (temp.substring(0, 1).equals("r")) {
                            prefix += "r";
                            if (cekLexicon(temp.substring(1))) {
                                ketemu = true;
                                hasilList.add(temp.substring(1));
                            } //else {
                            prefixTemp = temp.substring(1);
                            //}
                            String temp2 = temp;
                            prefixTemp2 = temp2;
                            if (cekLexicon(temp2)) {
                                ketemu = true;
                                hasilList.add(temp2);
                            }
                        }*/

                        if (temp.substring(0, 1).equals("n")) {
                            prefix += "n";
                            if (cekLexicon(temp.substring(1))) {
                                ketemu = true;
                                hasilList.add(temp.substring(1));
                            }

                            prefixTemp = temp.substring(1);
                            this.cekSufiks(prefixTemp, suf);

                            String temp2 = "t" + temp.substring(1);
                            if (cekLexicon(temp2)) {
                                ketemu = true;
                                hasilList.add(temp2);
                            } //else {
                            this.cekSufiks(temp2, suf);
                            prefixTemp2 = temp2;
                            //}
                        }
                        if (temp.substring(0, 2).equals("ng")) {
                            prefix += "g";

                            if (cekLexicon(temp.substring(2))) {
                                ketemu = true;
                                hasilList.add(temp.substring(2));
                            }

                            prefixTemp = temp.substring(2);

                            //ini kalo cuma satu suku kata
                            //harusnya kalo cuma satu suku kata si katanya paling banyak ada tiga huruf
                            if (temp.substring(2, 3).equals("e")) {
                                prefix += "e";
                                String temp2 = temp.substring(3);
                                //System.out.println("isi temp2 "+temp2);
                                //if(temp2.length() < 6){
                                if (cekLexicon(temp2)) {
                                    ketemu = true;
                                    hasilList.add(temp2);
                                }
                                this.cekSufiks(temp2, suf);
                                prefixTemp = temp2;

                                //}
                            }
                            //System.out.println(" "+prefixTemp);

                            String temp2 = "k" + temp.substring(2);
                            //System.out.println("DEBUG : " + temp2);
                            if (cekLexicon(temp2)) {
                                ketemu = true;
                                hasilList.add(temp2);
                            } //else {
                            prefixTemp2 = temp2;
                            this.cekSufiks(temp2, suf);
                            //}

                        }
                        if (temp.substring(0, 2).equals("ny")) {
                            prefix += "ny";
                            /*if (cekLexicon(temp.substring(2))) {
                                ketemu = true;
                                hasilList.add(temp.substring(2));
                            } else {
                                prefixTemp = temp.substring(2);
                            }*/
                            String temp2 = "s" + temp.substring(2);
                            //System.out.println("DEBUG : " + temp2);
                            if (cekLexicon(temp2)) {
                                ketemu = true;
                                hasilList.add(temp2);
                            } //else {
                            this.cekSufiks(temp2, suf);
                            prefixTemp = temp2;
                            //}
                        }
                    } else if (prefixList.get(i).equals("be") || prefixList.get(i).equals("te")) {
                        if (prefixList.get(i).equals("be")) {
                            prefix = "be";
                        } else {
                            prefix = "te";
                        }

                        if (temp.substring(0, 1).equals("r")) {
                            prefix += "r";
                            prefixTemp2 = temp;
                            if(cekLexicon(temp)){
                                ketemu = true;
                                hasilList.add(temp);
                            }
                            //System.out.println(prefixTemp2);
                            if (cekLexicon(temp.substring(1))) {
                                ketemu = true;
                                hasilList.add(temp.substring(1));
                            } else {
                                prefixTemp = temp.substring(1);

                            }
                        } else {
                            if (temp.length() > 2 && temp.substring(1, 3).equals("er")) {
                                if (cekLexicon(temp.substring(3))) {
                                    ketemu = true;
                                    hasilList.add(temp.substring(3));
                                } else {
                                    prefixTemp = temp.substring(3);
                                }
                            }
                        }
                    } else {
                        if (cekLexicon(temp)) {
                            ketemu = true;
                            hasilList.add(temp);
                        }
                    }
                    // KATA KHUSUS YG DIUBAH JADI L
                    // KALO TERNYATA ADA ATURAN YG BIKIN KATA BISA BERUBAH JADI L NANTI GANTI
                    // KATA KHUSUS KAYAKNYA HARUS HARDCODE
                    if (temp.contains("lajar")) {
                        if (cekLexicon(temp)) {
                            ketemu = true;
                            hasilList.add(temp);
                        } else {
                            prefixTemp = prefixTemp.substring(1);
                        }
                    }
                    if (temp.contains("lunjur")) {
                        if (cekLexicon(temp)) {
                            ketemu = true;
                            hasilList.add(temp);
                        } else {
                            prefixTemp = prefixTemp.substring(1);
                        }
                    }
                }
                this.cekSufiks(temp, suf);
            } //System.out.println(prefix);
            else if (prefixList.get(i).length() == 3) {
                temp = hasil.substring(3);
                if (temp.length() > 2) {
                    // ini kyknya ga perlu dikasih if yg ini
                    // kyknya bisa langsung cek lexicon aja
                    if (prefixList.get(i).equals("ber") || prefixList.get(i).equals("ter")) {
                        //System.out.println("Masuk ber atau ter");
                        if (cekLexicon(temp)) {
                            ketemu = true;
                            hasilList.add(temp);
                        } else {
                            prefixTemp = temp;
                        }
                    } else {
                        if (cekLexicon(temp)) {
                            ketemu = true;
                            hasilList.add(temp);
                        } else {
                            prefixTemp = temp;
                        }
                    }
                }
                this.cekSufiks(temp, suf);
            } else if (prefixList.get(i).length() == 4) {
                temp = hasil.substring(4);
                if (temp.length() > 2) {
                    if (cekLexicon(temp)) {
                        prefix = hasil.substring(0, 4);
                        ketemu = true;
                        hasilList.add(temp);
                    } else {
                        prefixTemp = temp;
                    }
                }
                this.cekSufiks(temp, suf);
            } else if (prefixList.get(i).length() == 5) {
                temp = hasil.substring(5);
                if (temp.length() > 2) {
                    if (cekLexicon(temp)) {
                        ketemu = true;
                        hasilList.add(temp);
                    } else {
                        prefixTemp = temp;
                    }
                }
                this.cekSufiks(temp, suf);
            }
            if (prefixList.get(i).length() == 6) {
                temp = hasil.substring(6);
                if (temp.length() > 2) {
                    if (cekLexicon(temp)) {
                        ketemu = true;
                        hasilList.add(temp);
                    } else {
                        prefixTemp = temp;
                    }
                }
                this.cekSufiks(temp, suf);
            }
        }

        //System.out.println("isi dari list");
        //System.out.println("Prefix " + prefix);
        //System.out.println("Prefixtemp " + prefixTemp);
        //System.out.println("Prefixtemp2 " + prefixTemp2);
        String kataUlang = "";

        if (hasilList.isEmpty()) {

            if (hasil.contains("-")) {
                Parser parser = new Parser(this);
                String[] cekDepanBelakang = hasil.split("-");
                if (cekDepanBelakang[0].length() == cekDepanBelakang[1].length()) {
                    try {
                        kataUlang = parser.cekPengulangan(hasil);
                    } catch (IOException ex) {
                        Logger.getLogger(MorphologicalParser.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (!kataUlang.isEmpty()) {
                        hasilList.add(kataUlang);
                        return hasilList;
                    }
                }
                this.cekSufiks(hasil, suf);

                try {
                    kataUlang = parser.cekPengulangan(hasilTanpaAkhiran);
                } catch (IOException ex) {
                    Logger.getLogger(MorphologicalParser.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (!kataUlang.isEmpty()) {
                    System.out.println(kataUlang);
                    hasilList.add(kataUlang);
                    return hasilList;
                }

                if (!prefixTemp2.isEmpty()) {
                    System.out.println("       " + prefixTemp2);
                    kataUlang = "";
                    try {
                        kataUlang = parser.cekPengulangan(prefixTemp2);
                        System.out.println("       " + kataUlang);
                    } catch (IOException ex) {
                        Logger.getLogger(MorphologicalParser.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (!kataUlang.isEmpty()) {
                        hasilList.add(kataUlang);
                        return hasilList;
                    }
                    this.cekSufiks(prefixTemp2, suf);

                    try {
                        kataUlang = new Parser().cekPengulangan(hasilTanpaAkhiran);
                        System.out.println("        " + hasilTanpaAkhiran);
                    } catch (IOException ex) {
                        Logger.getLogger(MorphologicalParser.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (!kataUlang.isEmpty()) {
                        System.out.println(kataUlang);
                        hasilList.add(kataUlang);
                        return hasilList;
                    }

                }
            } else {
                this.cekSufiks(hasil, suf);
            }

        }

        //boolean tandaBreak = false;
        //if (prefixTemp.length() != 0) {
        //    this.cekSufiks(prefixTemp, suf);
        /*for (int i = 0; i < suf.size() && !tandaBreak; i++) {
                if (prefixTemp.endsWith(suf.get(i))) {
                    System.out.println("suffix " + suf.get(i));
                    String temp = prefixTemp.substring(0, prefixTemp.length() - suf.get(i).length());
                    if (temp.length() > 2) {
                        if (cekLexicon(temp)) {

                            hasilList.add(temp);
                            tandaBreak = true;

                        }
                    }
                }
                if (prefixTemp2.endsWith(suf.get(i))) {
                    String temp = prefixTemp2.substring(0, prefixTemp2.length() - suf.get(i).length());

                    if (temp.length() > 2) {
                        if (cekLexicon(temp)) {

                            hasilList.add(temp);
                            tandaBreak = true;

                        }

                    }
                }

            }*/
//        }
//        if (prefixTemp2.length() != 0) {
//            this.cekSufiks(prefixTemp2, suf);
//        }
//        if (!tandaBreak) {
//            this.cekSufiks(in, suf);
//        }
//        
//        for (int i = 0; i < hasilList.size(); i++) {
//            this.cekInfiks(hasilList.get(i));
//        }
        this.cekSufiks(in, suf);

        if (hasilList.isEmpty() && counter != 2) {
            hasil = prefixTemp;
            counter++;
            System.out.println("Ulangi");
            hasilList = cekBerimbuhan(hasil, counter);

            if (hasilList.isEmpty()) {
                hasilList = cekBerimbuhan(prefixTemp2, counter);
            }
        }

        return hasilList;
    }

    private void cekSufiks(String s, ArrayList<String> suf) {
        this.hasilTanpaAkhiran = s;
        for (int i = 0; i < suf.size(); i++) {
            if (s.endsWith(suf.get(i))) {
                System.out.println("suffix " + suf.get(i));
                String temp = s.substring(0, s.length() - suf.get(i).length());
                if (temp.length() > 2) {
                    if (cekLexicon(temp)) {
                        hasilList.add(temp);
                        //tandaBreak = true;
                    } else {
                        this.hasilTanpaAkhiran = temp;
                    }
                }
            }
            /*if (prefixTemp2.endsWith(suf.get(i))) {
                String temp = prefixTemp2.substring(0, prefixTemp2.length() - suf.get(i).length());

                if (temp.length() > 2) {
                    if (cekLexicon(temp)) {

                        hasilList.add(temp);
                        tandaBreak = true;

                    }

                }
            }*/
        }
    }

    //inputanya itu string yg udh g ad sufix am infix sama file infiks.txt
    private void cekInfiks(String s) {
        String temp = s;
        String temp2 = "";
        if (!cekLexicon(s)) {
            if (s.contains("el")) {
                temp = s.replace("el", " ");
                for (int i = 0; i < temp.length(); i++) {
                    if (temp.charAt(i) != ' ') {
                        temp2 += temp.charAt(i);
                    }
                }
                if (cekLexicon(temp2)) {
                    hasilList.add(temp2);
                }
            } else if (s.contains("er")) {
                temp = s.replace("er", " ");
                for (int i = 0; i < temp.length(); i++) {
                    if (temp.charAt(i) != ' ') {
                        temp2 += temp.charAt(i);
                    }
                }
                if (cekLexicon(temp2)) {
                    hasilList.add(temp2);
                }
            } else if (s.contains("em")) {
                temp = s.replace("em", " ");
                for (int i = 0; i < temp.length(); i++) {
                    if (temp.charAt(i) != ' ') {
                        temp2 += temp.charAt(i);
                    }
                }
                if (cekLexicon(temp2)) {
                    hasilList.add(temp2);
                }
            } else if (s.contains("in")) {
                temp = s.replace("in", " ");
                for (int i = 0; i < temp.length(); i++) {
                    if (temp.charAt(i) != ' ') {
                        temp2 += temp.charAt(i);
                    }
                }
                if (cekLexicon(temp2)) {
                    hasilList.add(temp2);
                }
            }
        }
    }
}

class StringComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return Integer.compare(o1.length(), o2.length());
    }
}
