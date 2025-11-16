package me.tr.trformatter.palceholders.registers;

import me.tr.trformatter.palceholders.placeholders.Placeholder;
import me.tr.trformatter.registries.PlaceholdersRegistry;

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
