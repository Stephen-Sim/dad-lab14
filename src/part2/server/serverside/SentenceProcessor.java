package part2.server.serverside;

import java.util.HashMap;

public class SentenceProcessor {
	
	private int size = 65535;
	
	private String sentence;
	
	
	public SentenceProcessor(byte[] byteData) {
		
		this.sentence = new String(byteData);
	}
	
	public String getSentence() {
		
		return sentence;
	}
	
	/**
	 * This method convert value into an array of byte
	 * @param length
	 * @return
	 */
	public byte[] convertToByteArray(int value) {
		
		byte[] outData = new byte[size];
		
		String stringValue = Integer.valueOf(value).toString();
		
        outData = stringValue.getBytes();
        
        return outData;
	}
	
	/**
	 * This method count the number of characters in a sentence
	 * @return
	 */
	public int countCharacters() {
		
        int index = 0;
        char currentChar = sentence.charAt(index);
        while (currentChar != '\0') {
        	
        	currentChar = sentence.charAt(index++);
        }
        
        return index - 1;
	}
	
	/**
	 * this method count the vowels number of the sentence.
	 * 
	 * @return
	 */
	public int countVowels()
	{
		int count = 0;
		
		for(int index = 0; index < sentence.length(); index++)
		{
			if (sentence.toLowerCase().charAt(index) == 'a' ||
				sentence.toLowerCase().charAt(index) == 'e' ||
				sentence.toLowerCase().charAt(index) == 'i' ||
				sentence.toLowerCase().charAt(index) == 'o' ||
				sentence.toLowerCase().charAt(index) == 'u')
			{
				count++;
			}
		}
		
		return count;
	}
	
	/**
	 * this method count the consonants number of the sentence.
	 * 
	 * @return
	 */
	public int countConsonants()
	{
		int count = 0;
		
		for(int index = 0; index < sentence.length(); index++)
		{
			if (sentence.toLowerCase().charAt(index) != 'a' &&
				sentence.toLowerCase().charAt(index) != 'e' &&
				sentence.toLowerCase().charAt(index) != 'i' &&
				sentence.toLowerCase().charAt(index) != 'o' &&
				sentence.toLowerCase().charAt(index) != 'u')
			{
				if(sentence.toLowerCase().charAt(index) == '\0')
				{
					break;
				}
				count++;
			}
		}
		
		return count;
	}
	
	/**
	 * this method count the punctuation number of the sentence.
	 * 
	 * @return
	 */
	public int countPunctuations()
	{
		int count = 0;
		
		for(int index = 0; index < sentence.length(); index++)
		{
			if (sentence.toLowerCase().charAt(index) == ',' ||
				sentence.toLowerCase().charAt(index) == '\"' ||
				sentence.toLowerCase().charAt(index) == '\'' ||
				sentence.toLowerCase().charAt(index) == ' ')
			{
				count++;
			}
		}
		
		return count;
	}
}
