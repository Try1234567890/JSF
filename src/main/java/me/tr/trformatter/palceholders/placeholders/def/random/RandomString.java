package me.tr.trformatter.palceholders.placeholders.def.random;

import me.tr.trformatter.palceholders.functions.Function;
import me.tr.trformatter.palceholders.params.Params;
import me.tr.trformatter.palceholders.placeholders.Placeholder;
import me.tr.trformatter.uids.UID;

import java.util.Random;

public class RandomString extends Placeholder {

    public RandomString() {
        super(new UID("RandomStr"));
    }

    public RandomString(Params params, Function[] functions, int start, int end) {
        super(new UID("RandomStr"), params, functions, start, end, 0);
    }

    @Override
    public String process(String str) {
        int length = getParams().asInt(0);
        String stringChars = getParams().asString(1);
        if (stringChars.isEmpty()) {
            stringChars = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        }
        char[] chars = stringChars.toCharArray();
        Random random = new Random();
        if (length <= -1) {
            length = random.nextInt(101);
        }
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomInt = random.nextInt(chars.length);
            string.append(chars[randomInt]);
        }
        return string.toString();
    }

    @Override
    public RandomString newInstance(Params params, Function[] functions, int start, int end) {
        return new RandomString(params, functions, start, end);
    }
}
