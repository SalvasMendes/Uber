package eds;

import java.util.NoSuchElementException;

/**
 * 
 * @author Salvador Mendes (50503) sr.mendes@campus.fct.unl.pt
 * @author Miguel Candeias (50647) mb.candeias@campus.fct.unl.pt
 *
 *
 * @param <K>
 * @param <V>
 */
public interface HashTableIterator<K, V> {

	/**
	 * 
	 * @return true in case there is a next in the current iterator
	 */
	boolean hasNext();

	/**
	 * This method will return the current object in the iterator, and will get
	 * the next one and keep it
	 * 
	 * @return The saved object in the iterator
	 * @throws NoSuchElementException
	 * @throws InvalidPositionException
	 */
	Bucket<K, V> next() throws NoSuchElementException, InvalidPositionException;

	/**
	 * This method resets the iterator
	 * 
	 * @throws InvalidPositionException
	 */
	void rewind() throws InvalidPositionException;

}