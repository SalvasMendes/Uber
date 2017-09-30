package eds;

public interface Node<E> {

	Node<E> getNext();
	Node<E> getPrevious();
	void setPrevious(Node<E> node);
	E getObject();
	void setNext(Node<E> node);
	
}