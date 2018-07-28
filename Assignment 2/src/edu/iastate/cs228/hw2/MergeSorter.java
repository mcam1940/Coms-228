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
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	// Other private instance variables if you need ... 
	
	/**
	 * The two constructors below invoke their corresponding superclass constructors. They
	 * also set the instance variables algorithm and outputFileName in the superclass.
	 */

	/** 
	 * Constructor accepts an input array of points. 
	 * in the array. 
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		super(pts); 
		outputFileName = "merge.txt";
		algorithm = "merge sort"; 
	}
	
	
	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException 
	 */
	public MergeSorter(String inputFileName) throws InputMismatchException, FileNotFoundException 
	{
		super(inputFileName);
		outputFileName = "merge.txt";
		algorithm = "merge sort"; 
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 * @param order  1   by x-coordinate 
	 * 			     2   by polar angle 
	 *
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
		mergeSortRec(super.points);
		
		long endTime = System.nanoTime() - sortTime;
		super.sortingTime = endTime;
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts){
		if(pts.length > 1){
			Point[] firstPart = new Point[pts.length / 2];
			System.arraycopy(pts, 0, firstPart, 0, pts.length / 2);
			mergeSortRec(firstPart);
			
			int secondPartLength = pts.length - pts.length/2;
			Point[] secondPart = new Point[secondPartLength];
			System.arraycopy(pts, pts.length / 2, secondPart, 0, secondPartLength);
			mergeSortRec(secondPart);
			
			merge(firstPart, secondPart, pts);
			
		}


	}

	// Other private methods in case you need ...

	private void merge(Point[] list1, Point[] list2, Point[] temp)
	{
		int temp1 = 0;
		int temp2 = 0;
		int temp3 = 0;
		
		while(temp1 < list1.length && temp2 < list2.length){
			if(super.pointComparator.compare(list1[temp1], list2[temp2]) == -1){
				temp[temp3++] = list1[temp1++];
			}else{
				temp[temp3++] = list2[temp2++];
			}
		}
		while(temp1 < list1.length){
			temp[temp3++] = list1[temp1++];
		}
		while(temp2 < list2.length){
			temp[temp3++] = list2[temp2++];
		}
	}

}
