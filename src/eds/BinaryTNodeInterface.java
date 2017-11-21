package eds;

public interface BinaryTNodeInterface<K, V> {

	Bucket<K, V> getBucket();

	K getKey();

	V getValue();

	void setValue(V value);

	void setBucket(Bucket<K, V> bucket);

	BinaryTNodeInterface<K, V> getRightChild();

	BinaryTNodeInterface<K, V> getLeftChild();

	void setRightChild(BinaryTNodeInterface<K, V> node);

	void setLeftChild(BinaryTNodeInterface<K, V> node);

	boolean isLeaf();
	
}