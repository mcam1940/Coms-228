package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.util.InputMismatchException;
import java.lang.IllegalArgumentException; 


/**
 *  
 * @author
 *Matthew Martin
 */

/**
 * 
 * This class implements insertion sort.   
 *
 */

public class InsertionSorter extends AbstractSorter 
{
	// Other private instance variables if you need ... 
	
	/**
	 * The two constructors below invoke their corresponding superclass constructors. They
	 * also set the instance variables algorithm and outputFileName in the superclass.
	 */

	/**
	 * Constructor takes an array of points. 
	 * 
	 * @param pts  
	 */
	public InsertionSorter(Point[] pts){
		super(pts);
		outputFileName = "insert.txt";
		algorithm = "insertion sort";
	}	

	
	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException 
	 */
	public InsertionSorter(String inputFileName) throws InputMismatchException, FileNotFoundException{
		super(inputFileName);
		outputFileName = "insert.txt";
		algorithm = "insertion sort";
	}
	
	
	/** 
	 * Perform insertion sort on the array points[] of the parent class AbstractSorter.  
	 * 
	 * @param order  1   by x-coordinate 
	 * 			     2   by polar angle 
	 */
	@Override 
	public void sort(int order){
		long sortTime = System.nanoTime();
		
		if(order == 1){
			sortByAngle = false;
		}
	
		if(order == 2){
			sortByAngle = true;
		}
	
		super.setComparator();
		
		Point currentPoint;
		for(int i = 1; i < points.length; i++){
			currentPoint = points[i];
			int j = i-1;
			while((j > -1) && super.pointComparator.compare(points[j], currentPoint) == 1){
				points[j+1] = points[j];
				j--;
			}
			points[j+1] = currentPoint;
		}
	
		long endTime = System.nanoTime() - sortTime;
		super.sortingTime = endTime;
	
	}		

	
}				

