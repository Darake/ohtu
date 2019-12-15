package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTekoaly extends KPS {
    private Tekoaly tekoaly;

    public KPSTekoaly(Scanner scanner) {
        super(scanner);
        toinenPelaaja = "Tietokoneen";
        tekoaly = new Tekoaly();
    }

    @Override
    protected String tokanSiirto() {
        return tekoaly.annaSiirto();
    }
}