package eds;                                         

import java.io.Serializable; 

/**
 * BST node implementation
 * 
 * @author AED team
 * @version 1.0
 *
 * @param <K> Generic type Key
 * @param <V> Generic type Value 
 */
public class BSTNode<K,V> implements Serializable
{                                                                   

	/**
	 * Serial Version UID of the Class.
	 */
    static final long serialVersionUID = 657L;


    /**
     * Entry stored in the node.
     */
    private Bucket<K,V> entry;                                      

    /**
     * (Pointer to) the left child.
     * 
     */
    private BSTNode<K,V> leftChild;

    /**
     * (Pointer to) the right child.
     * 
     */
    private BSTNode<K,V> rightChild;
    
    private boolean isAlone;


    /**
     * Constructor for BST nodes
     * 
     * @param key to be stored in this BST tree node
     * @param value to be stored in this BST tree node
     * @param left sub-tree of this node
     * @param right sub-tree of this node
     */
    public BSTNode( K key, V value, BSTNode<K,V> left, BSTNode<K,V> right )
    {                                                                
        entry = new BucketClass<K,V>(key, value);
        leftChild = left; 
        rightChild = right;  
        isAlone = true;
    }


    /**
     * Constructor for BST nodes
     * 
     * @param key to be stored in this BST tree node
     * @param value to be stored in this BST tree node
     */
    public BSTNode( K key, V value )
    {    
        this(key, value, null, null);
    }


    /**
     * Returns the entry (key and value) of the current node.
     * 
     * @return
     */
    public Bucket<K,V> getEntry( )                           
    {
        return entry;
    }


    /**
     * Returns the key of the current node.
     * 
     * @return
     */
    public K getKey( )                           
    {
        return entry.getKey();
    }
    
    public boolean isAlone(){
    	return isAlone;
    }
    
    public void notAlone(){
    	isAlone = false;
    }


    /**
     * Returns the value of the current node.
     * 
     * @return
     */
    public V getValue( )                           
    {
        return entry.getObject();
    }


    /**
     * Returns the left child node of the current node.
     * 
     * @return
     */
    public BSTNode<K,V> getLeft( )                                     
    {    
        return leftChild;
    }


    /**
     * Returns the right child node of the current node.
     * 
     * @return
     */
    public BSTNode<K,V> getRight( )                                    
    {    
        return rightChild;
    }


    /**
     * Assigns a new entry (key and value) to the current BST node
     *   
     * @param newEntry
     */
    public void setEntry(Bucket<K,V> newEntry )
    {    
        entry = newEntry;
    }


    /**
     * Assigns new a key and value to the current BST node
     * 
     * @param newKey 
     * @param newValue
     */
    public void setEntry( K newKey, V newValue )
    {    
        entry.setKey(newKey);
        entry.setValue(newValue);
    }


    /**
     * Sets the new key of the current node.
     * 
     * @param newKey
     */
    public void setKey( K newKey )
    {    
        entry.setKey(newKey);
    }


    /**
     * Sets the new value object of the current node.
     * 
     * @param newValue
     */
    public void setValue( V newValue )
    {    
        entry.setValue(newValue);
    }


    /**
     * Sets the new left child node of the this node
     * 
     * @param newLeft the new left child node of the current node
     */
    public void setLeft( BSTNode<K,V> newLeft )                     
    {    
        leftChild = newLeft;
    }


    /**
     * Sets the new right child node of the this node
     * 
     * @param newLeft the new right child node of the current node
     */
    public void setRight( BSTNode<K,V> newRight )                   
    {    
        rightChild = newRight;
    }


    /**
     * Returns true iff the node is a leaf.
     * 
     * @return
     */
    public boolean isLeaf( )                                
    {    
        return leftChild == null && rightChild == null;          
    }                                                                  


}
