package eds;

public class TreeIteratorClass<K,V> implements TreeIterator<K, V> {
	
	private BSTNode<K,V> root;
	private Stack<BSTNode<K,V>> stack;
	
	
	
	
	
	public TreeIteratorClass(BSTNode<K,V>root) throws EmptyStackException, EmptyListException{
		this.root = root;
		stack = new StackInList<BSTNode<K, V>>();
		rewind();
		
	}
	
	
	public boolean hasNext() throws EmptyStackException, EmptyListException{
		return !stack.isEmpty();
		
	}
	
	private void rewind() throws EmptyStackException, EmptyListException{
		stack.clear();
		stack.push(root);
		fill();
	}
	
	
	
	public BSTNode<K,V> next() throws EmptyStackException, EmptyListException{
		
		BSTNode<K,V> next = stack.pop();
		if(next.getLeft() !=null){
			stack.push(next.getLeft());
			fill();
		}
			return next;
	}

	private void fill() throws EmptyStackException, EmptyListException{
		while(stack.top().getRight() != null){
			stack.push(stack.top().getRight());
		}
	}
	
}
