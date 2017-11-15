package eds;

/**
 * 
 * @author Salvador Mendes (50503) sr.mendes@campus.fct.unl.pt
 * @author Miguel Candeias (50647) mb.candeias@campus.fct.unl.pt
 *
 *
 * @param <K>
 * @param <V>
 */
public interface Bucket<K, V> {

	/**
	 * 
	 * @return A reference to the next bucket
	 */
	public Bucket<K, V> getNext();

	void setValue(V value);

	/**
	 * Sets the next bucket
	 * 
	 * @param bucket
	 */
	public void setNext(Bucket<K, V> bucket);

	/**
	 * Sets the previous bucket
	 * 
	 * @param bucket
	 */
	public void setPrevious(Bucket<K, V> bucket);

	/**
	 * 
	 * @return A reference to the previous bucket
	 */
	public Bucket<K, V> getPrevious();

	/**
	 * 
	 * @return Object
	 */
	public V getObject();

	/**
	 * 
	 * @return Key
	 */
	public K getKey();

}
