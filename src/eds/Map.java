package eds;

public interface Map<K, V> {

	void add(K key, V value);

	void remap() throws InvalidPositionException;

}