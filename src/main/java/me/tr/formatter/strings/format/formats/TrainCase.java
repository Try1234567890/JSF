package me.tr.formatter.strings.format.formats;

public class TrainCase extends SeparatorCase {

    public TrainCase(String str) {
        super(str, "-");
    }

    public static TrainCase of(String str) {
        return new TrainCase(str);
    }

    public static TrainCase of(String str, String separator) {
        return new TrainCase(str).separator(separator);
    }

    @Override
    public TrainCase newInstance(String text) {
        return new TrainCase(text);
    }

    @Override
    public TrainCase separator(String separator) {
        super.separator(separator);
        return this;
    }
}
