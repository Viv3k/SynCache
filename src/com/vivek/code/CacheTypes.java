package com.vivek.code;

public enum CacheTypes {

    LRU("LRU"), PSUEDO_LRU("PUSEDO_LRU"), LIFO("LIFO");

    private final String text;

    private CacheTypes(final String cacheType) {
        this.text = cacheType;
    }

}