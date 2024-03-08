import java.util.UUID;

/**
 * 
 * Student Class creates instances of Students, with private name, age and
 * grade,
 * 
 * the constructor requires: String name, int age, int grade
 * 
 */
class Student {
	private String ID;
	private String name;
	private Integer age;
	private Integer grade;

	/**
	 * create the Student with custom info and unique ID
	 * @param name  name of the Student as a String
	 * @param age   age of the Student as a int
	 * @param grade grade of the Student as a int
	 */
	public Student(String name, Integer age, Integer grade) {
		this.ID = UUID.randomUUID().toString();
		this.name = name;
		this.age = age;
		this.grade = grade;
	}

	/**
	 * print the user info to console
	 */
	public void printInfo() {
		System.out.println("ID: " + this.ID + " name: " + this.name + " age: " + this.age + " grade: " + this.grade);
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
	 * Update the Student grade
	 * @param grade new grade of the Student as a int
	 */
	public void update_grade(int grade) {
		this.grade = grade;
	}

	/**
	 * Get Student ID
	 * @return the ID of the student as a string
	 */
	public String get_ID() {
		return this.ID;
	}
}
