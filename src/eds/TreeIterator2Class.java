package eds;

public class TreeIterator2Class<K, V> implements TreeIterator2<K, V> {
	private BSTNode<K,V> root;
	private Stack<BSTNode<K,V>> stack;
	
	
	
	
	
	public TreeIterator2Class(BSTNode<K,V>root) throws EmptyStackException, EmptyListException{
		this.root = root;
		stack = new StackInList<BSTNode<K, V>>();
		rewind();
		
	}

	public boolean hasNext() throws EmptyStackException, EmptyListException {
		return !stack.isEmpty();

	}

	private void rewind() throws EmptyStackException, EmptyListException {
		stack.clear();
		stack.push(root);
		fill();
	}

	public BSTNode<K, V> next() throws EmptyStackException, EmptyListException {

		BSTNode<K, V> next = stack.pop();

		if (next.getRight() != null) {
			stack.push(next.getRight());
			fill();
		}

		return next;
	}

	private void fill() throws EmptyStackException, EmptyListException {
		while (stack.top().getLeft() != null) {
			stack.push(stack.top().getLeft());
		}
	}

}
