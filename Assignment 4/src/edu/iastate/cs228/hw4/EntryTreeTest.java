package edu.iastate.cs228.hw4;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Matthew Martin
 *
 * Entry Tree Test Class
 */
public class EntryTreeTest {

	Character[] post = {'p', 'o', 's', 't'};
	Character[] malone = {'m', 'a', 'l', 'o', 'n', 'e'};
	Character[] pat = {'p', 'a', 't'};
	Character[] jcole = {'j', 'c', 'o', 'l', 'e'};
	Character[] rockstar = {'r','o','c','k','s','t', 'a', 'r'};
	Character[] space = {};
	Character[] test = {'a', null, 'b'};
	Object NullPointerException = new NullPointerException();	
	EntryTree<Character, String> tree = new EntryTree<>();

	@Before
	public void setUp()
	{
		tree.add(jcole, "hills");
		tree.add(pat, "car");
		tree.add(post, "friend");
		tree.add(malone, "drive");
	}
	
	@Test(expected = NullPointerException.class)
	public void testPrefix(){
		Character[] pam = {'p', 'a', 'm'};
		Character[] pa = {'p','a'};
		assertArrayEquals(pa, tree.prefix(pam));
		assertArrayEquals(pat, tree.prefix(pat));
		assertArrayEquals(null, tree.prefix(rockstar));
		assertArrayEquals(null, tree.prefix(space));
		assertArrayEquals(null, tree.prefix(null));
		assertEquals(NullPointerException, tree.prefix(test));//Test exception
	}
	
	@Test(expected = NullPointerException.class)
	public void testSearch(){
		Character[] cars = {'c','a','r','s'};
		assertEquals(null, tree.search(cars));
		assertEquals("drive", tree.search(malone));
		assertEquals(null, tree.search(rockstar));
		assertEquals(null, tree.search(null));
		assertEquals(null, tree.search(space));
		assertEquals(NullPointerException, tree.search(test));//Test exception
	}
	
	@Test(expected = NullPointerException.class)
	public void testAdd() {
		Character[] pa = {'p','a'};
		Character[] patss = {'p','a','t','s','s'};
		assertEquals("hills", tree.search(jcole));
		assertEquals(true,tree.add(jcole, "lol"));
		assertEquals("lol", tree.search(jcole));
		assertEquals(true, tree.add(pa, "pa"));
		assertEquals("pa", tree.search(pa));
		assertEquals(true, tree.add(patss, "yayss"));
		assertEquals("yayss", tree.search(patss));
		assertEquals(true, tree.add(rockstar, "bob"));
		assertEquals(false, tree.add(null, "abc"));
		assertEquals(false, tree.add(jcole, null));
		assertEquals(false, tree.add(space, "abc"));
		assertEquals(NullPointerException, tree.add(test, "abc"));//Test exception
	}

	@Test(expected = NullPointerException.class)
	public void testRemove(){
		assertEquals("friend", tree.search(post));
		assertEquals("friend", tree.remove(post));
		assertEquals(null, tree.search(post));
		assertEquals(null, tree.remove(rockstar));
		assertEquals("car", tree.remove(pat));
		assertEquals("hills", tree.search(jcole));
		assertEquals("drive", tree.remove(malone));
		assertEquals(null, tree.remove(space));
		assertEquals(null, tree.remove(null));
		assertEquals(null, tree.remove(rockstar));
		assertEquals(NullPointerException, tree.remove(test));//Test exception
	}
}
