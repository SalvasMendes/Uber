package eds;
/**
 * 
 * @author Salvador Mendes (50503) sr.mendes@campus.fct.unl.pt
 * @author Miguel Candeias (50647) mb.candeias@campus.fct.unl.pt
 *
 * @param <K>
 * @param <V>
 */
public class MapClass<K, V> implements java.io.Serializable, Map<K, V> {

	private static final long serialVersionUID = 657L;
	private LBList<K, V>[] buckets;
	private int size;
	private int nbrEntries;
	private static double loadR = 0.75;
	private int newSize;
	private int index;
	private int hcode;

	public int nbr() {
		return nbrEntries;
	}

	public int getSize() {
		return size;
	}

	@SuppressWarnings("unchecked")
	public MapClass(int size) {
		this.size = size;
		buckets = new LBList[size];
		initLists(size, buckets);
		nbrEntries = 0;
	}

	@SuppressWarnings("unchecked")
	public MapClass() {
		size = 16;
		buckets = new LBList[size];
		initLists(size, buckets);
		nbrEntries = 0;
	}
	
	/**
	 * This method initializes all entries in the HashTable, with a new linked bucket list
	 * @param size
	 * @param list
	 */
	private void initLists(int size, LBList<K, V>[] list) {
		for (int i = 0; i < size; i++) {
			list[i] = new LinkedBucketList<K, V>();
		}
	}

	/**
	 * This is the hashing method, responsible for generating an Integer that
	 * represents the key in which the object is located in the hashtable
	 * 
	 * @param key
	 * @param cap
	 * @return
	 */
	private int hashKey(K key, int cap) {
		hcode = key.hashCode();
		hcode = Math.abs(hcode);
		index = hcode % cap;
		index = Math.abs(index);
		return index;
	}

	public int getIndex(K key) {
		int index = this.hashKey(key, size);
		return index;
	}

	public boolean exists(K key) {

		index = hashKey(key, size);
		LBList<K, V> temp = buckets[index];

		if (temp.isEmpty()) {
			return false;
		} else if (temp.findKey(key) == -1) {
			return false;
		} else {
			return true;
		}
	}

	public void add(K key, V value) throws InvalidPositionException {

		if ((double) nbrEntries / (double) size == loadR) {
			remap();
			index = hashKey(key, size);
			buckets[index].orderedAdd(key, value);
			
		} else {
			index = hashKey(key, size);
			buckets[index].orderedAdd(key, value);
		}
		nbrEntries++;
	}

	/**
	 * This method resizes the size of the hashtable, the algorithm works with
	 * the help of the iterator of the hashtable
	 * 
	 * @throws InvalidPositionException
	 */
	private void remap() throws InvalidPositionException {
		newSize = size * 2;
		K tempkey;
		Bucket<K, V> tempObj;
		HashTableIterator<K, V> it = new HashTableIteratorClass<>(buckets);
		@SuppressWarnings("unchecked")
		LBList<K, V>[] temp = new LBList[newSize];
		initLists(newSize, temp);
		buckets = temp;
		while (it.hasNext()) {
			tempObj = it.next();
			tempkey = tempObj.getKey();
			buckets[hashKey(tempkey, newSize)].orderedAdd(tempkey, tempObj.getObject());
		}
		size = newSize;
	}

	public V remove(K key) throws InvalidPositionException {

		int index = this.hashKey(key, size);
		V tempElement = buckets[index].remove(key);
		if (tempElement != null) {
			nbrEntries--;
		}
		return tempElement;
	}

	public V find(K key) throws InvalidPositionException {
		int index = this.hashKey(key, size);

		return buckets[index].get(key);
	}

	public HashTableIterator<K, V> iterate() throws InvalidPositionException {
		return new HashTableIteratorClass<K, V>(buckets);
	}

	@Override
	public TreeIterator<K, V> iterator() throws EmptyStackException, EmptyListException {
		// TODO Auto-generated method stub
		return null;
	}

}
