package Projekt;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KinoTest {

    @Test
    public void testBerechnePreis() {

        Kino kino = new Kino();

        String film = "Moana 2 (9.50 €)";
        String snack = "Popcorn süß (4.50 €)";
        String getränk = "Cola (4.50 €)";
        double erwarteterPreis = 9.50 + 4.50 + 4.50;

        double berechneterPreis = kino.berechnePreis(snack, getränk, film);

        assertEquals(erwarteterPreis, berechneterPreis, 0.01);
    }
}