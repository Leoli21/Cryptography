import java.util.Random;

public class AnotherEncryptionAlgorithm {

	public static void main(String[] args) {
		char[] lowerCaseAlphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k','l', 'm', 'n','o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		//char[] upperCaseAlphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		
		Random rand = new Random();
		
		
		// Swapping: 
		// char temp = permutedAlpha[index1]
		// permutedAlpha[index1] = permutedAlpha[index2];
		// permutedAlpha [index2] = temp;

		for (int i = 0; i < lowerCaseAlphabet.length; i++)
		{
			int random = rand.nextInt(27);
			char temp = lowerCaseAlphabet[i];
			lowerCaseAlphabet[i] = lowerCaseAlphabet[random];
			lowerCaseAlphabet[random] = temp;
		}
			
		// **Encrypt is going from original alphabet to permuted alphabet
		// **Decrypt is going from permuted alphabet to original alphabet

	}

}
