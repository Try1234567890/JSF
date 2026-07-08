package me.tr.trformatter.components;

import me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.ParamsContainer;
import me.tr.trformatter.registries.FunctionsRegistry;
import me.tr.trformatter.uids.UID;
import me.tr.utilities.validators.Preconditions;

import java.util.Optional;

public abstract class Function implements Component {
    private final UID uid;

    public Function(UID uid) {
        this.uid = Preconditions.parameterNotNull(uid, "uid");
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
    public abstract String evaluate(String tagResult, ParamsContainer params);


    public static Optional<Function> getFunction(UID name) {
        return FunctionsRegistry.getInstance().getOptional(name);
    }

    public static Optional<Function> getFunction(String name) {
        return FunctionsRegistry.getInstance().retrieve(name);
    }

}
