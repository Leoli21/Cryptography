import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class AnotherEncryptionAlgorithm {

	public static void main(String[] args) throws IOException {
		char[] perm = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		Random rand = new Random();
		for (int i = 0; i < perm.length; i++)
		{
			char temp = perm[i];
			int randomIndex = rand.nextInt(perm.length);
			perm[i] = perm[randomIndex];
			perm[randomIndex] = temp;
		}
		// create permuted alphabet array in main method
				// Swapping letters:
				// char temp = permutedAlpha[index1]
				// permutedAlpha[index1] = permutedAlpha[index2];
				// permutedAlpha [index2] = temp;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter file name: ");
		String name = input.nextLine();
		
		int shift;
		
		int locate = name.indexOf(".");
		String newName = name.substring(0, locate);
	    PrintWriter printWriter = new PrintWriter(newName + "_ENC.txt");
	    
	    boolean encrypt = true;
	    System.out.println("Would you like to encrypt, decrypt, or crack the file? ");
	    String answer = input.nextLine();
	    if(answer.equals("encrypt") || answer.equals("Encrypt"))
	    {
	    	System.out.println("What alogirthm, 'c' for Caesar cipher or 'p' for permutation?" );
	    	char algorithm = input.next().charAt(0);
	    	if (algorithm == 'p')
	    	{
	    		String newStr = perm_cipher(name, encrypt, perm);
				System.out.println("New String in encrypted/decrypted file: " + "\n" + newStr);
				printWriter.print(newStr);
				printWriter.close();
	    	}
	    	else if (algorithm == 'c')
	    	{
				System.out.println("How many places should the alphabet be shifted? ");
				shift = input.nextInt();
				String newStr = caesar_cipher(name, encrypt, shift);
				System.out.println("New String in encrypted/decrypted file: " + "\n" + newStr);
				printWriter.print(newStr);
				printWriter.close();
	    	}
	    }
	    else if (answer.equals("Decrypt") || answer.equals("decrypt"))
		{
	    	encrypt = false;
	    	System.out.println("What algorithm, 'c' for Caesar cipher or 'p' for permutation? ");
	    	char algorithm = input.next().charAt(0);
	    	if (algorithm == 'p')
	    	{
	    		System.out.println("Enter permutation of alphabet to use during decryption ");
	    		for (int i = 0; i < perm.length; i ++)
	    			perm[i] = input.next().charAt(0);
				String newStr = perm_cipher(name, encrypt, perm);
				System.out.println("New String in encrypted/decrypted file: " + "\n" + newStr);
				printWriter.print(newStr);
				printWriter.close();
	    	}
	    	else if (algorithm == 'c')
	    	{
	    		System.out.println("How many places should the alphabet be shifted? ");
				shift = input.nextInt();
				String newStr = caesar_cipher(name, encrypt, shift);
				System.out.println("New String in encrypted/decrypted file: " + "\n" + newStr);
				printWriter.print(newStr);
				printWriter.close();
	    	}
		}
	    else if (answer.equals("Crack") || answer.equals("crack"))
		{
			encrypt = false;
			
			String first100Letters;

			for (int i = 0; i < 26; i++)
			{
				String newStr = caesar_cipher(name, encrypt, 19);
				first100Letters = newStr.substring(0, 100);
				System.out.println(first100Letters);
				System.out.println("Does this look right? ");
				String answer2 = input.nextLine();
				if (answer2.equals("Yes") || answer2.equals("yes"))
				{
					i = 26;
					System.out.println("New String in encrypted/decrypted file: " + "\n" + newStr);
					printWriter.print(newStr);
					printWriter.close();
				}
			}
		}

		
		// create permuted alphabet array in main method
		// Swapping letters:
		// char temp = permutedAlpha[index1]
		// permutedAlpha[index1] = permutedAlpha[index2];
		// permutedAlpha [index2] = temp;
		
		
		// **Encrypt is going from original alphabet to permuted alphabet
		// **Decrypt is going from permuted alphabet to original alphabet

	}
	
	//Caesar Cipher Method
	public static String caesar_cipher(String fileName, boolean encrypt, int shiftAmount) throws IOException
	{
		File myFile = new File(fileName);
		Scanner inputFile = new Scanner(myFile);
	    String newStr = "";
	    if (!encrypt)
	    	shiftAmount *= -1;
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
				newStr += "\n";
			}
	    	else  //decrypting
			{	    		
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
				newStr += "\n";
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
	
	//Permutation Method
	// **Encrypt is going from original alphabet to permuted alphabet
	// **Decrypt is going from permuted alphabet to original alphabet
	public static String perm_cipher(String fileName, boolean encrypt, char[] perm) throws FileNotFoundException
	{
		File myFile = new File(fileName);
		Scanner inputFile = new Scanner(myFile);
	    String newStr = "";
	    while (inputFile.hasNext())
	    {
	    	String str = inputFile.nextLine();
	    	if (encrypt) //Encrypt
	    	{
	    		for(int i = 0; i< str.length(); i++)
	    		{
	    			char letter = str.charAt(i);
	    			if(isLower(letter))
	    			{
	    				int letterIndex = 0;
		    			for (int j = 0; j < perm.length; j++)
		    				if (perm[j] == letter)
		    					letterIndex = j;
		    			letter = perm[letterIndex];
		    			newStr += letter;
	    			}
	    			else
	    			{
	    				letter = str.charAt(i);;
	    				newStr += letter;
	    			}
	
	    		}
	    		newStr += "\n";			
	    	}
	    	else  //Decrypt
	    	{
	    		char [] originalAlphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	    		for(int i = 0; i < str.length(); i++)
	    		{
	    			char letter = str.charAt(i);
	    			if (isLower(letter))
	    			{
	    				int letterIndex = 0;
		    			for (int j = 0; j < originalAlphabet.length; j ++)
		    			{
		    				if (originalAlphabet[j] == letter)
		    					letterIndex = j;
		    			letter = originalAlphabet[letterIndex];
		    			newStr += letter;
		    			}
	    			}
		    		else
		    		{
		    			letter = str.charAt(i);
		    			newStr += letter;
		    		}	    						    			
	    		}
	    		newStr+= "\n";
	    		
	    	}
	    }	    	   	    
	    return newStr;
	}

}
