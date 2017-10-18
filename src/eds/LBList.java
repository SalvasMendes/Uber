package eds;

public interface LBList<K, V> {

	void addLast(K key, V value);

	V get(int n) throws InvalidPositionException;

	Bucket<K, V> getBucket(int pos) throws InvalidPositionException;

	void append(LBList<K, V> list) throws EmptyListException, InvalidPositionException;

	int findKey(K key);

	V removeHead();

	V removeTail();

	boolean remove(K key) throws InvalidPositionException;

	void swapNode(int a, int b) throws InvalidPositionException;

	int getSize();

	boolean isEmpty();

}