package eds;

/**
 * 
 * @author Salvador Mendes (50503) sr.mendes@campus.fct.unl.pt
 * @author Miguel Candeias (50647) mb.candeias@campus.fct.unl.pt
 *
 * @param <K>
 * @param <V>
 */
public interface Map<K, V> {

	/**
	 * This method adds a new object in the hashtable, it works by giving a key
	 * and a value, in which the key is generated with the help of an hashing
	 * method
	 * 
	 * @param key
	 * @param value
	 * @throws InvalidPositionException
	 */
	void add(K key, V value) throws InvalidPositionException;
	
	TreeIterator2<K, V>iterator2() throws EmptyStackException, EmptyListException;

	/**
	 * This method removes an object in the hashtable by giving its key, this
	 * method works with the help of the hashing method
	 * 
	 * @param key
	 * @return
	 * @throws InvalidPositionException
	 */
	V remove(K key) throws InvalidPositionException;

	/**
	 * This method returns an object stored in the hashtable by giving it its
	 * key, this method works with the help of the hashing method
	 * 
	 * @param key
	 * @return
	 * @throws InvalidPositionException
	 */
	V find(K key) throws InvalidPositionException;
	
	/**
	 * 
	 * @return an iterator of the hashtable
	 * @throws InvalidPositionException
	 */
	HashTableIterator<K, V> iterate() throws InvalidPositionException;
	
	/**
	 * 
	 * @param key
	 * @return True in case the object exists in the list, false in case he does not
	 */
	boolean exists(K key);
	/**
	 * 
	 * @return the size of the hashtable
	 */
	int getSize();
	
	/**
	 * 
	 * @return the number of entries in the hashtable
	 */
	int nbr();
	
	TreeIterator<K,V> iterator( ) throws EmptyStackException, EmptyListException;
}