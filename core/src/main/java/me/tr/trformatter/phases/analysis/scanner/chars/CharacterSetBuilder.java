package me.tr.trformatter.phases.analysis.scanner.chars;

public class CharacterSetBuilder {
    private String openPlaceholder;
    private String closePlaceholder;
    private String openTag;
    private String closeTag;
    private String openCondition;
    private String closeCondition;
    private String orCondition;
    private String andCondition;
    private String separateConditions;
    private String openFunction;
    private String closeFunction;
    private String separateFunction;
    private String openParams;
    private String closeParams;
    private String splitParams;
    private String associateParams;

    public CharacterSetBuilder setOpenTag(String openTag) {
        this.openTag = openTag;
        return this;
    }

    public CharacterSetBuilder setOpenPlaceholder(String openPlaceholder) {
        this.openPlaceholder = openPlaceholder;
        return this;
    }

    public CharacterSetBuilder setClosePlaceholder(String closePlaceholder) {
        this.closePlaceholder = closePlaceholder;
        return this;
    }

    public CharacterSetBuilder setCloseTag(String closeTag) {
        this.closeTag = closeTag;
        return this;
    }

    public CharacterSetBuilder setOpenCondition(String openCondition) {
        this.openCondition = openCondition;
        return this;
    }

    public CharacterSetBuilder setCloseCondition(String closeCondition) {
        this.closeCondition = closeCondition;
        return this;
    }

    public CharacterSetBuilder setOrCondition(String orCondition) {
        this.orCondition = orCondition;
        return this;
    }

    public CharacterSetBuilder setAndCondition(String andCondition) {
        this.andCondition = andCondition;
        return this;
    }

    public CharacterSetBuilder setSeparateConditions(String separateConditions) {
        this.separateConditions = separateConditions;
        return this;
    }

    public CharacterSetBuilder setOpenFunction(String openFunction) {
        this.openFunction = openFunction;
        return this;
    }

    public CharacterSetBuilder setCloseFunction(String closeFunction) {
        this.closeFunction = closeFunction;
        return this;
    }

    public CharacterSetBuilder setSeparateFunction(String separateFunction) {
        this.separateFunction = separateFunction;
        return this;
    }

    public CharacterSetBuilder setOpenParams(String openParams) {
        this.openParams = openParams;
        return this;
    }

    public CharacterSetBuilder setCloseParams(String closeParams) {
        this.closeParams = closeParams;
        return this;
    }

    public CharacterSetBuilder setSplitParams(String splitParams) {
        this.splitParams = splitParams;
        return this;
    }

    public CharacterSetBuilder setAssociateParams(String associateParams) {
        this.associateParams = associateParams;
        return this;
    }

    public CharacterSet build() {
        return new CharacterSet(
                openPlaceholder, closePlaceholder, openTag,
                closeTag, openCondition, closeCondition,
                orCondition, andCondition, separateConditions,
                openFunction, closeFunction, separateFunction,
                openParams, closeParams, splitParams, associateParams
        );
    }
}
