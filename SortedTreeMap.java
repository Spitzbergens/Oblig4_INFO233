package no.uib.info233v18.oblig4;






import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;


public class SortedTreeMap<K extends Comparable<? super K>, V> implements ISortedTreeMap<K,V> {


    private int size;
    private Entry<K, V> data;
    private Node<K, V> root;
    private Comparator<? super K> comparator;
    private ArrayList<K> keyList = new ArrayList<>();
    private ArrayList<V> valueList = new ArrayList<>();
    private ArrayList<Entry<K, V>> entryList = new ArrayList<>();


    public SortedTreeMap() {
        this(null);
    }

    public SortedTreeMap(Comparator<K> comparator) {
        this.comparator = comparator;
        Entry<K, V> data = null;
        Node<K, V> root = null;
        size = 0;

    }


    /**
     * Finds the minimum value in the map, if no value is found, returns null instead.
     *
     * @return minimum value
     */
    @Override
    public Entry<K, V> min() {
        if (!isEmpty()) {
            Node<K, V> current = root;
            while (current.hasLeft()) {
                current = current.getLeft();
            }
            return current.getData();
        } else {
            return null;
        }
    }

    private Node<K,V> min(Node<K,V> node){
        if (isEmpty()){
           Node<K,V> min = node;
        while (node.hasLeft()){
            min = node.getLeft();
            node = node.getLeft();
        }
            return min;
        }else{
            return null;
        }

    }


    /**
     * Finds the maximum value in the map, if no value is found returns null instead.
     *
     * @return maximum value
     */
    @Override
    public Entry<K, V> max() {
        if (!isEmpty()) {
            Node<K, V> current = root;
            while (current.hasRight()) {
                current = current.getRight();
            }
            return current.getData();
        } else {
            return null;
        }
    }


    /**
     * Inserts the specified value with the specified key as a new entry into the map.
     * If a value is already present for that key, return the previous value, else null.
     *
     * @param key   The key to be inserted
     * @param value The value to be inserted
     * @return Previous value
     */
    @Override
    public V add(K key, V value) {
        Node<K, V> node = new Node<>(new Entry<>(key, value));
        if (root == null) {
            root = node;
            size++;
            return null;
        } else {
            // If current is smaller than new
            Node<K, V> currentParent = root;
            while (true) {
                if (node.data.key.compareTo(currentParent.data.key) > 0) {
                    if (currentParent.getRight() == null) {
                        currentParent.right = node;
                        node.setParent(currentParent);
                        size++;
                        return null;
                    }
                    currentParent = currentParent.getRight();
                }

                // If current is bigger than new
                else if (node.data.key.compareTo(currentParent.data.key) < 0) {
                    if (currentParent.getLeft() == null) {
                        currentParent.left = node;
                        node.setParent(currentParent);
                        size++;
                        return null;
                    }
                    currentParent = currentParent.getLeft();
                }

                // If current is equal to new
                // newNode.data.key.compareTo(parent.data.key)
                if (currentParent.data.key.compareTo(node.data.key) == 0) {
                    V oldValue = currentParent.data.value;
                    currentParent.data = node.data;
                    return oldValue;
                }
            }

        }

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
        Node<K, V> node = new Node<>(entry);
        V result = null;
        if (root == null) {
            root = node;
            size++;
        } else {
            result = add(entry.key, entry.value);
        }
        return result;
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

        Node<K, V> replace = getEntry(key, root);
        if (replace == null) {
            throw new NoSuchElementException();
        } else {
            add(key, value);
        }
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

        Node<K, V> node = getEntry(key, root);
        if (node == null) {
            throw new NoSuchElementException();
        }

        node.setData(new Entry<>(key, f.apply(key, node.getData().value)));
        add(node.data);
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

        Node<K,V> result = getEntry((K)key, root);
        root = remove(root, (K)key);
        size--;
        return result.data.value;

    }


    public Node<K,V> remove(Node<K, V> node, K key) {

                if (node == null){
            return null;
        }

        if (node.left == null && node.right == null) {
            node = null;
        }else if (node.parent == null){
            node = null;
        }
        else {

            if (node.data.key.compareTo(key) < 0) {
                node.left = remove(node.left, key);
            } else if (node.data.key.compareTo(key) > 0) {
                node.right = remove(node.right, key);
            } else {
                if (node.left != null && node.right != null) {
                    Node<K, V> left = node.left;
                    Node<K, V> right = node.right;

                    node = removeMin(node.right, node);

                    Node<K, V> minRight = node.right;

                    node.left = left;
                    node.right = right;

                    if (node.right.data == node.data) {
                        node.right = minRight;
                    }
                } else if (node.left == null && node.right == null) {
                    node = null;
                } else if (node.left != null) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
        }
            return node;

    }




    public Node<K,V> removeMin(Node<K,V> root, Node<K,V> parent)
    {
        if (root == null)
        {
            return null;
        }

        if (root.left == null)
        {
            if (parent != root)
            {
                parent.left = root.right;
            }

            return root;
        }
        return removeMin(root.left, root);
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

        Node<K,V> result = getEntry((K) key, root);
        if (result == null){
            throw new NoSuchElementException();
        }else{
            return result.getData().value;
        }

    }

    private Node<K,V> getEntry(K key, Node<K,V> node){

        if (node == null){
            return null;
        }else {
            if (key.compareTo(node.data.key) < 0) {
                node = node.left;
                node = getEntry(key, node);
            } else if (key.compareTo(node.data.key) > 0) {
                node = node.right;
                node = getEntry(key, node);
            }
        }
        return node;
    }


    /**
     * Checks if a key is in the map.
     *
     * @param key The key to check
     * @return true if the key is in the map, false otherwise
     */
    @Override
    public boolean containsKey(K key) {
        return getEntry(key, root) != null;
    }

    /**
     * Checks if a value is in the map
     *
     * @param value the value to look for
     * @return True if the value is present, false otherwise
     */
    @Override
    public boolean containsValue(V value) {
           Iterable<V> values = values();
           Iterator<V> valuesIterator = values.iterator();
           while(valuesIterator.hasNext()){
               if (valuesIterator.next().equals(value)){
                   return true;
               }
           }
           return false;
    }


    /**
     * Finds all the keys in the map and returns them in order.
     *
     * @return keys in order
     */
    @Override
    public Iterable<K> keys() {
      addKeys(root);
      return keyList;
    }

    private void addKeys(Node<K,V> node){
        if (node != null){
            addKeys(node.left);
            keyList.add(node.data.key);
            addKeys(node.right);
        }
    }

    /**
     * Finds the values in order of the keys.
     *
     * @return values in order of the keys
     */
    @Override
    public Iterable<V> values() {
        addValues(root);
        return valueList;
    }

    private void addValues(Node<K,V> node){
        if (node != null){
            addValues(node.left);
            valueList.add(node.data.value);
            addValues(node.right);
        }
    }

    /**
     * Finds all entries in the map in order of the keys.
     *
     * @return All entries in order of the keys
     */
    @Override
    public Iterable<Entry<K, V>> entries() {
       addEntries(root);
       return entryList;
    }


    private void addEntries(Node<K,V> node){


        if (node != null){
            addEntries(node.left);
            entryList.add(node.data);
            addEntries(node.right);
        }
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

        for(Entry<K,V> entry : other.entries()){
            add(entry);
        }

    }



    /**
     * Removes any entry for which the predicate holds true. The predicate can
     * trigger on both the key and value of each entry.
     *
     * @param p The predicate that tests which entries should be kept.
     */
    @Override
    public void removeIf(BiPredicate<K, V> p) {

        for (Entry<K,V> entry : entries()){
            if (p.test(entry.key, entry.value)){
                remove(entry.key);
            }
        }


    }

    /**
     * Checks if the map is empty
     *
     * @return True if the map is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0 && root == null;
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
            data = null;
            root = null;
            size = 0;

    }




    private class Node<K,V>  {

        private Entry<K,V> data;
        private Node<K,V> parent;
        private Node<K,V> left;
        private Node<K,V> right;



        public Node(){
            this(null);
        }

        public Node(Entry<K,V> data){
            this(data, null, null, null);
        }

        public Node(Entry<K,V> data, Node<K,V> left, Node<K,V> right, Node<K,V> parent){
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public Entry<K, V> getData() {
            return data;
        }

        public Node<K, V> getParent() {
            return parent;
        }

        public void setParent(Node<K, V> parent) {
            this.parent = parent;
        }

        public void setData(Entry<K, V> data) {
            this.data = data;
        }

        public Node<K, V> getLeft() {
            return left;
        }

        public void setLeft(Node<K, V> left) {
            this.left = left;
        }

        public Node<K, V> getRight() {
            return right;
        }

        public void setRight(Node<K, V> right) {
            this.right = right;
        }

        public boolean hasLeft(){
            return getLeft() != null;
        }

        public boolean hasRight(){
            return getRight() != null;
        }
    }


}



