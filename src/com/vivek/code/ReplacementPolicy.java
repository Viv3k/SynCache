package com.vivek.code;

/*
    Replacement policy of a cache which performs the set and get logic as per the policy rules.
 */
public interface ReplacementPolicy {

    void set(String key, Object value);
    CacheEntry get(String key);
}
