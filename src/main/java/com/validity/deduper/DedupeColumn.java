package com.validity.deduper;

import java.lang.reflect.Field;
import java.util.function.Function;

public class DedupeColumn<T> {
    private final Class<T> clazz;
    private final Function<T, String> getter;

    private DedupeColumn(Class<T> clazz, Function<T, String> getter) {
        this.clazz = clazz;
        this.getter = getter;
    }

    public static <S> DedupeColumn<S> forClassField(Class<S> clazz, Function<S, String> getter) {
        return new DedupeColumn<>(clazz, getter);
    }

    public String get(T t) {
        return getter.apply(t);
    }
}
