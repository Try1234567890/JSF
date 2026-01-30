package me.tr.trformatter.analysis.lexer.tokens.params;

import me.tr.trformatter.analysis.lexer.tokens.params.manager.ParamsContainer;

public interface HasParams {

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
        return params().get(name);
    }

    default boolean isParamToken(Object value) {
        return params().isParamToken(value);
    }

    default boolean isParamToken(String name) {
        return params().isParamToken(name);
    }

    default PlaceholderParamToken getAsPlaceholderOrThrown(String name) {
        return params().getAsPlaceholderOrThrown(name);
    }

    default PlaceholderParamToken getAsPlaceholder(String name) {
        return params().getAsPlaceholder(name);
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
        return params().get(index);
    }

    default boolean isParamToken(int index) {
        return params().isParamToken(index);
    }

    default PlaceholderParamToken getAsPlaceholderOrThrown(int index) {
        return params().getAsPlaceholderOrThrown(index);
    }

    default PlaceholderParamToken getAsPlaceholder(int index) {
        return params().getAsPlaceholder(index);
    }
}
