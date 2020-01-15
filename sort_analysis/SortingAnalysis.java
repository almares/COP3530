//Aaron Mares
//COP 3503
//Project 2

import java.util.*;
public class SortingAnalysis {
	
	public static void main (String [] args) {
		
//--------------------------------------------------------------------------------------------------------------------------------------------
//SELECTION SORT
			
		int five [] = generate(5000); //Generate a random list of 5000 elements
		int ten [] = generate(10000); //Generate a random list of 10000 elements
		int twenty [] = generate(20000); //Generate a random list of 20000 elements
		
		int five_rand [] = five; //Generate random array of 5000 and run with selection sort to determine average case complexity
		System.out.println("Selection Sort Average Case for 5000: ");
		System.out.println(selection_test(five_rand, 5000));
		
		selectionSort(five, 5000);
		int five_asc [] = five; //Generate ascending array of 5000 and run with selection sort to determine best case complexity
		System.out.println("Selection Sort Best Case for 5000: ");
		System.out.println(selection_test(five_asc, 5000));
		
		int five_desc [] = reverse(five, 5000); //Generate descending array of 5000 and run with selection sort to determine worst case complexity
		System.out.println("Selection Sort Worst Case for 5000: ");
		System.out.println(selection_test(five_desc, 5000));
		
		int ten_rand [] = ten; //Generate random array of 10000 and run with selection sort to determine average case complexity
		System.out.println("Selection Sort Average Case for 10000: ");
		System.out.println(selection_test(ten_rand, 10000));
		
		selectionSort(ten, 5000);
		int ten_asc [] = ten; //Generate ascending array of 10000 and run with selection sort to determine best case complexity
		System.out.println("Selection Sort Best Case for 10000: ");
		System.out.println(selection_test(ten_asc, 10000));
		
		int ten_desc [] = reverse(ten, 5000); //Generate descending array of 10000 and run with selection sort to determine worst case complexity
		System.out.println("Selection Sort Worst Case for 10000: ");
		System.out.println(selection_test(ten_desc, 10000));
		
		int twenty_rand [] = twenty; //Generate random array of 20000 and run with selection sort to determine average case complexity
		System.out.println("Selection Sort Average Case for 20000: ");
		System.out.println(selection_test(twenty_rand, 20000));
		
		selectionSort(twenty, 20000);
		int twenty_asc [] = twenty; //Generate ascending array of 20000 and run with selection sort to determine best case complexity
		System.out.println("Selection Sort Best Case for 20000: ");
		System.out.println(selection_test(twenty_asc, 20000));
		
		int twenty_desc [] = reverse(twenty, 20000); //Generate descending array of 20000 and run with selection sort to determine worst case complexity
		System.out.println("Selection Sort Worst Case for 20000: ");
		System.out.println(selection_test(twenty_desc, 20000));
		
//--------------------------------------------------------------------------------------------------------------------------------------
//HEAP SORT		
		
		System.out.println();
		five = generate(5000); //Generate new random lists of 5000, 10000, and 20000
		ten = generate(10000);
		twenty = generate(20000);
		
		five_rand = five; //Generate random array of 5000 and run with heap sort to determine average case complexity
		System.out.println("Heap Sort Average Case for 5000: ");
		System.out.println(heap_test(five_rand, 5000));
		
		selectionSort(five, 5000);
		five_asc = five; //Generate ascending array of 5000 and run with heap sort to determine best case complexity
		System.out.println("Heap Sort Best Case for 5000: ");
		System.out.println(heap_test(five_asc, 5000));
		
		five_desc = reverse(five, 5000); //Generate descending array of 5000 and run with heap sort to determine worst case complexity
		System.out.println("Heap Sort Worst Case for 5000: ");
		System.out.println(heap_test(five_desc, 5000));
		
		ten_rand = ten; //Generate random array of 10000 and run with heap sort to determine average case complexity
		System.out.println("Heap Sort Average Case for 10000: ");
		System.out.println(heap_test(ten_rand, 10000));
		
		selectionSort(ten, 10000);
		ten_asc = ten; //Generate ascending array of 10000 and run with heap sort to determine best case complexity
		System.out.println("Heap Sort Best Case for 10000: ");
		System.out.println(heap_test(ten_asc, 10000));
		
		ten_desc = reverse(ten, 5000); //Generate descending array of 10000 and run with heap sort to determine worst case complexity
		System.out.println("Heap Sort Worst Case for 10000: ");
		System.out.println(heap_test(ten_desc, 10000));
		
		twenty_rand = twenty; //Generate random array of 20000 and run with heap sort to determine average case complexity
		System.out.println("Heap Sort Average Case for 20000: ");
		System.out.println(heap_test(twenty_rand, 20000));
		
		selectionSort(twenty, 20000);
		twenty_asc = twenty; //Generate ascending array of 20000 and run with heap sort to determine best case complexity
		System.out.println("Heap Sort Best Case for 20000: ");
		System.out.println(heap_test(twenty_asc, 20000));
		
		twenty_desc = reverse(twenty, 20000); //Generate descending array of 20000 and run with heap sort to determine worst case complexity
		System.out.println("Heap Sort Worst Case for 20000: ");
		System.out.println(heap_test(twenty_desc, 20000));
		
	}
	
	
	public static long selection_test (int numbers[], int size) {
		long startTime; //Initialize start and end time variables 
		long endTime;
		startTime = System.currentTimeMillis(); //Set start time right before the list is sorted
		selectionSort(numbers, size); //Sort list using selection sort
		endTime = System.currentTimeMillis(); //Set end time right after the list is sorted
			
		return (endTime-startTime); //Return the duration of the algorithm
	}
		
	public static long heap_test (int numbers [], int size) {
		long startTime; //Initialize start and end time variables
		long endTime;
		startTime = System.currentTimeMillis(); //Set start time right before the list is sorted
		heapSort(numbers); //Sort the list using heap sort
		endTime = System.currentTimeMillis(); //Set end time right after the list is sorted
		
		return (endTime - startTime); //Return the duration of the algorithm
	}
	
	public static int [] reverse (int numbers [], int numbersSize) {
		for (int i = 0; i < numbersSize; i++)
			numbers[i] *= -1; //Multiply each element in the list by -1, making all integers negative
		selectionSort(numbers, numbersSize); //Sort the negative elements using selection sort
		for (int j = 0 ; j < numbersSize; j++) 
			numbers[j] *= -1; //Multiply each element in the list by -1 again, restoring the original values
		
		return numbers; //Return the now descending ordered list
	}
	
	public static int [] generate(int n) {
		int [] arr = new int [n]; //Create array of size n
		Random rand = new Random(); //Create random object
		for (int i = 0; i < n; i++) {
			arr[i] = rand.nextInt(1000); //Add a random integer from 0 to 1000 at each index of the array
		}
		
		return arr; //Return the randomly sorted array
	}
	
	public static void selectionSort(int numbers [], int numbersSize) {
	     int i = 0;
	     int j = 0;
	     int indexSmallest = 0;
	     int temp = 0;  // Temporary variable for swap
	     for (i = 0; i < numbersSize; ++i) {

	         // Find index of smallest remaining element
	        indexSmallest = i;
	        for (j = i + 1; j < numbersSize; ++j) {
	            if (numbers[j] < numbers[indexSmallest]) {
	            	indexSmallest = j;
	            }
	        }

	         // Swap numbers[i] and numbers[indexSmallest]
	        temp = numbers[i];
	        numbers[i] = numbers[indexSmallest];
	        numbers[indexSmallest] = temp;
	     }
	}
	 
	 
	public static void heapSort(int numbers[]) {
		  int n = numbers.length;
	 
	        // Build heap (rearrange array)
	       for (int i = n / 2 - 1; i >= 0; i--)
	           heapify(numbers, n, i);
	 
	        // One by one extract an element from heap
	       for (int i=n-1; i>=0; i--)
	       {
	            // Move current root to end
	           int temp = numbers[0];
	           numbers[0] = numbers[i];
	           numbers[i] = temp;
	 
	            // call max heapify on the reduced heap
	           heapify(numbers, i, 0);
	       }
	}
	 
	    // To heapify a subtree rooted with node i which is
	    // an index in numbers[]. n is size of heap
	static void heapify(int arr[], int n, int i)
    {
	       int largest = i;  // Initialize largest as root
	       int l = 2*i + 1;  // left = 2*i + 1
	       int r = 2*i + 2;  // right = 2*i + 2
	 
	        // If left child is larger than root
	       if (l < n && arr[l] > arr[largest])
	            largest = l;
	 
	        // If right child is larger than largest so far
	       if (r < n && arr[r] > arr[largest])
	            largest = r;
	 
	        // If largest is not root
	       if (largest != i)
	       {
	           int swap = arr[i];
	           arr[i] = arr[largest];
	           arr[largest] = swap;
	 
	            // Recursively heapify the affected sub-tree
	           heapify(arr, n, largest);
	       }
	}
 
}
