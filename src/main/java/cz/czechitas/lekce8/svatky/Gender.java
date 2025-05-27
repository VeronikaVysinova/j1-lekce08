package cz.czechitas.lekce8.svatky;

/**
 * @author Filip Jirsák
 */
public enum Gender {
    MUZ("muž"),
    ZENA("žena"),
    ;

    private final String text;

    Gender(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
