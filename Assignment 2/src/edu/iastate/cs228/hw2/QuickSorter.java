package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.util.InputMismatchException;
import java.lang.IllegalArgumentException; 


/**
 *  
 * @author
 * Matthew Martin 
 */

/**
 * 
 * This class implements the version of the quicksort algorithm presented in the lecture.   
 *
 */

public class QuickSorter extends AbstractSorter
{
	
	// Other private instance variables if you need ... 

	/**
	 * The two constructors below invoke their corresponding superclass constructors. They
	 * also set the instance variables algorithm and outputFileName in the superclass.
	 */

	/** 
	 * Constructor accepts an input array of points. 
	 *   
	 * @param pts   input array of integers
	 */
	public QuickSorter(Point[] pts){
		super(pts);
		outputFileName = "quick.txt";
		algorithm = "quick sort"; 
	}
		

	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException 
	 */
	public QuickSorter(String inputFileName) throws InputMismatchException, FileNotFoundException {
		super(inputFileName);
		outputFileName = "quick.txt";
		algorithm = "quick sort"; 
	}


	/**
	 * Carry out quicksort on the array points[] of the AbstractSorter class.  
	 * 
	 * @param order  1   by x-coordinate 
	 * 			     2   by polar angle 
	 *
	 */
	@Override 
	public void sort(int order)
	{
		if(order == 1){
			sortByAngle = false;
		}
		if(order == 2){
			sortByAngle = true;
		}
		
		setComparator();
		long startTime = System.nanoTime();
		quickSortRec(0, points.length - 1);
		long endTime = System.nanoTime();
		sortingTime = endTime - startTime;
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last. 
	 * 
	 * @param first  starting index of the subarray
	 * @param last   ending index of the subarray
	 */
	private void quickSortRec(int first, int last)
	{
		if (first < last){
	         int index = partition(first, last);
	         quickSortRec(first, index - 1);
	         quickSortRec(index, last);
	      }
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	private int partition(int first, int last){
		  int i = first;
	      int j = last;
	      int pivot = (i + j) / 2;
	      Point pVal = points[pivot];
	      
	      while (i <= j){
	         while (pointComparator.compare(points[i], pVal) == -1){
	            ++i;
	         }
	 
	         while (pointComparator.compare(points[j], pVal) == 1){
	            --j;
	         }
	 
	         if (i <= j){
	            swap(i, j);
	            ++i;
	            --j;
	         }
	      }
	      
	      return i;

	}	
	// Other private methods in case you need ...
}
