package me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.getters;

import me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.ParamsContainer;

import java.nio.file.Path;
import java.util.Optional;

public class PathGetter implements ValueGetter<Path> {
    @Override
    public Path get(ParamsContainer params, String name) {
        Optional<Path> file = params.getAs(name, Path.class);
        if (file.isPresent()) {
            return file.get();
        }

        Optional<String> path = params.getAs(name, String.class);

        return path.map(Path::of).orElse(null);
    }
}
