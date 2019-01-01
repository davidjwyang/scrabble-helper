import java.util.*;

/**
 * This class represents a sequence of letters that are used to construct permutations,
 * and words from a specified dictionary. 
 * 
 * @author David (Jung Won) Yang
 * @version 3/2/2017
 */
public class Permutations 
{
	//Sequence of letters.
	private StringBuffer letters;
	
	/**
	 * Constructor method for the Permutations class. Check if the sequence of letters 
	 * contains only letters. Then initilizes the letters data field. 
	 * 
	 * @param letters is the sequence of letters that we contructs permutations or anagrams with.
	 * @throws IllegalArgumentException if the sequence of letters does not contain any characters,
	 * if it contains characters that are not letters, or if letters is null. 
	 */
	public Permutations(String letters) throws IllegalArgumentException
	{
		if (letters == null)
		{
			throw new IllegalArgumentException("The letters cannot be null");
		}
		
		
		char[] charArray = letters.toCharArray();
		if(charArray.length==0)
		{
			throw new IllegalArgumentException("Error: no letters provided, cannot construct"
					+ " any words or permutations.");
		}
		//Check if all characters in the string are letters. 
		for (int i=0; i<charArray.length; i++)
		{
			if ( !(Character.isLetter(charArray[i])) )
			{
				throw new IllegalArgumentException("Error: you entered invalid"
						+ " an invalid character/characters; only letters can be accepted.");
			}
		}
		letters = letters.toLowerCase();
		this.letters = new StringBuffer(letters);
	}
	
	
	/**
	 * Generates all permutations that can be constructed from a sequence of letters. 
	 * 
	 * @returns an ArrayList<String> containing all of the permutations. 
	 */
	public ArrayList<String> getAllPermutations()
	{	
		ArrayList<String> allPermutations = new ArrayList<String>();
		StringBuffer appendingStr = new StringBuffer();
		
		//Call the recusive method that will generate all permutations. 
		generatePermutations(this.letters, appendingStr, allPermutations);
		
		Collections.sort(allPermutations);
		
		return allPermutations;
	}
	
	
	/**
	 * Recursive method for generating all permutations of a sequence of letters.
	 * 
	 * @param deletingStr is the StringBuffer that we take a character from. Contains the
	 * letters that have not been added yet. 
	 * @param appendingStr is the StringBuffer that we add a chracters to. Contains the sequence
	 * of letters for the permutation. 
	 * @param allPermutations is the ArrayList<String> that contains all the permutations. 
	 */
	private void generatePermutations(StringBuffer deletingStr, StringBuffer appendingStr,
			ArrayList<String> allPermutations)
	{
		//If all the letters are in the sequence, add it to the ArrayList. 
		if (appendingStr.length()== this.letters.length())
		{
			allPermutations.add(appendingStr.toString());
		}
		
		//Iterate through all possibilities for the character. 
		else
		{
			for (int i=0; i<deletingStr.length(); i++)
			{
				StringBuffer newAppendingStr;
				StringBuffer newDeletingStr;
				
				//Create new StringBuffer with the ith character of deletingStr
				//appended to appendingStr. 
				newAppendingStr = new StringBuffer(appendingStr.toString());
				newAppendingStr.append(deletingStr.charAt(i));
				
				//Create a new StringBuffer with the ith character of deletingStr removed. 
				newDeletingStr = new StringBuffer(deletingStr.toString());
				newDeletingStr.deleteCharAt(i);
				
				//Recurisve call to add the next character. 
				generatePermutations(newDeletingStr, newAppendingStr, allPermutations);
			}
		}
	}
	
	
	/**
	 * Generates all words that can be constructed from a sequence of letters.
	 * 
	 * @param dictionary is the Dictionary object that we find words from. 
	 * @returns an ArrayList<String> contains all of the words. 
	 */
	public ArrayList<String> getAllWords(Dictionary dictionary) 
	{	
		ArrayList<String> allWords = new ArrayList<String>();
		StringBuffer appendingStr = new StringBuffer();
		
		//Call recursive method that will generate all words. 
		generateWords(this.letters, appendingStr, allWords, dictionary);
		
		//Sort and fill an ArrayList with only unqiue words. 
		ArrayList<String> uniqueWords = new ArrayList<String>();
		for (int i=0; i<allWords.size(); i++)
		{
			if( !( uniqueWords.contains(allWords.get(i) )) )
			{
				uniqueWords.add(allWords.get(i));
			}
		}
		Collections.sort(uniqueWords);
		return uniqueWords; 
	}
	
	
	/**
	 * Recursive method for generating all words from a sequence of letters. Uses backtracking
	 * to stop generating permutations of a given sequence if the sequence is not a prefix to 
	 * any word in the dictionary.
	 * 
	 * @param deletingStr is the StringBuffer that we take a character from. Contains the
	 * letters that have not been added yet. 
	 * @param appendingStr is the StringBuffer that we add a chracters to. Contains the sequence
	 * of letters for the permutation.
	 * @param allWords is the ArrayList<String> that contains all the words. 
	 * @param dictionary is the Dictionary object that we find words from. 
	 */
	private void generateWords(StringBuffer deletingStr, StringBuffer appendingStr,
			ArrayList<String> allWords, Dictionary dictionary)
	{
		//If the sequence is not a prefix to any word in the dictionary, stop generating the prefix. 
		if ( !(dictionary.isPrefix(appendingStr.toString())) )
		{
			return;
		}
		
		//If all the letters are in the sequence and it is a word, add it to the ArrayList. 
		if (deletingStr.length()==0 && dictionary.isWord(appendingStr.toString()))
		{
			allWords.add(appendingStr.toString());
		}
		
		//Iterate through all possibilities for the character. 
		else
		{
			for (int i=0; i<deletingStr.length(); i++)
			{
				StringBuffer newAppendingStr;
				StringBuffer newDeletingStr;
				
				//Create new StringBuffer with the ith character of deletingStr
				//appended to appendingStr. 
				newAppendingStr = new StringBuffer(appendingStr.toString());
				newAppendingStr.append(deletingStr.charAt(i));
				
				//Create a new StringBuffer with the ith character of deletingStr removed. 
				newDeletingStr = new StringBuffer(deletingStr.toString());
				newDeletingStr.deleteCharAt(i);
				
				//Recurisve call to add the next character. 
				generateWords(newDeletingStr, newAppendingStr, allWords, dictionary);
			}
		}
	}
}
