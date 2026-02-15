package me.tr.trformatter.defaults.tags.date;

import me.tr.trformatter.components.Tag;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.ParamsContainer;
import me.tr.trformatter.uids.UID;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Now extends Tag {

    public Now() {
        super(new UID("now"));
    }

    /**
     * Evaluate this component.
     *
     * @param params The params found for this component.
     * @return the string to replace with placeholder in raw text.
     */
    @Override
    public String evaluate(ParamsContainer params) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(getFormat(params)));
    }

    private String getFormat(ParamsContainer params) {
        return params.getAs("format", String.class).orElse("dd.MM.yyyy");
    }
}
