package me.tr.formatter.strings.format;

public class FormatResult {
    private final TextFormat format;
    private final String result;
    private final Caser caser;

    public FormatResult(TextFormat format) {
        this.format = format;
        this.result = format.getResult();
        this.caser = new Caser(format);
    }

    public String getResult() {
        return result;
    }

    public String toUpperCase() {
        return getCaser().toUpperCase();
    }

    public String toLowerCase() {
        return getCaser().toLowerCase();
    }

    public Caser getCaser() {
        return caser;
    }

    public TextFormat getFormat() {
        return format;
    }
}
