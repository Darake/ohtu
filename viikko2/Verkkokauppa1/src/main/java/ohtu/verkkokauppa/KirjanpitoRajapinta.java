package ohtu.verkkokauppa;

import java.util.ArrayList;

public interface KirjanpitoRajapinta {
    void lisaaTapahtuma(String tapahtuma);

    ArrayList<String> getTapahtumat();
}
