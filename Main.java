package no.uib.info233v18.oblig4;

import java.util.Comparator;

public class Main {

    public static void main(String[] args){
        Entry<Integer, String> test = new Entry<>(6, "first");
        Entry<Integer,String> test2 = new Entry<>(2, "second");
        Entry<Integer, String> test3 = new Entry<>(3, "third");
        Entry<Integer, String> test4 = new Entry<>(8, "third");
        Entry<Integer, String> test5 = new Entry<>(4, "third");
        Entry<Integer, String> test6 = new Entry<>(7, "third");

        SortedTreeMap<Integer, String> map = new SortedTreeMap<>();

        map.add(test);
        map.add(test2);
        map.add(test3);


        System.out.println(map.min().key);
        System.out.println(map.max().key);







    }

}
