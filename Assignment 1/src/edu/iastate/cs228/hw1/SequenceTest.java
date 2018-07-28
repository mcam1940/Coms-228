package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import org.junit.Test;

public class SequenceTest {

	//Test if it can catch numbers
		@Test(expected=IllegalArgumentException.class)
		public void ConstuctorTest1() 
		{
			Sequence sarr = new Sequence(new char[] {'5', 'P'});
		}

		//Test if it can catch special characters
		@Test(expected=IllegalArgumentException.class)
		public void ConstructorTest2() {
			Sequence sequence = new Sequence(new char[] {'p', 't', '%', 'r'});
		}
		
		//Test if the constructor can catch empty values
		@Test(expected=IllegalArgumentException.class)
		public void ConstructorTest3()
		{
			Sequence sequence = new Sequence(new char[] {'p', 'w', ' ', 'r'});
		}
		
		//Test if the constructor can catch null values
		@Test(expected=IllegalArgumentException.class)
		public void ConstructorTest4()
		{
			char[] asdf = new char[2];
			asdf[0] = 'c';
			Sequence sequence = new Sequence(asdf);
		}
		
		//test the length of sequence to make sure it returns the proper length
		@Test
		public void SequenceLengthTest1() 
		{
			Sequence sequence = new Sequence(new char[] {'q', 'f', 'W', 'i'});
			assertEquals(4, sequence.seqLength());
		}

		//Test sequence to make sure that it catches when the array is empty
		@Test
		public void SequenceLengthTest2()
		{
			Sequence sequence = new Sequence(new char[] {});
			
			assertEquals(0, sequence.seqLength());
		}

		//Is the new char array that's returned equal to the old one? 
		@Test
		public void GetSeqTest()
		{
			char[] c = new char[] {'R', 'C', 'g', 'y', 'm', 'O'};
			Sequence sequence = new Sequence(c);
			assertArrayEquals(c, sequence.getSeq());
		}

		//Were the characters converted to a string?
		@Test
		public void ToStringTest()
		{
			char[] c = new char[] {'g', 'Y', 'u', 'l'};
			Sequence sequence = new Sequence(c);
			String s = new String("gYul");
			assertEquals(s, sequence.toString());
		}

		//make sure that these two strings are not equal if the characters are different
		@Test
		public void EqualsTest1() 
		{
			Sequence sequence1 = new Sequence(new char[] {'r', 'Q', 'r', 'u'});
			Sequence sequence2 = new Sequence(new char[] {'z', 'y', 'r', 'u'});
			assertFalse(sequence1.equals(sequence2));
		}

		//make sure that the two strings are equal if the characters are the same, but lower/uppercase
		@Test
		public void EqualsTest2() {
			Sequence sequence1 = new Sequence(new char[] {'r', 'C', 'R', 'Q'});
			Sequence sequence2 = new Sequence(new char[] {'r', 'C', 'r', 'q'});
			assertTrue(sequence1.equals(sequence2));
		}

		//make sure that the character array is not equal to a string
		@Test
		public void EqualsTest3() {
			Sequence sequence = new Sequence(new char[] {'r', 'C', 'r', 'O'});
			assertFalse(sequence.equals("sequence2"));
		}

		@Test
		public void isValidLetter_test_1() {
			Sequence sequence = new Sequence(new char[] {'r', 'C', 'r', 'O'});
			assertTrue(sequence.isValidLetter('f'));
		}

		@Test
		public void IsValidLetterTest2() {
			Sequence sequence = new Sequence(new char[] {'Q', 'O', 'm'});
			assertFalse(sequence.isValidLetter('5'));
		}



}
