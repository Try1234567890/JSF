package me.tr.trformatter.color.validator;


import me.tr.trformatter.color.exceptions.InvalidColorException;

public interface ColorValidator<T> {

    T validate(T color) throws InvalidColorException;

}
