package no.uib.info233v18.oblig4;

import java.util.Comparator;

public class Main {

    public static void main(String[] args){
        Entry<Integer, String> test = new Entry<>(1, "first");
        Entry<Integer,String> test2 = new Entry<>(2, "second");
        Entry<Integer, String> test3 = new Entry<>(3, "third");






        SortedTreeMap<Integer, String> map = new SortedTreeMap<>();

        System.out.println(map.add(test.key, test.value));
        map.add(test2.key, test2.value);
        map.add(test3.key, test2.value);

        System.out.println(map.size());


    }

}
