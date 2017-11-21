package eds;

public interface TreeIterator<K, V> {

	boolean hasNext() throws EmptyStackException, EmptyListException;

	BSTNode<K, V> next() throws EmptyStackException, EmptyListException;

}