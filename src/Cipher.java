import java.util.Scanner;

public class Cipher {
	//When doing modular division, 

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Please input a String: ");
		String str = input.nextLine();
		String newStr = "";
		for(int i = 0; i < str.length(); i++)
		{
			char letter = str.charAt(i);
			int number = letter - 'a';
			number = (number - 3) % 26;
			letter = (char) number;
			newStr += letter;
		}
		System.out.println(newStr);
		//char letter = 'c';
		//int number = letter - 'a';
		//number = (number + 7) % 26;
		//System.out.println((char) (number + 'a'));
		
		//System.out.println(letter);
		//int number = letter -'a';
		//System.out.println(number);
		//number += 5;
		//System.out.print((char) (number + 'a'));

	}

}
 