package me.tr.formatter.palceholders.registers;

import me.tr.formatter.palceholders.functions.Function;
import me.tr.formatter.registries.FunctionsRegistry;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class FunctionRegister {

    public static void register(Class<?> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (Function.class.isAssignableFrom(clazz)
                && !Modifier.isAbstract(clazz.getModifiers())
                && !Modifier.isInterface(clazz.getModifiers())) {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            Function function = (Function) constructor.newInstance();
            FunctionsRegistry.getInstance().add(function.getUniqueID(), function);
        }
    }
}
