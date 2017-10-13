package eds;

/**
 * 
 * @author Salvador Mendes (50503) sr.mendes@campus.fct.unl.pt
 * @author Miguel Candeias (50647) mb.candeias@campus.fct.unl.pt
 *
 */
public interface Node<E> {

	/**
	 * 
	 * @return Next node
	 */
	Node<E> getNext();

	/**
	 * 
	 * @return Previous Node
	 */
	Node<E> getPrevious();

	/**
	 * Set the node with a previous one
	 * 
	 * @param
	 */
	void setPrevious(Node<E> node);

	/**
	 * 
	 * @return The element in the node
	 */
	E getObject();

	/**
	 * Set the node with a next one
	 * 
	 * @param node
	 */
	void setNext(Node<E> node);

}