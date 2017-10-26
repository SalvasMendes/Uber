package eds;

import java.util.NoSuchElementException;

public interface HashTableIterator<K, V> {

	boolean hasNext();

	Bucket<K, V> next() throws NoSuchElementException, InvalidPositionException;

	void rewind() throws InvalidPositionException;

}