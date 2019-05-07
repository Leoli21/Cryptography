import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Part_III {

	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner (System.in);
		
		System.out.println("Enter file name: ");
		String name = input.nextLine();
		
		int shift = 0;
		
		int locate = name.indexOf(".");
		String newName = name.substring(0, locate);
	    PrintWriter printWriter = new PrintWriter(newName + "_ENC.txt");
		
		
			
		boolean encrypt = true;
		System.out.println("Would you like to encrypt, decrypt, or crack a file? ");
		String answer = input.nextLine();
		if (answer.equals("Encrypt") || answer.equals("encrypt"))
		{
			encrypt = true;
			System.out.println("How many places should the alphabet be shifted? ");
			shift = input.nextInt();
			String newStr = caesar_cipher(name, encrypt, shift);
			
			printWriter.print(newStr);
			System.out.println("New String in encrypted/decrypted file: " + newStr);
			printWriter.close();
		}
		else if (answer.equals("Decrypt") || answer.equals("decrypt"))
		{
			encrypt = false;
			System.out.println("How many places should the alphabet be shifted? ");
			shift = input.nextInt();
			String newStr = caesar_cipher(name, encrypt, shift);
			
			printWriter.print(newStr);
			System.out.println("New String in encrypted/decrypted file: " + newStr);
			printWriter.close();
		}
		else if (answer.equals("Crack") || answer.equals("crack"))
		{
			encrypt = false;
			
			String first100Letters;

			for (int i =0; i < 26; i++)
			{
				String fileStr = caesar_cipher(name, encrypt, 19);
				first100Letters = fileStr.substring(0, 100);
				System.out.println(first100Letters);
				System.out.println("Does this look right? ");
				String answer2 = input.nextLine();
				if (answer2.equals("Yes") || answer2.equals("yes"))
				{
					i = 26;
					printWriter.print(fileStr);
					System.out.println("New String in encrypted/decrypted file: " + fileStr);
					printWriter.close();
				}
			}
		}	

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

