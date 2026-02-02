package me.tr.trformatter.phases.analysis.scanner.chars;

public class CharactersBuilder {
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

    public CharactersBuilder setOpenTag(String openTag) {
        this.openTag = openTag;
        return this;
    }

    public CharactersBuilder setOpenPlaceholder(String openPlaceholder) {
        this.openPlaceholder = openPlaceholder;
        return this;
    }

    public CharactersBuilder setClosePlaceholder(String closePlaceholder) {
        this.closePlaceholder = closePlaceholder;
        return this;
    }

    public CharactersBuilder setCloseTag(String closeTag) {
        this.closeTag = closeTag;
        return this;
    }

    public CharactersBuilder setOpenCondition(String openCondition) {
        this.openCondition = openCondition;
        return this;
    }

    public CharactersBuilder setCloseCondition(String closeCondition) {
        this.closeCondition = closeCondition;
        return this;
    }

    public CharactersBuilder setOrCondition(String orCondition) {
        this.orCondition = orCondition;
        return this;
    }

    public CharactersBuilder setAndCondition(String andCondition) {
        this.andCondition = andCondition;
        return this;
    }

    public CharactersBuilder setSeparateConditions(String separateConditions) {
        this.separateConditions = separateConditions;
        return this;
    }

    public CharactersBuilder setOpenFunction(String openFunction) {
        this.openFunction = openFunction;
        return this;
    }

    public CharactersBuilder setCloseFunction(String closeFunction) {
        this.closeFunction = closeFunction;
        return this;
    }

    public CharactersBuilder setSeparateFunction(String separateFunction) {
        this.separateFunction = separateFunction;
        return this;
    }

    public CharactersBuilder setOpenParams(String openParams) {
        this.openParams = openParams;
        return this;
    }

    public CharactersBuilder setCloseParams(String closeParams) {
        this.closeParams = closeParams;
        return this;
    }

    public CharactersBuilder setSplitParams(String splitParams) {
        this.splitParams = splitParams;
        return this;
    }

    public CharactersBuilder setAssociateParams(String associateParams) {
        this.associateParams = associateParams;
        return this;
    }

    public Characters build() {
        return new Characters(openPlaceholder, closePlaceholder, openTag, closeTag, openCondition, closeCondition, orCondition, andCondition, separateConditions, openFunction, closeFunction, separateFunction, openParams, closeParams, splitParams, associateParams);
    }
}
