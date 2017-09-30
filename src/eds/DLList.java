package eds;

public interface DLList<E> {

	void addFirst(E object);

	void addLast(E object);

	void addMiddle(E object, int pos) throws InvalidPositionException;

	void add(E object, int pos) throws InvalidPositionException;

	E getFirst() throws EmptyListException;

	E getLast() throws EmptyListException;

	E get(int n) throws  InvalidPositionException;

	Node<E> getNode(int pos) throws InvalidPositionException;

	void append(DLList<E> list) throws EmptyListException, InvalidPositionException;

	int find(E element);

	E removeHead();

	E removeTail();

	E remove(int pos) throws EmptyListException, InvalidPositionException;

	int getSize();

	boolean isEmpty();
	
	boolean remove(E element) throws InvalidPositionException;

}