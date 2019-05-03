import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Part_III {

	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);
		
		System.out.println("Enter file name: ");
		String name = input.nextLine();
		
		
		System.out.println("Would you like to encrypt, decrypt, or crack a file? ");
		String answer = input.nextLine();
		

	}

	
	
	
	public static String caesar_cipher(String fileName, boolean encrypt, int shiftAmount) throws IOException
	{
		File myFile = new File(fileName);
		Scanner inputFile = new Scanner(myFile);
	    
	    String newStr = "";
	    
	    while (inputFile.hasNext())
	    {
	    	String str = inputFile.nextLine();
	    	if (encrypt) //encrypting
			{
				for(int i = 0; i < str.length(); i++)
				{
					char letter = str.charAt(i);
					if (isLower(letter))
					{
						newStr = lowerCaseEncryptor(newStr, letter, shiftAmount);
					}
					else if (isUpper(letter))
					{
						newStr = upperCaseEncryptor(newStr, letter, shiftAmount);
					}
					else
					{
						letter = str.charAt(i);
						newStr += letter;
					}
				}	
			}
	    	else  //decrypting
			{
				shiftAmount *= -1;
				for(int i = 0; i < str.length(); i++)
				{
					char letter = str.charAt(i);
					if (isLower(letter))
					{
						newStr = lowerCaseDecryptor(newStr, letter, shiftAmount);
					}
					else if (isUpper(letter))
					{
						newStr = upperCaseDecryptor(newStr, letter, shiftAmount);
					}
					else
					{
						letter = str.charAt(i);
						newStr += letter;
					}
				}
			}
	    }	
		return newStr;
	}
	private static boolean isLower(char c)
	{
		return c >= 'a' && c <= 'z';
	}
	private static boolean isUpper(char c)
	{
		return c >= 'A' && c <= 'Z';
	}
	private static String upperCaseEncryptor(String newStr, char letter, int shiftAmount) {
		int number = letter - 'A';
		number = (number - shiftAmount) % 26;
		if (number < 0)
		{
			number += 26;
		}
		letter = (char) (number + 'A');
		newStr += letter;
		return newStr;
	}
	private static String lowerCaseEncryptor(String newStr, char letter, int shiftAmount) {
		int number = letter - 'a';
		number = (number - shiftAmount) % 26;
		if (number < 0)
		{
			number += 26;
		}
		letter = (char) (number + 'a');
		newStr += letter;
		return newStr;
	}
	private static String upperCaseDecryptor(String newStr, char letter, int shiftAmount) {
		int number = letter - 'A';
		number = (number - shiftAmount) % 26;
		if (number > 26)
		{
			number -= 26;
		}
		letter = (char) (number + 'A');
		newStr += letter;
		return newStr;
	}
	private static String lowerCaseDecryptor(String newStr, char letter, int shiftAmount) {
		int number = letter - 'a';
		number = (number - shiftAmount) % 26;
		if (number > 26)
		{
			number -= 26;
		}
		letter = (char) (number + 'a');
		newStr += letter;
		return newStr;
	}
}
}
