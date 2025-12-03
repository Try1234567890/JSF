package me.tr.trformatter;

import java.io.File;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class TrValidator {

    private TrValidator() {
    }


    /**
     * Checks if the object is null;
     *
     * <p>
     *     <ul>If obj is {@link CharSequence} and it's empty, is considered null.</ul>
     *     <ul>If obj is {@link Number} and it's minus of 0, is considered null.</ul>
     *     <ul>If obj is {@link Collection} and it's empty, is considered null.</ul>
     *     <ul>If obj is {@link Map} and it's empty, is considered null.</ul>
     *     <ul>If obj is {@link Array} and it's empty, is considered null.</ul>
     *     <ul>If obj is {@link Optional} and it's empty, is considered null.</ul>
     *     <ul>If obj is {@link OptionalInt} and it's empty, is considered null.</ul>
     *     <ul>If obj is {@link OptionalLong} and it's empty, is considered null.</ul>
     *     <ul>If obj is {@link OptionalDouble} and it's empty, is considered null.</ul>
     *     <ul>If obj is {@link File} and it not exists, is considered null.</ul>
     *     <ul>If obj is {@link Path} and it not exists or is empty, is considered null.</ul>
     *     <ul>If obj is {@link UUID} and it's empty (00000000-0000-0000-0000-000000000000),
     *     is considered null.</ul>
     * </p>
     * <p>
     *     <ul>
     *         If an error occurs while processing some checking (like {@link Files#size(Path)})
     *         {@code false} will be returned.
     *     </ul>
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
            case Collection<?> c -> c.isEmpty();
            case Map<?, ?> m -> m.isEmpty();
            case Object[] arr -> arr.length == 0;
            case Optional<?> opt -> opt.isEmpty();
            case OptionalInt opt -> opt.isEmpty();
            case OptionalLong opt -> opt.isEmpty();
            case OptionalDouble opt -> opt.isEmpty();
            case File file -> !file.exists();
            case Path path -> !Files.exists(path);
            case UUID uuid -> uuid.getMostSignificantBits() == 0 &&
                    uuid.getLeastSignificantBits() == 0;
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
     *     <ul>If obj is {@link Collection} and it's empty, is considered null.</ul>
     *     <ul>If obj is {@link Map} and it's empty, is considered null.</ul>
     *     <ul>If obj is {@link Array} and it's empty, is considered null.</ul>
     *     <ul>If obj is {@link Optional} and it's empty, is considered null.</ul>
     *     <ul>If obj is {@link OptionalInt} and it's empty, is considered null.</ul>
     *     <ul>If obj is {@link OptionalLong} and it's empty, is considered null.</ul>
     *     <ul>If obj is {@link OptionalDouble} and it's empty, is considered null.</ul>
     *     <ul>If obj is {@link File} and it not exists or is empty, is considered null.</ul>
     *     <ul>If obj is {@link Path} and it not exists or is empty, is considered null.</ul>
     *     <ul>If obj is {@link UUID} and it's empty (00000000-0000-0000-0000-000000000000),
     *     is considered null.</ul>
     * </p>
     * <p>
     *     <ul>
     *         If an error occurs while processing some checking (like {@link Files#size(Path)})
     *         {@code false} will be returned.
     *     </ul>
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
}
