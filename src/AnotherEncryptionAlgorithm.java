import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class AnotherEncryptionAlgorithm {

	public static void main(String[] args) throws IOException 
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Enter file name: ");
		String name = input.nextLine();
	   
	    System.out.println("Would you like to encrypt, decrypt, or crack the file? ");
	    String answer = input.nextLine();
	    MainFunction(input, name, answer);
	}
	//Establishing permuted alphabet
	private static char[] getPerm() {
		char[] perm = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		Random rand = new Random();
		for (int i = 0; i < perm.length; i++)
		{
			char temp = perm[i];
			int randomIndex = rand.nextInt(perm.length);
			perm[i] = perm[randomIndex];
			perm[randomIndex] =  temp;
		}
		return perm;
	}
	
	//
	private static void MainFunction( Scanner input, String name,  String answer)
			throws FileNotFoundException, IOException {
		 
		if(answer.equalsIgnoreCase("encrypt"))
	    {
	    	
	    	encrypt(input, name);
	    }
	    else if (answer.equalsIgnoreCase("decrypt"))
		{
	    	decrypt(input, name);
		}
	    else if (answer.equalsIgnoreCase("crack"))
		{
	    	crack(input, name);
		}
	}

	private static void crack(Scanner input, String name) throws FileNotFoundException, IOException {
		boolean encrypt;
		PrintWriter printWriter;
		printWriter = getPrintWriter(name, "DEC");
		encrypt = false;
		
		String first100Letters;

		for (int i = 0; i < 26; i++)
		{
			String newStr = caesar_cipher(name, encrypt, i);
			first100Letters = newStr.substring(0, 100);
			System.out.println(first100Letters);
			System.out.println("Does this look right? ");
			String answer2 = input.nextLine();
			if (answer2.equals("Yes") || answer2.equals("yes"))
			{
				i = 26;
				System.out.println("Result written to " + printWriter);
				printWriter.print(newStr);
				printWriter.close();
			}
		}
	}

	private static void decrypt(Scanner input, String name) throws FileNotFoundException, IOException {
		char[] perm;
		int shift;
		boolean encrypt;
		PrintWriter printWriter;
		printWriter = getPrintWriter(name, "DEC");
		
		encrypt = false;
		System.out.println("What algorithm, 'c' for Caesar cipher or 'p' for permutation? ");
		char algorithm = input.next().charAt(0);
		if (algorithm == 'p')
		{
			System.out.println("Enter permutation of alphabet to use during decryption ");
			String permutedAlphabet = input.next();
			perm = permutedAlphabet.toCharArray();
			String newStr = perm_cipher(name, encrypt, perm);
			System.out.println("Result written to " + printWriter);
			printWriter.print(newStr);
			printWriter.close();
		}
		else if (algorithm == 'c')
		{
			System.out.println("How many places should the alphabet be shifted? ");
			shift = input.nextInt();
			String newStr = caesar_cipher(name, encrypt, shift);
			System.out.println("Result written to " + printWriter);
			printWriter.print(newStr);
			printWriter.close();
		}
	}

	private static void encrypt(Scanner input, String name)
			throws FileNotFoundException, IOException {
		int shift;
		PrintWriter printWriter;
		printWriter =  getPrintWriter(name,"ENC");
		System.out.println("What alogirthm, 'c' for Caesar cipher or 'p' for permutation?" );
		char algorithm = input.next().charAt(0);
		if (algorithm == 'p')
		{
			char[] perm = getPerm();
			System.out.println("The folloiwng permuted alphabet will be used for encryption: " + Arrays.toString(perm));
			String newStr = perm_cipher(name, true, perm);
			System.out.println("Result written to " + printWriter);
			printWriter.print(newStr);
			printWriter.close();
		}
		else if (algorithm == 'c')
		{
			System.out.println("How many places should the alphabet be shifted? ");
			shift = input.nextInt();
			String newStr = caesar_cipher(name, true, shift);
			System.out.println("Result written to " + printWriter);
			printWriter.print(newStr);
			printWriter.close();
		}
	}

	private static PrintWriter getPrintWriter(String inputName, String functionName) throws FileNotFoundException {
		int locate = inputName.indexOf(".");
		String newName = inputName.substring(0, locate);
	    PrintWriter printWriter = new PrintWriter(newName + "_"+ functionName + ".txt");
		return printWriter;
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
	    		char [] originalAlphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	    		for(int i = 0; i< str.length(); i++)
	    		{
	    			char letter = str.charAt(i);
	    			if(isLower(letter))
	    			{
	    				int letterIndex = 0;
	    				for (int j = 0; j < originalAlphabet.length; j ++)
	    				{
	    					if (originalAlphabet[j] == letter)
	    					{
	    						letterIndex = j;
	    						j = originalAlphabet.length;
	    					}
	    				}
		    			letter = perm[letterIndex];
		    			newStr += letter;
	    			}
	    			else
	    			{
	    				letter = str.charAt(i);
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
		    			for (int j = 0; j < perm.length; j++)
		    			{
		    				if (perm[j] == letter)
		    				{
		    					letterIndex = j;
		    					j = perm.length;
		    				}		    				
		    			}
		    			letter = originalAlphabet[letterIndex];
		    			newStr += letter;
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
