package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Paaohjelma {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        var KPSPeli = new KPSTehdas(scanner);

        while (true) {
            System.out.println("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");

            String vastaus = scanner.nextLine();
            
            KPS peli;
            if (vastaus.endsWith("a")) {
                peli = KPSPeli.kaksinpeli();
            } else if (vastaus.endsWith("b")) {
                peli = KPSPeli.helppoYksinpeli();
            } else if (vastaus.endsWith("c")) {
                peli = KPSPeli.vaikeaYksinpeli();
            } else {
                break;
            }

            System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
            peli.pelaa();
        }

    }
}
