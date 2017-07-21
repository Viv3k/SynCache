import static com.vivek.code.DiskUtils.getCacheInfo;
import static com.vivek.code.DiskUtils.storeCacheInfo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.vivek.code.DiskCache;

public class CacheTests {

    private DiskCache diskCache;

    private static final int INT_CACHE = 42; //Size of 289
    private static final String STRING_CACHE = "stringCache"; //size 254
    private static final List<Integer> LIST_CACHE = Arrays.asList(42, 42, 42); //Size 409
    private static final int MODIFIED_INT_CACHE = 24;  //size 290

    private static final String FIRST = "first";
    private static final String SECOND = "second";
    private static final String THIRD = "third";
    private static final String FOURTH = "fourth";

    @Before
    public void setUp() {
        diskCache = getCacheInfo();
        if (diskCache == null) {
            diskCache = new DiskCache("MyCache", 1024);
            // storeCacheInfo(diskCache);
            // diskCache.printCacheDetails();
            // diskCache.printCacheDetails();
        }
    }

    @Test(expected = OutOfMemoryError.class)
    public void some() {
        DiskCache d = new DiskCache("MySmallCache", 200);
        d.setEntry(FIRST, "nananananananaBatman");
    }

    @Test
    public void checkCacheResurrect() {
        diskCache.setEntry(FIRST, INT_CACHE);
        storeCacheInfo(diskCache);
        DiskCache ghostCache = getCacheInfo();
        assertTrue(ghostCache != null);
        assertTrue(ghostCache.getRegistry().cacheJournal.size() == 1);
        assertTrue(ghostCache.hasEntry(FIRST));
    }

    @Test
    public void rewriteCache() {
        diskCache.setEntry(FIRST, INT_CACHE);
        diskCache.setEntry(FIRST, MODIFIED_INT_CACHE);
        assertTrue(diskCache.getEntry(FIRST).getValue().equals(MODIFIED_INT_CACHE));

    }

    @Test
    public void testLRU_1() {
        diskCache.setEntry(FIRST, INT_CACHE);
        diskCache.setEntry(SECOND, STRING_CACHE);
        diskCache.setEntry(THIRD, LIST_CACHE);

        //Trying to add a fourth one should remove the FIRST
        diskCache.setEntry(FOURTH, MODIFIED_INT_CACHE);
        assertFalse(diskCache.hasEntry(FIRST));
        assertFalse(new File("MyCache/first").exists());

    }

    @Test
    public void testLRU_2() {
        diskCache.setEntry(SECOND, STRING_CACHE);
        diskCache.setEntry(FIRST, INT_CACHE);
        diskCache.setEntry(THIRD, LIST_CACHE);

        //Trying to add a fourth one should remove the SECOND
        diskCache.setEntry(FOURTH, MODIFIED_INT_CACHE);
        assertFalse(diskCache.hasEntry(SECOND));
        assertFalse(new File("MyCache/second").exists());

    }

    @Test
    public void testLRU_3() {
        diskCache.setEntry(SECOND, STRING_CACHE);
        diskCache.setEntry(FIRST, INT_CACHE);
        diskCache.setEntry(THIRD, LIST_CACHE);
        diskCache.getEntry(SECOND);
        diskCache.getEntry(FIRST);
        diskCache.getEntry(THIRD);

        //Trying to add a fourth one should remove the SECOND
        diskCache.setEntry(FOURTH, MODIFIED_INT_CACHE);
        assertFalse(diskCache.hasEntry(SECOND));
        assertFalse(new File("MyCache/second").exists());

    }
}
