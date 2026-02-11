package me.tr.trformatter.phases.synthesis.interpolator;

import me.tr.trformatter.phases.synthesis.interpolator.components.IndexedPlaceholder;

import java.util.List;

public interface Interpolator {

    String interpolate(String text, List<IndexedPlaceholder> placeholderResults);

}
