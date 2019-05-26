import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AnotherEncryptionAlgorithm {

	public static void main(String[] args) throws FileNotFoundException {
		char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		
		for (int i = 0; i < alphabet.length; i++)
		{
			char temp = alphabet[i];
			for (int j = 0; j < alphabet.length; j++)
			{
				alphabet[i] = alphabet[j];
				alphabet[j] = temp;
			}
		}
		Scanner input = new Scanner(System.in);
		System.out.println("Enter file name: ");
		String name = input.nextLine();
		
		int shift;
		
		int locate = name.indexOf(".");
		String newName = name.substring(0, locate);
	    PrintWriter printWriter = new PrintWriter(newName + "_ENC.txt");
	    
	    System.out.println("Would you like to encrypt, decrypt, or crack the file? ");
	    String answer = input.nextLine();
	    if(answer.equals("encrypt") || answer.equals("Encrypt"))
	    {
	    	System.out.println("What alogirthm, 'c' for Caesar cipher or 'p' for permutation?" );
	    	char algorithm = input.next().charAt(0);
	    	if (algorithm)
	    }

		
		// create permuted alphabet array in main method
		// Swapping letters:
		// char temp = permutedAlpha[index1]
		// permutedAlpha[index1] = permutedAlpha[index2];
		// permutedAlpha [index2] = temp;
		
		
		// **Encrypt is going from original alphabet to permuted alphabet
		// **Decrypt is going from permuted alphabet to original alphabet

	}

}
