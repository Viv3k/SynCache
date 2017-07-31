package com.vivek.code;

import static com.vivek.code.DiskUtils.readFromFile;
import static com.vivek.code.DiskUtils.writeToFile;

import java.io.File;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheJournal implements Serializable {

    private long capacity;
    private Map<String, CacheEntry> cacheJournal = new ConcurrentHashMap<>();

    private CacheEntry head;
    private CacheEntry tail;

    public CacheJournal(long cacheSize) {
        capacity = cacheSize;
    }

    private synchronized void remove(CacheEntry entry) {
        if (entry.previous != null) {
            entry.previous.next = entry.next;
        } else {
            head = entry.next;
        }

        if (entry.next != null) {
            entry.next.previous = entry.previous;
        } else {
            tail = entry.previous;
        }
    }

    private synchronized void setHead(CacheEntry n) {
        n.next = head;
        n.previous = null;

        if (head != null)
            head.previous = n;

        head = n;

        if (tail == null)
            tail = head;
    }

    CacheEntry get(String key) {
        if (cacheJournal.containsKey(key)) {
            CacheEntry n = (CacheEntry) readFromFile(key);
            remove(n);
            setHead(n);
            return n;
        }
        // This accounts for cache miss. Leaving out the implementation on cache miss
        return null;
    }

    void set(String key, Object value) {
        if (cacheJournal.containsKey(key)) {
            CacheEntry old = cacheJournal.get(key);
            old.entryValue = value;
            remove(old);
            setHead(old);
            writeToFile(old, key);

        } else {
            CacheEntry created = new CacheEntry(key, value.getClass(), value);
            cacheJournal.put(key, created);

            if (getCacheSize() > capacity) {
                while (getCacheSize() > capacity) {
                    if (tail == null)
                        throw new OutOfMemoryError();
                    new File(tail.key).delete();
                    cacheJournal.remove(tail.key);
                    remove(tail);
                }
            }
            setHead(created);
            writeToFile(created, key);

        }
    }

    long getCacheSize() {
        long size = 0;
        for (String fileName : cacheJournal.keySet()) {
            size += new File(fileName).length();
        }
        return size;
    }

    boolean hasKey(String key) {
        return cacheJournal.containsKey(key);
    }
}
