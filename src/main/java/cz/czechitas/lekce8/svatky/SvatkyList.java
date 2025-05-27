package cz.czechitas.lekce8.svatky;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

/**
 * Implementace svátků pomocí seznamu {@code List} – pro porovnání se Streamy.
 */
class SvatkyList {
    private static final DateTimeFormatter MONTH_PARSER = DateTimeFormatter.ofPattern("d.M.");
    private final List<Svatek> seznamSvatku;

    public SvatkyList() {
        seznamSvatku = new ArrayList<>();
        try {
            Path path = Paths.get(SvatkyList.class.getResource("svatky.txt").toURI());
            for (String radek : Files.readAllLines(path)) {
                seznamSvatku.add(SvatkyList.parsujRadek(radek));
            }
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Vrátí seznam všech svátků v daném měsíci.
     *
     * @param mesic Měsíc, pro který se mají svátky vypsat.
     * @return Stream svátků.
     */
    public List<Svatek> nacistSvatkyVMesici(Month mesic) {
        List<Svatek> vysledek = new ArrayList<>();
        for (Svatek svatek : seznamSvatku) {
            if (svatek.den().getMonth() == mesic) {
                vysledek.add(svatek);
            }
        }
        return vysledek;
    }

    /**
     * Vrátí den, kdy má dotyčné jméno svátek.
     *
     * @param jmeno
     * @return
     */
    public List<MonthDay> urcitDatumSvatku(String jmeno) {
        List<MonthDay> vysledek = new ArrayList<>();
        for (Svatek svatek : seznamSvatku) {
            if (svatek.jmeno().equals(jmeno)) {
                vysledek.add(svatek.den());
            }
        }
        return vysledek;
    }

    /**
     * Vrátí všechna jména mužů.
     *
     * @return Stream jmen.
     */
    public List<String> nacistSeznamSvatkuMuzu() {
        List<String> vysledek = new ArrayList<>();
        for (Svatek svatek : seznamSvatku) {
            if (svatek.gender() == Gender.MUZ) {
                vysledek.add(svatek.jmeno());
            }
        }
        return vysledek;
    }

    /**
     * Vrátí všechna jména žen.
     *
     * @return Stream jmen.
     */
    public List<String> nacistSeznamSvatkuZen() {
        List<String> vysledek = new ArrayList<>();
        for (Svatek svatek : seznamSvatku) {
            if (svatek.gender() == Gender.ZENA) {
                vysledek.add(svatek.jmeno());
            }
        }
        return vysledek;
    }

    /**
     * Vrátí jména, která mají v daný den svátek.
     *
     * @return Stream jmen.
     */
    public List<String> urcitSvatkyProDen(MonthDay den) {
        List<String> vysledek = new ArrayList<>();
        for (Svatek svatek : seznamSvatku) {
            if (svatek.den().equals(den)) {
                vysledek.add(svatek.jmeno());
            }
        }
        return vysledek;
    }

    /**
     * Vrátí ženská jména, která mají svátek v daném měsíci.
     *
     * @param mesic Vybraný měsíc.
     * @return Stream jmen.
     */
    public List<String> nacistZenskaJmenaVMesici(Month mesic) {
        List<String> vysledek = new ArrayList<>();
        for (Svatek svatek : seznamSvatku) {
            if (!svatek.den().getMonth().equals(mesic)) {
                continue;   // Klíčové slovo „continue“ znamená „přeskoč zbytek těla cyklu a pokračuj rovnou na další obrátku cyklu“.
            }
            if (!svatek.gender().equals(Gender.ZENA)) {
                continue;
            }
            vysledek.add(svatek.jmeno());
        }
        return vysledek;
    }

    /**
     * Vrátí počet mužů, kteří mají svátek 1. den v měsíci.
     *
     * @return Počet mužských jmen.
     */
    public long zjistitPocetMuzskychSvatkuPrvniho() {
        long pocet = 0L;
        for (Svatek svatek : seznamSvatku) {
            if (svatek.den().getDayOfMonth() != 1) {
                continue;   // Klíčové slovo „continue“ znamená „přeskoč zbytek těla cyklu a pokračuj rovnou na další obrátku cyklu“.
            }
            if (!svatek.gender().equals(Gender.MUZ)) {
                continue;
            }
            pocet++;        // pocet++ znamená „k proměnné pocet přičti jedničku a výsledek ulož zpět do proměnné pocet“.
        }
        return pocet;
    }

    /**
     * Vypíše do konzole seznam jmen, která mají svátek v listopadu.
     */
    public void vypsatJmenaListopad() {
        for (Svatek svatek : seznamSvatku) {
            if (svatek.den().getMonth().equals(Month.NOVEMBER)) {
                continue;   // Klíčové slovo „continue“ znamená „přeskoč zbytek těla cyklu a pokračuj rovnou na další obrátku cyklu“.
            }
            System.out.println(svatek.jmeno());
        }
    }

    /**
     * Vypíše počet unikátních jmen v kalendáři.
     */
    public long zjistitPocetUnikatnichJmen() {
        Set<String> unikatniJmena = new HashSet<>();
        for (Svatek svatek : seznamSvatku) {
            unikatniJmena.add(svatek.jmeno());
        }
        return unikatniJmena.size();
    }

    /**
     * Vrátí seznam jmen, která mají svátek v červnu – přeskočí prvních 10 jmen.
     *
     * @see Stream#skip(long)
     */
    public List<String> urcitJmenavCervnuVynechatPrvnichDeset() {
        List<String> jmena = new ArrayList<>();
        for (Svatek svatek : seznamSvatku) {
            if (!svatek.den().getMonth().equals(Month.JUNE)) {
                continue;   // Klíčové slovo „continue“ znamená „přeskoč zbytek těla cyklu a pokračuj rovnou na další obrátku cyklu“.
            }
            jmena.add(svatek.jmeno());
        }
        return jmena.subList(10, jmena.size());
    }

    /**
     * Vrátí seznam jmen, která mají svátek od 24. 12. včetně do konce roku.
     *
     * @see Stream#dropWhile(java.util.function.Predicate)
     */
    public List<String> urcitJmenaOdVanoc() {
        MonthDay stedryDen = MonthDay.of(12, 24);
        List<String> vysledek = new ArrayList<>();
        for (Svatek svatek : seznamSvatku) {
            if (svatek.den().isBefore(stedryDen)) {
                continue;   // Klíčové slovo „continue“ znamená „přeskoč zbytek těla cyklu a pokračuj rovnou na další obrátku cyklu“.
            }
            vysledek.add(svatek.jmeno());
        }
        return vysledek;
    }

    private static Svatek parsujRadek(String line) {
        String[] parts = line.split("\\s");
        assert parts.length == 3;
        return new Svatek(
                MonthDay.parse(parts[0], MONTH_PARSER),
                parts[1],
                Gender.valueOf(parts[2].toUpperCase(Locale.ROOT))
        );
    }

}
