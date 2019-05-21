import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 
 * @author Leo Li
 *    Cryptography Part 1
 */
public class Cipher {

	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		
		//Prompt user for file name
		System.out.println("Please input file name: ");
		String name = input.nextLine();
		
		//String filePath = "C:\Users\lli21\Documents\Cryptography\src";
		//Read contents of the file
		File myFile = new File(name);
		Scanner inputFile = new Scanner(myFile);

		//Write to new file
		int locate = name.indexOf(".");
		String fileName = name.substring(0, locate);
	    PrintWriter printWriter = new PrintWriter(fileName + "_ENC.txt");
	   
	    String newStr = "";
	    
	    //Encrypt file and store into new file
		while (inputFile.hasNext())
		{
			String str = inputFile.nextLine();
			System.out.println(str);
			for(int i = 0; i < str.length(); i++)
			{
				char letter = str.charAt(i);
				if (isLower(letter))
				{
					newStr = lowerCaseEncryptor(newStr, letter);
				}
				else if (isUpper(letter))
				{
					newStr = upperCaseEncryptor(newStr, letter);
				}
				else
				{
					letter = str.charAt(i);
					newStr += letter;
				}
			}
		}
		printWriter.print(newStr);
		System.out.println("New String in encryped file: " + newStr);
		printWriter.close();	
	}
	
	private static boolean isLower(char c)
	{
		return c >= 'a' && c <= 'z';
	}
	private static boolean isUpper(char c)
	{
		return c >= 'A' && c <= 'Z';
	}
	private static String upperCaseEncryptor(String newStr, char letter) {
		int number = letter - 'A';
		number = (number - 3) % 26;
		if (number == -3 || number == -2 || number == -1)
		{
			number += 26;
		}
		letter = (char) (number + 'A');
		newStr += letter;
		return newStr;
	}
	private static String lowerCaseEncryptor(String newStr, char letter) {
		int number = letter - 'a';
		number = (number - 3) % 26;
		if (number == -3 || number == -2 || number == -1)
		{
			number += 26;
		}
		letter = (char) (number + 'a');
		newStr += letter;
		return newStr;
	}
	
}


 