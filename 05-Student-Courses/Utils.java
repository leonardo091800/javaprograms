import java.util.Scanner;

/**
 * Simple utils class with useful methods such as 
 *
 * print_success
 * print_err
 * getVlidInt
 */
public class Utils {
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

	/**
	 * ask for an integer (loop until integer is positive)
	 * @return the validated int (must be a positive integer)
	 */
	public static Integer getValidInt(Scanner s) {
		Integer tmpInt = -1;

		// loop until quantity is a valid positive number
		while (true) {
			// making sure it is an integer
			try {
				tmpInt = s.nextInt();
				// making sure it is positive
				if (tmpInt < 1) {
					Utils.print_err("Please insert a valid number: ");
				} else {
					s.nextLine(); // consume newline character
					return tmpInt;
				}
			} catch (Exception e) {
				Utils.print_err("Please insert an integer number: ");
				s.nextLine(); // consume newline character
			}
		}
	}
}
