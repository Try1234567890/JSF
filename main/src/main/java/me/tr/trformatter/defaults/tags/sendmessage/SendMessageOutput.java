package me.tr.trformatter.defaults.tags.sendmessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;
import java.util.function.Function;

public enum SendMessageOutput {

    SOUT((param) -> System.out),
    SERR((param) -> System.err),
    FILE((param) -> {
        try {
            return new FileOutputStream(getFile(param));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("An error occurs while creating a new file output stream. Make sure that the file exists and is writable.", e);
        }
    }),
    URL((param) -> {
        int index = param.indexOf(':');
        if (index == -1) {
            throw new ArrayIndexOutOfBoundsException("The url is not specified for param " + param + ". Make sure param follow this format: \"URL:{URL TO OUTSTREAM}\"");
        }

        String urlStr = param.substring(index + 1).trim();
        try {
            URL url = URI.create(urlStr).toURL();
            URLConnection connection = url.openConnection();

            connection.setDoOutput(true);

            if (connection instanceof HttpURLConnection) {
                ((HttpURLConnection) connection).setRequestMethod("POST");
            }

            return connection.getOutputStream();
        } catch (MalformedURLException e) {
            throw new RuntimeException("The URL " + urlStr + " is not valid.", e);
        } catch (ProtocolException e) {
            throw new RuntimeException("The protocol of URL " + urlStr + " not support \"POST\" request.", e);
        } catch (IOException e) {
            throw new RuntimeException("An I/O error occurs while opening output stream from the URL: " + urlStr, e);
        }
    });

    private static File getFile(String param) {
        int index = param.indexOf(':');
        if (index == -1) {
            throw new ArrayIndexOutOfBoundsException("The path of the file is not specified for param " + param + ". Make sure param follow this format: \"FILE:{FILE_PATH}\"");
        }

        File file = new File(param.substring(index + 1));
        if (!file.exists()) {
            throw new NullPointerException("The file specified for param " + param + " not exists.");
        }

        if (file.isDirectory()) {
            throw new NullPointerException("The provided path is a directory.");
        }

        if (!file.canWrite()) {
            throw new RuntimeException("The provided file is not writable.");
        }
        return file;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(SendMessageOutput.class);
    private final Function<String, OutputStream> function;

    SendMessageOutput(Function<String, OutputStream> function) {
        this.function = function;
    }

    public static SendMessageOutput parse(String str) {
        if (str == null) {
            LOGGER.warn("The send message output is null. Using \"SOUT\"");
            return SOUT;
        }

        int isColonPresent = str.indexOf(':');
        if (isColonPresent != -1) {
            str = str.substring(0, isColonPresent);
        }

        for (SendMessageOutput output : SendMessageOutput.values()) {
            if (output.name().equalsIgnoreCase(str)) {
                return output;
            }
        }
        LOGGER.warn("The send message output {} is not recognized. Using \"SOUT\"", str);
        return SOUT;
    }

    public Function<String, OutputStream> getFunction() {
        return function;
    }

    public OutputStream getOut(String name) {
        return function.apply(name);
    }
}
