import java.util.UUID;

/**
 * This class represents a template for Courses
 *
 * it has the following private variables:
 * course code (ID), name, description, max capacity (students_capacity), students enrolled
 */
public class Course {
	private static int students_enrolled_tot;
	private String ID;
	private String name;
	private String description;
	private int students_capacity;
	private int students_enrolled;

	/**
	 * create the Course with custom description and unique ID
	 * @param name name of the Course as a String
	 */
	public Course(String name, String description, Integer s_capacity) {		
		this.ID = UUID.randomUUID().toString();
		this.name = name;
		this.description = description;
		this.students_capacity = s_capacity;
		this.students_enrolled = 0;
	}

	/**
	 * print the basic info to console
	 */
	public String toString() {
		// print basic info
		return "- ID: " + this.ID + "\n  name: " + this.name + "\n  " + this.students_enrolled + " / " + this.students_capacity + " (enrolled / capacity)" + "\n  Description: "+ this.description;
	}

	// ############### getter methods ###############
	/**
	 * Get Course ID
	 * @return the ID of the course as a string
	 */
	public String getID() {
		return this.ID;
	}
	/**
	 * Get Course name
	 * @return the name of the course as a string
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Get Course description
	 * @return the description of the course as a string
	 */
	public String getDescription() {
		return this.description;
	}
	/**
	 * Get number of students capacity in the course
	 * @return the number of students capacity as an int
	 */
	public int getStudentsCapacity() {
		return this.students_capacity;
	}
	/**
	 * Get number of students enrolled in the course
	 * @return the number of students enrolled as an int
	 */
	public int getStudentsEnrolled() {
		return this.students_enrolled;
	}

	// ############### static getter methods ###############
	/**
	 * Get number of total students enrolled in all the courses
	 * 1 student can count as more than 1 if he/she enrolled in more than 1 course
	 * @return the number of students enrolled in all the courses as an int
	 */
	public static int getStudentsEnrolledTot() {
		return students_enrolled_tot;
	}

	// ############### setter methods ###############
	/**
	 * Set Course name
	 * @param c_name New Name as a String
	 */
	public void setName(String c_name) {
		this.name = c_name;
	}
	/**
	 * Set Course description
	 * @param c_description New Description as a String
	 */
	public void setDescription(String c_description) {
		this.description = c_description;
	}
	/**
	 * Set Course student capacity
	 * @param c_capacity New capacity
	 */
	public void setCapacity(Integer c_capacity) {
		this.students_capacity = c_capacity;
	}
	/**
	 * Increase number of students enrolled in the course
	 * @return true/false for success or fail
	 */
	public boolean enroll() {
		try {
			this.students_enrolled += 1;	// add student to course
			students_enrolled_tot += 1;		// keep track of all the students enrolled
			return true;
		} catch (Exception e) {
			System.out.println("ERROR, show this to the sys admin: "+e);
			return false;
		}
	}
	/**
	 * Decrease number of students enrolled in the course
	 * @return true/false for success or fail
	 */
	public boolean unenroll() {
		try {
			this.students_enrolled -= 1;	// rm student from course
			students_enrolled_tot -= 1;		// keep track of all the students enrolled
			return true;
		} catch (Exception e) {
			System.out.println("ERROR, show this to the sys admin: "+e);
			return false;
		}
	}
}
