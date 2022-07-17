package ru.baib;

import java.util.*;

public class Combinator<K, V> {

    private Map<K, List<V>> sources;
    private ListIterator<K> keysIterator;
    private Map<K, Integer> counter;
    private List<List<V>> result;

    public Combinator(Map<K, List<V>> sources) {
        this.sources = sources;
        counter = new HashMap<>();
        keysIterator = new ArrayList<>(sources.keySet()).listIterator();
    }

    public List<List<V>> makeCombinations() {
        result = new ArrayList<>();
        loop();
        return result;
    }

    private void loop() {
        if (keysIterator.hasNext()) {
            K key = keysIterator.next();
            counter.put(key, 0);

            while (counter.get(key) < sources.get(key).size()) {
                loop();
                counter.put(key, counter.get(key) + 1);
            }
            keysIterator.previous();
        } else {
            fill();
        }
    }

    private void fill() {
        List<V> combination = new ArrayList<>();

        for (K key: sources.keySet()) {
            Integer position = counter.get(key);
            combination.add(sources.get(key).get(position));
        }

        result.add(combination);
    }
}
