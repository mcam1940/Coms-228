package edu.iastate.cs228.hw1;

/*
 * @author
 * Matthew Martin
*/

public class ProteinSequence extends Sequence
{
  /**
   * If the character array argument psarr contains a character on which the method
   * isValidLetter() returns false, then it throws an IllegalArgumentException with
   * the message “Invalid sequence letter for class edu.iastate.cs228.hw1.ProteinSequence”
   * Otherwise, the constructor saves a copy of the character array argument in the field
   * of its superclass Sequence.
   * @param psarr
   * 	character array of proteins that is initialized using the super
   */
  public ProteinSequence(char[] psarr){
	  super(psarr);
  }

  @Override
  public boolean isValidLetter(char aa){
	  char lowerCase = Character.toLowerCase(aa);
	  boolean validLetter = "bjouxz".indexOf(lowerCase) == -1;
	  return validLetter;
  }
}
