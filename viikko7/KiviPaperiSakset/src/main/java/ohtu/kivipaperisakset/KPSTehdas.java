package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTehdas {
    private Scanner scanner;
    
    public KPSTehdas(Scanner scanner) {
        this.scanner = scanner;
    }

    public KPS kaksinpeli() {
        return new KPSPelaajaVsPelaaja(scanner);
    }

    public KPS helppoYksinpeli() {
        return new KPSTekoaly(scanner);
    }

    public KPS vaikeaYksinpeli() {
        return new KPSParempiTekoaly(scanner);
    }
}