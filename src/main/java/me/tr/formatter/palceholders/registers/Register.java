package me.tr.formatter.palceholders.registers;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Register {


    /**
     * Auto-Register all functions found into the provided jar.
     *
     * @param file File representing the jar to open.
     */
    public static void register(File file) throws IOException, ClassNotFoundException,
            InvocationTargetException, NoSuchMethodException, InstantiationException,
            IllegalAccessException {
        try (JarFile jar = new JarFile(file);
             URLClassLoader classLoader = new URLClassLoader(new URL[]{file.toURI().toURL()})) {
            Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (entry.getName().endsWith(".class")) {
                    Class<?> clazz = classLoader.loadClass(entry.getName().replace('/', '.').replace(".class", ""));
                    FunctionRegister.register(clazz);
                    PlaceholderRegister.register(clazz);
                    TagRegister.register(clazz);
                }
            }
        }
    }
}
