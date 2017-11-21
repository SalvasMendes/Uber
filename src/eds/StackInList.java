package eds;

public class StackInList<E> implements Stack<E>
{

    static final long serialVersionUID = 0L;


    // Memory of the stack: a list.
    protected DLList<E> list;                     


    public StackInList( )
    {     
        list = new LinkedList<E>();
    }


    // Returns true iff the stack contains no elements.
    public boolean isEmpty( )
    {     
        return list.isEmpty();
    }


    // Returns the number of elements in the stack.
    public int size( )
    {     
        return list.getSize();
    }


    // Returns the element at the top of the stack.
    public E top( ) throws EmptyStackException, EmptyListException 
    {     
        if ( list.isEmpty() )
            throw new EmptyStackException();
        
        return list.getLast().getObject();
    }


    // Inserts the specified element onto the top of the stack.
    public void push( E element )
    { 
        list.addLast(element);
    }


    // Removes and returns the element at the top of the stack.
    public E pop( ) throws EmptyStackException 
    {     
        if ( list.isEmpty() )
            throw new EmptyStackException();

        return list.removeTail();
    }
    
    public void clear(){
    	int size = list.getSize();
    	while(size>0){
    		list.removeTail();
    	}
    }


}
