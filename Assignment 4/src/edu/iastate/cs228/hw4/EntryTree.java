package edu.iastate.cs228.hw4;

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * @author Matthew Martin
 *
 * An entry tree class
 * Add Javadoc comments to each method
 */
public class EntryTree<K, V> {
	/**
	 * dummy root node made public for grading
	 */
	protected Node root;
	
	/**
	 * prefixlen is the largest index such that the keys in the subarray keyarr
	 * from index 0 to index prefixlen - 1 are, respectively, with the nodes on
	 * the path from the root to a node. prefixlen is computed by a private
	 * method shared by both search() and prefix() to avoid duplication of code.
	 */
	protected int prefixlen;

	protected class Node implements EntryNode<K, V> {
		protected Node child; // link to the first child node
		protected Node parent; // link to the parent node
		protected Node prev; // link to the previous sibling
		protected Node next; // link to the next sibling
		protected K key; // the key for this node
		protected V value; // the value at this node

		public Node(K aKey, V aValue) {
			key = aKey;
			value = aValue;
			child = null;
			parent = null;
			prev = null;
			next = null;
		}

		@Override
		public EntryNode<K, V> parent() {
			if(parent == null){
				return null;
			}else{
				return parent;
			}
		}

		@Override
		public EntryNode<K, V> child() {
			if(child == null){
				return null;
			}else{
				return child;
			}
		}

		@Override
		public EntryNode<K, V> next() {
			if(next == null){
				return null;
			}else{
				return next;
			}
		}

		@Override
		public EntryNode<K, V> prev() {
			if(prev == null){
				return null;
			}else{
				return prev;
			}
		}

		@Override
		public K key() {
			return key;
		}

		@Override
		public V value() {
			return value;
		}
	}

	public EntryTree() {
		root = new Node(null, null);
	}

	/**
	 * Returns the value of the entry with a specified key sequence, or null if
	 * this tree contains no entry with the key sequence.
	 * 
	 * @param keyarr
	 * @return
	 */
	public V search(K[] keyarr) {
		if(keyarr == null || keyarr.length < 1){
			return null;
		}
		
		for(int i = 0; i < keyarr.length; i++){
			if(keyarr[i] == null){
				throw new NullPointerException();
			}
		}	
		
		Node temp = root;
		prefixlen = prefixLength(keyarr);
		
		if(prefixlen < keyarr.length){
			return null;
		}
		
		for(int i = 0; i < prefixlen; i++){
			temp = temp.child;
			while(temp.key() != keyarr[i]){
				temp = temp.next;
			}
		}
		
		return temp.value();
	}

	/**
	 * The method returns an array of type K[] with the longest prefix of the
	 * key sequence specified in keyarr such that the keys in the prefix label
	 * the nodes on the path from the root to a node. The length of the returned
	 * array is the length of the longest prefix.
	 * 
	 * @param keyarr
	 * @return
	 */
	public K[] prefix(K[] keyarr) {
		if(keyarr == null || keyarr.length < 1){
			return null;
		}
		
		for(int i = 0; i < keyarr.length; i++){
			if(keyarr[i] == null){
				throw new NullPointerException();
			}
		}
		
		prefixlen = prefixLength(keyarr);
		
		if(prefixlen < 1){
			return null;
		}
		
		return Arrays.copyOf(keyarr, prefixlen);
	}

	/**
	 * The method locates the node P corresponding to the longest prefix of the
	 * key sequence specified in keyarr such that the keys in the prefix label
	 * the nodes on the path from the root to the node. If the length of the
	 * prefix is equal to the length of keyarr, then the method places aValue at
	 * the node P and returns true. Otherwise, the method creates a new path of
	 * nodes (starting at a node S) labelled by the corresponding suffix for the
	 * prefix, connects the prefix path and suffix path together by making the
	 * node S a child of the node P, and returns true.
	 * 
	 * @param keyarr
	 * @param aValue
	 * @return
	 */
	public boolean add(K[] keyarr, V aValue) {
		if(keyarr == null || keyarr.length < 1 || aValue == null){
			return false;
		}
		
		for(int i = 0; i < keyarr.length; i++){
			if(keyarr[i] == null){
				throw new NullPointerException();
			}
		}
		
		int startingPosition = prefixLength(keyarr);
		Node current = root;
		Node previous = root;
		Node temp;
		
		for(int i = 0; i < startingPosition; i++){
			previous = current;
			current = current.child;
			
			while(keyarr[i] != current.key() && current.next() != null){
				current = current.next;
			}
		}
		
		if(startingPosition == keyarr.length){
			temp = new Node(keyarr[startingPosition - 1], aValue);
			
			if(current.key().equals(keyarr[keyarr.length - 1])){
				previous.child = temp;
				temp.parent = previous;
				temp.child = current.child;
				temp.next = current.next;
				temp.prev = current.prev;
				
				if(current.child() != null){
					current.child.parent = temp;
				}
			}else{
				current.next = temp;
				temp.prev = current;
				temp.parent = previous;
			}
			return true;
		}
		for(int i = startingPosition; i < keyarr.length; i++){
			if(i == (keyarr.length - 1)){
				temp = new Node(keyarr[i], aValue);
			}else{
				temp = new Node(keyarr[i], null);
			}
			
			if(current.child != null){
				current = current.child;
				
				while(current.next() != null){
					current= current.next;
				}
				
				current.next = temp;
				temp.prev = current;
				temp.parent = current.parent;
				current = current.next;
			}else{
				current.child = temp;
				temp.parent = current;
				current = current.child;
			}
		}
		return true;
	}

	/**
	 * Removes the entry for a key sequence from this tree and returns its value
	 * if it is present. Otherwise, it makes no change to the tree and returns
	 * null.
	 * 
	 * @param keyarr
	 * @return
	 */
	public V remove(K[] keyarr) {
		if(keyarr == null || keyarr.length < 1){
			return null;
		}
		
		for(int i = 0; i < keyarr.length; i++){
			if(keyarr[i] == null){
				throw new NullPointerException();
			}
		}
		
		V value = search(keyarr);
		
		if(value == null){
			return value;
		}
		
		Node current = root;
		
		for(int i = 0; i < keyarr.length; i++){
			current = current.child;
			while(current.key() != keyarr[i]){
				current = current.next;
			}
		}
		
		if(nodesBelow(keyarr, current)){
			current.value = null;
		}else{
			deleteNodes(current);
		}
		
		return value;
	}

	/**
	 * The method prints the tree on the console in the output format shown in
	 * an example output file.
	 */
	public void showTree() {
		String space = "";
		EntryNode<K,V> cur = root;
		System.out.println("null->null");
		showDown(cur, space);
	}

	/**
	 * Helper method that shows the child in the tree/One level down on the tree.
	 * @param cur
	 * 		The current EntryNode that will be used for the tree
	 * @param space
	 * 		The amount of space needed for the proper indentation
	 */
	private void showDown(EntryNode<K,V> cur, String space){
		if(cur.child() != null){
			cur = cur.child();
			space = space + "   ";
			System.out.print(space);
			System.out.print(cur.key().toString() + "->");
			
			if(cur.value() != null){
				System.out.println(cur.value().toString());
				showDown(cur, space);
				showRight(cur,space);
			}else{
				System.out.println("null");
				showDown(cur, space);
				showRight(cur, space);
			}
		}
	}
	
	/**
	 * Helper method that shows the siblings in the tree at the current level of the tree.
	 * @param cur
	 * 		The current EntryNode that will be used for the tree
	 * @param space
	 * 		The amount of space that is needed for the proper indentation
	 */
	private void showRight(EntryNode<K,V> cur, String space){
		if(cur.next() != null){
			cur = cur.next();
			System.out.print(space);
			System.out.print(cur.key().toString() + "->");
			
			if(cur.value() != null){
				System.out.println(cur.value().toString());
				showDown(cur,space);
			}else{
				System.out.println("null");
				showDown(cur, space);
				showRight(cur, space);
			}
		}
	}
	
	/**
	 * A helper method that returns an int of the depth of the
	 * keyarr array being used.  This method helps to snow how
	 * much a method needs to be iterated through.
	 * @param keyarr
	 * 		The array for K values
	 * @return
	 * 		The depth of the array
	 */
	private int prefixLength(K[] keyarr){
		EntryNode<K,V> current = root;
		int depth = 0;
		
		while(current.child() != null && depth < keyarr.length ){
			current = current.child();
			
			if(current.key().equals(keyarr[depth])){
				depth = depth + 1;
			}else{
				if(current.next() == null){
					break;
				}else{
					while(current.next() != null){
						current = current.next();
						if(current.key().equals(keyarr[depth])){
							depth = depth + 1;
							break;
						}else if(current.next() == null){
							break;
						}
					}
				}
			}
		}
		return depth;
	}
	
	/**
	 * A helper method that will move up the tree from the
	 * current node and will delete all the nodes that are
	 * no longer needed.
	 * @param cur
	 * 		The current node to start from
	 */
	private void deleteNodes(Node cur){
		while(cur != root){
			if(cur.next() != null || cur.prev() != null){
				if(cur.next() != null){
					cur.next.prev = cur.prev;
				}
				
				if(cur.prev() != null){
					cur.prev.next = cur.next;
				}

				return;
			}else{
				cur = cur.parent;
				
				if(cur.value() != null){
					cur.child = null;
					return;
				}
			}
		}
	}
	
	/**
	 * A helper method that searches for the child nodes that contain a value.
	 * @param keyarr
	 * 		The array of K values that needs to be looked through
	 * @param current
	 * 		The current node to start from
	 * @return true if there are still nodes to search for; false otherwise
	 */
	private boolean nodesBelow(K[] keyarr, Node current){
		if(current.child() != null){
			current = current.child;
			if(current.value() != null){
				return true;
			}
			if(nodesRight(keyarr, current) == false){
				return nodesBelow(keyarr, current);
			}else{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * A helper method that searches for the sibling nodes that contain a value.
	 * @param keyarr
	 * 		The array of K values that needs to be looked through
	 * @param current
	 * 		The current node to start from
	 * @return true if there are still nodes to search for; false otherwise
	 */
	private boolean nodesRight(K[] keyarr, Node current){
		while(current.next() != null){
			current = current.next;
			if(current.value() != null){
				return true;
			}
		}
		return false;
	}
}
