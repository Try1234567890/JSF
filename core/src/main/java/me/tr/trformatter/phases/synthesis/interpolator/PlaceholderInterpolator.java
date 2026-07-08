package me.tr.trformatter.phases.synthesis.interpolator;

import me.tr.trformatter.phases.synthesis.interpolator.components.IndexedPlaceholder;
import me.tr.utilities.validators.Preconditions;
import me.tr.utilities.validators.ValidationUtils;

import java.util.List;

public class PlaceholderInterpolator implements Interpolator {
    public static final PlaceholderInterpolator INSTANCE = new PlaceholderInterpolator();


    @Override
    public String interpolate(String text, List<IndexedPlaceholder> placeholderResults) {
        if (ValidationUtils.isNull(placeholderResults)) return text;

        StringBuilder builder = new StringBuilder();
        int lastIndex = 0;

        for (IndexedPlaceholder placeholder : placeholderResults) {
            builder.append(text, lastIndex, placeholder.start());
            builder.append(placeholder.result());

            lastIndex = placeholder.end();
        }

        if (lastIndex < text.length()) {
            builder.append(text.substring(lastIndex));
        }

        return builder.toString();
    }
}
