package me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.getters;

import me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.ParamsContainer;

import java.io.File;
import java.util.Optional;

public class FileGetter implements ValueGetter<File> {
    @Override
    public File get(ParamsContainer params, String name) {
        Optional<File> file = params.getAsIgnoreGetters(name, File.class);
        if (file.isPresent()) {
            return file.get();
        }

        Optional<String> path = params.getAsIgnoreGetters(name, String.class);

        return path.map(File::new).orElse(null);
    }
}
