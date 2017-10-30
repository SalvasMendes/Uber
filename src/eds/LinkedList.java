package eds;


/**
 * 
 * @author Salvador Mendes (50503) sr.mendes@campus.fct.unl.pt
 * @author Miguel Candeias (50647) mb.candeias@campus.fct.unl.pt
 *
 */
public class LinkedList<E> implements DLList<E>, java.io.Serializable {

	private static final long serialVersionUID = 657L;
	protected int size;
	protected Node<E> head;
	protected Node<E> tail;

	public LinkedList() {
		size = 0;
		head = null;
		tail = null;
	}

	/*
	 * 
	 * 
	 */

	public void addFirst(E object) {

		Node<E> newNode = new NodeClass<E>(head, object, null);
		if (size == 0) {
			head = newNode;
			tail = newNode;
			size++;
		} else {
			head.setPrevious(newNode);
			head = newNode;
			size++;
		}
	}

	public void addLast(E object) {

		Node<E> newNode = new NodeClass<E>(null, object, tail);
		if (size == 0) {
			head = newNode;
			tail = newNode;
			size++;
		} else {
			tail.setNext(newNode);
			tail = newNode;
			size++;
		}
	}

	public void addMiddle(E object, int pos) throws InvalidPositionException {

		if (pos < 0 || pos >= size)
			throw new InvalidPositionException();

		Node<E> prevNode = this.getNode(pos - 1);
		Node<E> nextNode = this.getNode(pos + 1);
		Node<E> newNode = new NodeClass<E>(nextNode, object, prevNode);

		prevNode.setNext(newNode);
		nextNode.setPrevious(newNode);
		size++;

	}

	public void add(E object, int pos) throws InvalidPositionException {

		if (pos < 0 || pos >= size) {
			throw new InvalidPositionException();

		}

		if (pos == 0) {
			this.addFirst(object);

		} else if (pos == size - 1) {

			this.addLast(object);
		}

		else {
			this.addMiddle(object, pos);
		}

	}

	public Node<E> getFirst() throws EmptyListException {

		if (size == 0) {
			throw new EmptyListException();
		}

		return head;
	}

	public Node<E> getLast() throws EmptyListException {

		if (size == 0) {
			throw new EmptyListException();
		}

		return tail;
	}

	public E get(int n) throws InvalidPositionException {
		if (n < 0 || n >= size) {
			throw new InvalidPositionException();
		} else {
			return getNode(n).getObject();
		}
	}

	public Node<E> getNode(int pos) throws InvalidPositionException {

		if (pos < 0 || pos >= size) {
			throw new InvalidPositionException();
		}

		Node<E> node;

		if (pos <= ((size - 1) / 2)) {
			node = head;

			for (int i = 0; i < pos; i++) {
				node = node.getNext();
			}
		} else {
			node = tail;

			for (int i = size - 1; i > pos; i--) {
				node = node.getPrevious();
			}
		}

		return node;

	}

	public void append(DLList<E> list) throws EmptyListException, InvalidPositionException {

		if (size == 0) {
			throw new EmptyListException();
		}

		tail.setNext(list.getNode(0));
		list.getNode(0).setPrevious(head);
		tail = list.getNode(getSize() - 1);
		size += list.getSize();

	}

	public int find(E element) {

		Node<E> node = head;
		int currentPos = 0;

		while (node != null && !node.getObject().equals(element)) {
			node.getNext();
			currentPos++;
		}
		if (node == null) {
			return -1;
		}
		return currentPos;
	}

	protected void removeFirst() {

		head = head.getNext();

		if (head == null) {
			tail = null;
			size--;
		} else {
			head.setPrevious(null);
			size--;
		}
	}

	public E removeHead() {
		E element = head.getObject();
		this.removeFirst();
		return element;

	}

	protected void removeLast() {
		tail = tail.getPrevious();
		if (tail == null) {
			head = null;
			size--;
		} else {
			tail.setNext(null);
			size--;
		}
	}

	public E removeTail() {
		E element = tail.getObject();
		this.removeLast();
		return element;
	}

	public E remove(int pos) throws EmptyListException, InvalidPositionException {
		if (size == 0) {
			throw new EmptyListException();
		}

		else if (pos < 0 || pos >= size)
			throw new InvalidPositionException();

		else {
			if (pos == 0)
				return this.removeHead();
			else if (pos == size - 1) {
				return this.removeTail();
			} else {
				Node<E> nodeToRemove = this.getNode(pos);
				this.removeMid(nodeToRemove);
				return nodeToRemove.getObject();
			}
		}

	}

	protected void removeMid(Node<E> midNode) {

		Node<E> prevNode = midNode.getPrevious();
		Node<E> nextNode = midNode.getNext();
		prevNode.setNext(nextNode);
		nextNode.setPrevious(prevNode);
		size--;

	}

	public boolean remove(E element) throws InvalidPositionException {

		int pos = this.find(element);
		if (pos == -1) {
			throw new InvalidPositionException();
		}
		Node<E> node = this.getNode(pos);
		if (node == null)
			return false;
		else {
			if (node == head)
				this.removeHead();
			else if (node == tail)
				this.removeTail();
			else
				this.removeMid(node);
		}
		return true;
	}

	public void swapNode(int a, int b) throws InvalidPositionException {

		Node<E> nodeB = this.getNode(b);
		Node<E> nodeA = this.getNode(a);

		Node<E> nodeAPrev = nodeA.getPrevious();
		Node<E> nodeANext = nodeA.getNext();
		Node<E> nodeBPrev = nodeB.getPrevious();
		Node<E> nodeBNext = nodeB.getNext();
		if (nodeANext == nodeB || nodeBPrev == nodeA) {
			nodeA.setNext(nodeBNext);
			nodeB.setNext(nodeA);
			nodeB.setPrevious(nodeAPrev);
			nodeA.setPrevious(nodeB);
		} else {
			nodeA.setNext(nodeBNext);
			nodeA.setPrevious(nodeBPrev);
			nodeB.setNext(nodeANext);
			nodeB.setPrevious(nodeAPrev);
		}
	}

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return (size == 0);
	}

}
