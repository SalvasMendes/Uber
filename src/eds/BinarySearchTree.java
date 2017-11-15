package eds;

public class BinarySearchTree<K extends Comparable<K>, V> {

	protected BinaryTNodeInterface<K, V> root;

	protected int currentSize;

	protected static class PathStep<K, V> {

		protected BinaryTNodeInterface<K, V> parent;
		protected boolean isLeftChild;

		public PathStep(BinaryTNodeInterface<K, V> parent, boolean toTheleft) {
			this.parent = parent;
			this.isLeftChild = toTheleft;
		}

		public void setPath(BinaryTNodeInterface<K, V> parent, boolean toTheleft) {
			this.parent = parent;
			this.isLeftChild = toTheleft;
		}

	}

	public BinarySearchTree() {
		root = null;
		currentSize = 0;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public V insert(K key, V value) {

		PathStep<K, V> step = new PathStep<K, V>(null, false);
		BinaryTNodeInterface<K, V> node = this.searchNoderino(key, step);

		if (node == null) {
			BinaryTNodeInterface<K, V> newNode = new BinaryTNode<K, V>(key, value, null, null);
			this.linkSubtree(newNode, step);
			currentSize++;
			return null;

		} else {
			V oldValue = node.getValue();
			node.setValue(value);
			return oldValue;

		}
	}

	protected BinaryTNodeInterface<K, V> findNode(BinaryTNodeInterface<K, V> node, K key) {
		if (node == null) {
			return null;
		} else {
			int result = key.compareTo(node.getKey());

			if (result == 0)
				return node;

			else if (result < 0)
				return findNode(node.getLeftChild(), key);

			else
				return findNode(node.getRightChild(), key);
		}
	}

	protected BinaryTNodeInterface<K, V> searchNoderino(K key, PathStep<K, V> lastStep) {

		BinaryTNodeInterface<K, V> node = root;

		while (node != null) {
			int result = key.compareTo(node.getKey());

			if (result == 0)
				return node;

			else if (result < 0) {
				lastStep.setPath(node.getLeftChild(), true);
				node = node.getLeftChild();
			} else {
				lastStep.setPath(node.getRightChild(), false);
				node = node.getRightChild();
			}
		}

		return null;
	}

	public V find(K key) {

		BinaryTNodeInterface<K, V> node = this.findNode(root, key);

		if (node == null) {
			return null;
		} else {
			return node.getValue();
		}

	}

	protected void linkSubtree(BinaryTNodeInterface<K, V> node, PathStep<K, V> lastStep) {
		if (lastStep.parent == null) {
			this.root = node;

		} else {
			if (lastStep.isLeftChild) {
				node.setLeftChild(node);
			} else {
				node.setRightChild(node);
			}
		}
	}

}
