package me.tr.trformatter.palceholders.registers;

import me.tr.trformatter.registries.TagsRegistry;
import me.tr.trformatter.palceholders.tags.Tag;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class TagRegister {

    public static void register(Class<?> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (Tag.class.isAssignableFrom(clazz)
                && !Modifier.isAbstract(clazz.getModifiers())
                && !Modifier.isInterface(clazz.getModifiers())) {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            Tag tag = (Tag) constructor.newInstance();
            TagsRegistry.getInstance().add(tag.getUniqueID(), tag);
        }
    }
}
