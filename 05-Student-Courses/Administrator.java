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
 * the user is prompt to give an action to the program, the user must write the full action (e.g. view) and press Enter
 * the program depending on the action will output the desired info or ask to give a more specific action (e.g. in case the 'manageStudents' more action is given)
 * the program will loop endlessly until the user writes "exit"
 */
public class Administrator {
	static Scanner s = new Scanner(System.in);

	// variables used accross the different functions
	private static String action;
	private static String s_id;
	private static String s_name;
	private static Integer s_age;
	private static String c_id;
	private static String c_name;
	private static String c_info;
	private static Integer c_capacity;
	private static Student student;
	private static Course course;
	private static Integer grade;

	/**
	 * get the action through getValidAction() 
	 * and call different functions based on action
	 */
	public static void main(String[] args) {
		// start the program
		mainAction();
	}

	/**
	 * create Map of valid actions, based on action given as parameter
	 * ask for further action (different action for each possible parameter)
	 * as an infinite loop until the action is considered valid (must match the key of the Map created previously)
	 * @param a_type Action Type: either mainAction or StudentAction (studentAction is a possible improvement of s_update() currently not used)
	 * @return validated action as a String
	 */
	private static String getValidAction(String a_type) {
		Map<String, String> actions = new HashMap<String, String>();

		switch (a_type) {
			case "mainAction":
				actions.put("managestudents", "Manage the students");
				actions.put("managecourses", "Manage the courses");
				actions.put("exit", "exit");
				break;
			case "studentsAction":
				actions.put("view", "View all the students details");
				actions.put("add", "Add a student");
				actions.put("update", "Update the student details");
				actions.put("delete", "Delete a student");
				actions.put("back", "Go back to previous menu");
				break;
			case "coursesAction":
				actions.put("viewstudents", "View all the students details");
				actions.put("view", "View all the courses details");
				actions.put("add", "Add a course");
				actions.put("update", "Update a course");
				actions.put("delete", "Delete a course");
				actions.put("enroll", "Enroll a student in a course");
				actions.put("unenroll", "Un-Enroll a student from a course");
				actions.put("grade", "Grade a student in a course");
				actions.put("getoverallgrade", "get the overall grade of a student");
				actions.put("back", "Go back to previous menu");
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
				String key = action.getKey().toLowerCase();
				if (action_tmp.equals(key)) {
					action_is_accepted = true;
					break;
				}

			}

			// if it is not valid informs the user and repeat the loop.
			if (!action_is_accepted)
				Utils.print_err("Your input is not valid!");
		} while (!action_is_accepted);

		// if program arrives here the action is valid so we can save it
		return action_tmp;
	}

	private static void mainAction() {
		mainLoop: while (true) {
			// get the Main action
			action = getValidAction("mainAction");

			// depending on the main action call desired methods
			switch (action) {
			case "managestudents":
				studentsAction();
				break;

			case "managecourses":
				coursesAction();
				break;
			case "exit":
				break mainLoop;
			default:
				Utils.print_err("Something is not right, please try again");
				break;
			}
		}
		System.out.println("Goodbye and have a good day!");
	}

	private static void studentsAction() {
		studentLoop: while (true) {
			// get the Student action
			action = getValidAction("studentsAction");
			// depending on the Student action call desired methods
			switch (action) {
			case "view":
				ManageStudents.s_view();
				break;
			case "add":
				addStudent();
				break;
			case "delete":
				deleteStudent();
				break;
			case "update":
				// get new info for the student
				s_id = askStudentID();
				s_name = askStudentName();
				s_age = askStudentAge();
				ManageStudents.s_update(s_id, s_name, s_age);
				break;
			case "back":
				break studentLoop;
			default:
				Utils.print_err("Something is not right, please try again");
				break;
			}
		}
	}

	private static void coursesAction() {
		courseLoop: while (true) {
			// get the Course action
			action = getValidAction("coursesAction");
			// depending on the Student action call desired methods
			switch (action) {
			case "viewstudents":
				ManageStudents.s_view();
				break;
			case "view":
				ManageCourses.c_view();
				break;
			case "add":
				addCourse();
				break;
			case "delete":
				course = askCourse();
				ManageCourses.c_rm(course);
				break;
			case "update":
				course = askCourse();
				c_name = askCourseName();
				c_info = askCourseInfo();
				c_capacity = askCourseCapacity();
				ManageCourses.c_update(course, c_name, c_info, c_capacity);
				break;
			case "unenroll":
				student = askStudent();
				course = askCourse();
				ManageCourses.c_unenroll(student, course);
				break;
			case "enroll":
				student = askStudent();
				course = askCourse();
				ManageCourses.c_enroll(student, course);
				break;
			case "grade":
				student = askStudent();
				course = askCourse();
				grade = askGrade();
				if(student.isEnrolledInCourse(course)) {
					ManageCourses.c_grade(student, course, grade);
				} else {
					Utils.print_err("student not enrolled in course!");
				}
				break;
			case "getoverallgrade":
				student = askStudent();
				Double s_overall_grade = ManageCourses.s_getOverallGrade(student);
				if (s_overall_grade != -1) {
					System.out.println("The overall grade for Student "+student.getName()+" is: "+s_overall_grade);
				} else {
					Utils.print_err("there was an error while getting the grade for the student: "+student);
				}
				break;
			case "back":
				break courseLoop;
			default:
				Utils.print_err("Something is not right, please try again");
				break;
			}
		}
	}

	/**
	 * keep asking for the student ID until a Student is found,
	 * @return Student object
	 */
	private static Student askStudent() {
		if(ManageStudents.get_ss_number_tot() == 0) {
			Utils.print_err("No students found, please add one first!");
			addStudent();
		}
			
		Student st;

		while(true) {
			System.out.print("Please insert the ID of the student: ");
			s_id = s.nextLine();
			try {
				st = ManageStudents.getStudentFromID(s_id);
				if (st == null) {
					Utils.print_err("No Student found with with id = "+s_id+", here are the possible IDs:");
					ManageStudents.s_view();
				} else {
					break;
				}
			} catch (Exception e) {
				Utils.print_err("error while retrieving the Student with id = "+s_id+", show this to the IT: "+e);
				return null;
			}
		}

		return st;
	}
	private static String askStudentID() {
		System.out.print("Please insert the ID of the student: ");
		return s.nextLine();
	}
	private static String askStudentName() {
		System.out.print("Please insert the name and surname of the student: ");
		return s.nextLine();
	}
	private static Integer askStudentAge() {
		System.out.print("Please insert the age of the student: ");
		return Utils.getValidInt(s);
	}

	/**
	 * keep asking for the course ID until a course is found,
	 * @return Course object
	 */
	private static Course askCourse() {
		if(ManageCourses.get_cc_number_tot() == 0) {
			Utils.print_err("No courses found, please add one first!");
			addCourse();
		}
			
		Course c;

		while(true) {
			System.out.print("Please insert the ID of the course: ");
			c_id = s.nextLine();

			try {
				c = ManageCourses.getCourseFromID(c_id);
				if (c == null) {
					Utils.print_err("No Course found with with id = "+c_id+", here are the possible IDs:");
					ManageCourses.c_view();
				} else {
					break;
				}
			} catch (Exception e) {
				Utils.print_err("error while retrieving the Course with id = "+c_id+", show this to the IT: "+e);
				return null;
			}
		}

		return c;
	}
	private static String askCourseName() {
		System.out.print("Please insert the name of the course: ");
		return s.nextLine();
	}
	private static String askCourseInfo() {
		System.out.print("Please insert the description of the course: ");
		return s.nextLine();
	}
	private static Integer askCourseCapacity() {
		System.out.print("Please insert the course capacity (max number of students allowed): ");
		return Utils.getValidInt(s);
	}

	private static Integer askGrade() {
		System.out.print("Please insert the grade: ");
		return Utils.getValidInt(s);
	}


	/**
	 * get all the info needed 
	 * call the needed functions to create the student
	 */
	private static void addStudent() {
		// get the info
		s_name = askStudentName();
		s_age = askStudentAge();
		ManageStudents.s_add(s_name, s_age);
	}
	/**
	 * get all the info needed 
	 * call the needed functions to delete the student
	 */
	private static void deleteStudent() {
		// get the info
		student = askStudent();
		ManageCourses.c_unenroll(student);
		ManageStudents.s_rm(student.get_ID());
	}


	/**
	 * get all the info needed for the course
	 * call the needed functions to create the course
	 */
	private static void addCourse() {
		c_name = askCourseName();
		c_info = askCourseInfo();
		c_capacity = askCourseCapacity();
		ManageCourses.c_add(c_name, c_info, c_capacity);
	}
}
