package eds;

import java.util.NoSuchElementException;
/**
 * 
 * @author 50503_50647
 *
 * @param <E>
 */
public class TwoWayIteratorClass<E> extends IteratorClass<E> implements TwoWayIterator<E>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Node<E>tail;

	public TwoWayIteratorClass(Node<E>head, Node<E>tail) throws InvalidPositionException {
		super(head);
		this.tail = tail;
	}

	public boolean hasPrevious() {
		return (current != null);
	}

	public E previous() throws NoSuchElementException {
		E element = current.getObject();
		current = current.getPrevious();
		return element;
	}

	public void fullForward() {
		current = tail;
		
	}

	
}