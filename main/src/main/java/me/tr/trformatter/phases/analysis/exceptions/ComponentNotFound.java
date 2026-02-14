package me.tr.trformatter.phases.analysis.exceptions;

public class ComponentNotFound extends NullPointerException {

    public ComponentNotFound(String message) {
        super(message);
    }

    public static ComponentNotFound of(String name, String id) {
        return new ComponentNotFound("The " + name + " " + id + " is not present in TrFormatter registries");
    }

    public static ComponentNotFound tag(String id) {
        return of("tag", id);
    }

    public static ComponentNotFound function(String id) {
        return of("function", id);
    }

    public static ComponentNotFound condition(String id) {
        return of("condition", id);
    }
}
