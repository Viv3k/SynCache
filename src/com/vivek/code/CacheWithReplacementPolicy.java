package com.vivek.code;

/*
    Cache with a replacement policy which performs the set and get logic as per the policy rules.
 */
public interface CacheWithReplacementPolicy {

    void set(String key, Object value);
    CacheEntry get(String key);
    boolean hasKey(String key);
    long getCacheSize();
}
