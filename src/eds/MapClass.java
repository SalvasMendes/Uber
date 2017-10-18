package eds;

public class MapClass<K, V> {

	private LBList<K, V>[] buckets;
	private int size;

	@SuppressWarnings("unchecked")
	public MapClass(int size) {
		this.size = size;
		buckets = new LBList[size];
		initLists(size);
	}

	private void initLists(int size) {
		for (int i = 0; i < size; i++) {
			buckets[i] = new LinkedBucketList<K, V>();
		}
	}

	private int hashKey(K key) {
		int index;
		int hcode = key.hashCode();

		index = hcode % size;
		return index;
	}

	public void add(K key, V value) {
		int index;

		index = hashKey(key);
		buckets[index].addLast(key, value);
	}

}
