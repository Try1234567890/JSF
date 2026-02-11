package me.tr.trformatter.defaults.tags.readfile;

import me.tr.trformatter.utility.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

public class FileReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileReader.class);

    private FileReader() {}

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
                LOGGER.error("The provided path does not exist or is null: {}", path);
                return "";
            }

            if (file.isDirectory()) {
                LOGGER.error("Expected a file, but found a directory: {}", path);
                return "";
            }

            if (!file.canRead()) {
                LOGGER.error("File is not readable (no read permissions): {}", path);
                return "";
            }

            return Files.readString(file.toPath());
        } catch (IOException e) {
            LOGGER.error("Error while reading file: {}", path, e);
            return "";
        }

    }
}












