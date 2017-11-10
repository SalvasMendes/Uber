package eds;

import java.util.NoSuchElementException;

/**
 * 
 * @author Salvador Mendes (50503) sr.mendes@campus.fct.unl.pt
 * @author Miguel Candeias (50647) mb.candeias@campus.fct.unl.pt
 *
 * @param <K>
 * @param <V>
 */
public interface LBListIterator<K, V> {

    /** 
     * Returns true if next would return an element 
     * rather than throwing an exception.
     * @return true iff the iteration has more elements
     */
	boolean hasNext( );

    /**
     * Returns the next element in the iteration.
     * @return the next element in the iteration
     * @throws NoSuchElementException - if call is made without verifying pre-condition
     * @throws InvalidPositionException 
     */
    Bucket<K,V> next( ) throws NoSuchElementException, InvalidPositionException;

    /**
     * Restarts the iteration.
     * After rewind, if the iteration is not empty,
     * next will return the first element in the iteration.
     * @throws InvalidPositionException 
     */
    void rewind( ) throws InvalidPositionException;

}
