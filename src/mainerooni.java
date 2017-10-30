import eds.*;

public class mainerooni {

	public static void main(String[] args) throws EmptyListException, InvalidPositionException {
		DLList<Integer>list1 = new LinkedList<Integer>();
		DLList<Integer>list2 = new LinkedList<Integer>();
		
		Map<String, Integer> map = new MapClass<String, Integer>();
		
		map.add("jskdjasdjashdkaj", 2);
		//map.add("1", 7);
		//map.add("4", 4);
		map.add("9", 9);
		map.remove("9");
		map.add("dsa", -1);

		
		

		HashTableIterator<String, Integer> itt = map.iterate();
		while(itt.hasNext()){
		System.out.println(itt.next().getObject());
		}
		
		
	}	

}
