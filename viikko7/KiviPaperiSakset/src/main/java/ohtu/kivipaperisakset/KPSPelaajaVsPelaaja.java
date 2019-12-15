package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSPelaajaVsPelaaja extends KPS {

    public KPSPelaajaVsPelaaja(Scanner scanner) {
        super(scanner);
        toinenPelaaja = "Toisen pelaajan";
    }

    @Override
    protected String tokanSiirto() {
        return scanner.nextLine();
    }
}