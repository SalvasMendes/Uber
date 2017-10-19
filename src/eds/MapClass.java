package eds;

public class MapClass<K, V> implements java.io.Serializable, Map<K, V> {

	private static final long serialVersionUID = 657L;
	private LBList<K, V>[] buckets;
	private int size;
	private double loadR;

	@SuppressWarnings("unchecked")
	public MapClass(int size) {
		this.size = size;
		buckets = new LBList[size];
		initLists(size, buckets);
		loadR = 0.75;
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

	public void add(K key, V value) {
		int index;

		index = hashKey(key, size);
		buckets[index].addLast(key, value);
	}

	public void remap() throws InvalidPositionException {
		int newSize = size * 2;
		int i = 0;
		int j;
		LBList<K, V>[] temp = new LBList[newSize];
		initLists(newSize, temp);
		while (i < size) {
			if (buckets[i].isEmpty()) {
				i++;
			} else {
				j = 0;
				K tempKey;
				while (j < buckets[i].getSize()) {
					tempKey = buckets[i].getBucket(j).getKey();
					temp[hashKey(tempKey, newSize)].addLast(tempKey, buckets[i].get(tempKey));
					j++;
				}
				i++;
			}
		}
		buckets = temp;
	}
}
