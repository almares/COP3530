import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HuffmanEncoder implements HuffmanCoding {

	//build a heap from the string of frequencies
	public Heap heapConstruct(String chars) {

		Heap heap = new Heap();

		//the start of the new appearance starts at 0 and then occurs at i + 2
		int iterator = 0;
		String appearance;

		//iterate over each character in the frequency string
		for (int i = 0; i < chars.length(); i++) {

			//if its a newline then we've found the end of an appearance
			if (chars.charAt(i) == '\n') {

				//grab the appearance
				appearance = chars.substring(iterator, i);

				//insert a new huffTree into the heap, building it from the appearance
				heap.insert(new HuffTree(appearance.charAt(0), Integer.valueOf(appearance.substring(appearance.lastIndexOf(' ')+1)) ));

				//move i to the character after the newline
				iterator = i + 1;
			}
		}

		//return the heap
		return heap;
	}

	public StringBuilder convert(StringBuilder text, HuffBaseNode base, StringBuilder converted) {

		if (base.isLeaf()) {

			converted.append(  String.valueOf(((HuffLeafNode)base).value()));
			return text;
		}

		else if (text.length() == 0) return new StringBuilder();

		else if (text.charAt(0) == '1') {
			return convert(text.deleteCharAt(0), ((HuffInternalNode)base).right(), converted);
		}

		else if (text.charAt(0) == '0') {
			return convert(text.deleteCharAt(0), ((HuffInternalNode)base).left(), converted);
		}

		else  {
			converted.append("\n");
			return text.deleteCharAt(0);
		}
	}

	public String[] buildArr(String text) {

		//if the string of characters from the huffman tree traversal is invalid, then return null
		if (text == null || text.equals("")) return null;

		//instantiate the table to be returned
		String[] table = new String[128];

		//the start of the first appearance begins at index 0
		int start = 0;
		String appearance;

		//iterate over each character in code string
		for (int i = 0; i < text.length(); i++) {

			//if the character is equal to a newline, then we have found the end of an appearance
			if (text.charAt(i) == '\n') {

				//substring the appearance from its start to i (the newline, it is noninclusive)
				appearance = text.substring(start, i);

				/* the character associated with the code is at the first space in the appearance
				 while the code associated with the character is located after the space */
				table[appearance.charAt(0)] = appearance.substring(appearance.lastIndexOf(' ')+1);

				//the new start is immediately after the newline that we found before.
				start = i + 1;
			}
		}

		//return the filled table. if it equals null then it isn't present in the file.
		return table;
	}



	//take a file as input and create a table with characters and frequencies
	//print the characters and frequencies
	public String getFrequencies(File inputFile) throws FileNotFoundException {
		//declare scanner and wrap the file instantiation in a try catch
		Scanner sc;
		try {

			sc = new Scanner(inputFile);
		} 
		catch (Exception e) {

			e.printStackTrace();
			return null;
		}

		//use an empty string as delimiter such that you can pull each character from the input file
		sc.useDelimiter("");

		//instantiate the frequencies table with the index as the character and the element the number of times that it occurs
		int[] freq = new int[128];

		//iterate over each character in the file and increment its element if it is found in the array
		while (sc.hasNext()) {

			freq[sc.next().charAt(0)]++;
		}

		//build the string to return. formatted as char space frequency newline
		String temp = "";

		//iterate over the 
		for (int i = 32; i < 127; i++) {

			//if the character did not occur in the file then don't include it in the frequency string
			if (freq[i] == 0) continue;

			//else we want to append the character space frequency newline to the string
			temp += String.valueOf( (char)i ) + " " + freq[i] + "\n";
		}

		//return the string we have created
		return temp;
	}

	//build a tree from a min heap priority queue
	public HuffTree buildTree(Heap heap) {

		HuffTree a, b;

		while (heap.getSize() > 1) {

			//remove a and b from the heap
			a = heap.removeMin();
			b = heap.removeMin();

			//pair the nodes together into a huffman tree and insert back into the heap with the minimum on the left and the maximum on the right
			heap.insert( new HuffTree( new HuffInternalNode( a.root(), b.root(), a.weight()+b.weight() ) ) );
		}

		//return the only variable present in the heap, the root of the huffman tree
		return heap.removeMin();
	}

	//take a file as input and create a Huffman Tree
	public HuffTree buildTree(File inputFile) throws FileNotFoundException, Exception {
		//build a tree from a heap that is built from the frequencies that is obtained from the input file
		return buildTree(heapConstruct(getFrequencies(inputFile)));
	}



	//take a file and a HuffTree and encode the file.
	//output a string of 1's and 0's representing the file
	public String encodeFile(File inputFile, HuffTree huffTree) throws FileNotFoundException{
		//if the input is not valid then return null
		if (inputFile == null || huffTree == null) return null;

		//build the code table and initialize the encoded string as an empty string
		String [] table;
		try {
			table = buildArr(traverseHuffmanTree(huffTree));
		}catch(Exception e) {return "";}
		StringBuilder encoded = new StringBuilder();

		//declare scanner
		Scanner sc;

		//wrap scanner file instantiation in try catch
		try {

			sc = new Scanner(inputFile);
		} 
		catch (Exception e) {

			e.printStackTrace();
			return null;
		}

		//use delimiter to pull each character. each next() pulls the next character in the string
		sc.useDelimiter("");

		//as long as scanner has a next value
		while (sc.hasNext()) {

			//append the encoded string of 0 and 1 to the end of the encoded string, followed by a space
			encoded.append(String.valueOf(table[sc.next().charAt(0)]));
		}

		//return the encoded string
		return encoded.toString();


	}

	//take a String and HuffTree and output the decoded words
	public String decodeFile(String code, HuffTree huffTree) throws Exception {
		//the string we're going to be adding characters onto
		StringBuilder decoded = new StringBuilder();
		StringBuilder coded = new StringBuilder(code);

		while (coded.length() != 0) {

			coded = convert(coded, huffTree.root(), decoded);
		}
		return decoded.toString();
	}

	//print the characters and their codes
	public String traverseHuffmanTree(HuffTree huffTree) throws Exception {
		StringBuilder codes = new StringBuilder();
		String [] arr = new String[128];
		
		huffTree.traverseTree(huffTree.root(), arr);
		
		for (int i = 0; i < 127; i++) {
			if (arr[i] == null) 
				continue;
			else codes.append(arr[i]);
		}

		return codes.toString();
	}
}
