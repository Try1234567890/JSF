package me.tr.trformatter.defaults.tags.readfile;

import me.tr.trformatter.utility.Validator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

public class FileReader {

    private FileReader() {
    }

    public static String read(File file) {
        Validator.isNull(file, "The file is null");
        return getFromHistoryOrRead(file);
    }

    private static String getFromHistoryOrRead(File file) {
        Optional<String> history = getFromHistory(file);
        if (history.isPresent()) {
            return history.get();
        } else {
            String content = readFile(file);
            FileHistory.add(file, content);
            return content;
        }
    }

    private static Optional<String> getFromHistory(File file) {
        String path = file.getPath();

        if (!FileHistory.has(path)) {
            // Not in history.
            return Optional.empty();
        }


        File foundFile = FileHistory.getFileOrNull(path);

        if (foundFile == null) {
            // This is impossible.
            return Optional.empty();
        }

        return Optional.ofNullable(updateHistory(file, foundFile).orElse(FileHistory.getContentOrNull(path)));
    }

    private static Optional<String> updateHistory(File file, File historyFile) {
        if (file.length() != historyFile.length()) {
            String newContent = readFile(file);
            FileHistory.update(file, newContent);
            return Optional.of(newContent);
        }
        return Optional.empty();
    }

    private static String readFile(File file) {
        String path = (Validator.isNull(file) ? "null" : file.getPath());

        try {
            if (Validator.isNull(file) || !file.exists()) {
                new IOException("The provided path does not exist or is null: " + path).printStackTrace(System.err);
                return "";
            }

            if (file.isDirectory()) {
                new IOException("Expected a file, but found a directory: " + path).printStackTrace(System.err);
                return "";
            }

            if (!file.canRead()) {
                new IOException("File is not readable (no read permissions): " + path).printStackTrace(System.err);
                return "";
            }

            return Files.readString(file.toPath());
        } catch (IOException e) {
            e.printStackTrace(System.err);
            return "";
        }

    }
}












