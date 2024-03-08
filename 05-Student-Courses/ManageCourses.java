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
 * ManageCourses has the following methods:
 *	view(), add(), update(), rm(), exit()
 */
public class ManageCourses {
	// number of courses
	private static int cc_number_tot = 0;
	// list of Courses
	private static List<Course> cc = new ArrayList<Course>();
	// list of student associated with the overall course grades
	private static HashMap<Student,Double> s_overall_grade = new HashMap<Student,Double>();

	/**
	 * loop through all the courses in DB and print their info
	 * if there are none print an error message and exit
	 */
	public static void c_view() {
		try {
			if (cc_number_tot <= 0) {
				// if there are none print an error message
				Utils.print_err("No courses yet, please add them");
			} else {
				// if there are print their info
				for (Course c : cc) {
					System.out.println(c);
				}
			}
		} catch (Exception e) {
			Utils.print_err("Please show the following error to the IT technician");
			System.out.println(e);
		}
	}

	/**
	 * create and add new course to DB (a local list in this case) and update courses_number_total
	 * @param c_name name for the course
	 * @param c_description description for the course
	 */
	public static Course c_add(String c_name, String c_description, Integer c_capacity) {
		try {
			Course c = new Course(c_name, c_description, c_capacity);
			cc.add(c);
			cc_number_tot = cc.size();
			Utils.print_success("New course added with ID: "+c.getID());
			return c;
		} catch (Exception e) {
			Utils.print_err("Please show the following error to the IT technician");
			System.out.println(e);
			return null;
		}
	}

	/**
	 * rm Course from DB (from the local list in this case)
	 * update cc_number_tot
	 * @param c course to oelete
	 */
	public static void c_rm(Course c) {
		try {
			cc.remove(c);
			cc_number_tot = cc.size();
			Utils.print_success("Successffuly removed course");
		} catch (Exception e) {
			Utils.print_err("Please show the following error to the IT technician");
			System.out.println(e);
		}
	}

	/**
	 * update the fields of the course
	 * @param c_id id of the course
	 * @param c_new_name new name for the course
	 * @param c_new_description new description for the course
	 */
	public static void c_update(Course c, String c_new_name, String c_new_description, Integer c_new_capacity) {
		try {
			// update course with new info
			c.setName(c_new_name);
			c.setDescription(c_new_description);
			c.setCapacity(c_new_capacity);
			Utils.print_success("Course updated!");
		} catch (Exception e) {
			Utils.print_err("Please show the following error to the IT technician");
			System.out.println(e);
		}
	}

	/**
	 * update the courses the student is enrolled into
	 * @param s the student to enroll
	 * @param c the course the student is enrolling into
	 */
	public static void c_enroll(Student s, Course c) {
		// if course is full print error and do nothing
		if (c.getStudentsEnrolled() == c.getStudentsCapacity()) {
			Utils.print_err("Sorry, the course is full");
			return;
		}

		try {
			// update the Student info
			if(s.enroll(c)) {
				// update the Course info
				c.enroll();
				Utils.print_success("Student enrolled!");
			} else {
				Utils.print_err("Cannot add student with ID "+s.get_ID()+" to course with ID "+c.getID()+", maybe it is already enrolled in the course?");
			}


		} catch (Exception e) {
			Utils.print_err("Please show the following error to the IT technician");
			System.out.println(e);
		}
	}

	/**
	 * update the courses the student is enrolled into
	 * @param s the student to unenroll
	 * @param c the course the student is enrolled into
	 */
	public static void c_unenroll(Student s, Course c) {
		try {
			// update the Student info
			if(s.unenroll(c)) {
				// update the Course info
				c.unenroll();
				Utils.print_success("Student removed from course!");
			} else {
				Utils.print_err("Cannot remove student with ID "+s.get_ID()+" from course with ID "+c.getID()+", maybe it is not in the course?");
			}
		} catch (Exception e) {
			Utils.print_err("Please show the following error to the IT technician");
			System.out.println(e);
		}
	}
	/**
	 * update the courses the student is enrolled into
	 * @param s the student to unenroll from all the courses
	 */
	public static void c_unenroll(Student s) {
		try {
			// get the courses the student is enrolled into
			Map<Course, Integer> courses = s.getCourses();
	
			// do nothing if it is empty
			if (courses.isEmpty())
				return;
	
			// uneneroll from all the courses
			for (Course c : courses.keySet()) {
				// update the Student info
				s.unenroll(c);
				// update the Course info
				c.unenroll();
			}

			Utils.print_success("Unenrolled student from all the courses!");
		} catch (Exception e) {
			Utils.print_err("Please show the following error to the IT technician");
			System.out.println(e);
		}
	}

	/**
	 * update the grade of the student in the course
	 * @param s the student to enroll
	 * @param c the course the student is enrolling into
	 * @param grade the grade as an integer
	 */
	public static void c_grade(Student s, Course c, Integer grade) {
		try {
			// update the Student info
			s.grade(c, grade);
			// update the Course info
			calculateOverallGrade(s);

			Utils.print_success("Student graded!");
		} catch (Exception e) {
			Utils.print_err("Please show the following error to the IT technician");
			System.out.println(e);
		}
	}

	/**
	 * Calculate the overall grade of the student and save
	 * @param s the student 
	 */
	public static void calculateOverallGrade(Student s) {
		try {
			// get the courses with grade the student is enrolled into
			Map<Course, Integer> courses = s.getCourses();
	
			// do nothing if it is empty
			if (courses.isEmpty())
				return;
	
			// Get the sum of the grade by looping through the grades of each corse
			Integer gradeSum = 0;
			for (int grade : courses.values()) {
				gradeSum += grade;
			}
	
			// Save the average grade
			Double gradeAverage = (double) gradeSum / courses.size();
			s_overall_grade.put(s, gradeAverage);
		} catch (Exception e) {
			Utils.print_err("Please show the following error to the IT technician");
			System.out.println(e);
		}
	}

	/**
	 * Get the overall grade of the Student
	 * @param s the student 
	 * @return grade or -1 if error
	 */
	public static Double s_getOverallGrade(Student s) {
		return s_overall_grade.getOrDefault(s, -1.0);
	}




	/**
	 * check in the DB (in the local list in this case) if the ID has a match
	 * @return the Course else return null
	 */
	public static Course getCourseFromID(String c_id) {
		// check the ID through the courses in the DB
		for (Course c : cc) {
			if (c.getID().equals(c_id)) {
				// if ID match return the course
				return c;
			}
		}

		// if for loop ended without finding ID...
		return null;
	}
	/**
	 * !!! Only first match is given, if possible use getCourseFromID(String c_id) !!!
	 * check in the DB (in the local list in this case) if the name has a match
	 * @return the Course else return null
	 */
	public static Course getCourseFromName(String c_name) {
		// check the ID through the courses in the DB
		for (Course c : cc) {
			if (c.getID().equals(c_name)) {
				// if ID match return the course
				return c;
			}
		}

		// if for loop ended without finding ID...
		return null;
	}
	/**
	 * get the total number of courses
	 * @return the total nuber of courses
	 */
	public static int get_cc_number_tot() {
		return cc_number_tot;
	}
}
