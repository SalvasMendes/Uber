package eds;

import java.util.NoSuchElementException;

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
			newBucket = new BucketClass<K, V>(head, key, value, null);// faz sentido ter a head como next se nao tem
																		// nada
			head = newBucket;
			tail = newBucket;
			size++;
		} else if (key.toString().compareTo(tail.getKey().toString()) > 0) {
			addLast(key, value);
		} else if (key.toString().compareTo(head.getKey().toString()) < 0) {
			newBucket = new BucketClass<K, V>(head, key, value, null);
			head = newBucket;
			size++;
		} else {
			Bucket<K, V> temp = getBucket(findEntry(key));
			newBucket = new BucketClass<K, V>(temp, key, value, temp.getPrevious());
			temp.setPrevious(newBucket);
			size++;
		}
	}

	public int findEntry(K key) throws NoSuchElementException, InvalidPositionException {
		String sKey = key.toString();
		LBListIterator<K, V> it = iterator();
		String tempKey;
		boolean found = false;
		int i = 0;
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

		while (bucket != null && !bucket.getKey().equals(key)) {
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
				// lança exeçao
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
