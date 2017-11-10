package eds;

import java.util.NoSuchElementException;

/**
 * 
 * @author Salvador Mendes (50503) sr.mendes@campus.fct.unl.pt
 * @author Miguel Candeias (50647) mb.candeias@campus.fct.unl.pt
 *
 * @param <K>
 * @param <V>
 */
public interface LBList<K, V> {

	/**
	 * Adds an element in the end of the bucket list
	 * 
	 * @param key
	 * @param value
	 */
	void addLast(K key, V value);

	/**
	 * This method returns an object in the bucket list given its key
	 * 
	 * @param key
	 * @return
	 * @throws InvalidPositionException
	 */
	V get(K key) throws InvalidPositionException;

	/**
	 * This mehtod returns a bucket object in the bucket list, given its
	 * position in it
	 * 
	 * @param pos
	 * @return
	 * @throws InvalidPositionException
	 */
	Bucket<K, V> getBucket(int pos) throws InvalidPositionException;

	/**
	 * This method connects 2 given lists, it works by changing the pointers to
	 * the head and tail of the given lists
	 * 
	 * @param list
	 * @throws EmptyListException
	 * @throws InvalidPositionException
	 */
	void append(LBList<K, V> list) throws EmptyListException, InvalidPositionException;

	/**
	 * This method returns the position of the element in the list, by giving it
	 * its key
	 * 
	 * @param key
	 * @return
	 */
	int findKey(K key);

	/**
	 * This method removes the current head object of the list
	 * 
	 * @return head object if exists
	 */
	V removeHead();

	/**
	 * This method removes the current tail object of the list
	 * 
	 * @return tail object if exists
	 */
	V removeTail();

	/**
	 * 
	 * @param key
	 * @return the removed object
	 * @throws InvalidPositionException
	 */
	V remove(K key) throws InvalidPositionException;

	/**
	 * This method allows to swap two nodes in the bucket list, only created to
	 * allow the quick sort algorithm to work
	 * 
	 * @param a
	 * @param b
	 * @throws InvalidPositionException
	 */
	void swapNode(int a, int b) throws InvalidPositionException;

	/**
	 * 
	 * @return size of the bucket list
	 */
	int getSize();

	/**
	 * 
	 * @return true in case the list is empty, false in case it is not
	 */
	boolean isEmpty();

	/**
	 * 
	 * @return An iterator of the bucket list
	 * @throws InvalidPositionException
	 */
	LBListIterator<K, V> iterator() throws InvalidPositionException;

	/**
	 * This method inserts a new object in the bucket list, sorted by the keys
	 * 
	 * @param key
	 * @param value
	 * @throws NoSuchElementException
	 * @throws InvalidPositionException
	 */
	void orderedAdd(K key, V value) throws NoSuchElementException, InvalidPositionException;

}