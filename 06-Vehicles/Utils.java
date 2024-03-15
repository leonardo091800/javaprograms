package vehicles;

import java.util.Scanner;

public class Utils {
	// In this way it is possible to have one scanner for all the classes inside the package
	static Scanner s = new Scanner(System.in);
	public static Scanner getScanner() {
		return s;
	}

	/* Input validation *r

	/**
	 * ask for an integer (loop until integer is positive)
	 * @return the validated int (must be a positive integer)
	 */
	public static Integer getValidInt() {
		Integer tmpInt = -1;

		// loop until quantity is a valid positive number
		while (true) {
			// making sure it is an integer
			try {
				tmpInt = s.nextInt();
				// making sure it is positive
				if (tmpInt < 0) {
					print_err("Please insert a valid number: ");
				} else {
					s.nextLine(); // consume newline character
					return tmpInt;
				}
			} catch (Exception e) {
				print_err("Please insert an integer number: ");
				s.nextLine(); // consume newline character
			}
		}
	}


	/* Output formatter */

	/**
	 * simple function that prints to console in green
	 */
	public static void print_success(String s) {
		final String ANSI_RESET = "\u001B[0m";
		final String ANSI_GREEN = "\u001B[32m";
		System.out.println(ANSI_GREEN + s + ANSI_RESET);
	}

	/**
	 * simple function that prints to console in red
	 */
	public static void print_err(String s) {
		final String ANSI_RESET = "\u001B[0m";
		final String ANSI_RED = "\u001B[31m";
		System.out.println(ANSI_RED + s + ANSI_RESET);
	}
}
