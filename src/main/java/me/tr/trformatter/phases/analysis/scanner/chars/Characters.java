package me.tr.trformatter.phases.analysis.scanner.chars;

import me.tr.trformatter.utility.Validator;

public class Characters {
    public static final CharactersBuilder builder = new CharactersBuilder();
    public static final String DEF_OPEN_PLACEHOLDER = "${";
    public static final String DEF_CLOSE_PLACEHOLDER = "}$";

    public static final String DEF_OPEN_TAG = "#[";
    public static final String DEF_CLOSE_TAG = "]";

    public static final String DEF_OPEN_CONDITION = "@[";
    public static final String DEF_CLOSE_CONDITION = "]";

    public static final String DEF_GROUP_OPEN_CONDITION = "<";
    public static final String DEF_GROUP_CLOSE_CONDITION = ">";

    public static final String DEF_OR_CONDITION = "|";
    public static final String DEF_AND_CONDITION = "&";
    public static final String DEF_SPLIT_COMPONENTS = "^";

    public static final String DEF_OPEN_PARAMS = "(";
    public static final String DEF_CLOSE_PARAMS = ")";
    public static final String DEF_SPLIT_PARAMS = ",";
    public static final String DEF_ASSOCIATE_PARAMS = "=";


    private String openPlaceholder;
    private String closePlaceholder;
    private String openTag;
    private String closeTag;
    private String openCondition;
    private String closeCondition;
    private String groupOpenCondition;
    private String groupCloseCondition;
    private String andCondition;
    private String orCondition;
    private String openParams;
    private String closeParams;
    private String splitParams;
    private String associateParams;

    private String splitComponents;

    public Characters() {
        setOpenPlaceholder(DEF_OPEN_PLACEHOLDER);
        setClosePlaceholder(DEF_CLOSE_PLACEHOLDER);

        setOpenTag(DEF_OPEN_TAG);
        setCloseTag(DEF_CLOSE_TAG);

        setOpenCondition(DEF_OPEN_CONDITION);
        setCloseCondition(DEF_CLOSE_CONDITION);

        setGroupOpenCondition(DEF_GROUP_OPEN_CONDITION);
        setGroupCloseCondition(DEF_GROUP_CLOSE_CONDITION);

        setOrCondition(DEF_OR_CONDITION);
        setAndCondition(DEF_AND_CONDITION);

        setOpenParams(DEF_OPEN_PARAMS);
        setCloseParams(DEF_CLOSE_PARAMS);
        setSplitParams(DEF_SPLIT_PARAMS);
        setAssociateParams(DEF_ASSOCIATE_PARAMS);

        setSplitComponents(DEF_SPLIT_COMPONENTS);
    }

    public Characters(
            String openPlaceholder, String closePlaceholder,
            String openTag, String closeTag,
            String openCondition, String closeCondition,
            String groupOpenCondition, String groupCloseCondition,
            String orCondition, String andCondition,
            String openParams, String closeParams,
            String splitParams, String associateParams,
            String splitComponents
    ) {
        setOpenPlaceholder(openPlaceholder);
        setClosePlaceholder(closePlaceholder);

        setOpenTag(openTag);
        setCloseTag(closeTag);

        setOpenCondition(openCondition);
        setCloseCondition(closeCondition);

        setGroupOpenCondition(groupOpenCondition);
        setGroupCloseCondition(groupCloseCondition);

        setOrCondition(orCondition);
        setAndCondition(andCondition);

        setOpenParams(openParams);
        setCloseParams(closeParams);
        setSplitParams(splitParams);
        setAssociateParams(associateParams);

        setSplitComponents(splitComponents);
    }

    public String getOpenPlaceholder() {
        return openPlaceholder;
    }

    public void setOpenPlaceholder(String openPlaceholder) {
        if (!isValid(openPlaceholder)) {
            this.openPlaceholder = DEF_OPEN_PLACEHOLDER;
            return;
        }
        this.openPlaceholder = openPlaceholder;
    }

    public String getClosePlaceholder() {
        return closePlaceholder;
    }

    public void setClosePlaceholder(String closePlaceholder) {
        if (!isValid(closePlaceholder)) {
            this.closePlaceholder = DEF_CLOSE_PLACEHOLDER;
            return;
        }
        this.closePlaceholder = closePlaceholder;
    }

    public String getOpenTag() {
        return openTag;
    }

    public void setOpenTag(String openTag) {
        if (!isValid(openTag)) {
            this.openTag = DEF_OPEN_TAG;
            return;
        }
        this.openTag = openTag;
    }

    public String getCloseTag() {
        return closeTag;
    }

    public void setCloseTag(String closeTag) {
        if (!isValid(closeTag)) {
            this.closeTag = DEF_CLOSE_TAG;
            return;
        }
        this.closeTag = closeTag;
    }

    public String getOpenCondition() {
        return openCondition;
    }

    public void setOpenCondition(String openCondition) {
        if (!isValid(openCondition)) {
            this.openCondition = DEF_OPEN_CONDITION;
            return;
        }
        this.openCondition = openCondition;
    }

    public String getCloseCondition() {
        return closeCondition;
    }

    public void setCloseCondition(String closeCondition) {
        if (!isValid(closeCondition)) {
            this.closeCondition = DEF_CLOSE_CONDITION;
            return;
        }
        this.closeCondition = closeCondition;
    }

    public String getOrCondition() {
        return orCondition;
    }

    public void setOrCondition(String orCondition) {
        if (!isValid(orCondition)) {
            this.orCondition = DEF_OR_CONDITION;
            return;
        }
        this.orCondition = orCondition;
    }

    public String getAndCondition() {
        return andCondition;
    }

    public void setAndCondition(String andCondition) {
        if (!isValid(andCondition)) {
            this.andCondition = DEF_AND_CONDITION;
            return;
        }
        this.andCondition = andCondition;
    }

    public String getGroupCloseCondition() {
        return groupCloseCondition;
    }

    public void setGroupCloseCondition(String groupCloseCondition) {
        if (!isValid(groupCloseCondition)) {
            this.groupCloseCondition = DEF_GROUP_CLOSE_CONDITION;
            return;
        }
        this.groupCloseCondition = groupCloseCondition;
    }

    public String getGroupOpenCondition() {
        return groupOpenCondition;
    }

    public void setGroupOpenCondition(String groupOpenCondition) {
        if (!isValid(groupOpenCondition)) {
            this.groupOpenCondition = DEF_GROUP_OPEN_CONDITION;
            return;
        }
        this.groupOpenCondition = groupOpenCondition;
    }

    public String getSplitComponents() {
        return splitComponents;
    }

    public void setSplitComponents(String splitComponents) {
        if (!isValid(splitComponents)) {
            this.splitComponents = DEF_SPLIT_COMPONENTS;
            return;
        }
        this.splitComponents = splitComponents;
    }

    public String getOpenParams() {
        return openParams;
    }

    public void setOpenParams(String openParams) {
        if (!isValid(openParams)) {
            this.openParams = DEF_OPEN_PARAMS;
            return;
        }
        this.openParams = openParams;
    }

    public String getCloseParams() {
        return closeParams;
    }

    public void setCloseParams(String closeParams) {
        if (!isValid(closeParams)) {
            this.closeParams = DEF_CLOSE_PARAMS;
            return;
        }
        this.closeParams = closeParams;
    }

    public String getSplitParams() {
        return splitParams;
    }

    public void setSplitParams(String splitParams) {
        if (!isValid(splitParams)) {
            this.splitParams = DEF_SPLIT_PARAMS;
            return;
        }
        this.splitParams = splitParams;
    }

    public String getAssociateParams() {
        return associateParams;
    }

    public void setAssociateParams(String associateParams) {
        if (!isValid(associateParams)) {
            this.associateParams = DEF_ASSOCIATE_PARAMS;
            return;
        }
        this.associateParams = associateParams;
    }

    private static boolean isValid(String input) {
        if (Validator.isNull(input)) return false;

        for (char c : input.toCharArray()) {
            if (Character.isWhitespace(c)
                    || Character.isLetter(c)) return false;
        }

        return true;
    }

    public static CharactersBuilder builder() {
        return builder;
    }
}
