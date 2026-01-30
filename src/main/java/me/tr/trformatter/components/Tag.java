package me.tr.trformatter.components;

import me.tr.trformatter.Validator;
import me.tr.trformatter.analysis.lexer.tokens.params.manager.ParamsContainer;
import me.tr.trformatter.registries.TagsRegistry;
import me.tr.trformatter.uids.UID;

import java.util.Optional;

public abstract class Tag implements Component {
    private final UID uid;

    public Tag(UID uid) {
        Validator.isNull(uid, "The identifier cannot be null");
        this.uid = uid;
    }

    @Override
    public UID getUID() {
        return uid;
    }

    /**
     * Evaluate this component.
     *
     * @param params The params found for this component.
     * @return the string to replace with placeholder in raw text.
     */
    public abstract String evaluate(ParamsContainer params);


    public static Optional<Tag> getTag(UID name) {
        return Optional.ofNullable(TagsRegistry.getInstance().retrieve(name));
    }

    public static Optional<Tag> getTag(String name) {
        return Optional.ofNullable(TagsRegistry.getInstance().retrieve(name));
    }

}
