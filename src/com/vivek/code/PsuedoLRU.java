package com.vivek.code;

public class PsuedoLRU extends CacheJournal implements CacheWithReplacementPolicy {

    PsuedoLRU(long size) {
        super(size);
    }

    @Override
    public void set(String key, Object value) {
        //TODO: Add logic
    }

    @Override
    public CacheEntry get(String key) {
        //TODO: Add logic
        return null;
    }

    @Override
    public boolean hasKey(String key) {
        //TODO: Add logic
        return false;
    }

    @Override
    public long getCacheSize() {
        //TODO: Add logic
        return 0;
    }
}