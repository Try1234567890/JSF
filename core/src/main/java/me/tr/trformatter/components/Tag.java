package me.tr.trformatter.components;

import me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.ParamsContainer;
import me.tr.trformatter.registries.TagsRegistry;
import me.tr.trformatter.uids.UID;
import me.tr.utilities.validators.Preconditions;

import java.util.Optional;

public abstract class Tag implements Component {
    private final UID uid;

    public Tag(UID uid) {
        this.uid = Preconditions.simpleParameterNotNull(uid, "uid");
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
        return TagsRegistry.getInstance().getOptional(name);
    }

    public static Optional<Tag> getTag(String name) {
        return TagsRegistry.getInstance().retrieve(name);
    }

}
