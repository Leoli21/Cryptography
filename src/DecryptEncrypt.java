import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DecryptEncrypt {

	public static void main(String[] args) throws IOException
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Enter file name: ");
		String name = input.nextLine();
		
		System.out.println("How many places should the alphabet be shifted? ");
		int shift = input.nextInt();
		
		File myFile = new File(name);
		Scanner inputFile = new Scanner(myFile);
		
		int locate = name.indexOf(".");
		String fileName = name.substring(0, locate);
	    PrintWriter printWriter = new PrintWriter(fileName + "_ENC.txt");
	    
	    
		//caesar_cipher(myFile, encrypt or decrypt, shiftAmount);

	}
	public static String caesar_cipher(String fileName, boolean encrypt, int shiftAmount) throws IOException
	{
		if (encrypt)
		{
			shiftAmount = 1;
		}
		else 
		{
			shiftAmount = -1;
		}
		return fileName;
	}
}
