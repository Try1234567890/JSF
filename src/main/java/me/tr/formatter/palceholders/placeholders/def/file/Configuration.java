package me.tr.formatter.palceholders.placeholders.def.file;

import me.tr.formatter.palceholders.functions.Function;
import me.tr.formatter.palceholders.params.Params;
import me.tr.formatter.palceholders.placeholders.Placeholder;
import me.tr.formatter.uids.UID;
import me.tr.trfiles.configuration.implementations.FileConfiguration;

import java.io.*;

public class Configuration extends Placeholder {
    public Configuration() {
        super(new UID("Config"));
    }

    public Configuration(Params params, Function[] functions, int start, int end) {
        super(new UID("Config"), params, functions, start, end, 1);
    }

    @Override
    public String process(String str) {
        File configFile = getParams().asFile(0);
        String path = getParams().asString(1);
        if (!configFile.exists()) {
            return "FileNotExists";
        }
        if (!configFile.isFile()) {
            return "IsNotAFile";
        }
        if (!configFile.canRead()) {
            return "IsNotReadable";
        }
        if (path.isEmpty()) {
            StringBuilder content = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line);
                    content.append('\n');
                }
                return content.toString();
            } catch (IOException e) {
                return e.getClass().getSimpleName();
            }
        }
        FileConfiguration config = FileConfiguration.fromFile(configFile);
        return config.get(path, str).toString();
    }
}
