package me.tr.trformatter.defaults.tags.readfile;

import me.tr.trformatter.components.Tag;
import me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.ParamsContainer;
import me.tr.trformatter.uids.UID;
import me.tr.trformatter.utility.Validator;

import java.io.File;

public class ReadFile extends Tag {
    private static final UID KEY_NAME = new UID("file", "path");

    public ReadFile() {
        super(new UID("read_file"));
    }

    /**
     * Evaluate this component.
     *
     * @param params The params found for this component.
     * @return the string to replace with placeholder in raw text.
     */
    @Override
    public String evaluate(ParamsContainer params) {
        File file = params.getAsOrNull(KEY_NAME, File.class);
        Validator.isNull(file, "\"" + KEY_NAME + "\" param is not specified in " + params);

        return FileReader.read(file);
    }
}
