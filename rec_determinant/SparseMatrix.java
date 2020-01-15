//Aaron Mares
//COP3503
//Project 1

import java.util.*;

public class SparseMatrix implements SparseInterface{
	
	private LinkedList <E> matrix;
	private int size;

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//Main method 
	
	public static void main(String[] args) {
		Scanner scan = new Scanner (System.in);
		
		SparseMatrix mat = new SparseMatrix(); //Creates SparseMatrix object, mat
		int aa = 0;
		while (aa == 0) {
			System.out.println("Enter your choice: \n");
			System.out.println("1: Clear the matrix ");
			System.out.println("2. Set the size of the matrix (also clears it)");
			System.out.println("3: Add an element to the matrix");
			System.out.println("4: Remove an element from the matrix");
			System.out.println("5: Access an element within the matrix");
			System.out.println("6: Calculate the determinant of the matrix");
			System.out.println("7: Calculate the minor");
			System.out.println("8: Access the size of the matrix");
			System.out.println("9: View the current matrix");
			System.out.println("10: Exit");
		
			int choice = scan.nextInt();
		
			switch(choice) {
			case 1: choice = 1;
				mat.clear(); //Calls the clear method on mat
				break;
			case 2: choice = 2;
				System.out.println("What size would you like the matrix to be (N x N)?");
				int n_size = scan.nextInt();
				mat.setSize(n_size); //Calls the setSize method on mat based on user input
				break;
			case 3: choice = 3;
				System.out.println("Enter the row of the element to be added: ");
				int a_row = scan.nextInt();
				System.out.println("Enter the column of the element to be added: ");
				int a_col = scan.nextInt();
				System.out.println("Enter the data of the element to be added: ");
				int n_data = scan.nextInt();
				
				mat.addElement(a_row, a_col, n_data); //Calls the addElement function on mat based on user specifications
				break;
			case 4: choice = 4;
				System.out.println("Enter the row of the element to be removed: ");
				int r_row = scan.nextInt();
				System.out.println("Enter the column of the element to be removed: ");
				int r_col = scan.nextInt();
				
				mat.removeElement(r_row, r_col); //Calls the removeElement function on mat with user input
				break;
			case 5: choice = 5;
				System.out.println("Enter the row of the element to be accessed: ");
				int ac_row = scan.nextInt();
				System.out.println("Enter the column of the element to be accessed: ");
				int ac_col = scan.nextInt();
				
				System.out.println("Element: " +mat.getElement(ac_row, ac_col)); //Calls and prints out the getElement function
				break;
			case 6: choice = 6;
				System.out.println("Determinant: " +mat.determinant()); //Calls and prints the determinant function
				break;
			case 7: choice = 7;
				System.out.println("Enter the row of the minor's element: ");
				int m_row = scan.nextInt();
				System.out.println("Enter the column of the minor's element: ");
				int m_col = scan.nextInt();
				
				System.out.println("Minor: " +mat.minor(m_row, m_col)); //Calls and prints out the minor matrix function
				break;
			case 8: choice = 8;
				System.out.println(mat.getSize()); //Calls and prints the getSize function
				break;
			case 9: choice = 9;
				System.out.println(mat.toString()); //Calls and prints the toString function
				break;
			case 10: choice = 10;
				System.out.println("\nHave a nice day!"); //Exits the while loop
				++aa;
				break;
			default: System.out.println("Invalid entry, please enter a number 1-10."); //Default error for invalid input
			}
		}	
		
	}

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//Entry class
	
	public class E {
		private int row; //Establishes row, column, and data variables
		private int column;
		private int data;
		
		public E (int r, int c, int d) { //Constructor, sets row, column, and data of E
			this.row = r;
			this.column = c;
			this.data = d;
		}
		
		public E (int r, int c) { //Additional constructor with only row and column parameters given
			this.row = r;
			this.column = c;
		}
		
		public int getRow(){ //Row getter
			return row;
		}
		
		public int getColumn() { //Column getter
			return column;
		}
		
		public int getData() { //Data getter
			return data;
		}
		
		@Override
	    public boolean equals(Object el) { //Determines the equality of the rows and columns of mat and object el
	        return (row == ((E)el).row && column == ((E)el).column);
	    }
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	
	//Constructors
	
	public SparseMatrix() { //Constructor; sets the default size of a new SparseMatrix at 5
		setSize(5); 
	}
	
	
	public SparseMatrix(int size, LinkedList<E> m) { //Additional constructor with added LinkedList element m given
		this.size = size;
		this.matrix = m;
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//Sparse Interface methods

	
	/* Should clear the matrix of all entries (make all entries 0)
	 */
	@Override
	public void clear() { //Clears the matrix by setting it equal to a new LinkedList
		matrix  =  new LinkedList<E>();
	}

	
	/*Sets maximum size of the matrix.  Number of rows. It should also clear the matrix (make all elements 0)
	 */
	@Override
	public void setSize(int size) { //Initially clears the matrix, then sets the size to the function argument passed
		clear();
		this.size = size;
	}

	
	/*Adds an element to the row and column passed as arguments (overwrites if element is already present at that position).
    Throws an error if row/column combination is out of bounds.
    */
	@Override
	public void addElement(int row, int col, int data) {
		if (row < 0 || col < 0 || row >= size || col >= size) //Checks to see if the row or column parameters were larger than the matrix size or negative
			throw new IndexOutOfBoundsException("Error: row or column inputed is not within the specified size or negative"); //Throws OutOfBounds error if they are
		removeElement(row, col); //Removes the current element at the given index
		matrix.add(new E(row, col, data)); //Adds new E with specifications given at the index
	}

	
	/*Remove (make 0) the element at the specified row and column.
    Throws an error if row/column combination is out of bounds.
	*/
	@Override
	public void removeElement(int row, int col) { 
		if (row < 0 || col < 0 || row >= size || col >= size) //Checks to see if the row or column parameters were larger than the matrix size or negative
			throw new IndexOutOfBoundsException("Error: row or column inputed is not within the specified size or negative"); //Throws OutOfBounds error if they are
		matrix.remove(new E(row, col)); //Removes object at that index
	}

	
	/*Return the element at the specified row and column
    Throws an error if row/column combination is out of bounds.
	*/
	@Override
	public int getElement(int row, int col) {
		if (row < 0 || col < 0 || row >= size || col >= size) //Checks to see if the row or column parameters were larger than the matrix size or negative
			throw new IndexOutOfBoundsException("Error: row or column inputed is not within the specified size or negative"); //Throws OutOfBounds error if they are
		
		int y = matrix.indexOf(new E(row, col)); //Creates index variable for the location of the requested element
		
		if(y == -1) { 
			return 0; //Returns 0 if the index is not found
		}
		else return (matrix.get(y)).getData(); //Gets the object and returns its data getter
	}

	
	/*Returns the determinant of the matrix calculated recursively (Use the formula provided in the project description).
	 */
	@Override
	public int determinant() {
		if (size == 1) {
			return getElement(0, 0); //Returns element at 0,0 if there is a single element matrix
		}
		if (size == 2) {
			return (getElement(1,1)*getElement(0,0)) - (getElement(1,0)*getElement(0,1)); //Returns the calculated determinant of a 2X2 matrix
		}
		int det = 0;
		for (int i = 0; i < size; i++) 
			det += (Math.pow(-1, i))*(getElement(i, 0))*(minor(i, 0).determinant()); //Recursively returns the given formula of a determinant with its consequential minor determinants
		return det;
	}

	
	/* Returns a new matrix which is the minor of the original (See project description for minor definition).
     */
	@Override
	public SparseInterface minor(int row, int col) {
		if (row < 0 || col < 0 || row >= size || col >= size) //Checks to see if the row or column parameters were larger than the matrix size or negative
			throw new IndexOutOfBoundsException("Error: row or column inputed is not within the specified size or negative"); //Throws OutOfBounds error if they are
		
		LinkedList<E> minor_mat = new LinkedList<E>(); //Creates minor matrix object
		Iterator<E> i = matrix.iterator(); //Creates iterator set to the existing matrix object iterator
		while (i.hasNext()) {
			E minorInsert = i.next(); //Sets the iterator to the next object
			if (minorInsert.getRow() != row && minorInsert.getColumn() != col) { //Passes if the object is not within the given row or column of the minor element
				if(minorInsert.getRow() > row && minorInsert.getColumn() > col) {
                    minor_mat.add(new E(minorInsert.getRow()-1, minorInsert.getColumn() - 1, minorInsert.getData()));
                }

                else if(minorInsert.getRow() > row) {
                    minor_mat.add(new E(minorInsert.getRow() - 1, minorInsert.getColumn(), minorInsert.getData()));
                }

                else if(minorInsert.getColumn() > col) {
                    minor_mat.add(new E(minorInsert.getRow(), minorInsert.getColumn() - 1, minorInsert.getData()));
                }

                else minor_mat.add(minorInsert); //Adds the object to the minor matrix
            }
		}
		
		return new SparseMatrix(size - 1, minor_mat); //Returns the new minor matrix of 1 less size 
	}
	
	
	/*Should return the nonzero elements of your sparse matrix as a string.
    The String should be k lines, where k is the number of nonzero elements.
    Each line should be in the format "row column data" where row and column are the "coordinate" of the data and all are separated by spaces.
    An empty matrix should return an empty string.
    The print should be from left to right and from top to bottom (like reading a book) i.e. the matrix

                                                     3 0 1
                                                     0 2 0
                                                     0 0 4

                                                 Should print as:
                                                     0 0 3
                                                     0 2 1
                                                     1 1 2
                                                     2 2 4

     */
	public String toString() {
		StringBuilder display = new StringBuilder(); //Creates a StringBuilder to create the display

        for(int row = 0; row < size; row++) {
            for(int col = 0; col < size; col++) {
                if(getElement(row, col) != 0) {
                    display.append(row + " " + col + " " + getElement(row, col) + "\n"); //Appends the StringBuilder with the elements at each row and column index
                }
            }
        }
        return display.toString(); //Return the string
	}

	
	/*Should return the size of the matrix.
     */
	@Override
	public int getSize() {
		return size; //Return the size of the current matrix
	}
}
