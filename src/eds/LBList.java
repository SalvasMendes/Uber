package eds;

import java.util.NoSuchElementException;

public interface LBList<K, V> {

	void addLast(K key, V value);

	V get(K key) throws InvalidPositionException;

	Bucket<K, V> getBucket(int pos) throws InvalidPositionException;

	void append(LBList<K, V> list) throws EmptyListException, InvalidPositionException;

	int findKey(K key);

	V removeHead();

	V removeTail();

	V remove(K key) throws InvalidPositionException;

	void swapNode(int a, int b) throws InvalidPositionException;

	int getSize();

	boolean isEmpty();
	
	LBListIterator<K,V> iterator() throws InvalidPositionException;
	
	void orderedAdd(K key, V value) throws NoSuchElementException, InvalidPositionException;

}