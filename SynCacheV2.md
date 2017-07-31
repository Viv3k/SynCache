## SynCache

### Optimizing the storage and retrieval for the cache.
**Solution:**  The earlier idea was to use to synchronize the get and set methods of the cache. This is a bottle-neck to the performance in a multi-threaded scenario, as the cache will only allow to access on entry at any given point of time. The solution to this is to use a ConcurrentHashMap instead of synchronizing the get and set methods. Note that the pointer operation are still under synchornization here, this would not the effect the permormance (latency of retrivals as the udating) as the pointer manupulation logic is done after the result of operation if done.(Bank ledger book can be updated later, serve the customer first :)

**Explaination:** 

* Concurrent read to single entry: ConcurrentHashMap is a hash table supporting full concurrency of retrievals; property of [ConcurrentHashMap](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ConcurrentHashMap.html)

* Concurrent read to multiple entries: ConcurrentHashMap returns the values to the threads simultaneously(assuming cache hits). 

* Concurrent write to multiple entries: Adjustable expected concurrency for updates(writes) can be configured for ConcurrentHashMap. This allows multiple entry edit/storage.

* Concurrent write to single entry: Cannot be optimized as the data hinges to single memory point. Sequential in nature.


### Research scope on further optimizations:

* Thoughts on Lazy implementation of the Replacement policy.
* Refereces layout can be modeled better
