package me.tr.trformatter.defaults.tags.readfile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FileHistory {
    // path: file -> content
    private static final Map<String, Map.Entry<File, String>> files = new HashMap<>();

    private FileHistory() {
    }

    public static boolean has(String path) {
        return files.containsKey(path);
    }

    private static Map.Entry<File, String> getEntryOrNull(String path) {
        return files.get(path);
    }

    private static Optional<Map.Entry<File, String>> getEntry(String path) {
        return Optional.ofNullable(files.get(path));
    }

    public static Optional<File> getFile(String path) {
        Map.Entry<File, String> entry = getEntryOrNull(path);
        return entry == null ? Optional.empty() : Optional.ofNullable(entry.getKey());
    }

    public static File getFileOrNull(String path) {
        Map.Entry<File, String> entry = getEntryOrNull(path);
        return entry == null ? null : entry.getKey();
    }

    public static Optional<String> getContent(String path) {
        Map.Entry<File, String> entry = getEntryOrNull(path);
        return entry == null ? Optional.empty() : Optional.ofNullable(entry.getValue());
    }

    public static String getContentOrNull(String path) {
        Map.Entry<File, String> entry = getEntryOrNull(path);
        return entry == null ? null : entry.getValue();
    }

    public static boolean remove(String path) {
        return files.remove(path) != null;
    }

    public static boolean add(File file, String newContent) {
        return files.put(file.getPath(), Map.entry(file, newContent)) != null;
    }

    public static boolean update(File file, String newContent) {
        return files.put(file.getPath(), Map.entry(file, newContent)) != null;
    }
}
