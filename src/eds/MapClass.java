package eds;

public class MapClass<K, V> implements java.io.Serializable, Map<K, V> {

	private static final long serialVersionUID = 657L;
	private LBList<K, V>[] buckets;
	private int size;
	private int nbrEntries;
	private static double loadR = 0.75;

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

	private void initLists(int size, LBList<K, V>[] list) {
		for (int i = 0; i < size; i++) {
			list[i] = new LinkedBucketList<K, V>();
		}
	}

	private int hashKey(K key, int size) {
		int index;
		int hcode = key.hashCode();

		index = hcode % size;
		return index;
	}

	public void add(K key, V value) throws InvalidPositionException {
		int index;
		if (nbrEntries / size == loadR) {
			remap();
			index = hashKey(key, size);
			buckets[index].addLast(key, value);
		} else {
			index = hashKey(key, size);
			buckets[index].addLast(key, value);
		}
		nbrEntries++;
	}

	private void remap() throws InvalidPositionException {
		int newSize = size * 2;
		int i = 0;
		K tempkey;
		Bucket<K, V> tempObj;
		LBListIterator<K, V> it;
		@SuppressWarnings("unchecked")
		LBList<K, V>[] temp = new LBList[newSize];
		initLists(newSize, temp);
		while (i < size) {
			if (buckets[i].isEmpty()) {
				i++;
			} else {
				it = buckets[i].iterator();
				while (it.hasNext()) {
					tempObj = it.next();
					tempkey = tempObj.getKey();
					temp[hashKey(tempkey, newSize)].addLast(tempkey, tempObj.getObject());

				}

			}

		}
		size = newSize;
		buckets = temp;

	}

	public V remove(K key) throws InvalidPositionException {

		int index = this.hashKey(key, size);
		V tempElement = buckets[index].remove(key);
		if (tempElement != null) {
			size--;
		}
		return tempElement;
	}

	public V find(K key) throws InvalidPositionException {
		int index = this.hashKey(key, size);

		return buckets[index].get(key);
	}
	
		//TODO: Iterador do mapa, e meter o quicksort na list dos buckets

}
