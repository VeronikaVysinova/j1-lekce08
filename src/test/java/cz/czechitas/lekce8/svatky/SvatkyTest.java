package cz.czechitas.lekce8.svatky;

import org.junit.jupiter.api.Test;

import java.time.Month;
import java.time.MonthDay;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SvatkyTest {
    private final Svatky svatky = new Svatky();

    @Test
    void nacistSvatkyVMesici() {
        int pocetSvatkuVlednu = 50;
        String prvniSvatekVLednu = "Karina";

        List<Svatek> vysledek = svatky.nacistSvatkyVMesici(Month.JANUARY).toList();

        assertAll(
                () -> assertEquals(pocetSvatkuVlednu, vysledek.size()),
                () -> assertEquals(prvniSvatekVLednu, vysledek.getFirst().jmeno())
        );
    }

    @Test
    void urcitDatumSvatku() {
        assertAll(
                () -> {
                    MonthDay stedryDen = MonthDay.of(12, 24);

                    Stream<MonthDay> vysledek = svatky.urcitDatumSvatku("Eva");

                    assertIterableEquals(List.of(stedryDen), vysledek.toList());
                },
                () -> {
                    MonthDay svatekMaJosef = MonthDay.of(3, 19);

                    Stream<MonthDay> vysledek = svatky.urcitDatumSvatku("Josef");

                    assertIterableEquals(List.of(svatekMaJosef), vysledek.toList());
                },
                () -> {
                    List<MonthDay> svatekMaPetr = List.of(
                            MonthDay.of(2, 22),
                            MonthDay.of(6, 29)
                    );

                    Stream<MonthDay> vysledek = svatky.urcitDatumSvatku("Petr");

                    assertIterableEquals(svatekMaPetr, vysledek.toList());
                }
        );
    }

    @Test
    void nacistSeznamSvatkuMuzu() {
        long pocetJmenMuzu = svatky.nacistSeznamSvatkuMuzu().count();
        assertEquals(268, pocetJmenMuzu);
    }

    @Test
    void nacistSeznamSvatkuZen() {
        long pocetJmenZen = svatky.nacistSeznamSvatkuZen().count();
        assertEquals(276, pocetJmenZen);
    }

    @Test
    void urcitSvatkyProDen() {
        List<String> jmenaStedryDen = List.of("Adam", "Eva", "Gaia", "Kračun");

        Stream<String> vysledek = svatky.urcitSvatkyProDen(MonthDay.of(12, 24));

        assertIterableEquals(jmenaStedryDen, vysledek.toList());
    }

    @Test
    void nacistZenskaJmenaVMesici() {
        List<String> zenskaJmenaVUnoru = List.of(
                "Brigid",
                "Nela",
                "Jarmila",
                "Dobromila",
                "Dobromíra",
                "Vanda",
                "Danuše",
                "Veronika",
                "Bereniké",
                "Verona",
                "Milada",
                "Mlada",
                "Apolena",
                "Apolonie",
                "Božena",
                "Slavěna",
                "Slávka",
                "Věnceslava",
                "Věnka",
                "Valentýna",
                "Jiřina",
                "Jorga",
                "Ljuba",
                "Miloslava",
                "Gizela",
                "Lenka",
                "Eleonora",
                "Etel",
                "Liliana",
                "Lilie",
                "Dorota",
                "Dorothea"
        );

        Stream<String> vysledek = svatky.nacistZenskaJmenaVMesici(Month.FEBRUARY);

        assertIterableEquals(zenskaJmenaVUnoru, vysledek.toList());
    }

    @Test
    void zjistitPocetMuzskychSvatkuPrvniho() {
        assertEquals(10, svatky.zjistitPocetMuzskychSvatkuPrvniho());
    }

    @Test
    void vypsatJmenaListopad() {
        svatky.vypsatJmenaListopad();
    }

    @Test
    void zjistitPocetUnikatnichJmen() {
        assertEquals(541, svatky.zjistitPocetUnikatnichJmen());
    }

    @Test
    void urcitJmenavCervnuVynechatPrvnichDeset() {
        List<String> jmenaVCervnuBezPrvnichDeseti = List.of(
                "Slavoj",
                "Medard",
                "Stanislava",
                "Gita",
                "Bruno",
                "Amabel",
                "Mabel",
                "Antonie",
                "Antonín",
                "Roland",
                "Vít",
                "Svantovít",
                "Isolde",
                "Zbyněk",
                "Adolf",
                "Milan",
                "Leoš",
                "Leon",
                "Květa",
                "Alois",
                "Pavla",
                "Zdeňka",
                "Zdenka",
                "Jan",
                "Ivan",
                "Adrian",
                "Ladislav",
                "Lubomír",
                "Petr",
                "Pavel",
                "Šárka",
                "Vlastimír"
        );

        Stream<String> vysledek = svatky.urcitJmenavCervnuVynechatPrvnichDeset();

        assertIterableEquals(jmenaVCervnuBezPrvnichDeseti, vysledek.toList());
    }

    @Test
    void urcitJmenaOdVanoc() {
        List<String> jmenaOdVanoc = List.of(
                "Adam",
                "Eva",
                "Gaia",
                "Kračun",
                "Štěpán",
                "Štefan",
                "Žaneta",
                "Bohumila",
                "Judita",
                "David",
                "Silvestr"
        );

        Stream<String> vysledek = svatky.urcitJmenaOdVanoc();

        assertIterableEquals(jmenaOdVanoc, vysledek.toList());
    }

}
