package eds;

/**
 * 
 * @author Salvador Mendes (50503) sr.mendes@campus.fct.unl.pt
 * @author Miguel Candeias (50647) mb.candeias@campus.fct.unl.pt
 *
 *
 */

public class BucketClass<K, V> implements java.io.Serializable, Bucket<K, V> {

	private static final long serialVersionUID = 657L;
	private Bucket<K, V> next;
	private Bucket<K, V> previous;
	private K key;
	private V value;

	public BucketClass(Bucket<K, V> next, K key, V value, Bucket<K, V> previous) {
		this.next = next;
		this.key = key;
		this.value = value;
		this.previous = previous;

	}
	
	public BucketClass(K key, V value) {
		this.key = key;
		this.value = value;
		
	}
	
	public void setValue(V value){
		this.value = value;
	}

	public Bucket<K, V> getNext() {
		return next;
	}

	public void setNext(Bucket<K, V> bucket) {
		next = bucket;
	}

	public void setPrevious(Bucket<K, V> bucket) {
		previous = bucket;
	}

	public Bucket<K, V> getPrevious() {
		return previous;
	}

	public V getObject() {
		return value;
	}

	public K getKey() {
		return key;
	}

	
	public void setKey(K key) {
		this.key = key;
		
	}

}
