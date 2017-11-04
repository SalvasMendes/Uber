package eds;

import java.util.NoSuchElementException;

public class HashTableIteratorClass<K, V>
		implements LBListIterator<K, V>, java.io.Serializable, HashTableIterator<K, V> {

	private static final long serialVersionUID = 657L;

	private int current;
	private LBList<K, V>[] table;
	private LBListIterator<K, V> iterator;
	private boolean done;
	private Bucket<K, V> element;

	public HashTableIteratorClass(LBList<K, V>[] table) throws InvalidPositionException {
		this.table = table;
		this.rewind();
	}

	public boolean hasNext() {
		return iterator.hasNext();
	}

	public Bucket<K, V> next() throws InvalidPositionException, NoSuchElementException {

		element = iterator.next();

		if (element == null) {
			throw new NoSuchElementException();
		}
		if (!(iterator.hasNext())) {
			getNext();
		}
		return element;
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
