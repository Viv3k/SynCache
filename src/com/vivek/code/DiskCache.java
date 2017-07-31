package com.vivek.code;

import java.io.File;
import java.io.Serializable;

public class DiskCache implements Serializable {

    private File directory;
    private long cacheSize;
    private CacheJournal registry;

    public DiskCache(String directoryName, long cacheSize) {

        directory = new File(directoryName);
        directory.mkdir();
        registry = new CacheJournal(this.cacheSize = cacheSize);
    }

    public void setEntry(String key, Object o) {
        String name = directory.getName();
        registry.set(name + "/" + key, o);
    }

    public CacheEntry getEntry(String key) {
        return registry.get(directory.getName() + "/" + key);
    }

    public boolean hasEntry(String key) {
        return registry.hasKey(directory.getName() + "/" + key);
    }

    public void printCacheDetails() {
        System.out.println("[INFO] Cache directory:" + directory.getName());
        System.out.println("[INFO] Cache size:" + registry.getCacheSize());
        System.out.println("[INFO] No.of files:" + directory.listFiles().length);
        System.out.println("[INFO] Max cache capacity:" + cacheSize);
    }

//    public void printCacheContent() {
//        for (String entrykey : registry.cacheJournal.keySet()) {
//            System.out.println("-- " + entrykey + " : " + registry.cacheJournal.get(entrykey).getValue());
//        }
//    }

    public CacheJournal getRegistry() {
        return this.registry;
    }
}
