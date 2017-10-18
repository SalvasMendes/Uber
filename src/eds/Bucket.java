package eds;

public interface Bucket<K, V> {

	public Bucket<K, V> getNext();

	public void setNext(Bucket<K, V> bucket);

	public void setPrevious(Bucket<K, V> bucket);

	public Bucket<K, V> getPrevious();

	public V getObject();

	public K getKey();

}
