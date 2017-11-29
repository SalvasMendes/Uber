package eds;

public interface TreeIterator2<K, V> {

	boolean hasNext() throws EmptyStackException, EmptyListException;

	BSTNode<K, V> next() throws EmptyStackException, EmptyListException;

}