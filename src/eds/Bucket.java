package eds;

public interface Bucket {

	void addObj(Object obj);

	Iterator<Object> itemsToCompare() throws InvalidPositionException, EmptyListException;

}