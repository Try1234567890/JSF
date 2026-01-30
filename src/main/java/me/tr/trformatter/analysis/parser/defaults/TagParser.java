package me.tr.trformatter.analysis.parser.defaults;

import me.tr.trformatter.analysis.lexer.tokens.components.PlaceholderToken;
import me.tr.trformatter.analysis.lexer.tokens.components.TagToken;
import me.tr.trformatter.analysis.parser.Parser;
import me.tr.trformatter.components.Tag;
import me.tr.trformatter.evaluation.components.EvalTag;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TagParser implements Parser<TagToken> {

    @Override
    public EvalTag parse(TagToken token) {
        Optional<Tag> tagOpt = Tag.getTag(token.getName().getName());
        return tagOpt.map(value -> new EvalTag(value, token.params())).orElse(null);
    }

    public List<EvalTag> parse(List<TagToken> list) {
        List<EvalTag> components = new ArrayList<>();

        for (TagToken token : list) {
            components.add(parse(token));
        }

        return components;
    }
}
