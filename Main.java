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
        Entry<Integer, String> test7 = new Entry<>(1, "third");







        SortedTreeMap<Integer, String> map = new SortedTreeMap<>();

        System.out.println(map.add(test.key, test.value));
        map.add(test2.key, test2.value);
        map.add(test3.key, test3.value);
        map.add(test4.key, test4.value);
        map.add(test5.key, test5.value);
        map.add(test6.key, test6.value);
        map.add(test7.key, test7.value);


        System.out.println(map.size());


    }

}
