package eds;

/**
 * 
 * @author Salvador Mendes (50503) sr.mendes@campus.fct.unl.pt
 * @author Miguel Candeias (50647) mb.candeias@campus.fct.unl.pt
 *
 */
public class NodeClass<E> implements Node<E>, java.io.Serializable {

	private static final long serialVersionUID = 657L;
	private Node<E> next;
	private Node<E> previous;
	private E object;

	public NodeClass(Node<E> next, E object, Node<E> previous) {
		this.next = next;
		this.object = object;
		this.previous = previous;

	}

	public Node<E> getNext() {
		return next;
	}

	public void setNext(Node<E> node) {
		next = node;
	}

	public void setPrevious(Node<E> node) {
		previous = node;
	}

	public Node<E> getPrevious() {
		return previous;
	}

	public E getObject() {
		return object;
	}

}
