package no.uib.info233v18.oblig4;

import java.util.Comparator;

public class Main {

    public static void main(String[] args){
        Entry<Integer, String> test = new Entry<>(1, "Mats");
        Entry<Integer, String> test2 = new Entry<>(1, "Mats");

        SortedTreeMap<Integer, String> tester = new SortedTreeMap<>();
        SortedTreeMap<Integer, String> map = new SortedTreeMap<>(test);

        System.out.println(map.add(test2.key, test2.value));
        System.out.println(map.size());
    }

}
