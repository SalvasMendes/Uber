package eds;

public class LinkedBucketList<K, V> implements java.io.Serializable, LBList<K, V> {

	private static final long serialVersionUID = 657L;
	protected int size;
	protected Bucket<K, V> head;
	protected Bucket<K, V> tail;

	public LinkedBucketList() {
		size = 0;
		head = null;
		tail = null;
	}

	/* (non-Javadoc)
	 * @see eds.LBList#addLast(K, V)
	 */
	public void addLast(K key, V value) {

		Bucket<K, V> newBucket = new BucketClass<K, V>(head, key, value, null);
		if (size == 0) {
			head = newBucket;
			tail = newBucket;
			size++;
		} else {
			tail.setNext(newBucket);
			tail = newBucket;
			size++;
		}
	}

	/*
	 * public Bucket<K, V> getFirst() throws EmptyListException {
	 * 
	 * if (size == 0) { throw new EmptyListException(); }
	 * 
	 * return head; }
	 * 
	 * public Bucket<K, V> getLast() throws EmptyListException {
	 * 
	 * if (size == 0) { throw new EmptyListException(); }
	 * 
	 * return tail; }
	 */

	/* (non-Javadoc)
	 * @see eds.LBList#get(int)
	 */
	public V get(int n) throws InvalidPositionException {
		if (n < 0 || n >= size) {
			throw new InvalidPositionException();
		} else {
			return getBucket(n).getObject();
		}
	}

	/* (non-Javadoc)
	 * @see eds.LBList#getBucket(int)
	 */
	public Bucket<K, V> getBucket(int pos) throws InvalidPositionException {

		if (pos < 0 || pos >= size) {
			throw new InvalidPositionException();
		}

		Bucket<K, V> bucket;

		if (pos <= ((size - 1) / 2)) {
			bucket = head;

			for (int i = 0; i < pos; i++) {
				bucket = bucket.getNext();
			}
		} else {
			bucket = tail;

			for (int i = size - 1; i > pos; i--) {
				bucket = bucket.getPrevious();
			}
		}

		return bucket;

	}

	/* (non-Javadoc)
	 * @see eds.LBList#append(eds.LBList)
	 */
	public void append(LBList<K, V> list) throws EmptyListException, InvalidPositionException {

		if (size == 0) {
			throw new EmptyListException();
		}

		tail.setNext(list.getBucket(0));
		list.getBucket(0).setPrevious(head);
		tail = list.getBucket(getSize() - 1);

	}

	/* (non-Javadoc)
	 * @see eds.LBList#findKey(K)
	 */
	public int findKey(K key) {

		Bucket<K, V> bucket = head;
		int currentPos = 0;

		while (bucket != null && !bucket.getObject().equals(key)) {
			bucket.getNext();
			currentPos++;
		}
		if (bucket == null) {
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

	/* (non-Javadoc)
	 * @see eds.LBList#removeHead()
	 */
	public V removeHead() {
		V value = head.getObject();
		this.removeFirst();
		return value;

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

	/* (non-Javadoc)
	 * @see eds.LBList#removeTail()
	 */
	public V removeTail() {
		V value = tail.getObject();
		this.removeLast();
		return value;
	}

	/*
	 * public V remove(int pos) throws EmptyListException, InvalidPositionException
	 * { if (size == 0) { throw new EmptyListException(); }
	 * 
	 * else if (pos < 0 || pos >= size) throw new InvalidPositionException();
	 * 
	 * else { if (pos == 0) return this.removeHead(); else if (pos == size - 1) {
	 * return this.removeTail(); } else { Node<E> nodeToRemove = this.getNode(pos);
	 * this.removeMid(nodeToRemove); return nodeToRemove.getObject(); } }
	 * 
	 * }
	 */

	protected void removeMid(Bucket<K, V> midBucket) {

		Bucket<K, V> prevNode = midBucket.getPrevious();
		Bucket<K, V> nextNode = midBucket.getNext();
		prevNode.setNext(nextNode);
		nextNode.setPrevious(prevNode);
		size--;

	}

	/* (non-Javadoc)
	 * @see eds.LBList#remove(K)
	 */
	public boolean remove(K key) throws InvalidPositionException {

		int pos = this.findKey(key);
		if (pos == -1) {
			throw new InvalidPositionException();
		}
		Bucket<K, V> bucket = this.getBucket(pos);
		if (bucket == null)
			return false;
		else {
			if (bucket == head)
				this.removeHead();
			else if (bucket == tail)
				this.removeTail();
			else
				this.removeMid(bucket);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see eds.LBList#swapNode(int, int)
	 */
	public void swapNode(int a, int b) throws InvalidPositionException {
		Bucket<K, V> bucketA = this.getBucket(a);
		Bucket<K, V> bucketB = this.getBucket(b);

		Bucket<K, V> bucketANext = bucketA.getNext();
		Bucket<K, V> bucketAPrev = bucketA.getPrevious();
		Bucket<K, V> bucketBPrev = bucketB.getPrevious();
		Bucket<K, V> bucketBNext = bucketB.getNext();

		bucketA.setNext(bucketBNext);
		bucketB.setNext(bucketANext);
		bucketB.setPrevious(bucketAPrev);
		bucketA.setPrevious(bucketBPrev);

	}

	/* (non-Javadoc)
	 * @see eds.LBList#getSize()
	 */
	public int getSize() {
		return size;
	}

	/* (non-Javadoc)
	 * @see eds.LBList#isEmpty()
	 */
	public boolean isEmpty() {
		return (size == 0);
	}

}
