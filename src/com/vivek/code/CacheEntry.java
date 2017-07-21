package com.vivek.code;

import java.io.Serializable;

public class CacheEntry<T> implements Serializable {

    String key;
    Class<T> clazz;
    Object entryValue;
    CacheEntry previous;
    CacheEntry next;

    public CacheEntry(String key, Class<T> clz, Object o) {
        this.key = key;
        clazz = clz;
        entryValue = o;
    }

    public T getValue() {
        return clazz.cast(entryValue);
    }

}

