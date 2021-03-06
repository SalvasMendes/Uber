package eds;

import java.util.NoSuchElementException;

/**
 *
 * @author Salvador Mendes (50503) sr.mendes@campus.fct.unl.pt
 * @author Miguel Candeias (50647) mb.candeias@campus.fct.unl.pt
 *
 * @param <K>
 * @param <V>
 */
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

	public void orderedAdd(K key, V value) throws NoSuchElementException, InvalidPositionException {
		Bucket<K, V> newBucket;
		if (size == 0) {
			newBucket = new BucketClass<K, V>(head, key, value, null);
			head = newBucket;
			tail = newBucket;
			size++;
		} else if (key.toString().compareTo(tail.getKey().toString()) > 0) {
			addLast(key, value);
		} else if (key.toString().compareTo(head.getKey().toString()) < 0) {
			newBucket = new BucketClass<K, V>(head, key, value, null);
			head.setPrevious(newBucket);
			head = newBucket;
			size++;
		} else {
			int index = findEntry(key);
			Bucket<K, V> prevBucket = this.getBucket(index-1);
			Bucket<K, V> nextBucket = this.getBucket(index);

			newBucket = new BucketClass<K, V>(nextBucket, key, value, prevBucket);
			prevBucket.setNext(newBucket);
			nextBucket.setPrevious(newBucket);
			size++;
		}
	}

	public int findEntry(K key) throws NoSuchElementException, InvalidPositionException {
		String sKey = key.toString();
		LBListIterator<K, V> it = iterator();
		String tempKey;
		boolean found = false;
		int i = 1;
		it.next();
		while (it.hasNext() && !found) {
			tempKey = it.next().getKey().toString();
			if (sKey.compareTo(tempKey) <= 0) {
				found = true;
			} else {
				i++;
			}
		}
		return i;
	}

	public void addLast(K key, V value) {

		Bucket<K, V> newBucket = new BucketClass<K, V>(null, key, value, tail);
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

	public V get(K key) throws InvalidPositionException {

		int n = findKey(key);

		if (n < 0 || n >= size) {
			throw new InvalidPositionException();
		} else {
			return getBucket(n).getObject();
		}
	}

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

	public void append(LBList<K, V> list) throws EmptyListException, InvalidPositionException {

		if (size == 0) {
			throw new EmptyListException();
		}

		tail.setNext(list.getBucket(0));
		list.getBucket(0).setPrevious(head);
		tail = list.getBucket(getSize() - 1);

	}

	public int findKey(K key) {

		Bucket<K, V> bucket = head;
		int currentPos = 0;

		while (bucket != null) {
			if (bucket.getKey().equals(key)) {
				return currentPos;
			}
			bucket = bucket.getNext();
			currentPos++;
		}
		return -1;
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

	public V removeTail() {
		V value = tail.getObject();
		this.removeLast();
		return value;
	}

	protected void removeMid(Bucket<K, V> midBucket) {

		Bucket<K, V> prevNode = midBucket.getPrevious();
		Bucket<K, V> nextNode = midBucket.getNext();
		prevNode.setNext(nextNode);
		nextNode.setPrevious(prevNode);
		size--;

	}

	public V remove(K key) throws InvalidPositionException {

		int pos = this.findKey(key);
		if (pos == -1) {
			throw new InvalidPositionException();
		} else {
			Bucket<K, V> bucket = this.getBucket(pos);
			if (bucket == null) {
			} else {
				if (bucket == head)
					return (this.removeHead());
				else if (bucket == tail)
					return (this.removeTail());
				else {
					this.removeMid(bucket);
					return bucket.getObject();
				}
			}
		}
		return null;
	}

	public void swapNode(int a, int b) throws InvalidPositionException {

		Bucket<K, V> nodeB = this.getBucket(b);
		Bucket<K, V> nodeA = this.getBucket(a);

		Bucket<K, V> nodeAPrev = nodeA.getPrevious();
		Bucket<K, V> nodeANext = nodeA.getNext();
		Bucket<K, V> nodeBPrev = nodeB.getPrevious();
		Bucket<K, V> nodeBNext = nodeB.getNext();
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

	public LBListIterator<K, V> iterator() throws InvalidPositionException {
		return new LBListIteratorClass<K, V>(head);
	}
}
