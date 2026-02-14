package me.tr.trformatter.utility;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Supplier;

public class Validator {

    private Validator() {
    }


    /**
     * Checks if the object is null;
     *
     * <p>
     *     <ul>If obj is {@link CharSequence} and it's empty, is considered null.</ul>
     *     <ul>If obj is {@link Number} and it's minus of 0, is considered null.</ul>
     *     <ul>If obj is {@link Optional} and it's empty, is considered null.</ul>
     *     <ul>If obj is {@link OptionalInt} and it's empty, is considered null.</ul>
     *     <ul>If obj is {@link OptionalLong} and it's empty, is considered null.</ul>
     *     <ul>If obj is {@link OptionalDouble} and it's empty, is considered null.</ul>
     * </p>
     *
     * @param obj The object to checks if it is null.
     * @return {@code true} if object is {@code null}, otherwise {@code false}.
     */
    public static boolean isNull(Object obj) {
        return switch (obj) {
            case null -> true;
            case CharSequence s -> s.toString().trim().isEmpty();
            case Number n -> n.doubleValue() < 0;
            case Array arr -> Array.getLength(arr) == 0;
            case Optional<?> opt -> opt.isEmpty();
            case OptionalInt opt -> opt.isEmpty();
            case OptionalLong opt -> opt.isEmpty();
            case OptionalDouble opt -> opt.isEmpty();
            default -> false;
        };
    }

    /**
     * Checks if the object is null and thrown
     * an exception if it is.
     *
     * <p>
     *     <ul>If obj is {@link CharSequence} and it's empty, is considered null.</ul>
     *     <ul>If obj is {@link Number} and it's minus of 0, is considered null.</ul>
     *     <ul>If obj is {@link Optional} and it's empty, is considered null.</ul>
     *     <ul>If obj is {@link OptionalInt} and it's empty, is considered null.</ul>
     *     <ul>If obj is {@link OptionalLong} and it's empty, is considered null.</ul>
     *     <ul>If obj is {@link OptionalDouble} and it's empty, is considered null.</ul>
     * </p>
     *
     * @param obj The object to checks if it is null.
     * @param msg The msg to send with the exception.
     * @throws NullPointerException if object is {@code null}.
     */
    public static void isNull(Object obj, String msg) {
        if (isNull(obj))
            throw new NullPointerException(msg == null ? "[Unknown]" : msg);
    }


    /**
     * Returns the provided object if it is not null, otherwise returns the default value.
     *
     * @param <T> the type of the element
     * @param obj the object to check
     * @param def the default value to return if {@code obj} is null
     * @return {@code obj} if it is non-null, otherwise {@code def}
     */
    public static <T> T orElse(T obj, T def) {
        return (obj == null) ? def : obj;
    }
}
