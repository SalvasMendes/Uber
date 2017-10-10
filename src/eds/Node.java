package eds;

/**
 * 
 * @author 50503_50647
 *
 * @param <E>
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