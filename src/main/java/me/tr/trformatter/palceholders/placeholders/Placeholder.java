package me.tr.trformatter.palceholders.placeholders;

import me.tr.trformatter.palceholders.functions.Function;
import me.tr.trformatter.palceholders.params.InsufficientParamsException;
import me.tr.trformatter.palceholders.params.Params;
import me.tr.trformatter.uids.UID;

import java.util.Arrays;

public abstract class Placeholder {
    private UID uniqueID;
    private Params params;
    private Function[] functions;
    private int start, end;
    private int requiredParams;

    public Placeholder(UID uniqueID) {
        this.uniqueID = uniqueID;
    }

    public Placeholder(UID uniqueID, Params params, Function[] functions, int start, int end, int requiredParams) {
        if (params.getParams().length < requiredParams) {
            throw new InsufficientParamsException("Placeholder " + uniqueID.getId() + " need at least " + requiredParams + " parameters to work properly. Found: " + params.getParams().length);
        }
        this.uniqueID = uniqueID;
        this.params = params;
        this.functions = functions;
        this.start = start;
        this.end = end;
        this.requiredParams = requiredParams;
    }

    public int getRequiredParams() {
        return requiredParams;
    }

    public void setRequiredParams(int requiredParams) {
        this.requiredParams = requiredParams;
    }

    public UID getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(UID uniqueID) {
        this.uniqueID = uniqueID;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public Function[] getFunctions() {
        return functions;
    }

    public void setFunctions(Function[] functions) {
        this.functions = functions;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public abstract String process(String str);

    @Override
    public String toString() {
        return "Placeholder{uniqueID=" + uniqueID + ", params=" + params + ", functions=" + Arrays.toString(functions) + ", start=" + start + ", end=" + end + '}';
    }
}
