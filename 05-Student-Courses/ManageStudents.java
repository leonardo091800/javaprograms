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
 * ManageStudents has the following methods:
 *	view(), add(), update(), rm()
 */
public class ManageStudents {
	// variables used accross the different functions
	static String action = "";

	// list of students
	private static List<Student> ss = new ArrayList<Student>();
	private static int ss_number_tot = 0;

	/**
	 * loop through all the students in DB and print their info through Student.printInfo()
	 * if there are no students print an error message and exit
	 */
	public static void s_view() {
		try {
			if (ss_number_tot <= 0) {
				// if there are no students print an error message
				Utils.print_err("No students yet, please add them");
			} else {
				// if there are call the printInfo() method of the Student class
				for (int i = 0; i < ss_number_tot; i++) {
					ss.get(i).printInfo();
				}
			}
		} catch (Exception e) {
			Utils.print_err("Please show the following error to the IT technician");
			System.out.println(e);
		}
	}

	/**
	 * create and add new student to DB (a local list in this case) and update students_number_total
	 * @param st a student instance
	 */
	public static Student s_add(String s_name, Integer s_age) {
		try {
			Student st = new Student(s_name, s_age);
			ss.add(st);
			ss_number_tot = ss.size();
			Utils.print_success("New student added with ID: "+st.get_ID());
			return st;
		} catch (Exception e) {
			Utils.print_err("Please show the following error to the IT technician");
			System.out.println(e);
			return null;
		}
	}

	/**
	 * get key of student through getKeyFromID() (not as a loop, because if there isn't any student ID then
	 * user cannot exit)
	 * rm student from DB (from the local list in this case)
	 * update students_number_tot
	 */
	public static void s_rm(String s_id) {
		int ss_key = -1;

		// get the key of the student to delete
		ss_key = getKeyFromID(s_id);
		if (ss_key == -1) {
			// if key not valid exit, error message already printed by getKeyFromID()
			return;
		} else {
			// if key is valid, remove student & update students_number_tot
			ss.remove(ss_key);
			ss_number_tot = ss.size();
			Utils.print_success("Successffuly removed student");
		}
	}

	/**
	 * get the user key through getKeyFromID()
	 * ask for info to change
	 * update the desired fields
	 */
	public static void s_update(String s_id, String s_new_name, Integer s_new_age) {
		// get the student ID
		int ss_key = getKeyFromID(s_id);
		if (ss_key == -1) {
			return;
		}

		try {
			// update student with new info
			ss.get(ss_key).update_name(s_new_name);
			ss.get(ss_key).update_age(s_new_age);
			Utils.print_success("Student updated!");
		} catch (Exception e) {
			Utils.print_err("Please show the following error to the IT technician");
			System.out.println(e);
		}
	}


	/**
	 * check in the DB (in the local list in this case) if the ID has a match
	 * @return the key of the student (as an int) if its ID is in the DB (in the local list in this case), else return -1 and print error
	 */
	private static int getKeyFromID(String s_id) {
		// check the ID through the students in the DB
		for (int i = 0; i < ss_number_tot; i++) {
			if (ss.get(i).get_ID().equals(s_id)) {
				// if ID match return the key of the user
				return i;
			}
		}

		// if for loop ended without finding ID...
		Utils.print_err("Student with ID=" + s_id + " not found");
		return -1;
	}

	/**
	 * check in the DB (in the local list in this case) if the ID has a match
	 * @return the student else return null
	 */
	public static Student getStudentFromID(String s_id) {
		// check the ID through the students in the DB
		for (Student s : ss) {
			if (s.get_ID().equals(s_id)) {
				// if ID match return the course
				return s;
			}
		}

		// if for loop ended without finding ID...
		return null;
	}
	/**
	 * !!! Only first match is given, if possible use getCourseFromID(String c_id) !!!
	 * check in the DB (in the local list in this case) if the ID has a match
	 * @return the student else return null
	 */
	public static Student getStudentFromName(String s_name) {
		// check the ID through the students in the DB
		for (Student s : ss) {
			if (s.get_ID().equals(s_name)) {
				// if ID match return the course
				return s;
			}
		}

		// if for loop ended without finding ID...
		return null;
	}
	/**
	 * get the total number of students
	 * @return the total nuber of students
	 */
	public static int get_ss_number_tot() {
		return ss_number_tot;
	}
}
