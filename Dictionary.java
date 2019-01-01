import java.util.*; 
import java.io.*;

/**
 * This class represents a collection of words. The words are read from an input file
 * and stored in an ArrayList. Searches for words through binary search which uses 
 * a recursive algorithm.  
 * 
 * @author David (Jung Won) Yang
 * @version 3/2/2017
 */
public class Dictionary 
{
	//ArrayList that stores words that are read from a file.
	private ArrayList<String> words;
	
	
	/**
	 * Constructor for the Dictionary class. Stores the words from the file, into 
	 * the ArrayList. 
	 * 
	 * @param f is a reference to a File object. 
	 * @throws IllegalArgumentException if the file does not exist or it is not readable.
	 */
	public Dictionary(File f) throws IllegalArgumentException
	{
		if (f == null)
		{
			throw new IllegalArgumentException("The dictionary file cannot be null");
		}
		
		Scanner input; 	
		//Create Scanner object to read from the file. 
		try
		{
			input = new Scanner(f);
		}
		//Throw IllegalArgumentException with a message.
		catch(FileNotFoundException e)
		{
			throw new IllegalArgumentException("The file " + f.toString() + " does not exist"
					+ " or is not readable.");
		}
		
		//Create ArrayList to store words, with an intial size of 1000.
		words = new ArrayList<String>(1000);
		
		while (input.hasNextLine())
		{
			words.add(input.nextLine());
		}
		
		input.close();
	}

	
	/**
	 * Through binary search, this method determines if the specified word is inside 
	 * the ArrayList. 
	 * 
	 * @param str is the word that we check for in the ArrayList. 
	 * @return true if the word is in the ArrayList,
	 * false if it isnt. 
	 */
	public boolean isWord(String str)
	{
		//If the ArrayList does not contain any words return false. 
		if(words.size()==0)
		{
			return false;
		}
		//Recursive method for binary search.
		return binarySearchWord(str, 0, words.size()-1);
	}
	
	
	/**
	 * Uses a recursive algorithm to do a binary search for a word in the ArrayList. 
	 * 
	 * @param str is the word that we are looking for in the dictionary. 
	 * @param start is the starting index of the section of the ArrayList in which we are searching. 
	 * @param end is the ending index of the section of the ArrayList in whihch we are searching.
	 * @return true if the word is found,
	 * false if the word is not in the ArrayList. 
	 */
	private boolean binarySearchWord(String str, int start, int end)
	{
		//Check if the word is the middle element (round down if even number of elements).
		if (str.equals(words.get((end+start)/2)))
		{
			return true;
		}
		//If there is only one element left, then false.  
		else if (start==end)
		{
			return false;
		}
		
		//If the word we are searching for is lexicographically less than the 
		//middle element do a binary search on the bottom half of the section 
		//of the ArrayList that we are considering. 
		else if (str.compareTo(words.get((end+start)/2))<0)
		{
			//If two elements left, then false.  
			if (start==end-1)
			{
				return false;
			}
			return binarySearchWord(str , start, (end+start)/2-1);
		}
		//If it is lexicographically greater than the middle element do a binary search
		//on the bottom half of the section.
		else 
		{
			return binarySearchWord(str , (end+start)/2+1 , end);
		}
	}
	
	
	/**
	 * Through binary search, this method determines if there is a word with the specified 
	 * specified prefix inside the ArrayList. 
	 * 
	 * @param str is the prefix that we check for in the ArrayList. 
	 * @return true if the prefix is in the ArrayList,
	 * false if it isnt. 
	 */
	public boolean isPrefix(String str)
	{
		//If the ArrayList does not contain any words return false. 
		if(words.size()==0)
		{
			return false;
		}
		//Call recursive method for binary search.
		return binarySearchPrefix(str, 0, words.size()-1);
	}
	

	/**
	 * Uses a recursive algorithm to do a binary search for a prefix inside the ArrayList. 
	 * 
	 * @param str is the prefix that we are looking for in the ArrayList. 
	 * @param start is the starting index of the section of the ArrayList in which we are searching. 
	 * @param end is the ending index of the section of the ArrayList in whihch we are searching.
	 * @return true if the prefix is found,
	 * false if the prefix is not in the ArrayList. 
	 */
	private boolean binarySearchPrefix(String str, int start, int end)
	{
		//Check the middle element(round down if even number of elements)contains the prefix.
		if (words.get((end+start)/2).startsWith(str))
		{
			return true;
		}
		//If there is only one element left, return false.  
		else if (start==end)
		{
			return false;
		}
		
		//If the prefix we are searching for is lexicographically less than the 
		//middle element, do a binary search on the bottom half of the section 
		//of the ArrayList that we are looking at. 
		else if (str.compareTo(words.get((end+start)/2))<0)
		{
			//If only two elements left, then false.  
			if (end==start+1)
			{
				return false;
			}
			
			return binarySearchPrefix(str , start, (end+start)/2-1);
		}
		//If it is lexicographically greater than the middle element, do a binary search
		//on the top half of the section.
		else 
		{
			return binarySearchPrefix(str , (end+start)/2+1 , end);
		}
	}
	
}
