package eds;

import java.util.NoSuchElementException;

public class HashTableIteratorClass<K, V> implements LBListIterator<K, V>, java.io.Serializable, HashTableIterator<K, V> {

	/**
	* 
	*/
	private static final long serialVersionUID = 657L;

	private int current;
	private LBList<K, V>[] table;
	private LBListIterator<K, V> iterator;
	private boolean done;
	private Bucket<K, V> element;

	public HashTableIteratorClass(LBList<K, V>[] table) {
		this.table = table;
	}

	public boolean hasNext() {
		return iterator.hasNext();
	}

	public Bucket<K, V> next() throws NoSuchElementException, InvalidPositionException {
		element = iterator.next();

		if (element != null) {
			return element;
		} else {
			if (iterator.hasNext()) {
				getNext();
				return next();
			} else {
				throw new NoSuchElementException();
			}

		}

	}

	public void rewind() throws InvalidPositionException {
		current = 0;
		getNext();

	}

	private void getNext() throws InvalidPositionException {
		done = false;
		while (current <= (table.length - 1) && !done) {
			if (!table[current].isEmpty()) {
				iterator = table[current].iterator();
				done = true;
			}

			current++;
		}
	}

}
