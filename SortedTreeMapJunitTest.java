package no.uib.info233v18.oblig4;

import static org.junit.jupiter.api.Assertions.*;

class SortedTreeMapJunitTest {

    private SortedTreeMap<Integer,String> map;
    private Entry<Integer, String> entry;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

        entry = new Entry<>(1, "test1");
        map = new SortedTreeMap<>(entry, null);

    }

    @org.junit.jupiter.api.Test
    void min() {
    }

    @org.junit.jupiter.api.Test
    void max() {
    }

    @org.junit.jupiter.api.Test
    void add() {
        Entry<Integer,String> value2 = new Entry<>(1, "test2");




    }

    @org.junit.jupiter.api.Test
    void add1() {
    }

    @org.junit.jupiter.api.Test
    void replace() {
    }

    @org.junit.jupiter.api.Test
    void replace1() {
    }

    @org.junit.jupiter.api.Test
    void remove() {
    }

    @org.junit.jupiter.api.Test
    void getValue() {
    }

    @org.junit.jupiter.api.Test
    void containsKey() {
    }

    @org.junit.jupiter.api.Test
    void containsValue() {
    }

    @org.junit.jupiter.api.Test
    void keys() {
    }

    @org.junit.jupiter.api.Test
    void values() {
    }

    @org.junit.jupiter.api.Test
    void entries() {
    }

    @org.junit.jupiter.api.Test
    void higherOrEqualEntry() {
    }

    @org.junit.jupiter.api.Test
    void lowerOrEqualEntry() {
    }

    @org.junit.jupiter.api.Test
    void merge() {
    }

    @org.junit.jupiter.api.Test
    void removeIf() {
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
    }

    @org.junit.jupiter.api.Test
    void size() {
    }

    @org.junit.jupiter.api.Test
    void clear() {
    }
}