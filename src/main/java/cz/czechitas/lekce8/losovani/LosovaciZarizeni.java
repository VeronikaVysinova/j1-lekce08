package cz.czechitas.lekce8.losovani;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Třída pro losování náhodných čísel.
 */
public class LosovaciZarizeni {

    private final Random random = new Random();

    /**
     * Losuje seznam náhodných čísel.
     * <p>
     * Losovaná čísla se mohou opakovat.
     *
     * @param minimum Nejmenší možná vylosovaná hodnota.
     * @param maximum Nejvyšší možná vylosovaná hodnota.
     * @param pocet   Počet vylosovaných čísel.
     * @return Stream vylosovaných čísel.
     */
    public Stream<Integer> losovatSeznamCisel(int minimum, int maximum, int pocet) {
        return random.ints(pocet, minimum, maximum + 1)
                .boxed();
    }

    /**
     * Losuje 6 čísel v rozmezí 1–49.
     * <p>
     * Losovaná čísla se nesmí opakovat.
     *
     * @return Stream vylosovaných čísel.
     */
    public Stream<Integer> losovatSportkuHlavniTah() {
        return random.ints(1, 49 + 1) // 49 + 1, protože parametr horní hranice musí být mimo rozsah vracených hodnot.
                .distinct()
                .limit(6)
                .boxed();
    }

    /**
     * Losuje 6 čísel v rozmezí 1–9.
     * <p>
     * Losovaná čísla se mohou opakovat.
     *
     * @return Seznam vylosovaných čísel.
     */
    public Stream<Integer> losovatSanci() {
        return losovatSeznamCisel(1, 9, 6);
    }

    /**
     * Vrátí seznam 6 vylosovaných čísel v rozmezí 1–49.
     * <p>
     * Losovaná čísla se nesmí opakovat.
     *
     * @return Seznam vylosovaných čísel.
     */
    public List<Integer> getSportkaHlavniTah() {
        return losovatSportkuHlavniTah().toList();
    }

    /**
     * Vrátí seznam 6 vylosovaných čísel v rozmezí 1–9.
     * <p>
     * Losovaná čísla se mohou opakovat.
     *
     * @return Seznam vylosovaných čísel.
     */
    public List<Integer> getSance() {
        return losovatSanci().toList();
    }

    /**
     * Losuje seznam čísel – všechna tažená čísla musí být sudá.
     * <p>
     * Implementováno pomocí filtru, který propustí jen sudá čísla.
     *
     * @param minimum Nejmenší možná vylosovaná hodnota.
     * @param maximum Nejvyšší možná vylosovaná hodnota.
     * @param pocet   Počet vylosovaných čísel.
     * @return Stream vylosovaných čísel.
     */
    public IntStream losovatSeznamSudychCisel(int minimum, int maximum, int pocet) {
        return random.ints(minimum, maximum)
                .filter(cislo -> cislo % 2 == 0)
                .limit(pocet);
    }

    /**
     * Losuje seznam čísel – všechna tažená čísla musí být sudá.
     * <p>
     * Implementováno pomocí mapy, která všechna tažená čísla zdvojnásobí.
     *
     * @param minimum Nejmenší možná vylosovaná hodnota.
     * @param maximum Nejvyšší možná vylosovaná hodnota.
     * @param pocet   Počet vylosovaných čísel.
     * @return Stream vylosovaných čísel.
     */
    public IntStream losovatSeznamSudychCiselPomociMapy(int minimum, int maximum, int pocet) {
        return random.ints(pocet, minimum / 2, maximum / 2)
                .map(cislo -> cislo * 2);
    }

}
