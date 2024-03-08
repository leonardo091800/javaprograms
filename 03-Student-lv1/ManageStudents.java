import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

/**
 * 
 * ManageStudents has the following methods:
 * 
 * view(), add(), update(), rm(), exit()
 *
 * 
 * 
 * the user is prompt to give an action to the program, the user must write the
 * full action (e.g. view) and press Enter
 * 
 * the program depending on the action will output the desired info or ask to
 * give a more specific action (e.g. in case the 'update' more action is given)
 * 
 * the program will loop endlessly until the user writes "exit"
 * 
 */
public class ManageStudents {
	static Scanner s = new Scanner(System.in);
	// variables used accross the different functions
	static String action = "";
	// list of students
	private static List<Student> ss = new ArrayList<Student>();
	private static int ss_number_tot = 0;

	/**
	 * 
	 * get the action through getValidAction()
	 * 
	 * and call different functions based on action
	 * 
	 */
	public static void main(String[] args) {
		mainLoop: while (true) {
			// get the action
			action = getValidAction("mainAction");
			// depending on the action call desired methods
			switch (action) {
				case "view":
					s_view();
					break;
				case "add":
					// get the student info
					System.out.print("\nPlease insert the Name and Surname of the student: ");
					String s_name = s.nextLine();
					System.out.print("Please insert the age of the student: ");
					int s_age = getValidInt();
					System.out.print("Please insert the grade of the student: ");
					int s_grade = getValidInt();
					Student st = new Student(s_name, s_age, s_grade);
					// save student in DB
					s_add(st);
					break;
				case "delete":
					s_rm();
					break;
				case "update":
					s_update();
					break;
				case "exit":
					break mainLoop;
				default:
					print_err("Something is not right, please try again");
					break;
			}
		}
		System.out.println("Goodbye and have a good day!");
	}

	/**
	 * 
	 * loop through all the students in DB and print their info through
	 * Student.printInfo()
	 * 
	 * if there are no students print an error message and exit
	 * 
	 */
	private static void s_view() {
		try {
			if (ss_number_tot <= 0) {
				// if there are no students print an error message
				print_err("No students yet, please add them");
			} else {
				// if there are call the printInfo() method of the Student class
				for (int i = 0; i < ss_number_tot; i++) {
					ss.get(i).printInfo();
				}
				print_success("Done");
			}
		} catch (Exception e) {
			print_err("Please show the following error to the IT technician");
			System.out.println(e);
		}
	}

	/**
	 * 
	 * add new student to DB (a local list in this case) and update
	 * students_number_total
	 * 
	 * @param st a student instance
	 * 
	 */
	private static void s_add(Student st) {
		try {
			ss.add(st);
			ss_number_tot = ss.size();
			print_success("New student added");
		} catch (Exception e) {
			print_err("Please show the following error to the IT technician");
			System.out.println(e);
		}
	}

	/**
	 * 
	 * get key of student through getKeyFromID() (not as a loop, because if there
	 * isn't any student ID then user cannot exit)
	 * 
	 * rm student from DB (from the local list in this case)
	 * 
	 * update students_number_tot
	 * 
	 */
	private static void s_rm() {
		int ss_key = -1;
		// get the key of the student to delete
		ss_key = getKeyFromID();
		if (ss_key == -1) {
			// if key not valid exit, error message already printed by getKeyFromID()
			return;
		} else {
			// if key is valid, remove student & update students_number_tot
			ss.remove(ss_key);
			ss_number_tot = ss.size();
			print_success("Successffuly removed student");
		}
	}

	/**
	 * 
	 * get the user key through getKeyFromID()
	 * 
	 * ask for info to change
	 * 
	 * update the desired fields
	 * 
	 */
	private static void s_update() {
		// get the student ID
		int ss_key = getKeyFromID();
		if (ss_key == -1) {
			return;
		}
		// get new info for the student
		System.out.print("\nPlease insert the Name and Surname of the student: ");
		String s_name = s.nextLine();
		System.out.print("Please insert the age of the student: ");
		int s_age = getValidInt();
		System.out.print("Please insert the grade of the student: ");
		int s_grade = getValidInt();
		try {
			// update student with new info
			ss.get(ss_key).update_name(s_name);
			ss.get(ss_key).update_age(s_age);
			ss.get(ss_key).update_grade(s_grade);
			print_success("Student updated!");
		} catch (Exception e) {
			print_err("Please show the following error to the IT technician");
			System.out.println(e);
		}
	}

	/**
	 * 
	 * create Map of valid actions, based on action given as parameter
	 * 
	 * ask for further action (different action for each possible parameter)
	 * 
	 * as an infinite loop until the action is considered valid (must match the key
	 * of the Map created previously)
	 * 
	 * @param a_type Action Type: either mainAction or StudentAction (studentAction
	 *               is a possible improvement of s_update() currently not used)
	 * 
	 * @return validated action as a String
	 * 
	 */
	private static String getValidAction(String a_type) {
		Map<String, String> actions = new HashMap<String, String>();
		switch (a_type) {
			case "mainAction":
				actions.put("view", "view all the students details");
				actions.put("add", "Add a student");
				actions.put("update", "update the student details");
				actions.put("delete", "Delete a student");
				actions.put("exit", "exit");
				break;
			case "studentAction":
				actions.put("name", "Change student name");
				actions.put("age", "Change student age");
				actions.put("grade", "Change student grade");
				actions.put("exit", "exit");
				break;
			default:
				break;
		}
		// tmp values
		boolean action_is_accepted = false;
		String action_tmp = "";
		do {
			// program will print the possible actions
			System.out.println("\nWrite what you want this program to do: ");
			for (Map.Entry<String, String> action : actions.entrySet()) {
				String key = action.getKey();
				String val = action.getValue();
				System.out.println(" - " + key + ": " + val);
			}
			// read the user input and check is valid
			action_tmp = s.nextLine();
			for (Map.Entry<String, String> action : actions.entrySet()) {
				String key = action.getKey();
				if (action_tmp.equals(key)) {
					action_is_accepted = true;
					break;
				}
			}
			// if it is not valid informs the user and repeat the loop.
			if (!action_is_accepted)
				print_err("Your input is not valid!");
		} while (!action_is_accepted);
		// if program arrives here the action is valid so we can save it
		return action_tmp;
	}

	/**
	 * 
	 * ask for an integer (loop until integer is positive)
	 * 
	 * @return the validated int (must be a positive integer)
	 * 
	 */
	private static Integer getValidInt() {
		Integer tmpInt = -1;
		// loop until quantity is a valid positive number
		while (true) {
			// making sure it is an integer
			try {
				tmpInt = s.nextInt();
				// making sure it is positive
				if (tmpInt < 1) {
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

	/**
	 * 
	 * ask the user ONCE the ID
	 * 
	 * check in the DB (in the local list in this case) if the ID has a match
	 * 
	 * @return the key of the student (as an int) if its ID is in the DB (in the
	 *         local list in this case), else return -1 and print error
	 * 
	 */
	private static int getKeyFromID() {
		// get the ID
		System.out.print("\nPlease insert the ID of the student: ");
		String s_id_tmp = s.nextLine();
		// check the ID through the students in the DB
		for (int i = 0; i < ss_number_tot; i++) {
			if (ss.get(i).get_ID().equals(s_id_tmp)) {
				// if ID match return the key of the user
				return i;
			}
		}
		// if for loop ended without finding ID...
		print_err("Student with ID=" + s_id_tmp + " not found");
		return -1;
	}

	/**
	 * 
	 * simple function that prints to console in green
	 * 
	 */
	private static void print_success(String s) {
		final String ANSI_RESET = "\u001B[0m";
		final String ANSI_GREEN = "\u001B[32m";
		System.out.println(ANSI_GREEN + s + ANSI_RESET);
	}

	/**
	 * 
	 * simple function that prints to console in red
	 * 
	 */
	private static void print_err(String s) {
		final String ANSI_RESET = "\u001B[0m";
		final String ANSI_RED = "\u001B[31m";
		System.out.println(ANSI_RED + s + ANSI_RESET);
	}
}
