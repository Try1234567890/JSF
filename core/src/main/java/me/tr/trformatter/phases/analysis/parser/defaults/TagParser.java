package me.tr.trformatter.phases.analysis.parser.defaults;

import me.tr.trformatter.components.Tag;
import me.tr.trformatter.phases.analysis.exceptions.ComponentNotFound;
import me.tr.trformatter.phases.analysis.lexer.tokens.components.TagToken;
import me.tr.trformatter.phases.analysis.parser.Parser;
import me.tr.trformatter.phases.evaluation.components.EvalTag;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TagParser implements Parser<TagToken> {
    public static final TagParser INSTANCE = new TagParser();

    @Override
    public EvalTag parse(TagToken token) {
        if (token == null) return null;
        Optional<Tag> tagOpt = Tag.getTag(token.getName().getName());

        return tagOpt.map(value -> new EvalTag(value,
                        FunctionParser.INSTANCE.parseAll(token.getFunctions()), token.params()))
                .orElseGet(() -> {
                    ComponentNotFound.tag(token.getName().getName()).printStackTrace(System.err);
                    return null;
                });
    }

    public List<EvalTag> parseAll(List<TagToken> list) {
        List<EvalTag> components = new ArrayList<>();

        for (TagToken token : list) {
            EvalTag tag = parse(token);

            if (tag == null) continue;


            components.add(tag);
        }

        return components;
    }
}
