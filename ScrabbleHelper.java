import java.io.*;
import java.util.*;

/**
 * This class recieves recieves 2 arguments through command line arguments. 
 * The two arguments must be the path of the textfile containing a dictionary, 
 * and a sequence of letters. The program displays a list of words in the dictionary 
 * that are anagrams of the sequence of letters. 
 * 
 * @author David (Jung Won) Yang 
 * @version 3/2/2017
 */
public class ScrabbleHelper 
{
	/**
	 * This is the method creates the Dictionary and Permutations objects. These 
	 * objects are used inorder get all words in a dictionary that are anagrams of
	 * a string sequence. The words are then displayed on the console. 
	 * 
	 * @param args is the Array that contains the command line arguments. The first 
	 * command line argument should be the path of the dictionary file, and the
	 * second command line argument should be the letter sequence.  
	 */
	public static void main(String[] args) 
	{
		//If there aren't two arguments in the file, print an error and exit.
		if ((args.length<2))
		{
			System.err.println("Missing the path of the dictionary file,"
					+ " or the lettter sequence. (or both)");
			System.exit(1);
		}
		
		File dictionaryFile = new File(args[0]);
		
		//Create Dictionary object. Exit if the Dictionary file does not exist or is 
		//no readable. 
		//Create Permutations objects. Exit if the letter sequence contains non-letters. 
		Dictionary dictionary=null;
		Permutations permutations=null;
		ArrayList<String> allWords = new ArrayList<String>();
		try
		{
			dictionary = new Dictionary(dictionaryFile);
			permutations = new Permutations(args[1]);
		}
		catch(IllegalArgumentException e)
		{
			System.err.println(e.getMessage());
			System.exit(1);
		}
		
		//Get all the words constructed. 
		allWords = permutations.getAllWords(dictionary);
		
		//Display words.
		if (allWords.size()==0)
		{
			System.out.println("No words found.");
		}
		else
		{
			System.out.println("Found " + allWords.size() + " words:");
			for (int i=0; i<allWords.size(); i++)
			{
				System.out.println("\t"+ allWords.get(i));
			}
		}
	}

}
