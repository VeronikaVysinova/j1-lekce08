package cz.czechitas.lekce8.losovani;

import org.junit.jupiter.api.RepeatedTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Filip Jirsák
 */
class LosovaciZarizeniTest {
    private final LosovaciZarizeni losovaciZarizeni = new LosovaciZarizeni();

    @RepeatedTest(100)
    void getSazkaHlavniTah() {
        int ocekavanyPocetTazenychCisel = 6;
        List<Integer> vylosovanaCisla = losovaciZarizeni.getSportkaHlavniTah();

        assertEquals(ocekavanyPocetTazenychCisel, vylosovanaCisla.size(), "Vylosovaných čísel není %d.".formatted(ocekavanyPocetTazenychCisel));
        assertAll(
                vylosovanaCisla.stream()
                        .map(cislo -> (
                                        () -> assertTrue(cislo >= 1 && cislo <= 49, () -> "Vylosované číslo %d není v rozsahu 1–49.".formatted(cislo))
                                )
                        )
        );
        //Pokud ze seznamu odstraníme duplicity a zbyde nám stejný počet čísel, jako na začátku, znamená to, že čísla byla unikátní.
        assertEquals(ocekavanyPocetTazenychCisel,
                vylosovanaCisla.stream()
                        .distinct()
                        .count(),
                () -> String.format("Vylosovaná čísla nejsou unikátní: %s", formatovatSeznamCisel(vylosovanaCisla)));
    }

    @RepeatedTest(100)
    void getSance() {
        int ocekavanyPocetTazenychCisel = 6;
        List<Integer> vylosovanaCisla = losovaciZarizeni.getSance();

        assertEquals(ocekavanyPocetTazenychCisel, vylosovanaCisla.size(), "Vylosovaných čísel není %d.".formatted(ocekavanyPocetTazenychCisel));
        assertAll(
                vylosovanaCisla.stream()
                        .map(cislo -> (
                                        () -> assertTrue(cislo >= 1 && cislo <= 9, () -> "Vylosované číslo %d není v rozsahu 1–9.".formatted(cislo))
                                )
                        )
        );
    }

    @RepeatedTest(100)
    void losujSudaCislaFilter() {
        IntStream sudaCisla = losovaciZarizeni.losovatSeznamSudychCisel(2, 60, 10);
        overitSudaCisla(sudaCisla);
    }

    @RepeatedTest(100)
    void losujSudaCislaMap() {
        IntStream sudaCisla = losovaciZarizeni.losovatSeznamSudychCiselPomociMapy(2, 60, 10);
        overitSudaCisla(sudaCisla);
    }

    private void overitSudaCisla(IntStream sudaCisla) {
        assertAll(sudaCisla.boxed()
                .flatMap(cislo ->
                        Stream.of(
                                () -> assertEquals(0, cislo % 2, () -> "Číslo %d není sudé.".formatted(cislo)),
                                () -> assertTrue(cislo >= 2, () -> "Číslo %d je menší než 2.".formatted(cislo)),
                                () -> assertTrue(cislo <= 60, () -> "Číslo %d je větší než 60".formatted(cislo))
                        )
                )
        );
    }

    private String formatovatSeznamCisel(Stream<Integer> streamCisel) {
        return streamCisel.map(cislo -> Integer.toString(cislo)).collect(Collectors.joining(", "));
    }

    private String formatovatSeznamCisel(List<Integer> seznamCisel) {
        return formatovatSeznamCisel(seznamCisel.stream());
    }
}
