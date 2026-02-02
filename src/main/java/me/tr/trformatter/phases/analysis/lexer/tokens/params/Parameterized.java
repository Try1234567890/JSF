package me.tr.trformatter.phases.analysis.lexer.tokens.params;

import me.tr.trformatter.phases.analysis.lexer.tokens.params.manager.ParamsContainer;

public interface Parameterized {

    ParamsContainer params();

    default boolean insert(String name, Object value) {
        return params().insert(name, value);
    }

    default boolean insert(ParamToken token) {
        return params().insert(token);
    }

    default boolean has(String name) {
        return params().has(name);
    }

    default boolean isPresent(String name) {
        return params().isPresent(name);
    }

    default Object pop(String name) {
        return params().pop(name);
    }

    default boolean remove(String name) {
        return params().remove(name);
    }

    default Object get(String name) {
        return params().getOrNull(name);
    }

    default boolean insert(int index, Object value) {
        return params().insert(index, value);
    }

    default boolean has(int index) {
        return params().has(index);
    }

    default boolean isPresent(int index) {
        return params().isPresent(index);
    }

    default Object pop(int index) {
        return params().pop(index);
    }

    default boolean remove(int index) {
        return params().remove(index);
    }

    default Object get(int index) {
        return params().getOrNull(index);
    }


}
