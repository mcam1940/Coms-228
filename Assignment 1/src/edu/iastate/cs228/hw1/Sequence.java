package edu.iastate.cs228.hw1;

/*
 * @author
 * Matthew Martin
*/

public class Sequence
{
  public char[] seqarr; // made public instead of protected for grading.
  
  /**
   * Constructor that checks that every character is valid and then uses the super class to assign the character array sarr
   * @param sarr
   * 	Character array that has the sequence of letters
   */
  public Sequence(char[] sarr){
	  for(char c : sarr){
		  if(!isValidLetter(c)){
			  throw new IllegalArgumentException("Invalid sequence letter for class " + this.getClass().getName());
		  }
	  }
	  
	  seqarr = sarr.clone();
  }
  
  /**
   * The method returns the length of the character array seqarr.
   * @return the length of seqarr
   */
  public int seqLength(){
	  return seqarr.length;
  }
  
  /**
   * The method makes and returns a copy of the char array seqarr
   * @return character array copy of seqarr
   */
  public char[] getSeq(){
	  char[] seqarrCopy = seqarr.clone();
	  return seqarrCopy;
  }

  /**
   * The method returns the string representation of the char array seqarr.
   * @return A string variable of seqarr
   */
  public String toString(){
	  String seqarrString = new String (seqarr);
	  return seqarrString;
  }

  /**
   * The method returns true if the argument obj is not null and is of the same type
   * as this object such that both objects represent the identical sequence of characters
   * in a case insensitive mode (”ACgt” is identical to ”AcGt”). The equals() method
   * should be defined in such a way that there is no need to define it again in any subclass
   * of class Sequence. In other words, when an object of the subclass calls the equals()
   * method, it should return the right answer.
   * @param obj
   * 	an object that is checked to make sure it exists and is the same as in the class
   * @return True if object is not null and false if it is
   */
  public boolean equals(Object obj){ 
	  int count = 0;
	  if((obj != null) && (obj.getClass() == this.getClass())){
		  Sequence test = (Sequence) obj;
		  char[] sequence = test.getSeq();
		  
		  if(this.seqLength() == test.seqLength()){
			  for(int i = 0; i < this.seqLength(); i++){
				  if(Character.toUpperCase(seqarr[i]) == Character.toUpperCase(sequence[i])){
					  count++;
				  }
			  }
		  }
	  }
	  
	  if(count == this.seqLength()){
		  return true;
	  }
	  return false;
  }

  /**
   * The method returns true if the character let is an uppercase (Character.isUpperCase(let)
   *	is true) or lowercase (Character.isLowerCase(let) is true). Otherwise, it returns
   *	false.
   * @param let
   * 	A character to see if it is valid.
   * @return If the character is a valid uppercase or lowercase letter
   */
  public boolean isValidLetter(char let){
	  if((Character.isUpperCase(let) == true) || (Character.isLowerCase(let) == true)){
		  return true;
	  }else{
		  return false;
	  }
  }

}
