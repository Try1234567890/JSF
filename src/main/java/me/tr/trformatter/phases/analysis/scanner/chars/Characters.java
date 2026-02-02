package me.tr.trformatter.phases.analysis.scanner.chars;

import me.tr.trformatter.utility.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Characters {
    private static final Logger LOGGER = LoggerFactory.getLogger(Characters.class);
    private static final CharactersBuilder builder = new CharactersBuilder();

    public static final String DEF_OPEN_PLACEHOLDER = "${";
    public static final String DEF_CLOSE_PLACEHOLDER = "}$";

    public static final String DEF_OPEN_TAG = "#[";
    public static final String DEF_CLOSE_TAG = "]";

    public static final String DEF_OPEN_CONDITION = "@[";
    public static final String DEF_CLOSE_CONDITION = "]";

    public static final String DEF_OR_CONDITION = "|";
    public static final String DEF_AND_CONDITION = "&";
    public static final String DEF_SEPARATE_CONDITIONS = "^";

    public static final String DEF_OPEN_FUNCTIONS = "%[";
    public static final String DEF_CLOSE_FUNCTIONS = "]";
    public static final String DEF_SEPARATE_FUNCTIONS = "*";

    public static final String DEF_OPEN_PARAMS = "(";
    public static final String DEF_CLOSE_PARAMS = ")";
    public static final String DEF_SPLIT_PARAMS = ",";
    public static final String DEF_ASSOCIATE_PARAMS = "=";


    private String openPlaceholder = DEF_OPEN_PLACEHOLDER;
    private String closePlaceholder = DEF_CLOSE_PLACEHOLDER;

    private String openTag = DEF_OPEN_TAG;
    private String closeTag = DEF_CLOSE_TAG;

    private String openCondition = DEF_OPEN_CONDITION;
    private String closeCondition = DEF_CLOSE_CONDITION;
    private String andCondition = DEF_OR_CONDITION;
    private String orCondition = DEF_AND_CONDITION;
    private String separateConditions = DEF_SEPARATE_CONDITIONS;

    private String openFunction = DEF_OPEN_FUNCTIONS;
    private String closeFunction = DEF_CLOSE_FUNCTIONS;
    private String separateFunction = DEF_SEPARATE_FUNCTIONS;

    private String openParams = DEF_OPEN_PARAMS;
    private String closeParams = DEF_CLOSE_PARAMS;
    private String splitParams = DEF_SPLIT_PARAMS;
    private String associateParams = DEF_ASSOCIATE_PARAMS;


    public Characters() {
        setOpenPlaceholder(DEF_OPEN_PLACEHOLDER);
        setClosePlaceholder(DEF_CLOSE_PLACEHOLDER);

        setOpenTag(DEF_OPEN_TAG);
        setCloseTag(DEF_CLOSE_TAG);

        setOpenCondition(DEF_OPEN_CONDITION);
        setCloseCondition(DEF_CLOSE_CONDITION);
        setAndCondition(DEF_AND_CONDITION);
        setOrCondition(DEF_OR_CONDITION);
        setSeparateConditions(DEF_SEPARATE_CONDITIONS);

        setOpenFunction(DEF_OPEN_FUNCTIONS);
        setCloseFunction(DEF_CLOSE_FUNCTIONS);
        setSeparateFunction(DEF_SEPARATE_FUNCTIONS);

        setOpenParams(DEF_OPEN_PARAMS);
        setCloseParams(DEF_CLOSE_PARAMS);
        setSplitParams(DEF_SPLIT_PARAMS);
        setAssociateParams(DEF_ASSOCIATE_PARAMS);
    }

    public Characters(
            String openPlaceholder, String closePlaceholder,
            String openTag, String closeTag,
            String openCondition, String closeCondition,
            String orCondition, String andCondition,
            String separateConditions, String openFunction,
            String closeFunction, String separateFunction,
            String openParams, String closeParams,
            String splitParams, String associateParams) {
        setOpenPlaceholder(openPlaceholder);
        setClosePlaceholder(closePlaceholder);

        setOpenTag(openTag);
        setCloseTag(closeTag);

        setOpenCondition(openCondition);
        setCloseCondition(closeCondition);
        setOrCondition(orCondition);
        setAndCondition(andCondition);
        setSeparateConditions(separateConditions);

        setOpenFunction(openFunction);
        setCloseFunction(closeFunction);
        setSeparateFunction(separateFunction);

        setOpenParams(openParams);
        setCloseParams(closeParams);
        setSplitParams(splitParams);
        setAssociateParams(associateParams);
    }

    public void setSeparateFunction(String separateFunction) {
        if (!isValid(separateFunction, "SeparateFunction")) {
            this.separateFunction = DEF_SEPARATE_FUNCTIONS;
            return;
        }
        this.separateFunction = separateFunction;
    }

    public void setOpenPlaceholder(String openPlaceholder) {
        if (!isValid(openPlaceholder, "OpenPlaceholder")) {
            this.openPlaceholder = DEF_OPEN_PLACEHOLDER;
            return;
        }
        this.openPlaceholder = openPlaceholder;
    }

    public void setClosePlaceholder(String closePlaceholder) {
        if (!isValid(closePlaceholder, "ClosePlaceholder")) {
            this.closePlaceholder = DEF_CLOSE_PLACEHOLDER;
            return;
        }
        this.closePlaceholder = closePlaceholder;
    }

    public void setOpenTag(String openTag) {
        if (!isValid(openTag, "OpenTag")) {
            this.openTag = DEF_OPEN_TAG;
            return;
        }
        this.openTag = openTag;
    }

    public void setCloseTag(String closeTag) {
        if (!isValid(closeTag, "CloseTag")) {
            this.closeTag = DEF_CLOSE_TAG;
            return;
        }
        this.closeTag = closeTag;
    }

    public void setOpenCondition(String openCondition) {
        if (!isValid(openCondition, "OpenCondition")) {
            this.openCondition = DEF_OPEN_CONDITION;
            return;
        }
        this.openCondition = openCondition;
    }

    public void setCloseCondition(String closeCondition) {
        if (!isValid(closeCondition, "CloseCondition")) {
            this.closeCondition = DEF_CLOSE_CONDITION;
            return;
        }
        this.closeCondition = closeCondition;
    }

    public void setAndCondition(String andCondition) {
        if (!isValid(andCondition, "AndCondition")) {
            this.andCondition = DEF_AND_CONDITION;
            return;
        }
        this.andCondition = andCondition;
    }

    public void setOrCondition(String orCondition) {
        if (!isValid(orCondition, "OrCondition")) {
            this.orCondition = DEF_OR_CONDITION;
            return;
        }
        this.orCondition = orCondition;
    }

    public void setOpenFunction(String openFunction) {
        if (!isValid(openFunction, "OpenFunction")) {
            this.openFunction = DEF_OPEN_FUNCTIONS;
            return;
        }
        this.openFunction = openFunction;
    }

    public void setCloseFunction(String closeFunction) {
        if (!isValid(closeFunction, "CloseFunction")) {
            this.closeFunction = DEF_CLOSE_FUNCTIONS;
            return;
        }
        this.closeFunction = closeFunction;
    }

    public void setOpenParams(String openParams) {
        if (!isValid(openParams, "OpenParams")) {
            this.openParams = DEF_OPEN_PARAMS;
            return;
        }
        this.openParams = openParams;
    }

    public void setCloseParams(String closeParams) {
        if (!isValid(closeParams, "CloseParams")) {
            this.closeParams = DEF_CLOSE_PARAMS;
            return;
        }
        this.closeParams = closeParams;
    }

    public void setSplitParams(String splitParams) {
        if (!isValid(splitParams, "SplitParams")) {
            this.splitParams = DEF_SPLIT_PARAMS;
            return;
        }
        this.splitParams = splitParams;
    }

    public void setAssociateParams(String associateParams) {
        if (!isValid(associateParams, "AssociateParams")) {
            this.associateParams = DEF_ASSOCIATE_PARAMS;
            return;
        }
        this.associateParams = associateParams;
    }

    public void setSeparateConditions(String separateConditions) {
        if (!isValid(separateConditions, "SeparateConditions")) {
            this.separateConditions = DEF_SEPARATE_CONDITIONS;
            return;
        }
        this.separateConditions = separateConditions;
    }

    public String getOpenPlaceholder() {
        return openPlaceholder;
    }

    public String getClosePlaceholder() {
        return closePlaceholder;
    }

    public String getOpenTag() {
        return openTag;
    }

    public String getCloseTag() {
        return closeTag;
    }

    public String getOpenCondition() {
        return openCondition;
    }

    public String getCloseCondition() {
        return closeCondition;
    }

    public String getAndCondition() {
        return andCondition;
    }

    public String getOrCondition() {
        return orCondition;
    }

    public String getSeparateConditions() {
        return separateConditions;
    }

    public String getOpenFunction() {
        return openFunction;
    }

    public String getCloseFunction() {
        return closeFunction;
    }

    public String getSeparateFunction() {
        return separateFunction;
    }

    public String getOpenParams() {
        return openParams;
    }

    public String getCloseParams() {
        return closeParams;
    }

    public String getSplitParams() {
        return splitParams;
    }

    public String getAssociateParams() {
        return associateParams;
    }

    private boolean isValid(String input, String character) {
        if (Validator.isNull(input)) return false;

        for (char c : input.toCharArray()) {
            if (Character.isWhitespace(c)
                    || Character.isLetter(c)) return false;
        }

        //if (getOpenPlaceholder().equals(input)) {
        //    LOGGER.error("The provided input for {} ({}) is equals to OpenPlaceholder. Change to another one.", character, input);
        //    return false;
        //}
        //if (getClosePlaceholder().equals(input)) {
        //    LOGGER.error("The provided input for {} ({}) is equals to ClosePlaceholder. Change to another one.", character, input);
        //    return false;
        //}
        //if (getOpenTag().equals(input)) {
        //    LOGGER.error("The provided input for {} ({}) is equals to OpenTag. Change to another one.", character, input);
        //}
        //if (getCloseTag().equals(input)) {
        //    LOGGER.error("The provided input for {} ({}) is equals to CloseTag. Change to another one.", character, input);
        //    return false;
        //}
        //if (getOpenCondition().equals(input)) {
        //    LOGGER.error("The provided input for {} ({}) is equals to OpenCondition. Change to another one.", character, input);
        //    return false;
        //}
        //if (getCloseCondition().equals(input)) {
        //    LOGGER.error("The provided input for {} ({}) is equals to CloseCondition. Change to another one.", character, input);
        //    return false;
        //}
        //if (getAndCondition().equals(input)) {
        //    LOGGER.error("The provided input for {} ({}) is equals to AndCondition. Change to another one.", character, input);
        //    return false;
        //}
        //if (getOrCondition().equals(input)) {
        //    LOGGER.error("The provided input for {} ({}) is equals to OrCondition. Change to another one.", character, input);
        //    return false;
        //}
        //if (getSeparateConditions().equals(input)) {
        //    LOGGER.error("The provided input for {} ({}) is equals to SeparateConditions. Change to another one.", character, input);
        //    return false;
        //}
        //if (getOpenFunction().equals(input)) {
        //    LOGGER.error("The provided input for {} ({}) is equals to OpenFunction. Change to another one.", character, input);
        //    return false;
        //}
        //if (getCloseFunction().equals(input)) {
        //    LOGGER.error("The provided input for {} ({}) is equals to CloseFunction. Change to another one.", character, input);
        //    return false;
        //}
        //if (getSeparateFunction().equals(input)) {
        //    LOGGER.error("The provided input for {} ({}) is equals to SeparateFunction. Change to another one.", character, input);
        //    return false;
        //}
        //if (getOpenParams().equals(input)) {
        //    LOGGER.error("The provided input for {} ({}) is equals to OpenParams. Change to another one.", character, input);
        //    return false;
        //}
        //if (getCloseParams().equals(input)) {
        //    LOGGER.error("The provided input for {} ({}) is equals to CloseParams. Change to another one.", character, input);
        //    return false;
        //}
        //if (getSplitParams().equals(input)) {
        //    LOGGER.error("The provided input for {} ({}) is equals to SplitParams. Change to another one.", character, input);
        //    return false;
        //}
        //if (getAssociateParams().equals(input)) {
        //    LOGGER.error("The provided input for {} ({}) is equals to AssociateParams. Change to another one.", character, input);
        //    return false;
        //}

        return true;
    }

    public static CharactersBuilder builder() {
        return builder;
    }
}
