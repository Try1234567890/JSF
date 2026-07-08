package me.tr.trformatter.phases.analysis.scanner.chars;

import me.tr.trformatter.phases.analysis.scanner.exceptions.DuplicatedCharacters;
import me.tr.trformatter.utility.Validator;

import java.util.Objects;

public class CharacterSet {
    private static final CharacterSetBuilder builder = new CharacterSetBuilder();
    public static final Character DEF_OPEN_PLACEHOLDER = new Character("OPEN_PLACEHOLDER", "${");
    public static final Character DEF_CLOSE_PLACEHOLDER = new Character("CLOSE_PLACEHOLDER", "}$");

    public static final Character DEF_OPEN_TAG = new Character("OPEN_TAG", "#[");
    public static final Character DEF_CLOSE_TAG = new Character("CLOSE_TAG", "]#");

    public static final Character DEF_OPEN_CONDITION = new Character("OPEN_CONDITION", "@[");
    public static final Character DEF_CLOSE_CONDITION = new Character("CLOSE_CONDITION", "]@");

    public static final Character DEF_OR_CONDITION = new Character("OR_CONDITION", "|");
    public static final Character DEF_AND_CONDITION = new Character("AND_CONDITION", "&");
    public static final Character DEF_SEPARATE_CONDITIONS = new Character("SEPARATE_CONDITIONS", "^");

    public static final Character DEF_OPEN_FUNCTION = new Character("OPEN_FUNCTION", "%[");
    public static final Character DEF_CLOSE_FUNCTION = new Character("CLOSE_FUNCTION", "]%");
    public static final Character DEF_SEPARATE_FUNCTIONS = new Character("SEPARATE_FUNCTIONS", "*");

    public static final Character DEF_OPEN_PARAMS = new Character("OPEN_PARAMS", "(");
    public static final Character DEF_CLOSE_PARAMS = new Character("CLOSE_PARAMS", ")");
    public static final Character DEF_SPLIT_PARAMS = new Character("SPLIT_PARAMS", ",");
    public static final Character DEF_ASSOCIATE_PARAMS = new Character("ASSOCIATE_PARAMS", "=");
    public static final CharacterSet DEFAULT = new CharacterSet();


    private Character openPlaceholder = DEF_OPEN_PLACEHOLDER.clone();
    private Character closePlaceholder = DEF_CLOSE_PLACEHOLDER.clone();

    private Character openTag = DEF_OPEN_TAG.clone();
    private Character closeTag = DEF_CLOSE_TAG.clone();

    private Character openCondition = DEF_OPEN_CONDITION.clone();
    private Character closeCondition = DEF_CLOSE_CONDITION.clone();

    private Character andCondition = DEF_OR_CONDITION.clone();
    private Character orCondition = DEF_AND_CONDITION.clone();
    private Character separateConditions = DEF_SEPARATE_CONDITIONS.clone();

    private Character openFunction = DEF_OPEN_FUNCTION.clone();
    private Character closeFunction = DEF_CLOSE_FUNCTION.clone();
    private Character separateFunction = DEF_SEPARATE_FUNCTIONS.clone();

    private Character openParams = DEF_OPEN_PARAMS.clone();
    private Character closeParams = DEF_CLOSE_PARAMS.clone();
    private Character splitParams = DEF_SPLIT_PARAMS.clone();
    private Character associateParams = DEF_ASSOCIATE_PARAMS.clone();


    private CharacterSet() {
        setOpenPlaceholder(DEF_OPEN_PLACEHOLDER.getDelimiter());
        setClosePlaceholder(DEF_CLOSE_PLACEHOLDER.getDelimiter());

        setOpenTag(DEF_OPEN_TAG.getDelimiter());
        setCloseTag(DEF_CLOSE_TAG.getDelimiter());

        setOpenCondition(DEF_OPEN_CONDITION.getDelimiter());
        setCloseCondition(DEF_CLOSE_CONDITION.getDelimiter());
        setAndCondition(DEF_AND_CONDITION.getDelimiter());
        setOrCondition(DEF_OR_CONDITION.getDelimiter());
        setSeparateConditions(DEF_SEPARATE_CONDITIONS.getDelimiter());

        setOpenFunction(DEF_OPEN_FUNCTION.getDelimiter());
        setCloseFunction(DEF_CLOSE_FUNCTION.getDelimiter());
        setSeparateFunctions(DEF_SEPARATE_FUNCTIONS.getDelimiter());

        setOpenParams(DEF_OPEN_PARAMS.getDelimiter());
        setCloseParams(DEF_CLOSE_PARAMS.getDelimiter());
        setSplitParams(DEF_SPLIT_PARAMS.getDelimiter());
        setAssociateParams(DEF_ASSOCIATE_PARAMS.getDelimiter());
    }

    public CharacterSet(
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
        setSeparateFunctions(separateFunction);

        setOpenParams(openParams);
        setCloseParams(closeParams);
        setSplitParams(splitParams);
        setAssociateParams(associateParams);
    }

    public void setSeparateFunctions(String separateFunction) {
        if (!isValid(separateFunction, DEF_SEPARATE_FUNCTIONS)) {
            this.separateFunction = DEF_SEPARATE_FUNCTIONS;
            return;
        }
        this.separateFunction.setDelimiter(separateFunction);
    }

    public void setOpenPlaceholder(String openPlaceholder) {
        if (!isValid(openPlaceholder, DEF_OPEN_PLACEHOLDER)) {
            this.openPlaceholder = DEF_OPEN_PLACEHOLDER;
            return;
        }
        this.openPlaceholder.setDelimiter(openPlaceholder);
    }

    public void setClosePlaceholder(String closePlaceholder) {
        if (!isValid(closePlaceholder, DEF_CLOSE_PLACEHOLDER)) {
            this.closePlaceholder = DEF_CLOSE_PLACEHOLDER;
            return;
        }
        this.closePlaceholder.setDelimiter(closePlaceholder);
    }

    public void setOpenTag(String openTag) {
        if (!isValid(openTag, DEF_OPEN_TAG)) {
            this.openTag = DEF_OPEN_TAG;
            return;
        }
        this.openTag.setDelimiter(openTag);
    }

    public void setCloseTag(String closeTag) {
        if (!isValid(closeTag, DEF_CLOSE_TAG)) {
            this.closeTag = DEF_CLOSE_TAG;
            return;
        }
        this.closeTag.setDelimiter(closeTag);
    }

    public void setOpenCondition(String openCondition) {
        if (!isValid(openCondition, DEF_OPEN_CONDITION)) {
            this.openCondition = DEF_OPEN_CONDITION;
            return;
        }
        this.openCondition.setDelimiter(openCondition);
    }

    public void setCloseCondition(String closeCondition) {
        if (!isValid(closeCondition, DEF_CLOSE_CONDITION)) {
            this.closeCondition = DEF_CLOSE_CONDITION;
            return;
        }
        this.closeCondition.setDelimiter(closeCondition);
    }

    public void setAndCondition(String andCondition) {
        if (!isValid(andCondition, DEF_AND_CONDITION)) {
            this.andCondition = DEF_AND_CONDITION;
            return;
        }
        this.andCondition.setDelimiter(andCondition);
    }

    public void setOrCondition(String orCondition) {
        if (!isValid(orCondition, DEF_OR_CONDITION)) {
            this.orCondition = DEF_OR_CONDITION;
            return;
        }
        this.orCondition.setDelimiter(orCondition);
    }

    public void setOpenFunction(String openFunction) {
        if (!isValid(openFunction, DEF_OPEN_FUNCTION)) {
            this.openFunction = DEF_OPEN_FUNCTION;
            return;
        }
        this.openFunction.setDelimiter(openFunction);
    }

    public void setCloseFunction(String closeFunction) {
        if (!isValid(closeFunction, DEF_CLOSE_FUNCTION)) {
            this.closeFunction = DEF_CLOSE_FUNCTION;
            return;
        }
        this.closeFunction.setDelimiter(closeFunction);
    }

    public void setOpenParams(String openParams) {
        if (!isValid(openParams, DEF_OPEN_PARAMS)) {
            this.openParams = DEF_OPEN_PARAMS;
            return;
        }
        this.openParams.setDelimiter(openParams);
    }

    public void setCloseParams(String closeParams) {
        if (!isValid(closeParams, DEF_CLOSE_PARAMS)) {
            this.closeParams = DEF_CLOSE_PARAMS;
            return;
        }
        this.closeParams.setDelimiter(closeParams);
    }

    public void setSplitParams(String splitParams) {
        if (!isValid(splitParams, DEF_SPLIT_PARAMS)) {
            this.splitParams = DEF_SPLIT_PARAMS;
            return;
        }
        this.splitParams.setDelimiter(splitParams);
    }

    public void setAssociateParams(String associateParams) {
        if (!isValid(associateParams, DEF_ASSOCIATE_PARAMS)) {
            this.associateParams = DEF_ASSOCIATE_PARAMS;
            return;
        }
        this.associateParams.setDelimiter(associateParams);
    }

    public void setSeparateConditions(String separateConditions) {
        if (!isValid(separateConditions, DEF_SEPARATE_CONDITIONS)) {
            this.separateConditions = DEF_SEPARATE_CONDITIONS;
            return;
        }
        this.separateConditions.setDelimiter(separateConditions);
    }

    public Character getOpenPlaceholderCharacter() {
        return openPlaceholder;
    }

    public Character getClosePlaceholderCharacter() {
        return closePlaceholder;
    }

    public Character getOpenTagCharacter() {
        return openTag;
    }

    public Character getCloseTagCharacter() {
        return closeTag;
    }

    public Character getOpenConditionCharacter() {
        return openCondition;
    }

    public Character getCloseConditionCharacter() {
        return closeCondition;
    }

    public Character getAndConditionCharacter() {
        return andCondition;
    }

    public Character getOrConditionCharacter() {
        return orCondition;
    }

    public Character getSeparateConditionsCharacter() {
        return separateConditions;
    }

    public Character getOpenFunctionCharacter() {
        return openFunction;
    }

    public Character getCloseFunctionCharacter() {
        return closeFunction;
    }

    public Character getSeparateFunctionCharacter() {
        return separateFunction;
    }

    public Character getOpenParamsCharacter() {
        return openParams;
    }

    public Character getCloseParamsCharacter() {
        return closeParams;
    }

    public Character getSplitParamsCharacter() {
        return splitParams;
    }

    public Character getAssociateParamsCharacter() {
        return associateParams;
    }


    public String getOpenPlaceholder() {
        return openPlaceholder.getDelimiter();
    }

    public String getClosePlaceholder() {
        return closePlaceholder.getDelimiter();
    }

    public String getOpenTag() {
        return openTag.getDelimiter();
    }

    public String getCloseTag() {
        return closeTag.getDelimiter();
    }

    public String getOpenCondition() {
        return openCondition.getDelimiter();
    }

    public String getCloseCondition() {
        return closeCondition.getDelimiter();
    }

    public String getAndCondition() {
        return andCondition.getDelimiter();
    }

    public String getOrCondition() {
        return orCondition.getDelimiter();
    }

    public String getSeparateConditions() {
        return separateConditions.getDelimiter();
    }

    public String getOpenFunction() {
        return openFunction.getDelimiter();
    }

    public String getCloseFunction() {
        return closeFunction.getDelimiter();
    }

    public String getSeparateFunction() {
        return separateFunction.getDelimiter();
    }

    public String getOpenParams() {
        return openParams.getDelimiter();
    }

    public String getCloseParams() {
        return closeParams.getDelimiter();
    }

    public String getSplitParams() {
        return splitParams.getDelimiter();
    }

    public String getAssociateParams() {
        return associateParams.getDelimiter();
    }

    private boolean isValid(String input, Character usedFor) {
        if (Validator.isNull(input)) return false;

        for (char c : input.toCharArray()) {
            if (java.lang.Character.isWhitespace(c)
                    || java.lang.Character.isLetter(c)
                    || java.lang.Character.isDigit(c)) {
                return false;
            }
        }

        for (Character other : Characters.all()) {
            if (input.equals(other.getDelimiter())
                    && (!usedFor.getName().equals(other.getName()))) {
                new DuplicatedCharacters(input, other).printStackTrace(System.err);
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CharacterSet that)) return false;
        return Objects.equals(getOpenPlaceholder(), that.getOpenPlaceholder()) &&
                Objects.equals(getClosePlaceholder(), that.getClosePlaceholder()) &&
                Objects.equals(getOpenTag(), that.getOpenTag()) &&
                Objects.equals(getCloseTag(), that.getCloseTag()) &&
                Objects.equals(getOpenCondition(), that.getOpenCondition()) &&
                Objects.equals(getCloseCondition(), that.getCloseCondition()) &&
                Objects.equals(getAndCondition(), that.getAndCondition()) &&
                Objects.equals(getOrCondition(), that.getOrCondition()) &&
                Objects.equals(getSeparateConditions(), that.getSeparateConditions()) &&
                Objects.equals(getOpenFunction(), that.getOpenFunction()) &&
                Objects.equals(getCloseFunction(), that.getCloseFunction()) &&
                Objects.equals(getSeparateFunction(), that.getSeparateFunction()) &&
                Objects.equals(getOpenParams(), that.getOpenParams()) &&
                Objects.equals(getCloseParams(), that.getCloseParams()) &&
                Objects.equals(getSplitParams(), that.getSplitParams()) &&
                Objects.equals(getAssociateParams(), that.getAssociateParams());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getOpenPlaceholder(),
                getClosePlaceholder(),
                getOpenTag(),
                getCloseTag(),
                getOpenCondition(),
                getCloseCondition(),
                getAndCondition(),
                getOrCondition(),
                getSeparateConditions(),
                getOpenFunction(),
                getCloseFunction(),
                getSeparateFunction(),
                getOpenParams(),
                getCloseParams(),
                getSplitParams(),
                getAssociateParams()
        );
    }

    public boolean isDefault() {
        return this == DEFAULT
                || equals(DEFAULT);
    }

    public static CharacterSetBuilder builder() {
        return builder;
    }
}
