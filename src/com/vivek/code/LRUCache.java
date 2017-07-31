package com.vivek.code;

import static com.vivek.code.DiskUtils.readFromFile;
import static com.vivek.code.DiskUtils.writeToFile;

import java.io.File;

public class LRUCache extends CacheJournal implements CacheWithReplacementPolicy {

    private CacheEntry head;
    private CacheEntry tail;

    LRUCache(long cacheSize) {
        super(cacheSize);
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

    @Override
    public CacheEntry get(String key) {
        if (cacheJournal.containsKey(key)) {
            CacheEntry n = (CacheEntry) readFromFile(key);
            remove(n);
            setHead(n);
            return n;
        }
        // This accounts for cache miss. Leaving out the implementation on cache miss
        return null;
    }

    @Override
    public void set(String key, Object value) {
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

    @Override
    public long getCacheSize() {
        long size = 0;
        for (String fileName : cacheJournal.keySet()) {
            size += new File(fileName).length();
        }
        return size;
    }

    @Override
    public boolean hasKey(String key) {
        return cacheJournal.containsKey(key);
    }
}
