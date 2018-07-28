package edu.iastate.cs228.hw3;
/*
 *  @author
 *	Matthew Martin
 *  An implementation of List<E> based on a doubly-linked list with an array for indexed reads/writes
 *
 */

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class AdaptiveList<E> implements List<E>
{
  public class ListNode // private member of outer class
  {                     
    public E data;        // public members:
    public ListNode link; // used outside the inner class
    public ListNode prev; // used outside the inner class
    
    public ListNode(E item)
    {
      data = item;
      link = prev = null;
    }
  }
  
  public ListNode head;  // dummy node made public for testing.
  public ListNode tail;  // dummy node made public for testing.
  private int numItems;  // number of data items
  private boolean linkedUTD; // true if the linked list is up-to-date.

  public E[] theArray;  // the array for storing elements
  private boolean arrayUTD; // true if the array is up-to-date.

  public AdaptiveList()
  {
    clear();
  }

  @Override
  public void clear()
  {
    head = new ListNode(null);
    tail = new ListNode(null);
    head.link = tail;
    tail.prev = head;
    numItems = 0;
    linkedUTD = true;
    arrayUTD = false;
    theArray = null;
  }

  public boolean getlinkedUTD()
  {
    return linkedUTD;
  }

  public boolean getarrayUTD()
  {
    return arrayUTD;
  }

  public AdaptiveList(Collection<? extends E> c){
	  this();
	  addAll(c);
  }

  // Removes the node from the linked list.
  // This method should be used to remove a node from the linked list.
  private void unlink(ListNode toRemove)
  {
    if ( toRemove == head || toRemove == tail )
      throw new RuntimeException("An attempt to remove head or tail");
    toRemove.prev.link = toRemove.link;
    toRemove.link.prev = toRemove.prev;
  }

  // Inserts new node toAdd right after old node current.
  // This method should be used to add a node to the linked list.
  private void link(ListNode current, ListNode toAdd)
  {
    if ( current == tail )
      throw new RuntimeException("An attempt to link after tail");
    if ( toAdd == head || toAdd == tail )
      throw new RuntimeException("An attempt to add head/tail as a new node");
    toAdd.link = current.link;
    toAdd.link.prev = toAdd;
    toAdd.prev = current;
    current.link = toAdd;
  }
  
  /**
   * Makes theArray up-to-date.
   */
  private void updateArray()
  {
    if ( numItems < 0 )
      throw new RuntimeException("numItems is negative: " + numItems);
    if ( ! linkedUTD )
      throw new RuntimeException("linkedUTD is false");
    
    theArray = (E[]) new Object[numItems];
    
    AdaptiveListIterator adaptiveListIter = new AdaptiveListIterator();
    for(int i = 0; i < numItems; i++){
    	theArray[i] = adaptiveListIter.next();
    }
    
    arrayUTD = true;
  }
  
  /**
   * Makes the linked list up-to-date
   */
  private void updateLinked()
  {
    if ( numItems < 0 )
      throw new RuntimeException("numItems is negative: " + numItems);
    if ( ! arrayUTD )
      throw new RuntimeException("arrayUTD is false");

    if ( theArray == null || theArray.length < numItems )
      throw new RuntimeException("theArray is null or shorter");

    tail.prev = head;
    head.link = tail;
    
    for(int i = 0; i < numItems; i++){
    	add(theArray[i]);
    }
    
    linkedUTD = true;
  }

  @Override
  public int size(){
	  if(numItems > Integer.MAX_VALUE){
		  numItems = Integer.MAX_VALUE;
		  return numItems;
	  }else{
		  return numItems;
	  }
  }

  @Override
  public boolean isEmpty(){
	  if( ! linkedUTD){
		  updateLinked();
	  }
	  
	  if(numItems == 0){
    	return true;
	  }else{
    	return false;
	  }
  }

  @Override
  public boolean add(E obj){
	  if( ! linkedUTD){
		  updateLinked();
	  }
    
	  ListNode temp = new ListNode(obj);
	  link(tail.prev, temp);
    
	  numItems++;
	  
	  arrayUTD = false;
	  return true;
  }

  @Override
  public boolean addAll(Collection< ? extends E> c){
	  if( ! linkedUTD){
		  updateLinked();
	  }
	
	  Iterator iter = c.iterator();
	
	  while(iter.hasNext()){
		  add((E) iter.next());
	  }
	
	  if(isEmpty()){
		  return false;
	  }
	
	  arrayUTD = false;
	  return true;
  } // addAll 1

  @Override
  public boolean remove(Object obj){
	  if( ! linkedUTD){
			updateLinked();
	  }
	  
	  AdaptiveListIterator iter = new AdaptiveListIterator();
	  
	  while(iter.hasNext()){
		  if(obj == null){
			  if(iter.next() == null){
				  iter.remove();
				  numItems--;
				  return true;
			  }
		  }else if(iter.next().equals(obj)){
			  iter.remove();
			  numItems--;
			  return true;
		  }
	  }
	  
	  arrayUTD = false;
	  return false;
  }

  private void checkIndex(int pos) // a helper method
  {
    if ( pos >= numItems || pos < 0 )
     throw new IndexOutOfBoundsException(
       "Index: " + pos + ", Size: " + numItems);
  }

  private void checkIndex2(int pos) // a helper method
  {
    if ( pos > numItems || pos < 0 )
     throw new IndexOutOfBoundsException(
       "Index: " + pos + ", Size: " + numItems);
  }

  private void checkNode(ListNode cur) // a helper method
  {
    if ( cur == null || cur == tail )
     throw new RuntimeException(
      "numItems: " + numItems + " is too large");
  }

  private ListNode findNode(int pos)   // a helper method
  {
    ListNode cur = head;
    for ( int i = 0; i < pos; i++ )
    {
      checkNode(cur);
      cur = cur.link;
    }
    checkNode(cur);
    return cur;
  }

  @Override
  public void add(int pos, E obj){
	  if( ! linkedUTD){
		  updateLinked();
	  }
	  
	  checkIndex2(pos);
	  
	  AdaptiveListIterator iter = new AdaptiveListIterator(pos);
	  iter.add(obj);
	  numItems++;
	  arrayUTD = false;
  }

  @Override
  public boolean addAll(int pos, Collection< ? extends E> c)
  {
	  if( ! linkedUTD){
		  updateLinked();
	  }
	  
	  checkIndex2(pos);
	  
	  Iterator iter = c.iterator();
	  AdaptiveListIterator adaptiveListIter = new AdaptiveListIterator(pos);
	  
	  while(iter.hasNext()){
		  adaptiveListIter.add((E) iter.next());
		  numItems++;
	  }
	  
	  if(isEmpty()){
		  return false;
	  }
	  
	  arrayUTD = false; 
	  return true;
  } // addAll 2

  @Override
  public E remove(int pos){
	  if( ! linkedUTD){
		  updateLinked();
	  }
	  
	  checkIndex2(pos);
	  
	  ListNode nodeToRemove = findNode(pos).link;
	  unlink(nodeToRemove);
	  numItems--;
    
	  arrayUTD = false;
	  linkedUTD = true;
	  return nodeToRemove.data;
  }

  @Override
  public E get(int pos){
	  if( ! arrayUTD){
		  updateArray();
	  }
    
	  linkedUTD = true;
	  return theArray[pos];
  }

  @Override
  public E set(int pos, E obj){
	  if( ! arrayUTD){
	    	updateArray();
	   }
	  
	  E temp = null;
	  temp = theArray[pos];
	  theArray[pos] = obj;
	  
	  linkedUTD = false;
	  return temp;
  } 

  // If the number of elements is at most 1, the method returns false.
  // Otherwise, it reverses the order of the elements in the array
  // without using any additional array, and returns true.
  // Note that if the array is modified, then linkedUTD needs to be set to false.
  /**
   * Reverses the order of the elements if it has enough elements
   * @return  True if enough elements are present to be reversed.
   */
  public boolean reverse(){
	  updateArray();
	  
	  if(numItems <= 1){
		  return false;
	  }
	  
	  for(int i = 0; i < numItems/2; i++){
		  E temp = theArray[i];
		  theArray[i] = theArray[numItems - i - 1];
		  theArray[numItems - i - 1] = temp;
	  }
	  
	  linkedUTD = false;
	  return true;
  }

  @Override
  public boolean contains(Object obj){
	  if( ! linkedUTD){
		  updateLinked();
	  }
	  
	  AdaptiveListIterator adaptiveListIter = new AdaptiveListIterator();
	  
	  for(int i = 0; i < numItems; i++){
		  if(adaptiveListIter.next() == obj){
			  return true;
		  }
	  }
	  
	  arrayUTD = false;
	  return false;
  }

  @Override
  public boolean containsAll(Collection< ? > c){
	  if( ! linkedUTD){
		  updateLinked();
	  }
	  
	  Iterator iter = c.iterator();
	  
	  while(iter.hasNext()){
		  if(!(contains(iter.next()))){
			  return false;
		  }
	  }
	  
	  arrayUTD = false;
	  return true;
  } // containsAll


  @Override
  public int indexOf(Object obj){
	  if( ! linkedUTD){
		  updateLinked();
	  }
	  
	  AdaptiveListIterator adaptiveListIter = new AdaptiveListIterator();
	  int count = -1;
	  
	  while(adaptiveListIter.hasNext()){
		  count++;
		  
		  if(adaptiveListIter.next().equals(obj)){
			  return count;
		  }
	  }
	  arrayUTD = false;
	  return -1;
  }

  @Override
  public int lastIndexOf(Object obj){
	  if( ! linkedUTD){
		  updateLinked();
	  }
	  
	  AdaptiveListIterator adaptiveListIter = new AdaptiveListIterator();
	  int counter = -1;
	  boolean isMoreList = false;
	  
	  while(adaptiveListIter.hasNext()){
		  isMoreList = false;
		  counter++;
		  
		  if(adaptiveListIter.next().equals(obj)){
			  isMoreList = true;
		  }
	  }
	  
	  if(isMoreList){
		  return counter;
	  }
	  
	  arrayUTD = false;
	  return -1;
  }

  @Override
  public boolean removeAll(Collection<?> c){
	  if( ! linkedUTD){
		  updateLinked();
	  }
	  
	  if(c.isEmpty()){
		  return false;
	  }
	  
	  AdaptiveListIterator adaptiveListIter = new AdaptiveListIterator();
	  
	  for(int i = 0; i < c.size() + this.size(); i++){
		  if(c.contains(adaptiveListIter.next())){
			  adaptiveListIter.remove();
			  numItems--;
		  }
	  }
	  
	  arrayUTD = false;
	  return true;
  }

  @Override
  public boolean retainAll(Collection<?> c){
	  if( ! linkedUTD){
		  updateLinked();
	  }
	  
	  if(c.isEmpty() && this.isEmpty()){
		  return false;
	  }
	  
	  AdaptiveListIterator adaptiveListIter = new AdaptiveListIterator();
	  
	  for(int i = 0; i < numItems; i++){
		  if(!(c.contains(adaptiveListIter.next()))){
			  adaptiveListIter.remove();
			  numItems--;
		  }
	  }
	  
	  arrayUTD = false;
	  return true;
  }

  @Override
  public Object[] toArray(){
	  Object[] arr = new Object[numItems];
	  ListIterator<E> iter = listIterator();
	
	  for(int i = 0; i < numItems; i++){
		  arr[i] = iter.next();
	  }
	
	  return arr;
  }
  
  @Override
  public <T> T[] toArray(T[] arr){
	 if(arr.length < numItems){
		 arr = Arrays.copyOf(arr, numItems);
	 }
	 
	 System.arraycopy(toArray(), 0, arr, 0, numItems);
	 
	 if(arr.length > numItems){
		 arr[numItems] = null;
	 }
	 
	 return arr;
  }

  @Override
  public List<E> subList(int fromPos, int toPos)
  {
    throw new UnsupportedOperationException();
  }

  private class AdaptiveListIterator implements ListIterator<E>
  {
    private int    index;  // index of next node;
    private ListNode cur;  // node at index - 1
    private ListNode last; // node last visited by next() or previous()
    
    /**
     * Constructor for AdaptiveListIterator
     */
    public AdaptiveListIterator()
    {
    	if ( ! linkedUTD ) updateLinked();
    	cur = new ListNode(null);
    	cur.link = head.link;
    	cur.prev = head;
    	index = 0;
    	last = null;
    }
    
    /**
     * Constructor that sets up the AdaptiveListIterator at the desired pos
     * @param pos	The position that the AdaptiveListIterator is started at.
     */
    public AdaptiveListIterator(int pos)
    {
    	if ( ! linkedUTD ) updateLinked();
    	cur = new ListNode(null);
    	cur.link = head.link;
    	cur.prev = head;
    	index = 0;
    	
    	for(int i = 0; i < pos; i++){
    		next();
    	}
    	
    	last = null;
    }

    @Override
    public boolean hasNext(){
    	if(cur.link == tail){
    		return false;
    	}else{
    		return true;
    	}
    }

    @Override
    public E next(){
    	if( ! hasNext()){
    		throw new NoSuchElementException();
    	}
    	
    	cur.prev= cur.link;
    	cur.link = cur.link.link;
    	last = cur.prev;
    	
    	index++;
    	E temp = cur.prev.data;
    	return temp;
    } 

    @Override
    public boolean hasPrevious(){
    	if(cur.prev == head){
    		return false;
    	}
    	return true;
    }

    @Override
    public E previous(){
    	if(!hasPrevious()){
    		throw new NoSuchElementException();
    	}
    	
    	cur.link = cur.prev;
    	cur.prev = cur.prev.prev;
    	last = cur.link;
    	index--;
    	
    	return cur.link.data;
    }
    
    @Override
    public int nextIndex(){
    	return index;
    }

    @Override
    public int previousIndex(){
    	return index-1;
    }
    
    /**
     * Removes from the list the last element that was returned by next() or previous() 
     * (optional operation). This call can only be made once per call to next or previous. 
     * It can be made only if add(E) has not been called after the last call to next or previous.
     */
    public void remove(){
    	if(last == null){
    		throw new IllegalStateException();
    	}
    	
    	cur = last;
    	cur.link.prev = cur.prev;
    	cur.prev.link = cur.link;
    	last = null;
    	index--;
    }
    
    /**
     * Inserts the specified element into the list (optional operation). The element is inserted 
     * immediately before the element that would be returned by next(), if any, and after the 
     * element that would be returned by previous(), if any. (If the list contains no elements, 
     * the new element becomes the sole element on the list.) The new element is inserted before 
     * the implicit cursor: a subsequent call to next would be unaffected, and a subsequent call 
     * to previous would return the new element.
     * 
     * @param obj	The E to be added to the array
     */
    public void add(E obj){ 
    	ListNode tempNode = new ListNode(obj);
    	tempNode.prev = cur.prev;
    	tempNode.link = cur.link;
    	cur.prev = tempNode;
    	tempNode.prev.link = tempNode;
    	tempNode.link.prev = tempNode;
    	last = null;
    	index++;
    } // add

    @Override
    public void set(E obj){
    	if(last == null){
    		throw new IllegalStateException();
    	}
    	
    	last.data = obj;
    } // set
  } // AdaptiveListIterator
  
  @Override
  public boolean equals(Object obj)
  {
    if ( ! linkedUTD ) updateLinked();
    if ( (obj == null) || ! ( obj instanceof List<?> ) )
      return false;
    List<?> list = (List<?>) obj;
    if ( list.size() != numItems ) return false;
    Iterator<?> iter = list.iterator();
    for ( ListNode tmp = head.link; tmp != tail; tmp = tmp.link )
    {
      if ( ! iter.hasNext() ) return false;
      Object t = iter.next();
      if ( ! (t == tmp.data || t != null && t.equals(tmp.data) ) )
         return false;
    }
    if ( iter.hasNext() ) return false;
    return true;
  } // equals

  @Override
  public Iterator<E> iterator()
  {
    return new AdaptiveListIterator();
  }

  @Override
  public ListIterator<E> listIterator()
  {
    return new AdaptiveListIterator();
  }

  @Override
  public ListIterator<E> listIterator(int pos)
  {
    checkIndex2(pos);
    return new AdaptiveListIterator(pos);
  }

  // Adopted from the List<E> interface.
  @Override
  public int hashCode()
  {
    if ( ! linkedUTD ) updateLinked();
    int hashCode = 1;
    for ( E e : this )
       hashCode = 31 * hashCode + ( e == null ? 0 : e.hashCode() );
    return hashCode;
  }

  // You should use the toString*() methods to see if your code works as expected.
  @Override
  public String toString()
  {
   String eol = System.getProperty("line.separator");
   return toStringArray() + eol + toStringLinked();
  }

  public String toStringArray()
  {
    String eol = System.getProperty("line.separator");
    StringBuilder strb = new StringBuilder();
    strb.append("A sequence of items from the most recent array:" + eol );
    strb.append('[');
    if ( theArray != null )
      for ( int j = 0; j < theArray.length; )
      {
        if ( theArray[j] != null )
           strb.append( theArray[j].toString() );
        else
           strb.append("-");
        j++;
        if ( j < theArray.length )
           strb.append(", ");
      }
    strb.append(']');
    return strb.toString();
  }

  public String toStringLinked()
  {
    return toStringLinked(null);
  }

  // iter can be null.
  public String toStringLinked(ListIterator<E> iter)
  {
    int cnt = 0;
    int loc = iter == null? -1 : iter.nextIndex();

    String eol = System.getProperty("line.separator");
    StringBuilder strb = new StringBuilder();
    strb.append("A sequence of items from the most recent linked list:" + eol );
    strb.append('(');
    for ( ListNode cur = head.link; cur != tail; )
    {
      if ( cur.data != null )
      {
        if ( loc == cnt )
        {
          strb.append("| ");
          loc = -1;
        }
        strb.append(cur.data.toString());
        cnt++;

        if ( loc == numItems && cnt == numItems )
        {
          strb.append(" |");
          loc = -1;
        }
      }
      else
         strb.append("-");
      
      cur = cur.link;
      if ( cur != tail )
         strb.append(", ");
    }
    strb.append(')');
    return strb.toString();
  }
}
