package cz.czechitas.lekce8.svatky;

import org.junit.jupiter.api.Test;

import java.time.MonthDay;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Filip Jirsák
 */
class SvatekTest {

    @Test
    void testToString() {
        String denAJmeno = "1. 2. Hynek";

        String vysledek = new Svatek(MonthDay.of(2, 1), "Hynek", Gender.MUZ).toString();

        assertEquals(denAJmeno, vysledek);
    }
}
