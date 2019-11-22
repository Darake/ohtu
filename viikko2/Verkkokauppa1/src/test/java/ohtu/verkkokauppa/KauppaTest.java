package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

    private Pankki pankki;
    private Viitegeneraattori viite;
    private Varasto varasto;
    private Kauppa k;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);

        viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(42);

        varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        k = new Kauppa(varasto, pankki, viite);
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaArgumenteilla() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42,"12345", "33333-44455",5);
    }

    @Test
    public void ostoksenPaaytyttyaKahdellaTuotteellaPankinMetodiaTilisiirtoKutsutaanOikeillaArgumenteilla() {
        when(varasto.saldo(2)).thenReturn(7);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 2));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), anyInt(),eq("12345"), eq("33333-44455"),eq(7));
    }

    @Test
    public void ostoksenPaaytyttyaKahdellaSamallaTuotteellaPankinMetodiaTilisiirtoKutsutaanOikeillaArgumenteilla() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), anyInt(),eq("12345"), eq("33333-44455"),eq(10));
    }

    @Test
    public void ostoksenPaaytyttyaLoppuneellaTuotteellaPankinMetodiaTilisiirtoKutsutaanOikeillaArgumenteilla() {
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 2));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), anyInt(),eq("12345"), eq("33333-44455"),eq(5));
    }
}