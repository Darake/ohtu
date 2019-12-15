package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSParempiTekoaly extends KPS {
    private TekoalyParannettu tekoaly;

    public KPSParempiTekoaly(Scanner scanner) {
        super(scanner);
        toinenPelaaja = "Tietokoneen";
        tekoaly = new TekoalyParannettu(20);
    }

    @Override
    protected String tokanSiirto() {
        return tekoaly.annaSiirto();
    }
}