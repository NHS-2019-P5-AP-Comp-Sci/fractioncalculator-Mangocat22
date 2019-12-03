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
		String operation = "";
		int addition = input.indexOf(" + ");
		int subtraction = input.indexOf(" - ");
		int division = input.indexOf(" / ");
		int multiplication = input.indexOf(" + ");

		if (addition != -1) {
			pointofoperator = addition;
			operation = "+";
		} else if (subtraction != -1) {
			pointofoperator = subtraction;
			operation = "-";
		} else if (division != -1) {
			pointofoperator = division;
			operation = "/";
		} else if (multiplication != -1) {
			pointofoperator = multiplication;
			operation = "*";
		}

		String firstpacket = "";
		String secondpacket = "";
		for (int i = 0; i < pointofoperator; i++) {
			firstpacket = firstpacket + input.charAt(i);
		}

		for (int i = 0; i < input.length() - 3 - pointofoperator; i++) {
			secondpacket = secondpacket + input.charAt(3 + pointofoperator + i);
		}

		int firstpacketWhole = Integer.parseInt(whole(firstpacket));
		int secondpacketWhole = Integer.parseInt(whole(secondpacket));
		int firstpacketNumerator = Integer.parseInt(numerator(firstpacket));
		int secondpacketNumerator = Integer.parseInt(numerator(secondpacket));
		int firstpacketDenominator = Integer.parseInt(denominator(firstpacket));
		int secondpacketDenominator = Integer.parseInt(denominator(secondpacket));

		int firstFractionnumerator = ((firstpacketWhole * firstpacketDenominator) + firstpacketNumerator);

		int secondFractionnumerator = ((secondpacketWhole * secondpacketDenominator) + secondpacketNumerator);
		
		int calculatedfirstfractionnumerator = firstFractionnumerator * secondpacketDenominator;
		int calculatedfirstfractiondenominator = firstpacketDenominator * secondpacketDenominator;
		int calculatedsecondfractionnumerator = secondFractionnumerator * firstpacketDenominator;
		int calculatedsecondfractiondenominator = firstpacketDenominator * secondpacketDenominator;
		String answer = "";
		if (operation.equals("+")) {
			answer = (calculatedfirstfractionnumerator + calculatedsecondfractionnumerator) + "/"
					+ (calculatedfirstfractiondenominator);
		} else if (operation.equals("-")) {
			answer = (calculatedfirstfractionnumerator - calculatedsecondfractionnumerator) + "/"
					+ (calculatedfirstfractiondenominator);
		} else if (operation.equals("*")) {
			answer = (firstpacketNumerator*secondpacketNumerator) + "/"
					+ (firstpacketDenominator * secondpacketDenominator);
		} else if (operation.equals("/")) {
			answer = (calculatedfirstfractionnumerator * calculatedsecondfractiondenominator) + "/"
					+ (calculatedfirstfractiondenominator * calculatedsecondfractionnumerator);
		}
		return answer;
}
		
	public static String whole(String fraction) {
		String wholenum = "";
		if (fraction.indexOf("/") == -1) {

			wholenum = fraction;

		} else if (fraction.indexOf("_") != -1) {

			int numbersBeforeUnderscore = fraction.indexOf("_");

			for (int i = 0; i < numbersBeforeUnderscore; i++) {

				wholenum = wholenum + fraction.charAt(i);

			}
		} else if (fraction.indexOf("_") == -1) {

			wholenum = "0";

		}

		return wholenum;

	}

	public static String numerator(String fraction) {

		String numeratornum = "";

		if (fraction.indexOf("/") == -1) {

			numeratornum = "0";

		} else if (fraction.indexOf("_") != -1) {

			int numsBeforeSlash = fraction.indexOf("/");

			int numsBeforeUnderscore = fraction.indexOf("_");

			for (int i = 0; i < numsBeforeSlash - numsBeforeUnderscore - 1; i++) {

				numeratornum = numeratornum + fraction.charAt(numsBeforeUnderscore + 1 + i);

			}

		} else if (fraction.indexOf("_") == -1) {

			int numsBeforeSlash = fraction.indexOf("/");

			for (int i = 0; i < numsBeforeSlash; i++) {

				numeratornum = numeratornum + fraction.charAt(i);

			}

		}

		return numeratornum;

	}

//finds the denominator of a fraction

	public static String denominator(String fraction) {

		String denominatornum = "";

		if (fraction.indexOf("/") == -1) {

			denominatornum = "1";

		} else if (fraction.indexOf("_") != -1) {

			int numsBeforeSlash = fraction.indexOf("/");

			for (int i = 0; i < fraction.length() - numsBeforeSlash - 1; i++) {

				denominatornum = denominatornum + fraction.charAt(numsBeforeSlash + 1 + i);

			}

		} else if (fraction.indexOf("_") == -1) {

			int numsBeforeSlash = fraction.indexOf("/");

			for (int i = 0; i < fraction.length() - numsBeforeSlash - 1; i++) {

				denominatornum = denominatornum + fraction.charAt(numsBeforeSlash + 1 + i);

			}

		}

		return denominatornum;
	}
}