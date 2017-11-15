package eds;

import java.io.Serializable;

public class BinaryTNode<K, V> implements Serializable, BinaryTNodeInterface<K, V>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 0L;

	private Bucket<K, V> bucket;
	private BinaryTNodeInterface<K, V> leftChild;
	private BinaryTNodeInterface<K, V> rightChild;

	public BinaryTNode(K key, V value, BinaryTNodeInterface<K, V> left, BinaryTNodeInterface<K, V> right) {

		bucket = new BucketClass<K, V>(key, value);
		leftChild = left;
		rightChild = right;
	}

	public Bucket<K, V> getBucket() {
		return bucket;
	}
	
	public K getKey(){
		return bucket.getKey();
	}

	public V getValue(){
		return bucket.getObject();
	}
	
	public void setValue(V value){
		bucket.setValue(value);
	}
	
	public void setBucket(Bucket<K, V> bucket){
		this.bucket = bucket;
	}
	
	public BinaryTNodeInterface<K, V> getRightChild(){
		return rightChild;
	}
	
	public BinaryTNodeInterface<K, V> getLeftChild(){
		return leftChild;
	}
	
	public void setRightChild(BinaryTNodeInterface<K, V> node){
		rightChild = node;
	}
	
	public void setLeftChild(BinaryTNodeInterface<K, V> node){
		leftChild = node;
	}
	
	public boolean isLeaf(){
		return ((leftChild == null) && (rightChild == null));
	}
}
