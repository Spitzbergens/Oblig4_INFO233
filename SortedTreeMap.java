package no.uib.info233v18.oblig4;






import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;


public class SortedTreeMap<K extends Comparable<? super K>, V> implements ISortedTreeMap<K,V> {


    private int size;
    private Entry<K,V> data;
    private SortedTreeMap<K,V> parent, leftChild, rightChild;
    private Comparator<? super K> comparator;




    public SortedTreeMap(){

    }

    public SortedTreeMap(Comparator<? super K> comparator) {
        this.comparator = comparator;

    }



    public SortedTreeMap(Entry<K,V> data) {

        this.data = data;
        parent = this;
        size++;



    }

    public SortedTreeMap(SortedTreeMap<K,V> parent, SortedTreeMap<K,V> rightChild,
                         SortedTreeMap<K,V> leftChild)
    {
        this.parent = parent;
        this.rightChild = rightChild;
        this.leftChild = leftChild;
    }




    /**
     * Finds the minimum value in the map, if no value is found, returns null instead.
     *
     * @return minimum value
     */
    @Override
    public Entry<K, V> min() {
       SortedTreeMap<K,V> currentNode = parent;
       while (currentNode.getRightChild() != null){
           currentNode = currentNode.getRightChild();
       }
       return currentNode.getData();
    }


    /**
     * Finds the maximum value in the map, if no value is found returns null instead.
     *
     * @return maximum value
     */
    @Override
    public Entry<K, V> max() {
        SortedTreeMap<K,V> currentNode = parent;
        if (currentNode.getLeftChild() != null){
            currentNode = currentNode.getLeftChild();
        }
        return currentNode.getData();
    }


    /**
     * Inserts the specified value with the specified key as a new entry into the map.
     * If a value is already present for that key, return the previous value, else null.
     * @param key The key to be inserted
     * @param value The value to be inserted
     * @return Previous value
     */
    @Override
    public V add(K key, V value) {
        SortedTreeMap<K,V> newNode = new SortedTreeMap<>(new Entry<>(key, value));

        int compare = comparator.compare(newNode.data.key, parent.data.key);

        if(parent == null) {
            parent = newNode;
        }

            // If current is smaller than new
            if(compare < 0) {
                if(parent.getRightChild() == null) {
                    parent.setRightChild(newNode);
                    newNode.setParent(parent);
                }
                parent = parent.getRightChild();
            }
            // If current is bigger than new
            else if(compare > 0) {
                if(parent.getLeftChild() == null) {
                    parent.setLeftChild(newNode);
                    newNode.setParent(parent);
                }
                parent = parent.getLeftChild();
            }
            // If current is equal to new
       // newNode.data.key.compareTo(parent.data.key)
            else {
                V oldValue = parent.data.value;
                V newValue = value;




                return oldValue;
            }

            size++;
        return null;


        }





    /*
     * Inserts the specified entry into the map. If the key is already a part of the map,
     * return the previous value, else null.
     *
     * @param entry The new entry to be inserted into the map
     * @return Previous value
     */
    @Override
    public V add(Entry<K, V> entry) {
        return null;
    }

    /**
     * Replaces the value for key in the map as long as it is already present. If they key
     * is not present, the method throws an exception.
     *
     * @param key   The key for which the value is replaced
     * @param value The new value
     * @throws NoSuchElementException When key is not in map
     */
    @Override
    public void replace(K key, V value) throws NoSuchElementException {

    }

    /**
     * Applies a function to the value at key and replaces that value. Throws an exception
     * if the key is not present in the map.
     *
     * @param key The key for which we are replacing the value
     * @param f   The function to apply to the value
     * @throws NoSuchElementException When key is not in map
     */
    @Override
    public void replace(K key, BiFunction<K, V, V> f) throws NoSuchElementException {

    }

    public SortedTreeMap<K, V> getParent() {
        return parent;
    }

    public void setParent(SortedTreeMap<K, V> parent) {
        this.parent = parent;
    }

    public SortedTreeMap<K, V> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(SortedTreeMap<K, V> leftChild) {
        this.leftChild = leftChild;
    }

    public SortedTreeMap<K, V> getRightChild() {
        return rightChild;
    }

    public void setRightChild(SortedTreeMap<K, V> rightChild) {
        this.rightChild = rightChild;
    }

    /**
     * Removes the entry for key in the map. Throws an exception if the key is not present
     * in the map.
     *
     * @param key The key for the entry to remove
     * @return The removed value
     * @throws NoSuchElementException When key is not in map.
     */
    @Override
    public V remove(Object key) throws NoSuchElementException {
        return null;
    }

    /**
     * Retrieves the value for the key in the map.
     *
     * @param key The key for the value to retrieve
     * @return The value for the key
     * @throws NoSuchElementException When key is not in map
     */
    @Override
    public V getValue(Object key) throws NoSuchElementException {
        return null;
    }

    /**
     * Checks if a key is in the map.
     *
     * @param key The key to check
     * @return true if the key is in the map, false otherwise
     */
    @Override
    public boolean containsKey(K key) {
        return getEntry(key) != null;
    }

    /**
     * Checks if a value is in the map
     *
     * @param value the value to look for
     * @return True if the value is present, false otherwise
     */
    @Override
    public boolean containsValue(V value) {
           return false;
    }


    /**
     * Finds all the keys in the map and returns them in order.
     *
     * @return keys in order
     */
    @Override
    public Iterable<K> keys() {
        return null;
    }

    /**
     * Finds the values in order of the keys.
     *
     * @return values in order of the keys
     */
    @Override
    public Iterable<V> values() {
        return null;
    }

    /**
     * Finds all entries in the map in order of the keys.
     *
     * @return All entries in order of the keys
     */
    @Override
    public Iterable<Entry<K, V>> entries() {
        return null;
    }

    /**
     * Finds the entry for the key, if the key is not in the map returns the next
     * highest entry if such an entry exists
     *
     * @param key The key to find
     * @return The entry for the key or the next highest
     */
    @Override
    public Entry<K, V> higherOrEqualEntry(K key) {
        return null;
    }

    /**
     * Finds the entry for the key, if the key is not in the map, returns the next
     * lower entry if such an entry exists
     *
     * @param key The key to find
     * @return The entry for the key or the next lower
     */
    @Override
    public Entry<K, V> lowerOrEqualEntry(K key) {
        return null;
    }

    /**
     * Adds all entries in the other map into the current map. If a key is present
     * in both maps, the key in the other map takes precedent.
     * @param other The map to add to the current map.
     */
    @Override
    public void merge(ISortedTreeMap<K, V> other) {

    }



    /**
     * Removes any entry for which the predicate holds true. The predicate can
     * trigger on both the key and value of each entry.
     *
     * @param p The predicate that tests which entries should be kept.
     */
    @Override
    public void removeIf(BiPredicate<K, V> p) {

    }

    /**
     * Checks if the map is empty
     *
     * @return True if the map is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0 && parent == null;
    }

    /**
     * Returns the number of entries in the map
     *
     * @return Number of entries
     */
    @Override
    public int size() {
        return size;
    }

    private Entry<K, V> getData() {
        return data;
    }

    private void setData(Entry<K, V> data) {
        this.data = data;
    }

    /**
     * Clears the map of entries.
     */
    @Override
    public void clear() {
        if (!isEmpty()){
            if (parent.getLeftChild() != null){
                parent.setLeftChild(null);
            }
            if(parent.getRightChild() != null){
                parent.setLeftChild(null);
            }if (parent != null){
                parent =null;
            }
            setData(null);
            size = 0;

        }
    }


    private K getEntry(K key){
        if (this.parent == null){
            return null;
        }
        K entryKey = data.key;
        if (key.equals(entryKey)){
            return entryKey;
        }
        if (key.compareTo(entryKey) < 0){
            return this.parent.getLeftChild().getEntry(key);
        }

        return this.parent.getLeftChild().getEntry(key);

    }


}



