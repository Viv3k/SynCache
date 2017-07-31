package com.vivek.code;

import com.sun.media.sound.InvalidDataException;

public class CacheFactory {

    private static final long DEFAULT_CACHE_SIZE = 1024;

    public static CacheWithReplacementPolicy getCacheWithSize(CacheTypes cacheType, long size) throws InvalidDataException {

        switch (cacheType) {
        case LRU:
            return new LRUCache(size);
        case LIFO:
            return new LIFOCache(size);
        case PSUEDO_LRU:
            return new PsuedoLRU(size);
        default:
            throw new InvalidDataException("Cache type not found");
        }

    }

    public static CacheWithReplacementPolicy getCacheWithSize(CacheTypes cacheType) throws InvalidDataException {
        return getCacheWithSize(cacheType, DEFAULT_CACHE_SIZE);
    }

}
