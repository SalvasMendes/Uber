package eds;

/**
 * 
 * @author Salvador Mendes (50503) sr.mendes@campus.fct.unl.pt
 * @author Miguel Candeias (50647) mb.candeias@campus.fct.unl.pt
 *
 */
public interface DLList<E> {

	/**
	 * Adds an object to the head
	 * 
	 * @param object
	 */
	void addFirst(E object);

	/**
	 * Adds an object to the tail
	 * 
	 * @param object
	 */
	void addLast(E object);

	/**
	 * Adds an obejct in a certain position of the list
	 * 
	 * @param object
	 * @param pos
	 * @throws InvalidPositionException
	 */
	void addMiddle(E object, int pos) throws InvalidPositionException;

	/**
	 * Adds an obejct in a certain position of the list, mid, head or tail
	 * 
	 * @param object
	 * @param pos
	 * @throws InvalidPositionException
	 */
	void add(E object, int pos) throws InvalidPositionException;

	/**
	 * 
	 * @return Head node
	 * @throws EmptyListException
	 */
	Node<E> getFirst() throws EmptyListException;

	/**
	 * 
	 * @return Tail Node
	 * @throws EmptyListException
	 */
	Node<E> getLast() throws EmptyListException;

	/**
	 * Returns an element of the list given a position in it
	 * 
	 * @param n
	 * @return
	 * @throws InvalidPositionException
	 */
	E get(int n) throws InvalidPositionException;

	/**
	 * 
	 * @param pos
	 * @return A node given its position
	 * @throws InvalidPositionException
	 */
	Node<E> getNode(int pos) throws InvalidPositionException;

	/**
	 * This method connects two given lists, and turn them in just one list with
	 * head and tail
	 * 
	 * @param list
	 * @throws EmptyListException
	 * @throws InvalidPositionException
	 */
	void append(DLList<E> list) throws EmptyListException, InvalidPositionException;

	/**
	 * 
	 * @param element
	 * @return the position of a given element in the list
	 */
	int find(E element);

	/**
	 * 
	 * @return Head element, and removes the head.
	 */
	E removeHead();

	/**
	 * 
	 * @return Tail element, and removes the tail
	 */
	E removeTail();

	/**
	 * 
	 * @param pos
	 * @return A given element, and removes the given position one
	 * @throws EmptyListException
	 * @throws InvalidPositionException
	 */
	E remove(int pos) throws EmptyListException, InvalidPositionException;

	/**
	 * 
	 * @return Size of the list
	 */
	int getSize();

	/**
	 * 
	 * @return True in case list is empty, false in case it is not
	 */
	boolean isEmpty();

	/**
	 * 
	 * @param element
	 * @return True in case element can be removed
	 * @throws InvalidPositionException
	 */
	boolean remove(E element) throws InvalidPositionException;

	/**
	 * This method allows to swap two nodes, given its positions It is used in
	 * the QuickSort class
	 * 
	 * @param a
	 * @param b
	 * @throws InvalidPositionException
	 * @throws EmptyListException 
	 */
	void swapNode(int a, int b) throws InvalidPositionException;
	
	TwoWayIterator<E>twoWayIterator() throws InvalidPositionException;
	
	

}