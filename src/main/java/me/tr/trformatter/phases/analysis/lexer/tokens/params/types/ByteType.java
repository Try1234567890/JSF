package me.tr.trformatter.phases.analysis.lexer.tokens.params.types;

public class ByteType implements ParamType<Byte> {


    @Override
    public boolean isCorrectType(String obj) {
        try {
            long parsed = Long.parseLong(obj);
            return parsed >= Byte.MIN_VALUE && parsed <= Byte.MAX_VALUE;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public Byte convert(String obj) {
        return Byte.parseByte(obj);
    }

    @Override
    public Class<Byte> getType() {
        return Byte.class;
    }

}
