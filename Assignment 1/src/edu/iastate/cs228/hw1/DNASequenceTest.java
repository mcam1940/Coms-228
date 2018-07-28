package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DNASequenceTest {

	char[] sequence = {'T', 'C', 'A', 'G', 'A', 'T', 'G', 'G', 'A', 'C', 'A', 'A', 'G', 'G', 'C' };
	char[] seqlow = {'t', 'c', 'a', 'g', 'a', 't', 'g', 'g', 'a', 'c', 'a', 'a', 'g', 'g', 'c' };

	DNASequence seqq = new DNASequence(sequence);
	DNASequence seqqlow = new DNASequence(seqlow);
	
	@Rule
	public ExpectedException expected = ExpectedException.none();
	
	@Test
	public void testIllegalException() {
		expected.expect(IllegalArgumentException.class);
		expected.expectMessage("Invalid  sequence  letter  for  class edu.iastate.cs228.hw1.DNASequence");
		char [] testCharacters = {'2', 'c', '#'};
		DNASequence tester = new DNASequence(testCharacters);
	}
	@Test
	public void testIsValidLetterm() {
		assertFalse(seqq.isValidLetter('m'));
	}
	@Test
	public void testIsValidLetterM() {
		assertFalse(seqq.isValidLetter('M'));
	}
	@Test
	public void testIsValidLettera() {
		assertTrue(seqq.isValidLetter('a'));
	}
	@Test
	public void testIsValidLetterA() {
		assertTrue(seqq.isValidLetter('A'));
	}
	@Test
	public void testIsValidLetterg() {
		assertTrue(seqq.isValidLetter('g'));
	}
	@Test
	public void testIsValidLetterG() {
		assertTrue(seqq.isValidLetter('G'));
	}
	@Test
	public void testIsValidLetterC() {
		assertTrue(seqq.isValidLetter('C'));
	}
	@Test
	public void testIsValidLetterc() {
		assertTrue(seqq.isValidLetter('C'));
	}
	@Test
	public void testIsValidLetterT() {
		assertTrue(seqq.isValidLetter('T'));
	}
	@Test
	public void testIsValidLettert() {
		assertTrue(seqq.isValidLetter('t'));
	}
	@Test
	public void testIsValidLetter$() {
		assertFalse(seqq.isValidLetter('$'));
	}
	
	@Test
	public void testDNASequencework() {
		char [] finals = {'G', 'C', 'C', 'T', 'T', 'G', 'T', 'C', 'C', 'A', 'T', 'C', 'T', 'G', 'A'};
		assertArrayEquals(seqq.getReverseCompSeq(), finals);
	}
	
	@Test
	public void testDNASequencelowwork() {
		char [] finals = {'g', 'c', 'c', 't', 't', 'g', 't', 'c', 'c', 'a', 't', 'c', 't', 'g', 'a'};
		
		assertArrayEquals(seqqlow.getReverseCompSeq(), finals);
	}
	
	@Test
	public void testDNASequencefail() {
		
		char [] finals = {'G', 'C', 'G', 'T', 'G', 'G', 'T', 'C', 'G', 'A', 'G', 'C', 'T', 'G', 'A'};
		

		assertFalse(Arrays.equals(seqq.getReverseCompSeq(), finals));
		
	}
	
	@Test
	public void testDNASequencelowfail() {
		
		char [] finals = {'g', 'c', 'c', 'a', 'a', 't', 't', 'c', 'c', 'a', 't', 'c', 't', 'g', 'a'};
		

		assertFalse(Arrays.equals(seqqlow.getReverseCompSeq(), finals));
		
	}
	
	@Test
	public void testGetReverseCompSeqwork() {
		seqq.reverseComplement();
		char [] finals = {'G', 'C', 'C', 'T', 'T', 'G', 'T', 'C', 'C', 'A', 'T', 'C', 'T', 'G', 'A'};
		assertArrayEquals(seqq.getSeq(), finals);
	}
	
	@Test
	public void testGetReverseCompSeqlowwork() {
		seqqlow.reverseComplement();
		char [] finals = {'g', 'c', 'c', 't', 't', 'g', 't', 'c', 'c', 'a', 't', 'c', 't', 'g', 'a'};
		assertArrayEquals(seqqlow.getSeq(), finals);
	}
	
	@Test
	public void testGetReverseCompSeqfail() {
		seqq.reverseComplement();
		char [] finals = {'G', 'C', 'G', 'T', 'G', 'G', 'T', 'C', 'G', 'A', 'G', 'C', 'T', 'G', 'A'};
		assertFalse(Arrays.equals(seqq.getSeq(), finals));
	}
	
	@Test
	public void testGetReverseCompSeqlowfail() {
		seqqlow.reverseComplement();
		char [] finals = {'g', 'c', 'a', 't', 't', 'g', 't', 'c', 'c', 'a', 'a', 'c', 't', 'g', 'a'};
		assertFalse(Arrays.equals(seqqlow.getSeq(), finals));
	}
	
	@Test
	public void testGetReverseCompSeqfail2() {
		seqq.reverseComplement();
		char [] finals = {'G', 'F', 'G', 'T', 'T', 'A', 'C', 'G', 'C', 'A', 'T', 'C', 'T', 'G', 'A'};
		assertFalse(Arrays.equals(seqq.getSeq(), finals));
	}


}
