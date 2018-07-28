package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import org.junit.Test;

public class GenomicDNASequenceTest {

	@Test(expected=IllegalArgumentException.class)
	public void constructorTest(){
		GenomicDNASequence genomicDNASequenceTester = new GenomicDNASequence(new char[] {'c', 'm', 'a', 'G', 'T'});
	}

	@Test(expected=IllegalArgumentException.class)
	public void constructorTest2(){
		GenomicDNASequence genomicDNASequenceTester = new GenomicDNASequence(new char[] {'G', 'a', '7', 'T'});
	}

	@Test()
	public void extractExonsTest() {
		GenomicDNASequence genomicDNASequenceTester = new GenomicDNASequence(new char[] {'c', 'C', 'a', 'G', 'T', 'T', 'g', 'a', 't', 't', 't', 'A'});
		genomicDNASequenceTester.markCoding(1, 7);
		assertEquals("CaGTTga", new String(genomicDNASequenceTester.extractExons(new int[] {1,7})).replace("[]", ""));
		
	}

	@Test(expected=IllegalStateException.class)
	public void extractExonsTest2() {
		GenomicDNASequence genomicDNASequenceTester = new GenomicDNASequence(new char[] {'c', 'C', 'a', 'G', 'T', 'T', 'g', 'a', 't', 't', 't', 'A'});
		genomicDNASequenceTester.markCoding(1, 2);
		assertEquals("CaGTTg", new String(genomicDNASequenceTester.extractExons(new int[] {1,7})).replace("[]", ""));
	}

	@Test(expected=IllegalArgumentException.class)
	public void extractExonsTest3() {
		GenomicDNASequence genomicDNASequenceTester = new GenomicDNASequence(new char[] {'c', 'C', 'a', 'G', 'T', 'T', 'g', 'a', 't', 't', 't', 'A'});
		genomicDNASequenceTester.markCoding(1, 2);
		assertEquals("CaGTTg", new String(genomicDNASequenceTester.extractExons(new int[] {1,999})).replace("[]", ""));
	}

	@Test(expected=IllegalArgumentException.class)
		public void extractExonsTest4() {
			GenomicDNASequence genomicDNASequenceTester = new GenomicDNASequence(new char[] {'c', 'C', 'a', 'G', 'T', 'T', 'g', 'a', 't', 't', 't', 'A'});
			genomicDNASequenceTester.markCoding(1, 2);
			assertEquals("CaGTTg", new String(genomicDNASequenceTester.extractExons(new int[] {1,999,4})).replace("[]", ""));
		}

	@Test(expected=IllegalArgumentException.class)
		public void extractExonsTest5() {
			GenomicDNASequence genomicDNASequenceTester = new GenomicDNASequence(new char[] {'c', 'C', 'a', 'G', 'T', 'T', 'g', 'a', 't', 't', 't', 'A'});
			genomicDNASequenceTester.markCoding(1, 10);
			assertEquals("CaGTTg", new String(genomicDNASequenceTester.extractExons(new int[] {1,9,4,5})).replace("[]", ""));
		}


}
