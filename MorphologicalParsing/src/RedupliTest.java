
import java.io.IOException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Angelica
 */
public class RedupliTest {
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        Parser p = new Parser();
        while(sc.hasNext()){
            //System.out.println("Hasil = " + p.cekReduplikasi(sc.nextLine()));
            System.out.println("Hasil = " + p.cekPengulangan(sc.nextLine()));
        }
    }
}
