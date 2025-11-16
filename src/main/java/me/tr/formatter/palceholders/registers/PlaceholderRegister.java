package me.tr.formatter.palceholders.registers;

import me.tr.formatter.palceholders.placeholders.Placeholder;
import me.tr.formatter.registries.PlaceholdersRegistry;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class PlaceholderRegister {

    public static void register(Class<?> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (Placeholder.class.isAssignableFrom(clazz)
                && !Modifier.isAbstract(clazz.getModifiers())
                && !Modifier.isInterface(clazz.getModifiers())) {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            Placeholder placeholder = (Placeholder) constructor.newInstance();
            PlaceholdersRegistry.getInstance().add(placeholder.getUniqueID(), placeholder);
        }
    }

}
