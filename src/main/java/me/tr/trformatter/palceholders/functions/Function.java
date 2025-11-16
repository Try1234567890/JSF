package me.tr.trformatter.palceholders.functions;

import me.tr.trformatter.palceholders.params.InsufficientParamsException;
import me.tr.trformatter.palceholders.params.Params;
import me.tr.trformatter.uids.UID;

public abstract class Function {
    private UID uniqueID;
    private Params params;
    private int requiredParams;

    public Function(UID uniqueID) {
        this.uniqueID = uniqueID;
    }

    public Function(UID uniqueID, Params params, int requiredParams) {
        if (params.getParams().length < requiredParams) {
            throw new InsufficientParamsException("Function " + uniqueID.getId() + " need at least " + requiredParams + " parameters to work properly. Found: " + params.getParams().length);
        }
        this.uniqueID = uniqueID;
        this.params = params;
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

    public int getRequiredParams() {
        return requiredParams;
    }

    public void setRequiredParams(int requiredParams) {
        this.requiredParams = requiredParams;
    }

    public abstract String process(String str);

    @Override
    public String toString() {
        return "Function{uniqueID=" + uniqueID + ", params=" + params + ", requiredParams=" + requiredParams + '}';
    }
}
