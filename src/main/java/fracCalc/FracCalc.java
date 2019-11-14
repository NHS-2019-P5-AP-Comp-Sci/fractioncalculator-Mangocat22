package fracCalc;

import java.util.Scanner;

public class FracCalc {

	public static void main(String[] args) {
		Scanner Userinput = new Scanner(System.in);
		String input = Userinput.nextLine();
		while (!(input.toUpperCase()).equals("QUIT")) {
			System.out.println(produceAnswer(input));
			input = Userinput.nextLine();
		}
	}

	public static String produceAnswer(String input) {
		int pointofoperator = 0;
		String operation;
		int addition = input.indexOf(" + ");
		int subtraction = input.indexOf(" - ");
		int division = input.indexOf(" / ");
		int multiplication = input.indexOf(" + ");

		if (addition!=-1) {
			pointofoperator=addition;
			operation="+";
		} else if (subtraction!=-1) {
			pointofoperator=subtraction;
			operation="-";
		} else if (division!=-1) {
			pointofoperator=division;
			operation="/";
		} else if (multiplication!=-1) {
			pointofoperator=multiplication;
			operation="*";
		}
		
	String firstpacket="";
	String secondpacket="";
	for (int i=0;i<pointofoperator;i++) {
		firstpacket=firstpacket+input.charAt(i);
	}	
	for (int i = 0; i < input.length() - 3 - pointofoperator; i++) {
		secondpacket = secondpacket + input.charAt(3 + pointofoperator + i);
		}
			return secondpacket;
		}
}
