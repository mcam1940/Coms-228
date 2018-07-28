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
 * This class implements selection sort.   
 *
 */

public class SelectionSorter extends AbstractSorter
{
	// Other private instance variables if you need ... 
	private Point[] pointArr;
	
	/**
	 * The two constructors below invoke their corresponding superclass constructors. They
	 * also set the instance variables algorithm and outputFileName in the superclass.
	 */

	/**
	 * Constructor takes an array of points.
	 *  
	 * @param pts  
	 */
	public SelectionSorter(Point[] pts)  
	{
		super(pts);
		outputFileName = "select.txt";
		algorithm = "selection sort";
	}	

	
	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException 
	 */
	public SelectionSorter(String inputFileName) throws InputMismatchException, FileNotFoundException 
	{
		super(inputFileName);
		outputFileName = "select.txt";
		algorithm = "selection sort";
	}
	
	
	/** 
	 * Apply selection sort on the array points[] of the parent class AbstractSorter.  
	 *
	 * @param order  1   by x-coordinate 
	 * 			     2   by polar angle 
	 *
	 */
	@Override 
	public void sort(int order){
		long sortingTime = System.nanoTime();
		
		if(order == 1){
			sortByAngle = false;
		}
		
		if(order == 2){
			sortByAngle = true;
		}
		
		super.setComparator();
		
		for(int i = 0; i < points.length - 1; i++){
			int index = i;
			for(int j = i+1; j < points.length; j++){
				if(super.pointComparator.compare(points[j], points[index]) == -1){
					index = j;
				}
			}
			Point smallestPoint = points[index];
			points[index] = points[i];
			points[i] = smallestPoint;				
		}
		long endTime = System.nanoTime() - sortingTime;
		super.sortingTime = endTime;
	}		
}
