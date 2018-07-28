package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProteinSequenceTest {

	@Test(expected=IllegalArgumentException.class)
	public void constuctor_test_1() {
		ProteinSequence my_ProteinSequence = new ProteinSequence(new char[] {'M', 'o'});
	}

	@Test(expected=IllegalArgumentException.class)
	public void constuctor_test_2() {
		ProteinSequence my_ProteinSequence = new ProteinSequence(new char[] {'F', 'M', 'b', 'j', '1'});
	}
	

}
