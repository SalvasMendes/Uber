package eds;

import java.util.NoSuchElementException;

/**
 * 
 * @author Salvador Mendes (50503) sr.mendes@campus.fct.unl.pt
 * @author Miguel Candeias (50647) mb.candeias@campus.fct.unl.pt
 *
 *
 */
public class LBListIteratorClass <K, V> implements LBListIterator<K, V>, java.io.Serializable {

	private static final long serialVersionUID = 657L;
	
	protected Bucket<K, V> head, current;


	public LBListIteratorClass(Bucket<K, V> head) throws InvalidPositionException {
		current = head;
		this.head = current;
	}
	public boolean hasNext() {
		return (current != null);
	}

	public Bucket<K,V> next() throws NoSuchElementException {
		Bucket<K,V> element = current;
		current = current.getNext();

		return element;
	}

	public void rewind() {
		current = head;

	}

}
