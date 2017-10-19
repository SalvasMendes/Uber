package eds;

public class MapClass<K, V> {

	private LBList<K, V>[] buckets;
	private int size;
	private float loadR;

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
	
	public void remap(){
		int newSize = size*2;
		int i = 0;
		int j;
		LBList<K, V>[] temp = new LBList[newSize];
		initLists(newSize, temp);
		while(i<size){
			if(buckets[i].isEmpty(){
				i++;
			}
			 else{
				 j = 0;
				 while(j < buckets[i].getSize()){
				 K tempKey = buckets[i].getKey(j);
				 temp[hashKey(tempKey, newSize)] = buckets[i].get(tempKey);
				 j++;
				 }				
		}
	}

}
