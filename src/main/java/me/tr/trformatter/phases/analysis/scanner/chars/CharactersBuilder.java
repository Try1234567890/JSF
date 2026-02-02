package me.tr.trformatter.phases.analysis.scanner.chars;

public class CharactersBuilder {
    private String openPlaceholder;
    private String closePlaceholder;
    private String openTag;
    private String closeTag;
    private String openCondition;
    private String closeCondition;
    private String groupOpenCondition;
    private String groupCloseCondition;
    private String orCondition;
    private String andCondition;

    private String openParams;
    private String closeParams;
    private String splitParams;
    private String associateParams;

    private String splitComponents;

    public CharactersBuilder setOpenCondition(String openCondition) {
        this.openCondition = openCondition;
        return this;
    }

    public CharactersBuilder setOpenPlaceholder(String openPlaceholder) {
        this.openPlaceholder = openPlaceholder;
        return this;
    }

    public CharactersBuilder setOpenTag(String openTag) {
        this.openTag = openTag;
        return this;
    }

    public CharactersBuilder setCloseTag(String closeTag) {
        this.closeTag = closeTag;
        return this;
    }

    public CharactersBuilder setClosePlaceholder(String closePlaceholder) {
        this.closePlaceholder = closePlaceholder;
        return this;
    }

    public CharactersBuilder setCloseCondition(String closeCondition) {
        this.closeCondition = closeCondition;
        return this;
    }

    public CharactersBuilder setGroupCloseCondition(String groupCloseCondition) {
        this.groupCloseCondition = groupCloseCondition;
        return this;
    }

    public CharactersBuilder setGroupOpenCondition(String groupOpenCondition) {
        this.groupOpenCondition = groupOpenCondition;
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

    public CharactersBuilder setSplitComponents(String splitComponents) {
        this.splitComponents = splitComponents;
        return this;
    }

    public Characters build() {
        return new Characters(
                openPlaceholder,
                closePlaceholder,
                openTag,
                closeTag,
                openCondition,
                closeCondition,
                groupOpenCondition,
                groupCloseCondition,
                orCondition,
                andCondition,
                openParams,
                closeParams,
                splitParams,
                associateParams,
                splitComponents
        );
    }
}
