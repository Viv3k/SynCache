package com.vivek.code;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DiskUtils {

    private static final String CACHE_JOURNAL = "cacheJournal";

    public static void storeCacheInfo(DiskCache d) {
        System.out.println(new File(CACHE_JOURNAL).exists());
        writeToFile(d, CACHE_JOURNAL);
    }

    public static DiskCache getCacheInfo() {
        return (DiskCache) readFromFile(CACHE_JOURNAL);
    }

    public static void writeToFile(Object object, String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
        } catch (IOException e) {
            System.out.println("Unable to write to file " + fileName);
        }
    }

    public static Object readFromFile(String fileName) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(fileName));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return objectInputStream.readObject();
        } catch (IOException e) {
            System.out.println("Unable to read to file " + fileName);
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found exception while reading " + fileName);
        }
        return null;
    }
}
