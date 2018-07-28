package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import org.junit.Test;

public class CodingDNASequenceTest {

		@Test(expected=IllegalArgumentException.class)
		public void constructor_test_1(){
			CodingDNASequence my_CodingDNASequence = new CodingDNASequence(new char[] {'c', 't', 'a', 'M', 'T'});
		}

		@Test
		public void checkStartCodon_test_1(){
			CodingDNASequence my_CodingDNASequence = new CodingDNASequence(new char[] {'c', 'T', 'A', 'G', 'T'});
			assertFalse(my_CodingDNASequence.checkStartCodon());
		}

		@Test
		public void checkStartCodon_test_2(){
			CodingDNASequence my_CodingDNASequence = new CodingDNASequence(new char[] {'a', 'T', 'g', 'G', 'T'});
			assertTrue(my_CodingDNASequence.checkStartCodon());
		}

		@Test
		public void translate_test_1(){
			CodingDNASequence my_CodingDNASequence = new CodingDNASequence(new char[] {'a', 'T', 'g', 'G', 'T', 'A', 'a', 'a', 'T'});
			assertEquals(new String(my_CodingDNASequence.translate()), "MVN");
		}

		@Test
		public void translate_test_2(){
			CodingDNASequence my_CodingDNASequence = new CodingDNASequence(new char[] {'a', 'T', 'g', 't', 'a', 'G', 'a', 'a', 'T'});
			assertEquals(new String(my_CodingDNASequence.translate()), "M");
		}



}
