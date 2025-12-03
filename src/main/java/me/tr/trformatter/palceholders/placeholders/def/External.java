package me.tr.trformatter.palceholders.placeholders.def;

import me.tr.trformatter.TrFormatter;
import me.tr.trformatter.palceholders.functions.Function;
import me.tr.trformatter.palceholders.params.ParamParser;
import me.tr.trformatter.palceholders.params.Params;
import me.tr.trformatter.palceholders.placeholders.Placeholder;
import me.tr.trformatter.uids.UID;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class External extends Placeholder {

    private External() {
        super(new UID("External"));
    }

    public External(Params params, Function[] functions, int start, int end) {
        super(new UID("External"), params, functions, start, end, 2);
    }

    @Override
    public String process(String str) {
        String className = getParams().asString(0);
        String methodName = getParams().asString(1);
        if (className.isEmpty()) return "ClassNameIsNotSpecified";
        if (methodName.isEmpty()) return "MethodNameIsNotSpecified";
        Object result;
        try {
            Params classParams = getParams(className);
            Object[] classRealParams = getRealParams(classParams);
            Class<?>[] classParamsType = getParamsType(classParams);
            Params methodParams = getParams(methodName);
            Object[] methodRealParams = getRealParams(methodParams);
            Class<?>[] methodParamsType = getParamsType(methodParams);
            Class<?> clazz = Class.forName(classParams.getStringWithoutParams());
            Method method = methodRealParams.length > 0 ? clazz.getDeclaredMethod(methodParams.getStringWithoutParams(), methodParamsType) : clazz.getDeclaredMethod(methodParams.getStringWithoutParams());
            if (Modifier.isStatic(method.getModifiers())) {
                result = invoke(method, null, methodRealParams);
            } else {
                Object instance = classRealParams.length > 0 ? clazz.getDeclaredConstructor(classParamsType).newInstance(classRealParams) : clazz.getConstructor().newInstance();
                result = invoke(method, instance, methodRealParams);
            }
            return result != null ? result.toString() : str;
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException |
                 InstantiationException e) {
            return e.getClass().getSimpleName();
        }
    }

    @Override
    public External newInstance(Params params, Function[] functions, int start, int end) {
        return new External(params, functions, start, end);
    }


    private Object invoke(Method method, Object instance, Object[] params) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        if (params.length > 0) {
            return method.invoke(instance, params);
        } else {
            return method.invoke(instance);
        }
    }

    private Class<?>[] getParamsType(Params params) {
        final List<Class<?>> classes = new ArrayList<>();
        for (int i = 0; i < params.getParams().length; i++) {
            if (i % 2 == 0) continue;
            String className = params.getParams()[i].toString();
            try {
                classes.add(Class.forName(className));
            } catch (ClassNotFoundException e) {
                classes.add(String.class);
            }
        }
        return classes.toArray(new Class[0]);
    }

    private Object[] getRealParams(Params params) {
        final List<Object> objects = new ArrayList<>();
        for (int i = 0; i < params.getParams().length; i++) {
            if (i % 2 != 0) continue;
            objects.add(params.getParams()[i]);
        }
        return objects.toArray();
    }

    private Params getParams(String name) {
        int len = name.length();
        int openIndex = -1;
        int closeIndex = -1;
        for (int i = 0; i < len; i++) {
            if (name.charAt(i) == TrFormatter.PARAM_PARAMS_START.toChar()) {
                openIndex = i;
                break;
            }
        }
        for (int j = len - 1; j >= 0; j--) {
            if (name.charAt(j) == TrFormatter.PARAM_PARAMS_END.toChar()) {
                closeIndex = j;
                break;
            }
        }
        if (openIndex != -1 && closeIndex != -1) {
            String params = name.substring(openIndex + 1, closeIndex);
            Params param = ParamParser.parseForSubParams(params);
            param.setStr(name);
            param.setStart(openIndex);
            param.setEnd(closeIndex + 1);
            return param;
        }
        return new Params(name, openIndex, closeIndex + 1);
    }
}
