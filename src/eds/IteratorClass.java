package eds;

import java.util.NoSuchElementException;

public class IteratorClass<E> implements Iterator<E> {

	protected Node<E> current, head;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IteratorClass(Node<E>head) throws InvalidPositionException {
		current = head;
		head = current;
	}

	public boolean hasNext() {
		return (current != null);
	}

	public E next() throws NoSuchElementException {
		E element = current.getObject();
		current = current.getNext();

		return element;
	}

	
	public void rewind() {
		current = head;

	}

}
