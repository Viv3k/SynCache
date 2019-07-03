## SynCache

### About
This repository contains implementation code for a disk based Cache model which uses LRU cache replacement policy. 

### Implementation of LRU

LRU is implemeted using a double linked list and two pointers **HEAD** and **TAIL**. When a new [Entry](https://github.com/Viv3k/SynCache/blob/master/src/com/vivek/code/CacheEntry.java) is added, it is appened at the head of the linked list. When an [Entry](https://github.com/Viv3k/SynCache/blob/master/src/com/vivek/code/CacheEntry.java) is accessed from the cache, it is moved to the head of the list.

Whenever there is a need for eviction, the [Entry](https://github.com/Viv3k/SynCache/blob/master/src/com/vivek/code/CacheEntry.java) at the tail is removed, since as per the updating logic the last element of the list is the least recently used element of the cache.

### Features
The details about the cache are maintained in using the [CacheJournal](https://github.com/Viv3k/SynCache/blob/master/src/com/vivek/code/CacheJournal.java). This maintains the details about the entries and also the most and least recently used entries. All the information about the model is seriablizable, and hence we can dump the objects to a file using ObjectStreams and pick up right where we leave. This feature can be seen in [DiskUtils](https://github.com/Viv3k/SynCache/blob/master/src/com/vivek/code/DiskUtils.java).

Also the muti-process/ multi-threaded access of the Cache is made feasible by allowing [synchronous call](https://github.com/Viv3k/SynCache/blob/master/src/com/vivek/code/DiskCache.java) to add and query the Entries. The values of the entries are generic and hence we can get proper [typed values](https://github.com/Viv3k/SynCache/blob/master/src/com/vivek/code/CacheEntry.java) for the entries.

### Performance
Since the memory management modeling is largely left to the Java Data Structures, the performence of the Cache model is close to optimal. Here, we use a HashMap to store/search the entries and pointer manipulation for each of the search/store. This should result in a **O(1)** performance. The space complexity of the model is **O(n)**

### Assumptions
The low level memory management is not included. The main focus is on implementing a LRU Cache model with **get()** and **add()** .

##### Ignore
Adding slack integration ignore
