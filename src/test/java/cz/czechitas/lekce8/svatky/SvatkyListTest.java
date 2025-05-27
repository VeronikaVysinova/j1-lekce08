package cz.czechitas.lekce8.svatky;

import org.junit.jupiter.api.Test;

import java.time.Month;
import java.time.MonthDay;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SvatkyListTest {
    private final SvatkyList svatky = new SvatkyList();

    @Test
    void nacistSvatkyVMesici() {
        int pocetSvatkuVlednu = 50;
        String prvniSvatekVLednu = "Karina";

        List<Svatek> vysledek = svatky.nacistSvatkyVMesici(Month.JANUARY);

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

                    List<MonthDay> vysledek = svatky.urcitDatumSvatku("Eva");

                    assertIterableEquals(List.of(stedryDen), vysledek);
                },
                () -> {
                    MonthDay svatekMaJosef = MonthDay.of(3, 19);

                    List<MonthDay> vysledek = svatky.urcitDatumSvatku("Josef");

                    assertIterableEquals(List.of(svatekMaJosef), vysledek);
                },
                () -> {
                    List<MonthDay> svatekMaPetr = List.of(
                            MonthDay.of(2, 22),
                            MonthDay.of(6, 29)
                    );

                    List<MonthDay> vysledek = svatky.urcitDatumSvatku("Petr");

                    assertIterableEquals(svatekMaPetr, vysledek);
                }
        );
    }

    @Test
    void nacistSeznamSvatkuMuzu() {
        long pocetJmenMuzu = svatky.nacistSeznamSvatkuMuzu().size();
        assertEquals(268, pocetJmenMuzu);
    }

    @Test
    void nacistSeznamSvatkuZen() {
        long pocetJmenZen = svatky.nacistSeznamSvatkuZen().size();
        assertEquals(276, pocetJmenZen);
    }

    @Test
    void urcitSvatkyProDen() {
        List<String> jmenaStedryDen = List.of("Adam", "Eva", "Gaia", "Kračun");

        List<String> vysledek = svatky.urcitSvatkyProDen(MonthDay.of(12, 24));

        assertIterableEquals(jmenaStedryDen, vysledek);
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

        List<String> vysledek = svatky.nacistZenskaJmenaVMesici(Month.FEBRUARY);

        assertIterableEquals(zenskaJmenaVUnoru, vysledek);
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

        List<String> vysledek = svatky.urcitJmenavCervnuVynechatPrvnichDeset();

        assertIterableEquals(jmenaVCervnuBezPrvnichDeseti, vysledek);
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

        List<String> vysledek = svatky.urcitJmenaOdVanoc();

        assertIterableEquals(jmenaOdVanoc, vysledek);
    }

}
