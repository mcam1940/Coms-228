package edu.iastate.cs228.hw2;

/**
 *  
 * @author
 *Matthew Martin
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random; 


public class CompareSorters 
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Perform the four sorting algorithms over each sequence of integers, comparing 
	 * points by x-coordinate or by polar angle with respect to the lowest point.  
	 * 
	 * @param args
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException 
	 **/
	public static void main(String[] args) throws InputMismatchException, FileNotFoundException 
	{			
		// TODO 
		// 
		// Conducts multiple sorting rounds. In each round, performs the following: 
		// 
		//    a) If asked to sort random points, calls generateRandomPoints() to initialize an array 
		//       of random points. 
		//    b) Reassigns to elements in the array sorters[] (declared below) the references to the 
		//       four newly created objects of SelectionSort, InsertionSort, MergeSort and QuickSort. 
		//    c) Based on the input point order, carries out the four sorting algorithms in a for 
		//       loop that iterates over the array sorters[], to sort the randomly generated points
		//       or points from an input file.  
		//    d) Meanwhile, prints out the table of runtime statistics.
		// 
		// A sample scenario is given in Section 2 of the assignment description. 
		// 	
		
		
		// Within a sorting round, every sorter object write its output to the file 
		// "select.txt", "insert.txt", "merge.txt", or "quick.txt" if it is an object of 
		// SelectionSort, InsertionSort, MergeSort, or QuickSort, respectively. 
		
		AbstractSorter[] sorted = new AbstractSorter[4];
		Scanner scanner = new Scanner(System.in);
		int scannedNumber;
		int order;
		int pointCount;
		int trialNum = 1;
		String filename = "";
		
		System.out.println("keys: 1 (random integers) 2 (file input) 3 (exit)\n"
				         + "order: 1 (by x-coordinate) 2 (by polar angle)");
		while (true) {
			scannedNumber = 0;
			order = 0;
			pointCount = 0;
			
			System.out.print("\n\nTrial " + trialNum + " => Enter key: ");
			scannedNumber = scanner.nextInt();
			
			if (scannedNumber == 1){
				System.out.print("Enter number of random points: ");
				pointCount = scanner.nextInt();
				Random generator = new Random();
				Point[] pointsArray = generateRandomPoints(pointCount, generator);
				
				sorted[0] = new SelectionSorter(pointsArray);
				sorted[1] = new InsertionSorter(pointsArray);
				sorted[2] = new MergeSorter(pointsArray);
				sorted[3] = new QuickSorter(pointsArray);
			}else if (scannedNumber == 2) {
				System.out.print("Points from a file\nFile name: ");
				filename = scanner.next();
				
				sorted[0] = new SelectionSorter(filename);
				sorted[1] = new InsertionSorter(filename);
				sorted[2] = new MergeSorter(filename);
				sorted[3] = new QuickSorter(filename);
			}else{
					Runtime.getRuntime().exit(0);
			}
			System.out.print("Order used in sorting: ");
			order = scanner.nextInt();
			System.out.println("\n\nalgorithm           size         time (ns)"
					           + "\n---------------------------------------------\n");
			
			sorted[0].sort(order);
			sorted[1].sort(order);
			sorted[2].sort(order);
			sorted[3].sort(order);
			
			sorted[0].writePointsToFile();
			
			System.out.println(sorted[0].stats());
			System.out.println(sorted[1].stats());
			System.out.println(sorted[2].stats());
			System.out.println(sorted[3].stats());
			System.out.println("---------------------------------------------");
			trialNum++;
			
			scanner.close();
		}

		
		// Within a sorting round, every sorter object write its output to the file 
		// "select.txt", "insert.txt", "merge.txt", or "quick.txt" if it is an object of 
		// SelectionSort, InsertionSort, MergeSort, or QuickSort, respectively. 
		
	}
	
	
	/**
	 * This method generates a given number of random points to initialize randomPoints[].
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] � [-50,50]. Please refer to Section 3 of assignment description document on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */ 
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException{ 
		if (numPts < 1){
			throw new IllegalArgumentException("Must have at least 2 points");
		}
		int x = 0;
		int y = 0;
		
		Point[] pointsArray = new Point[numPts];
		
		for (int i = 0; i < numPts; i++) {
			x = rand.nextInt(101) - 50;
			y = rand.nextInt(101) - 50;
			
			pointsArray[i] = new Point(x, y);
		}
		
		return pointsArray;
	}
}
