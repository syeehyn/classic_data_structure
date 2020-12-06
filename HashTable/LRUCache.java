/*
 * NAME: Jackie Yuan
 */
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
/**
 * This class contains the implement of LRUCache.
 *
 * @author Shuibenyang Yuan
 * @Since {May 4 2018}
 */
public class LRUCache  {
    // Initialize constructor variables.
    private int capacity;
    private HashMap cache;
    private int size;
    private LinkedList lru;
    private final int NULLVAL = -1;

    /**
     * Constructor of LRUCache.
     * @param capacity the capacity of the Cache.
     */
    public LRUCache(int capacity) {
        assert capacity > 0; //the capacity should not be zero.
        this.cache = new HashMap<Integer, Integer>();
        this.lru = new LinkedList<Integer>();
        this.size = 0;
        this.capacity = capacity;
    }

    /**
     * The method to get the item in the cache.
     * @param key the location of the item.
     * @return Return the value (would always be positive) of the key if the key exists in the
     * cache, otherwise return -1.
     */
    public Integer get(int key) {
        // if the key is not existed.
        if (!this.cache.containsKey(key)) {
            return NULLVAL;
        } else {
            // find to key and refresh the usage.
            this.lru.remove(this.cache.get(key));
            this.lru.add(this.lru.size(), this.cache.get(key));
            return (Integer) this.cache.get(key);
        }
    }

    /**
     * Set the value of the key, if the key does not exist in the cache.
     * When the cache reached its capacity, it should invalidate the least recently used item
     * before inserting a new item.
     * @param key where to set the element.
     * @param value what to be set.
     */
    public void set(int key, int value) {
        // if the key is existed.
        if (this.cache.containsKey(key)) {
            this.lru.remove(this.cache.get(key));
        }
        // when the size is full.
        if (this.size == this.capacity) {
            // remove the item.
            Integer toRemove = (Integer) this.lru.get(0); // conflict
            this.lru.remove(this.lru.get(0));
            for (Integer i : (Set<Integer>) this.cache.keySet()) {
                if (toRemove.equals(this.cache.get(i))) {
                    if (this.cache.containsKey(key)) {
                        this.lru.remove(this.cache.get(key));
                        this.size--;
                    }
                    this.cache.remove(i);
                    break;
                }
            }
        } else {
            this.size++;
        }
        this.lru.add(this.lru.size(), value);
        this.cache.put(key, value);

    }
}
