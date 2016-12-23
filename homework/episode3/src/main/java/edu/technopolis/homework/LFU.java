package edu.technopolis.homework;

import java.util.*;

public class LFU<K, V> implements Map<K, V> {
    private static class LFUEntry<K, V> {
        K key;
        V value;
        int freq;

        LFUEntry(K key, V value, int freq) {
            this.key = key;
            this.value = value;
            this.freq = freq;
        }
    }

    private Map<K, LFUEntry<K, V>> cache;
    private LinkedHashSet[] freqs;
    private int minFreq;
    private int maxFreq;
    private final float loadFactor;
    private final int maxSize;

    public LFU(float loadFactor, int maxSize) {
        if (loadFactor <= 0 || loadFactor >= 1) {
            System.err.println("Illegal load factor value");
            throw new IllegalArgumentException();
        }
        cache = new HashMap<>(maxSize);
        freqs = new LinkedHashSet[maxSize];
        minFreq = 0;
        maxFreq = maxSize - 1;
        this.loadFactor = loadFactor;
        this.maxSize = maxSize;
        initFreqs();
    }

    private void initFreqs() {
        for (int i = 0; i <= maxFreq; ++i) {
            freqs[i] = new LinkedHashSet<>();
        }
    }

    @SuppressWarnings("unchecked")
    public V put(K key, V value) {
        V elem = null;
        LFUEntry<K, V> curr = cache.get(key);
        if (curr == null) {
            if (cache.size() == maxSize) {
                eviction();
            }
            LinkedHashSet<LFUEntry<K, V>> nodes = freqs[0];
            curr = new LFUEntry<>(key, value, 0);
            nodes.add(curr);
            cache.put(key, curr);
            minFreq = 0;
        } else {
            elem = curr.value;
            curr.value = value;
        }
        return elem;
    }

    @SuppressWarnings("unchecked")
    public V get(Object key) {
        LFUEntry<K, V> curr = cache.get(key);
        if (curr == null) {
            return null;
        } else {
            int currFreq = curr.freq;
            if (currFreq < maxFreq) {
                int nextFreq = currFreq + 1;
                LinkedHashSet<LFUEntry<K, V>> nodes = freqs[currFreq];
                LinkedHashSet<LFUEntry<K, V>> newNodes = freqs[nextFreq];
                moveToNextFreq(curr, nextFreq, nodes, newNodes);
                cache.put((K) key, curr);

                if (minFreq == currFreq && nodes.isEmpty()) {
                    minFreq = nextFreq;
                }
            } else {
                LinkedHashSet<LFUEntry<K, V>> nodes = freqs[currFreq];
                nodes.remove(curr);
                nodes.add(curr);
            }
            return curr.value;
        }
    }

    @SuppressWarnings("unchecked")
    public V remove(Object key) {
        LFUEntry<K, V> curr = cache.remove(key);
        if (curr == null) {
            return null;
        } else {
            LinkedHashSet<LFUEntry<K, V>> nodes = freqs[curr.freq];
            nodes.remove(curr);
            if (minFreq == curr.freq) {
                nextMinFreq();
            }
            return curr.value;
        }
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry: map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public Set<K> keySet() {
        return this.cache.keySet();
    }

    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    public Collection<V> values() {
        return null;
    }

    public int freqOf(K key) {
        LFUEntry<K, V> node = cache.get(key);
        if (node == null) {
            return 0;
        } else {
            return node.freq + 1;
        }
    }

    public void clear() {
        for (int i = 0; i <= maxFreq; ++i) {
            freqs[i].clear();
        }
        cache.clear();
        minFreq = 0;
    }

    public int size() {
        return cache.size();
    }

    public boolean isEmpty() {
        return cache.isEmpty();
    }

    public boolean containsKey(Object o) {
        return cache.containsKey(o);
    }

    public boolean containsValue(Object o) {
        return false;
    }

    @SuppressWarnings("unchecked")
    private void eviction() {
        int currDeleted = 0;
        float target = maxSize * loadFactor;
        while (currDeleted < target) {
            LinkedHashSet<LFUEntry<K, V>> nodes = freqs[minFreq];
            if (nodes.isEmpty()) {
                throw new IllegalStateException();
            } else {
                Iterator<LFUEntry<K, V>> iter = nodes.iterator();
                while (iter.hasNext() && (currDeleted++ < target)) {
                    LFUEntry<K, V> node = iter.next();
                    iter.remove();
                    cache.remove(node.key);
                }
                if (!iter.hasNext()) {
                    nextMinFreq();
                }
            }
        }
    }

    private void moveToNextFreq(LFUEntry<K, V> curr, int nextFreq, LinkedHashSet<LFUEntry<K, V>> nodes, LinkedHashSet<LFUEntry<K, V>> newNodes) {
        nodes.remove(curr);
        newNodes.add(curr);
        curr.freq = nextFreq;
    }

    private void nextMinFreq() {
        while (minFreq <= maxFreq && freqs[minFreq].isEmpty()) {
            minFreq++;
        }
        if (minFreq > maxFreq) {
            minFreq = 0;
        }
    }
}


