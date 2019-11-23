
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukuJoukko;      // Joukon luvut säilytetään taulukon alkupäässä.
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this(KAPASITEETTI);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti virheellinen");
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kasvatuskoko virheellinen");
        }
        lukuJoukko = new int[kapasiteetti];
        this.kasvatuskoko = kasvatuskoko;
        alkioidenLkm = 0;
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            lukuJoukko[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm >= lukuJoukko.length) {
                kasvataTaulukkoa();
            }
            return true;
        }
        return false;
    }

    private void kasvataTaulukkoa() {
        int[] kopioLukuJoukosta = lukuJoukko;
        lukuJoukko = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(kopioLukuJoukosta, lukuJoukko);
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukuJoukko[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukuJoukko[i]) {
                lukuJoukko[i] = 0;
                alkioidenLkm--;
                siirraKohdastaAlkaenLuvutVasemmalle(i);
                return true;
            }
        }
        return false;
    }

    private void siirraKohdastaAlkaenLuvutVasemmalle(int kohta) {
        for (int i = kohta; i < alkioidenLkm; i++) {
            lukuJoukko[i] = lukuJoukko[i + 1];
        }
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < alkioidenLkm; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int koko() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        String lukuJoukkoMerkkijonona;
        if (alkioidenLkm == 0) {
            lukuJoukkoMerkkijonona = "{}";
        } else if (alkioidenLkm == 1) {
            lukuJoukkoMerkkijonona = "{" + lukuJoukko[0] + "}";
        } else {
            lukuJoukkoMerkkijonona = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                lukuJoukkoMerkkijonona += lukuJoukko[i];
                lukuJoukkoMerkkijonona += ", ";
            }
            lukuJoukkoMerkkijonona += lukuJoukko[alkioidenLkm - 1];
            lukuJoukkoMerkkijonona += "}";
        }
        return lukuJoukkoMerkkijonona;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        kopioiTaulukko(lukuJoukko, taulu);
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;

    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(bTaulu[i]);
        }
 
        return z;
    }
        
}
