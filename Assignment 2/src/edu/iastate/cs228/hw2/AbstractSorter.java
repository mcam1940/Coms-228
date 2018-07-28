package edu.iastate.cs228.hw2;

import java.util.Arrays;

/**
 *  
 * @author
 *Matthew Martin
 */

import java.util.Comparator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *  
 * @author
 *Matthew Martin
 */

/**
 * 
 * This abstract class is extended by SelectionSort, InsertionSort, MergeSort, and QuickSort.
 * It stores the input (later on the sorted) sequence and records the employed sorting algorithm, 
 * the comparison method, and the time spent on sorting. 
 *
 */


public abstract class AbstractSorter
{
	
	protected Point[] points;    // Array of points operated on by a sorting algorithm. 
	                             // The number of points is given by points.length.
	
	protected String algorithm = null; // "selection sort", "insertion sort",  
	                                   // "merge sort", or "quick sort". Initialized by a subclass 
									   // constructor.
	protected boolean sortByAngle;     // true if last sort was done by polar angle and false 
									   // if by x-coordinate 
	
	protected String outputFileName;   // "select.txt", "insert.txt", "merge.txt", or "quick.txt"
	
	protected long sortingTime; 	   // execution time in nanoseconds. 
	 
	protected Comparator<Point> pointComparator;  // comparator which compares polar angle if 
												  // sortByAngle == true and x-coordinate if 
												  // sortByAngle == false 
	
	private Point lowestPoint; 	    // lowest point in the array, or in case of a tie, the
									// leftmost of the lowest points. This point is used 
									// as the reference point for polar angle based comparison.

	
	// Add other protected or private instance variables you may need. 
	
	/*protected AbstractSorter()
	{
		// No implementation needed. Provides a default super constructor to subclasses. 
		// Removable after implementing SelectionSorter, InsertionSorter, MergeSorter, and QuickSorter.
	}*/
	
	
	/**
	 * This constructor accepts an array of points as input. Copy the points into the array points[]. 
	 * Sets the instance variable lowestPoint.
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	protected AbstractSorter(Point[] pts) throws IllegalArgumentException{
		if(pts == null || pts.length == 0){
			throw new IllegalArgumentException();
		}
		points = Arrays.copyOf(pts, pts.length);
		
		Point lowest = points[0];
		int smallestIndex = points[0].getY();
		
		for(int i = 0; i < points.length; i++){
		   if(points[i].getY() < smallestIndex){
			   lowest = points[i];
			   smallestIndex = lowest.getY();
		   }else if(points[i].getY() == smallestIndex){
			   if(points[i].getX() < lowest.getX()){
				   lowest = points[i];
			   }
		   }
		}
		
		this.lowestPoint = lowest;
	}

	
	/**
	 * This constructor reads points from a file. Sets the instance variables lowestPoint and 
	 * outputFileName.
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   when the input file contains an odd number of integers
	 */
	protected AbstractSorter(String inputFileName) throws FileNotFoundException, InputMismatchException{
		File file = new File(inputFileName);

		if(!file.exists()){
			throw new FileNotFoundException();
		}
		Scanner scanner = new Scanner(file);
	    int count = 0;
	    while(scanner.hasNextInt()){
	    	scanner.nextInt();
	    	count++;	
	    }
	    scanner.close();
	    	
	    if(count % 2 == 1){
	    	throw new InputMismatchException();
	    }
	    
	    points = new Point[count / 2];
	    Scanner scanner2 = new Scanner(file);
	    
	    int index = 0;
		while(scanner2.hasNext()){
			int nextX = scanner2.nextInt();
			int nextY = scanner2.nextInt();
			points[index] = new Point(nextX, nextY);		
			index++;
		}
		scanner2.close();
		
		
		Point lowestPoint = points[0];
		int lowestY = points[0].getY();
		
		for(int i=1; i<points.length; i++){
			if(points[i].getY() < lowestY){
				lowestPoint = points[i];
				lowestY = lowestPoint.getY();
			}
			if(points[i].getY() == lowestY){
				if(points[i].getX() < lowestPoint.getX()){
					lowestPoint = points[i];
					lowestY = lowestPoint.getY();
				}
			}
		}
		this.lowestPoint = lowestPoint;
	}
	

	/**
	 * Sorts the elements in points[]. 
	 * 
	 *     a) in the non-decreasing order of x-coordinate if order == 1
	 *     b) in the non-decreasing order of polar angle w.r.t. lowestPoint if order == 2 
	 *        (lowestPoint will be at index 0 after sorting)
	 * 
	 * Sets the instance variable sortByAngle based on the value of order. Calls the method 
	 * setComparator() to set the variable pointComparator and use it in sorting.    
	 * Records the sorting time (in nanoseconds) using the System.nanoTime() method. 
	 * (Assign the time to the variable sortingTime.)  
	 * 
	 * @param order  1   by x-coordinate 
	 * 			     2   by polar angle w.r.t lowestPoint 
	 *
	 * @throws IllegalArgumentException if order is less than 1 or greater than 2
	 */
	public abstract void sort(int order) throws IllegalArgumentException; 
	
	
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the assignment description. 
	 */
	public String stats(){
		String stats = algorithm;
		
		for(int i = 0; i < 20 - algorithm.length(); i++){
			stats = stats + " ";
		}
		
		stats = stats + points.length;
		
		for(int i = 0; i < 12 - Integer.toString(points.length).length(); i++){
			stats = stats + " ";
		}
		return stats + sortingTime;
	}
	
	
	/**
	 * Write points[] to a string.  When printed, the points will appear in order of increasing
	 * index with every point occupying a separate line.  The x and y coordinates of the point are 
	 * displayed on the same line with exactly one blank space in between. 
	 */
	@Override
	public String toString(){
		String pointsString = points[0].getX() + " " + points[0].getY();
		for(int i = 1; i < points.length; i++){
			pointsString = pointsString + "\r\n" + points[i].getX() + " " + points[i].getY();
		}
		return pointsString; 
	}

	
	/**
	 *  
	 * This method, called after sorting, writes point data into a file by outputFileName. It will 
	 * be used for Mathematica plotting to verify the sorting result.  The data format depends on 
	 * sortByAngle.  It is detailed in Section 4.1 of the assignment description assn2.pdf. 
	 * 
	 * @throws FileNotFoundException
	 */
	public void writePointsToFile() throws FileNotFoundException{		
		
		PrintWriter writer = new PrintWriter(outputFileName);
		if(sortByAngle == false){
			for(int i = 0; i < points.length; i++){
				writer.println(points[i].getX() + " " + points[i].getY());
			}
		}
		else if(sortByAngle == true){
			for(int j = 0; j < points.length; j++){
				if(j == 0){
					writer.println(points[j].getX() + " " + points[j].getY());
				}else{
					writer.print(points[j].getX() + " " + points[j].getY());
					writer.print(" ");
					writer.print(points[0].getX() + " " + points[j].getY());
					writer.print(" ");
					writer.println(points[j].getX() + " " + points[j].getY());
					
				}
			}
		}
		writer.close();
	}	

	
	/**
	 * Generates a comparator on the fly that compares by polar angle if sortByAngle == true
	 * and by x-coordinate if sortByAngle == false. Set the protected variable pointComparator
	 * to it. Need to create an object of the PolarAngleComparator class and call the compareTo() 
	 * method in the Point class, respectively for the two possible values of sortByAngle.  
	 * 
	 * @param order
	 */
	protected void setComparator() {		
		if(sortByAngle == true){
			 pointComparator = new PolarAngleComparator(lowestPoint);
		 }else if(sortByAngle == false){
			 pointComparator = new Comparator<Point>(){
					 @Override
					 public int compare(Point o1, Point o2){
				 		int result = o1.compareTo(o2);
				 		return result;
			 		 }
			 };
		 }	
	}

	
	/**
	 * Swap the two elements indexed at i and j respectively in the array points[]. 
	 * 
	 * @param i
	 * @param j
	 */
	protected void swap(int i, int j){
		 Point temp = points[i];
		 points[i] = points[j];
		 points[j] = temp;
	}	
}
