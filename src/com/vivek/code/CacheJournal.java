package com.vivek.code;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheJournal implements Serializable {

    public long capacity;
    public Map<String, CacheEntry> cacheJournal = new ConcurrentHashMap<>();

    public CacheJournal(long cacheSize) {
        capacity = cacheSize;
    }

    public CacheJournal(){}
}
