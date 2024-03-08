import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Student Class creates instances of Students, with private name, age and grade,
 * the constructor requires: String name, int age
 */
class Student {
	private String ID;
	private String name;
	private Integer age;
	private Map<Course, Integer> courses;

	/**
	 * create the Student with custom info and unique ID
	 * @param name name of the Student as a String
	 * @param age age of the Student as a int
	 */
	public Student(String name, Integer age) {
		this.ID = UUID.randomUUID().toString();
		this.name = name;
		this.age = age;
		this.courses = new HashMap<Course, Integer>();
	}

	/**
	 * print the user info to console
	 */
	public void printInfo() {
		// print basic info
		System.out.println("- ID: " + this.ID + "\n  name: " + this.name + "\n  age: " + this.age);

		// print info that student is not enrolled in any courses if courses are empty
		if(this.courses.isEmpty()) {
			System.out.println("The student is not enrolled in any course yet");
		} else {
			printCourses();
		}
	}
	/**
	 * List all courses the student is enrolled in
	 */
	public void printCourses() {
		courses.forEach((course, grade) -> { System.out.println("\tgrade: "+grade+" --> "+course.getName());} );
	}


	/**
	 * Update the Student name
	 * @param name New name of the Student as a String
	 */
	public void update_name(String name) {
		this.name = name;
	}

	/**
	 * Update the Student age
	 * @param age new age of the Student as a int
	 */
	public void update_age(int age) {
		this.age = age;
	}
	/**
	 * Get Student ID
	 * @return the ID of the student as a string
	 */
	public String get_ID() {
		return this.ID;
	}
	/**
	 * Get Student name
	 * @return the name as string
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Get Student courses (with grades)
	 * @return the Map of each course and its grade
	 */
	public Map getCourses() {
		return this.courses;
	}


	/**
	 * Enroll student in course with default grade of 0
	 * @param course a valid course as a String
	 */
	public boolean enroll(Course c) {
		if(this.isEnrolledInCourse(c)) {
			return false;
		} else {
			this.courses.put(c, 0);
			return true;
		}
	}
	/**
	 * Check if student is enrolled in course
	 * @param course a valid course as a String
	 */
	public boolean isEnrolledInCourse(Course c) {
		return (courses.containsKey(c)) ? true : false;
	}
	/**
	 * UN-Enroll student from a course
	 * @param course a valid course as a String
	 */
	public boolean unenroll(Course c) {
		if (this.courses.remove(c) != null) {
			return true;
		} else {
			return false;
		}
	}


	/**
	 * Update the Student grade of a specific course
	 * @param course course to change the grade
	 * @param grade new grade of the Student as a int
	 */
	public void grade(Course c, Integer grade) {
		this.courses.put(c, grade);
	}

}
