package eds;

public class MapClass<K, V> implements java.io.Serializable, Map<K, V>{

	private static final long serialVersionUID = 657L;
	private LBList<K, V>[] buckets;
	private int size;
	private int nbrEntries;
	private static double loadR = 0.75;
	private int newSize;
	private int index;
	private int hcode;
	
	public int nbr(){
		return nbrEntries;
	}
	
	public int getSize(){
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

	private void initLists(int size, LBList<K, V>[] list) {
		for (int i = 0; i < size; i++) {
			list[i] = new LinkedBucketList<K, V>();
		}
	}

	private int hashKey(K key, int size) {
		hcode = key.hashCode();
		hcode = Math.abs(hcode);
		index = hcode % size;
		index = Math.abs(index);
		return index;
	}
	
	public int getIndex(K key){
		int index = this.hashKey(key, size);
		return index;
	}
	
	public boolean exists(K key){
		
		index = hashKey(key, size);
		LBList<K, V> temp = buckets[index];
		
		if(temp.isEmpty()){
			return false;
		}
		else if(temp.findKey(key) == -1){
			return false;
		}
		else{
			return true;
		}
	}

	public void add(K key, V value) throws InvalidPositionException {
		
		if ((double)nbrEntries / (double)size == loadR) {
			remap();
			index = hashKey(key, size);
			buckets[index].orderedAdd(key, value);
		} else {
			index = hashKey(key, size);
			buckets[index].orderedAdd(key, value);
		}
		nbrEntries++;
	}

	private void remap() throws InvalidPositionException {
		newSize = size * 2;
		K tempkey;
		Bucket<K, V> tempObj;
		HashTableIterator<K, V> it = new HashTableIteratorClass<>(buckets);
		@SuppressWarnings("unchecked")
		LBList<K, V>[] temp = new LBList[newSize];
		initLists(newSize, temp);
		while (it.hasNext()) {
			tempObj = it.next();
			tempkey = tempObj.getKey();
			temp[hashKey(tempkey, newSize)].orderedAdd(tempkey, tempObj.getObject());
		}
		size = newSize;
		buckets = temp;
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
	// TODO: Iterador do mapa, e meter o quicksort na list dos buckets

}
