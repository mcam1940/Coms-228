package edu.iastate.cs228.hw1;

/*
 * @author
 * Matthew Martin
*/

public class DNASequence extends Sequence
{

  /**
   * Constructor that calls the super to initialize the character array dnaarr	
   * @param dnaarr
   * 	The character array dnaarr of dna sequences
   */
  public DNASequence(char[] dnaarr){
	  super(dnaarr);
  }

  @Override
  public boolean isValidLetter(char let){
	  String validLetters = "aAcCgGtT";
	  if (validLetters.indexOf(let) >= 0){
		  return true;
	  }else{
		  return false;
	  }
  }
  
  /**
   * The method produces the reverse complement of the DNA sequence saved in the
   * char array seqarr, and returns a char array with the resulting sequence. DNA is
   * double-stranded with one strand being the reverse complement of the other strand.
   * The reverse complement of a DNA sequence is obtained by reversing the sequence
   * (turning it by 180 degrees or making the right end left) and complementing each base
   * (changing A, C, G and T into T, G, C, and A, respectively and changing a, c, g, and
   * t into t, g, c and a, respectively). See the example below.
   * @return
   * 	A character array of the reverse complement of the DNA sequence.
   */
  public char[] getReverseCompSeq(){
	  char[] reverseComp = new char[seqarr.length];
	  for(int i = 0; i < seqarr.length; i++){
		  reverseComp[i] = seqarr[reverseComp.length-(i+1)];
	  }
	  for(int j = 0; j < reverseComp.length; j++){
		  if(reverseComp[j] == 'a'){
			  reverseComp[j] = 't';
		  }else if(reverseComp[j] == 't'){
			  reverseComp[j] = 'a';
		  }else if(reverseComp[j] == 'g'){
			  reverseComp[j] = 'c';
		  }else if(reverseComp[j] == 'c'){
			  reverseComp[j] = 'g';
		  }else if(reverseComp[j] == 'T'){
			  reverseComp[j] = 'A';
		  }else if(reverseComp[j] == 'A'){
			  reverseComp[j] = 'T';
		  }else if(reverseComp[j] == 'G'){
			  reverseComp[j] = 'C';
		  }else if(reverseComp[j] == 'C'){
			  reverseComp[j] = 'G';
		  }
	  }
	  return reverseComp;
  }

  /**
   * The method calls getReverseCompSeq() and saves the result in a temporary array
   * and then copies the array back into the char array seqarr. As a result, the char
   * array seqarr contains the reverse complement of its original sequence. When a coding
   * region is in the other strand of one in char array seqarr, this method is used to save
   * the other strand in the char array seqarr. See markCoding(int first, int last)
   * below.
   */
  public void reverseComplement(){
	  char[] complement = new char[seqarr.length];
	  complement = getReverseCompSeq();
	  seqarr = complement.clone();
  }
}
