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
			if (number == -3)
			{
				number +=  ;
			}
			else if (number == -2)
			{
				number +=4 ;
			}
			else if (number == -1)
			{
				number += 3;
			}
			letter = (char) (number + 'a');
			newStr += letter;
		}
		System.out.println(newStr);
		
		//System.out.println(letter);
		//int number = letter -'a';
		//System.out.println(number);
		//number += 5;
		//System.out.print((char) (number + 'a'));

	}

}
 