package eds;

public interface Map<K, V> {

	void add(K key, V value) throws InvalidPositionException;

	V remove(K key) throws InvalidPositionException;
}