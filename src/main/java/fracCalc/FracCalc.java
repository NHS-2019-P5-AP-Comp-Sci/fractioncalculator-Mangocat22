package fracCalc;

import java.util.Scanner;

public class FracCalc {
	// Defines calculatedNumeratorValue and calculatedDenominatorValue as static
	// integer values so that their scope is the entire class FracCalc.
	// These are updated throughout the program to mitigate redundancy (instead of
	// defining new data points)
	static int calculatedNumeratorValue;
	static int calculatedDenominatorValue;

	public static void main(String[] args) {
		System.out.println("To exit FracCalc, enter \"quit\".");
		Scanner feedback = new Scanner(System.in);
		String input = feedback.nextLine(); // The fractions the user writes make up input
		while (!input.equalsIgnoreCase("quit")) { // Run the function unless the user writes quit (capitalized or not)
			System.out.println(produceAnswer(input));
			System.out.println("To exit FracCalc, enter \"quit\".");
			input = feedback.nextLine();
		}
		feedback.close();
		System.out.println("Here are the results.");
	}

	// ** IMPORTANT ** DO NOT DELETE THIS FUNCTION. This function will be used to
	// test your code
	// This function takes a String 'input' and produces the result
	//
	// input is a fraction string that needs to be evaluated. For your program, this
	// will be the user input.
	// e.g. input ==> "1/2 + 3/4"
	//
	// The function should return the result of the fraction after it has been
	// calculated
	// e.g. return ==> "1_1/4"
	public static String produceAnswer(String input) {
// Finds the space and gives it a positional value
		int positionofspace = input.indexOf(" ");
		if (positionofspace == -1) {
			return ("ERROR");
		} else {
// Pulls apart user input by parsing and stores the various parts of each fraction (like numerators, denominators, and operations).
			String datapiece = pullData(input);
			input = updateRawInput(input);
			parseFraction(datapiece);
			int firstPacketNumerator = calculatedNumeratorValue;
			int firstPacketDenominator = calculatedDenominatorValue;
// First round (above) for first fraction
			datapiece = pullData(input);
			input = updateRawInput(input);
			String operation = datapiece;
// Second round (above) for operation
			datapiece = pullData(input);
			input = updateRawInput(input);
			parseFraction(datapiece);
			int secondPacketNumerator = calculatedNumeratorValue;
			int secondPacketDenominator = calculatedDenominatorValue;
// Third round (above) for second fraction
			boolean calcCheck;
// calcCheck is a boolean that is true if the user input follows proper formatting and mathematical rules.
			calcCheck = calculator(firstPacketNumerator, firstPacketDenominator, secondPacketNumerator,
					secondPacketDenominator, operation);
			if (calcCheck) {
				return print(calculatedNumeratorValue, calculatedDenominatorValue);
			} else {
				return "ERROR";
			}
		}
	}

// Static method used for parsing in the method produceAnswer. It isolates a section of a string to create a substring that is then parsed.
	public static String pullData(String input) {
		int positionofspace = input.indexOf(' ');
		if (positionofspace == -1)
			return input;
		return input.substring(0, positionofspace);
	}

// Static method that takes the input returned from the method pullData and updates for future use.   
// The paired calling of pullData and updateRawInput parses the user feedback packets into useful components.
	public static String updateRawInput(String input) {
		int space = input.indexOf(" ");
		if (space == -1)
			return "";
		return input.substring(space + 1);
	}

	public static void parseFraction(String input) {

		int underscore = input.indexOf('_');
		int frontslash = input.indexOf('/');
		if (underscore > 0) {
// This part of the if statement only runs if the user inputs a mixed number
			int whole = Integer.parseInt(input.substring(0, underscore));
			calculatedNumeratorValue = Integer.parseInt(input.substring(underscore + 1, frontslash));
			if (whole < 0)
// This part of the if statement only runs if the whole number part of the mixed fraction is negative
				calculatedNumeratorValue = 0 - calculatedNumeratorValue;
			calculatedDenominatorValue = Integer.parseInt(input.substring(frontslash + 1));
			calculatedNumeratorValue = calculatedNumeratorValue + whole * calculatedDenominatorValue;
		} else if (frontslash > 0) {
// This part of the if statement only runs if the user inputs a mixed number. It logically is paired with the first if statement.
			calculatedNumeratorValue = Integer.parseInt(input.substring(0, frontslash));
			calculatedDenominatorValue = Integer.parseInt(input.substring(frontslash + 1));
		} else {
// This part of the if statement only runs if the user inputs a whole number.
			calculatedNumeratorValue = Integer.parseInt(input);
			calculatedDenominatorValue = 1;
		}
	}

	// this method runs the calculation and returns true if the user input follows
	// all the rules. If not, it returns an error statement.
	public static boolean calculator(int firstnumerator, int firstdenominator, int secondnumerator,
			int seconddenominator, String operation) {
		if (operation.equals("+")) {
			calculatedNumeratorValue = firstnumerator * seconddenominator + secondnumerator * firstdenominator;
			calculatedDenominatorValue = firstdenominator * seconddenominator;
		} else if (operation.equals("-")) {
			calculatedNumeratorValue = firstnumerator * seconddenominator - secondnumerator * firstdenominator;
			calculatedDenominatorValue = firstdenominator * seconddenominator;
		} else if (operation.equals("*")) {
			calculatedNumeratorValue = firstnumerator * secondnumerator;
			calculatedDenominatorValue = firstdenominator * seconddenominator;
		} else if (operation.equals("/")) {
			calculatedNumeratorValue = firstnumerator * seconddenominator;
			calculatedDenominatorValue = firstdenominator * secondnumerator;
		}
		// Checks for user input formatting or mathematical rule error
		else {
			System.out.println("ERROR");
			return false;
		}
		return true;
	}

	// calculates denominator
	public static int calculateReducedDenominator(int numerator, int denominator) {
		while (true) {
			if (numerator < denominator) {
				int a = numerator;
				numerator = denominator;
				denominator = a;
			}
			if (denominator == 0)
				return numerator;
			numerator = numerator % denominator;
		}
	}

// Prints the reduced version of the calculated fraction produced by the method calculator
	public static String print(int finalnumerator, int finaldenominator) {
		String result = "";
// Switches negative on denominator to negative on numerator
		if (finaldenominator < 0) {
			finalnumerator = 0 - finalnumerator;
			finaldenominator = Math.abs(finaldenominator);
		}
		if (finalnumerator < 0) {
// Adds a negative to final numerator if it is negative
			result += "-";
			finalnumerator = Math.abs(finalnumerator);
		}
// Calculates greatest common denominator 
		int commondem = calculateReducedDenominator(finalnumerator, finaldenominator);
		finalnumerator = finalnumerator / commondem;
		finaldenominator = finaldenominator / commondem;
// Simplify
		int wholenumber = finalnumerator / finaldenominator;
		finalnumerator = finalnumerator % finaldenominator;
		if (finalnumerator == 0) {
			result += wholenumber;
		} else {
			if (wholenumber > 0) {
				result += (wholenumber + "_");
			}
			result += (finalnumerator + "/" + finaldenominator);
		}
		return result;
	}
}
