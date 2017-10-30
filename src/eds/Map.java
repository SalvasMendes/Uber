package eds;

public interface Map<K, V> {

	void add(K key, V value) throws InvalidPositionException;

	V remove(K key) throws InvalidPositionException;

	V find(K key) throws InvalidPositionException;

	HashTableIterator<K, V> iterate() throws InvalidPositionException;

}