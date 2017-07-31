## Low level design specs of SynCache

* [CacheJournal](https://github.com/Viv3k/SynCache/blob/ReplacementPolicy/src/com/vivek/code/CacheJournal.java) contains all the resources necessary for a cache.
* [CacheWithReplacementPolicy](https://github.com/Viv3k/SynCache/blob/ReplacementPolicy/src/com/vivek/code/CacheWithReplacementPolicy.java) has all the fuctionalities of a cache.
* Caches can be generated via a [CacheFactory](https://github.com/Viv3k/SynCache/blob/ReplacementPolicy/src/com/vivek/code/CacheFactory.java) which swiches on [CacheTypes](https://github.com/Viv3k/SynCache/blob/ReplacementPolicy/src/com/vivek/code/CacheTypes.java) enum to generate the required cache.
* The class design is kept highly modular.

### TODO Further imporvements
Implement the hasKey() and getCacheSize() as **default** functions to the interface, to avoid code duplication???
