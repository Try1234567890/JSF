package me.tr.formatter.palceholders.params;

import me.tr.formatter.palceholders.placeholders.PlaceholderParser;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Params {
    private String str;
    private Object[] param;
    private final List<Integer> paramsRetrieved = new ArrayList<>();
    private int start, end;


    public Params(String str, int start, int end) {
        this.str = str;
        this.param = new Object[0];
        this.start = start;
        this.end = end;
    }

    public Params(String str, int start, int end, Object[] param) {
        this.str = str;
        this.param = param;
        this.start = start;
        this.end = end;
    }

    public Params(String str, Object[] param) {
        this.str = str;
        this.param = param;
        this.start = -1;
        this.end = -1;
    }

    public byte asByte(int index) {
        String param = asString(index);
        try {
            return Byte.parseByte(param);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public short asShort(int index) {
        String param = asString(index);
        try {
            return Short.parseShort(param);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public int asInt(int index) {
        String param = asString(index);
        try {
            return Integer.parseInt(param.trim());
        } catch (NumberFormatException ignored) {
            return -1;
        }
    }

    public long asLong(int index) {
        String param = asString(index);
        try {
            return Long.parseLong(param.trim());
        } catch (NumberFormatException ignored) {
            return -1L;
        }
    }

    public float asFloat(int index) {
        String param = asString(index);
        try {
            return Float.parseFloat(param.trim());
        } catch (NumberFormatException ignored) {
            return -1F;
        }
    }

    public double asDouble(int index) {
        String param = asString(index);
        try {
            return Double.parseDouble(param.trim());
        } catch (NumberFormatException ignored) {
            return -1.0;
        }
    }

    public boolean asBoolean(int index) {
        String param = asString(index);
        return param.equals("yes") || param.equals("no") || param.equals("true") || param.equals("false");
    }

    public String asString(int index) {
        if (param.length <= index) return "";
        Object obj = this.param[index];
        if (obj == null) return "";
        String param = obj.toString();
        if (isNull(param)) return "";
        paramsRetrieved.add(index);
        return PlaceholderParser.parse(param).resolve();
    }

    public Class<?> getType(int index) {
        if (asDouble(index) != -1) return Double.class;
        else if (asByte(index) != -1) return Byte.class;
        else if (asShort(index) != -1) return Short.class;
        else if (asInt(index) != -1) return Integer.class;
        else if (asLong(index) != -1) return Long.class;
        else if (asFloat(index) != -1) return Float.class;
        else if (asBoolean(index)) return Boolean.class;
        else return String.class;
    }

    public File asFile(int index) {
        return new File(asString(index));
    }

    public Object[] getRemaining() {
        final List<Object> list = new ArrayList<>();
        for (int i = 0; i < paramsRetrieved.size(); i++) {
            if (paramsRetrieved.contains(i)) continue;
            list.add(paramsRetrieved.get(i));
        }
        return list.toArray();
    }

    private boolean isNull(String str) {
        return str == null || str.isEmpty();
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Object[] getParams() {
        return param;
    }

    public void setParam(Object[] param) {
        this.param = param;
    }

    public List<Integer> getParamsRetrieved() {
        return paramsRetrieved;
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

    public String getStringWithoutParams() {
        if (start < 0 || end > str.length() || end < start) return "";
        return str.substring(0, start) + str.substring(end);
    }

    public static Params copy(Params params) {
        if (params == null) return null;
        return new Params(params.getStr(), params.getStart(), params.getEnd(), params.getParams());
    }

    @Override
    public String toString() {
        return "Params{" +
                "str='" + str + '\'' +
                ", param=" + Arrays.toString(param) +
                ", paramsRetrieved=" + paramsRetrieved +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
