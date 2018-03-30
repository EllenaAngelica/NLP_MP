
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

    public ArrayList<String> cekBerimbuhan(String input) {
        String hasil = "";
        String in = input;
        String prefix = "";
        String infix = "";
        String suffix = "";
        ArrayList<String> listKata = new ArrayList<String>();
        ArrayList<String> listHurufPertama = new ArrayList<String>();
        boolean hasPrefix = false; //klo true berarti ada prefiks
        boolean prefixChecked = false; //buat ngeliat apakaha methodnya msh meriksa prefix. kalo true berarti methodnya mulai meriksa yg setelah prefiks
        boolean hasInfix = false; //kalo true berarti ada infix
        boolean hasSuffix = false; //kalo true berarti ada suffix
        boolean flagPrefixM = true; //masih ngaco. otak atik aja
        boolean ubahHuruf = false; //kalo true berarti huruf pertamanya dirubah
        int preIndex = 0; //buat nentuin dimana prefiksnya berhenti
        int panjangKata = 0; // ga guna?
        
        ArrayList<String> hasilList = new ArrayList<>();
        
        
        if (Trie.getInstance().search(hasil)) {
            hasilList.add(hasil);
            return hasilList;
        }
        else{
              if(in.startsWith("be")||in.startsWith("te")){
                  hasil=in.substring(2);
                  if(hasil.startsWith("l")){
                      hasil=hasil.substring(1);
                      if (Trie.getInstance().search(hasil)){
                        hasilList.add(hasil);
                        return hasilList;
                      }
                  }
                  else if(hasil.startsWith("r")){
                      if (Trie.getInstance().search(hasil)){
                        hasilList.add(hasil);
                        return hasilList;
                      }
                      else{
                          hasil=hasil.substring(1);
                          if (Trie.getInstance().search(hasil)){
                                hasilList.add(hasil);
                                return hasilList;
                          }
                      }
                  }
              
                  else{
                      if (Trie.getInstance().search(hasil)){
                                hasilList.add(hasil);
                                return hasilList;
                          }
                  }
              } else {
                          
        for (int i = 0; i < in.length(); i++) {
            hasil += in.substring(i, i + 1);
            if (prefixChecked) {
                panjangKata++;
            }

            //cek prefix
            if (!hasPrefix && preIndex < 2) {
                for (int j = 0; j < pref.size(); j++) {
                    if (hasil.equals(pref.get(j))) {
                        hasPrefix = true;
                        prefix = prefix.concat(hasil);
                        break;
                    }
                }
            }
            if (hasPrefix && !prefixChecked) {
                /**
                 *
                 * MASIH NGACO TOLONG BENERIN CONTOH KATA YG GA KEDETECT:
                 * menari, mendusta, menampilkan KALO MASUKIN KATA (MISALNYA)
                 * mencari, YANG MUNCUL CUMA ari
                 *
                 */
                if (preIndex >= 2) {
                    //prefixnya ga ngerubah huruf pertama
                    if ((prefix.equals("me") || prefix.equals("pe"))) {
                        if ((in.charAt(preIndex) == 'm') && flagPrefixM) {
                            listHurufPertama.add("b");
                            listHurufPertama.add("f");
                            listHurufPertama.add("v");
                            listHurufPertama.add("m");
                            listHurufPertama.add("p");
                            listHurufPertama.add("t");
                            listHurufPertama.add("k");
                            prefixChecked = true;
                            ubahHuruf = true;
                        } else {
                            flagPrefixM = false;
                            if (preIndex == 3) {
                                if (in.charAt(preIndex) == 'g') {
                                    listHurufPertama.add("a");
                                    listHurufPertama.add("e");
                                    listHurufPertama.add("g");
                                    listHurufPertama.add("h");
                                    listHurufPertama.add("i");
                                    listHurufPertama.add("u");
                                    listHurufPertama.add("o");
                                    listHurufPertama.add("k");
                                    listHurufPertama.add("g");
                                    ubahHuruf = true;
                                } else if (in.charAt(preIndex) == 'y') {
                                    listHurufPertama.add("s");
                                    ubahHuruf = true;
                                }
                                prefixChecked = true;
                            }
                            //preIndex++;
                        }
                    } else {
                        if (preIndex > 3) {
                            prefixChecked = true;
                        }
                    }
                }
                preIndex++;
            }
        }
        
        //kalo ga ada prefiks langsung masukin
        //kalo udh bisa ngedetect suffix sm infix nanti benerin lg
        if (Trie.getInstance().search(hasil)) {
            hasilList.add(hasil);
        }
        if (!hasPrefix) {
            //System.out.println(hasil);
        } else {
            String temp = hasil.substring(preIndex, hasil.length());
            //System.out.println(temp); //buat ngecek isi temp
            if (ubahHuruf) {
                for (int i = 0; i < listHurufPertama.size(); i++) {
                    String temp2 = listHurufPertama.get(i) + temp;
                    //System.out.println(temp2);
                    if (Trie.getInstance().search(temp2)) {
                        hasilList.add(temp2);
                        //break;
                    }
                }
            }
            if (Trie.getInstance().search(temp)) {
                hasilList.add(temp);
            }
        }
        
              }
              
        return hasilList;}
        /*if(Trie.getInstance().search(in)){
             return in;
         }
         for(int i =0;i<suf.size();i++){
            
            if(in.endsWith(suf.get(i))){
                
                in = in.substring(0, (in.length()-suf.get(i).length()));
                System.out.println(in);
                break;
            }
        }
         
        for(int i =0;i<pref.size();i++){
          
            if(in.startsWith(pref.get(i))){
                //System.out.println(pref.get(i));
                if("meng".equals(pref.get(i))){
                    System.out.println(" "+"k"+in.substring(pref.get(i).length()));
                    
                        if(Trie.getInstance().search("k"+in.substring(pref.get(i).length()))){
                            in="k"+in.substring(pref.get(i).length());
                        }
                        else{
                            
                            String temp = in.substring(pref.get(i).length());
                            if(Trie.getInstance().search(temp)){
                                in=temp;
                            }
                            
                            else{
                                temp = in.substring(pref.get(i).length()+1);
                                if(Trie.getInstance().search(temp)){                                
                                        in=temp;
                                    }
                                }
                        }
                        
                    
                }
                else if("men".equals(pref.get(i))){
                     if(in.charAt(pref.get(i).length())=='e'){
                        in="t"+in.substring(pref.get(i).length()); 
                    }
                }
                else{
                    in=in.substring(pref.get(i).length()); 
                }
                
                System.out.println(in);
                break;
            }
        }
        if(Trie.getInstance().search(in)){
             return in;
         }
        
        hasil=in;
        if(hasil!=""){
                System.out.println(hasil);
                return hasil;
        }*/
        //return input;
    }

}

class StringComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return Integer.compare(o1.length(), o2.length());
    }
}
