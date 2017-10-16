package eds;

public class BucketClass implements java.io.Serializable, Bucket {

	private static final long serialVersionUID = 657L;
	DLList<Object> elements;

	public BucketClass(Object obj) {
		elements = new LinkedList<Object>();
	}

	@Override
	public void addObj(Object obj) {
		elements.addLast(obj);
	}

	@Override
	public Iterator<Object> itemsToCompare() throws InvalidPositionException, EmptyListException {
		return new IteratorClass<>(elements.getFirst());
	}

}
