package edu.iastate.cs228.hw1;

/*
 * @author
 * Matthew Martin
*/

public class GenomicDNASequence extends DNASequence
{
  public boolean[] iscoding; // made public instead of private for grading.
  
  /**
   * If the character array argument has a character on which the method isValidLetter()
   * returns false, then it throws an IllegalArgumentException with the message “Invalid
   * sequence letter for class edu.iastate.cs228.hw1.GenomicDNASequence” Otherwise,
   * the constructor saves a copy of the character array argument in the field of its
   * superclass Sequence. And it creates a boolean array of the same length as gdnaarr,
   * saves its reference in the field iscoding, and sets the boolean array to false at each
   * index.
   * @param gdnaarr
   * 	Character array that is initialized by using the super.
   */
  public GenomicDNASequence(char[] gdnaarr){
	  super(gdnaarr);
	  boolean[] tempDNASequence = new boolean[gdnaarr.length];
	  iscoding = tempDNASequence;
	  for(int i = 0; i < gdnaarr.length; i++){
		  iscoding[i] = false;
	  }
  }

  /**
   * The method throws an IllegalArgumentException exception with the message
   * “Coding border is out of bound” if first or last is less than 0, or first or last
   * is greater than or equal to the length (named slen) returned by the seqLength()
   * method. If first is greater than last, then obtain the coding strand by calling the
   * method reverseComplement() and transforming first and last with the formula
   * x = slen − 1 − x. Otherwise, the sequence
   * saved in the field seqarr is the coding strand, so no reverse complementation or
   * transformation is needed. Finally, the method sets the boolean array iscoding to
   * true at each index between first and last inclusive.
   * @param first
   * 	Variable to tell when the first of the coding strand is
   * @param last
   * 	Variable to tell when the last of the coding strand is
   */
  public void markCoding(int first, int last){
	  
	  int sequenceLength = this.seqLength();
	  
	  if((first < 0) || (last < 0) || (first >= seqLength()) || (last >= seqLength())){
		  throw new IllegalArgumentException("Coding border is out of bound");
	  }else if(first > last){
		  this.reverseComplement();
		  first = sequenceLength - 1 - first;
		  last = sequenceLength - 1 - last;
	  }
	  for(int i = 0; i <= last; i++){
		  iscoding[i] = true;
	  }
  }

  /**
   * The method takes all the coding exons specified by the array exonpos, concatenates them in order, and returns
   * the resulting sequence in a new character array. Note that the
   * array length is the length of the resulting sequence.
   * @param exonpos
   * 	Used to specify the start and end positions of every coding exon in the genomic sequence
   * @return
   * 	The sequence of codons in a character array
   */
  public char[] extractExons(int[] exonpos){
    if((exonpos.length == 0) || (exonpos.length % 2 == 1)){
    	throw new IllegalArgumentException("Empty array or odd number of array elements");
    }
    for(int i = 0; i < exonpos.length; i++){
    	if((exonpos[i] < 0) || exonpos[i] >= seqLength()){
    		throw new IllegalArgumentException("Exon position is out of bound");
    	}
    }
    for(int i = 0; i < exonpos.length-1; i++){
    	if(exonpos[i] > exonpos[i+1]){
    		throw new IllegalArgumentException("Exon positions are not in order");
    	}
    }
    for(int i = 0; i < exonpos.length; i++){
    	if(iscoding[exonpos[i]] == false){
    		throw new IllegalStateException("Noncoding position is found");
    	}
    }
    
    int postMalone = 0; //Counter variable
    for(int i = 0; i < exonpos.length; i++){
    	int num1;
    	int num2;
    	num1 = exonpos[i];
    	num2 = exonpos[i+1];
    	i++;
    	postMalone = postMalone + ((num2 - num1)+1); //postMalone is counter
    }
    
    char[] seq = new char[postMalone];
    int jCole = 0; //Counter variable
    int num1;
	int num2;
    
    for(int i = 0; i < exonpos.length; i++){
    	num1 = exonpos[i];
    	num2 = exonpos[i+1];
    	
    	for(int j = num1; j <= num2; j++){
    		seq[jCole] = seqarr[j];//jCole is a counter
    		jCole++;//counter increases
    	}
    	i++;
    }
    
    return seq;
  }

}
