package me.tr.trformatter.strings.cases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrainCase extends StringCase {
    protected TrainCase(String word) {
        super(word);
    }

    public static TrainCase of(String word) {
        return new TrainCase(word);
    }

    @Override
    public List<String> toSpaceCase(String text) {
        String[] parts = text.split("-");
        return new ArrayList<>(Arrays.asList(parts));
    }

    @Override
    public TrainCase fromSpaceCase(List<String> text) {
        StringBuilder sb = new StringBuilder();
        for (String part : text) {
            sb.append(part).append('-');
        }
        return new TrainCase(sb.substring(0, (sb.toString().length() - 1)));
    }
}
