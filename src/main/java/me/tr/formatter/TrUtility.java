package me.tr.formatter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class TrUtility {

    private TrUtility() {
    }

    public static <T> T instantiate(Class<T> clazz, Object... parameters)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException,
            IllegalAccessException {

        if (Modifier.isAbstract(clazz.getModifiers()) || Modifier.isInterface(clazz.getModifiers()))
            throw new RuntimeException("Cannot instantiate " + clazz.getName() + " because it is abstract or is an interface.");

        Constructor<T> constructor = clazz.getDeclaredConstructor(Arrays.stream(parameters)
                .map(par -> isWrapper(par.getClass()) ? resolve(par) : par.getClass())
                .toArray(Class<?>[]::new));

        constructor.setAccessible(true);
        return constructor.newInstance(parameters);
    }

    private static Class<?> resolve(Object wrapper) {
        if (wrapper == null) return null;
        return switch (wrapper) {
            case Byte b -> byte.class;
            case Short s -> short.class;
            case Integer i -> int.class;
            case Long l -> long.class;
            case Double d -> double.class;
            case Float f -> float.class;
            case Boolean b -> boolean.class;
            case Character c -> char.class;
            default -> wrapper.getClass();
        };
    }

    public static boolean isWrapper(Class<?> wrapper) {
        return String.class.isAssignableFrom(wrapper) ||
                Byte.class.isAssignableFrom(wrapper) ||
                Short.class.isAssignableFrom(wrapper) ||
                Integer.class.isAssignableFrom(wrapper) ||
                Long.class.isAssignableFrom(wrapper) ||
                Double.class.isAssignableFrom(wrapper) ||
                Float.class.isAssignableFrom(wrapper) ||
                Boolean.class.isAssignableFrom(wrapper) ||
                Character.class.isAssignableFrom(wrapper);
    }


}
