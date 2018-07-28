package edu.iastate.cs228.hw1;

/*
 * @author
 * Matthew Martin
*/

public class CodingDNASequence extends DNASequence
{
  /**
   * If the character array argument has a character on which the method isValidLetter()
   * returns false, then it throws an IllegalArgumentException with the message “Invalid
   * sequence letter for class edu.iastate.cs228.hw1.CodingDNASequence” Otherwise, the
   * constructor saves a copy of the character array argument in the field of its superclass 
   * Sequence.
   * @param cdnaarr
   * 	Character array that is initialized in the super
   */
  public CodingDNASequence(char[] cdnaarr){
	  super(cdnaarr);
  }

  /**
   * If the length of the field character array seqarr is less than 3, then the method
   * returns false. Otherwise, if the first three characters in the array seqarr are A/a,
   * T/t, G/g in this order (case insensitive), then the method returns true. Otherwise,
   * it returns false.
   * @return
   * 	Will return true if the codon has the letters in a correct order and has length of 3 or greater.
   * 	Else will return false
   */
  public boolean checkStartCodon(){
	  if(seqarr.length < 3){
		  return false;
	  }
	  if(((seqarr[0] == 'a') || (seqarr[0] == 'A')) && ((seqarr[1] == 't') || (seqarr[1] == 'T')) && ((seqarr[2] == 'g') || (seqarr[2] == 'G'))){
		  return true;
	  }else{
		  return false;
	  }
  }

  /**
   * The method translates the coding sequence in the character array seqarr into a protein
   * sequence by calling the private method getAminoAcid on every codon in the coding
   * sequence. The translation stops if the method getAminoAcid returns the character
   * ‘$’, which is not part of the protein sequence. Otherwise, the translation stops when
   * the end of the array seqarr is reached.
   * @return
   * 	The protein sequence in a new character array, where the length of the protein sequence is equal to the
   *    length of the array
   */
  public char[] translate(){
	  int seqLength = super.seqarr.length;
	  int nextProtein = 0;
	  boolean isDone = false;
	  char[] proteinSequence = new char[seqarr.length/3];
	  char[] finalProteinSequence;
	  char[] subseq = new char[3];
	  
	  if(this.checkStartCodon() == false){
		  throw new RuntimeException("No Start Codon");
	  }else{
		  for(int i = 0; i < seqLength; i+=3){
			  for(int j = 0; j < 3; j++){
				  if(seqLength <= i + j){
					  isDone = true;
					  break;
				  }
				  subseq[j] = seqarr[i+j];
			  }
			  if(!isDone){
				  String codon = new String(subseq);
				  if(getAminoAcid(codon) == '$'){
					  proteinSequence[nextProtein] = ' ';
					  break;
				  }else{
					  proteinSequence[nextProtein] = getAminoAcid(codon);
					  nextProtein++;
				  }
			  }
		  }
		  int count = 0;
		  for(int i = 0; i < proteinSequence.length; i++){
			  if(proteinSequence[i] == ' '){
				  break;
			  }else{
				  count++;
			  }
		  }
		  finalProteinSequence = new char[count];
		  for(int j = 0; j < finalProteinSequence.length; j++){
			  finalProteinSequence[j] = proteinSequence[j];
		  }
		  return finalProteinSequence;
	  }
  }

  /**
   * If the string argument codon encodes an amino acid, then the method returns the
   * character representing the amino acid.  Otherwise, it returns the character ‘$’.
   * @param codon
   * 	The string that needs to be converted into an amino acid
   * @return
   * 	The amino acid of the string
   */
  private char getAminoAcid(String codon)
  {
    if ( codon == null ) return '$';
    char aa = '$';
    switch ( codon.toUpperCase() )
    {
      case "AAA": aa = 'K'; break;
      case "AAC": aa = 'N'; break;
      case "AAG": aa = 'K'; break;
      case "AAT": aa = 'N'; break;

      case "ACA": aa = 'T'; break;
      case "ACC": aa = 'T'; break;
      case "ACG": aa = 'T'; break;
      case "ACT": aa = 'T'; break;

      case "AGA": aa = 'R'; break;
      case "AGC": aa = 'S'; break;
      case "AGG": aa = 'R'; break;
      case "AGT": aa = 'S'; break;

      case "ATA": aa = 'I'; break;
      case "ATC": aa = 'I'; break;
      case "ATG": aa = 'M'; break;
      case "ATT": aa = 'I'; break;

      case "CAA": aa = 'Q'; break;
      case "CAC": aa = 'H'; break;
      case "CAG": aa = 'Q'; break;
      case "CAT": aa = 'H'; break;

      case "CCA": aa = 'P'; break;
      case "CCC": aa = 'P'; break;
      case "CCG": aa = 'P'; break;
      case "CCT": aa = 'P'; break;

      case "CGA": aa = 'R'; break;
      case "CGC": aa = 'R'; break;
      case "CGG": aa = 'R'; break;
      case "CGT": aa = 'R'; break;

      case "CTA": aa = 'L'; break;
      case "CTC": aa = 'L'; break;
      case "CTG": aa = 'L'; break;
      case "CTT": aa = 'L'; break;

      case "GAA": aa = 'E'; break;
      case "GAC": aa = 'D'; break;
      case "GAG": aa = 'E'; break;
      case "GAT": aa = 'D'; break;

      case "GCA": aa = 'A'; break;
      case "GCC": aa = 'A'; break;
      case "GCG": aa = 'A'; break;
      case "GCT": aa = 'A'; break;

      case "GGA": aa = 'G'; break;
      case "GGC": aa = 'G'; break;
      case "GGG": aa = 'G'; break;
      case "GGT": aa = 'G'; break;

      case "GTA": aa = 'V'; break;
      case "GTC": aa = 'V'; break;
      case "GTG": aa = 'V'; break;
      case "GTT": aa = 'V'; break;

      case "TAA": aa = '$'; break;
      case "TAC": aa = 'Y'; break;
      case "TAG": aa = '$'; break;
      case "TAT": aa = 'Y'; break;

      case "TCA": aa = 'S'; break;
      case "TCC": aa = 'S'; break;
      case "TCG": aa = 'S'; break;
      case "TCT": aa = 'S'; break;

      case "TGA": aa = '$'; break;
      case "TGC": aa = 'C'; break;
      case "TGG": aa = 'W'; break;
      case "TGT": aa = 'C'; break;

      case "TTA": aa = 'L'; break;
      case "TTC": aa = 'F'; break;
      case "TTG": aa = 'L'; break;
      case "TTT": aa = 'F'; break;
      default:    aa = '$'; break;
    }
    return aa;
  }
}
