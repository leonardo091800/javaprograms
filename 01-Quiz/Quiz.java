import java.util.Arrays;
import java.util.Scanner;

/*
 * the Quiz class has an internal DB made by 3 arrarys:
 * questions[4] { q1, q2, q3, q4, q5]			// qX are stored as Strings
 * answers[4][3] {
 *	0 -> a1, a2, a3, a4
 *	1 -> a1, a2, a3, a4
 *	2 -> a1, a2, a3, a4
 *	3 -> a1, a2, a3, a4 }				// aX are stored as Strings
 * answers_correct[4] { a2_key, a4_key, a2_key, a1_key }	// aX_key are stored as int, they are the keys of the previous aX
 *
 * it also has some global parameters such as
 * int q_correct = n' of correct answers
 * int q_total = n' of questions answered to
 *
 * the flow of the program is:
 *	print questions
 *		print answers
 *	get response
 *	check response & update score
 *	print final score
 *	ask if wants to play again
 */
public class Quiz {
	static boolean playQuiz = true; // by default the user can play the game indefintely, if false the program stops
	
	// array with questions
	static String[] questions = {
		"What is my name?",
		"What is my surname?",
		"What world am I from?",
		"What color is my favorite?",
		"What is our ultimate goal?"
	};

	// array with answers for each of the previous questions
	static String[][] answers = {
		{"Eldwizand", "Devredout", "Redwition", "Golangnet"},
		{"Froelvand", "Kinbrolds", "Drawitles", "Deaunkoon"},
		{"Redwition", "Redsorrth", "Quefaialm", "Redwaroon"},
		{"Amber", "Beaver", "Bole", "Buff"},
		{"live", "have fun", "play", "no idea"}
	};

	// array with the key of the answers that are correct
	static int[] answers_correct = { 1, 0, 2, 0, 3 };

	// array with the acceptable answers (to make sure users cannot insert other characters)
	static char[] a_acceptables = {'A', 'B', 'C', 'D'};

	// global variables to keep track of the score
	static int q_correct = 0;
	static int q_tot = 0;

	public static void main(String[] args) { 
		Scanner s = new Scanner(System.in);

		// a bit of introduction to the game:
		System.out.println("Hi and welcome to this game, please answer each question with a single character \n");

		// start the playQuiz
		while (playQuiz) {
			// loop through the various questions
			for (int i = 0; i < questions.length; i++) {
				// print questions + answers and store the correct answer
				char a_correct = printQuestion(i);
				
				// ask the user to guess and store the guessed answer
				char a_guessed = a_get(s);

				// compare the answers and update the score
				score_update(a_correct == a_guessed);
			}

			// in order to keep the main tidy an additional function is made
			score_print();

			// ask the user if he wants to play again
			System.out.println("Do you want to play the quiz again? (y/n)");
			char playQuizAnswer = s.next().charAt(0);

			// if he prints 'y' then it continues, any other values is considered false (so no need to validate with try catch)
			playQuiz = (playQuizAnswer == 'y') ? true : false;
		}
		s.close();
	}

	/*
	 * This function print the questions and relative answers
	 * it returns the LETTER of the correct answer
	 */
	public static char printQuestion(int q_key) {
		// Let's print the Question with a bit of space in order to make it prettier
		System.out.println("\n "+questions[q_key]);

		// the beginning char for the answers
		char a_letter = 'A';
		// let's print the various answers related to this question
		for ( int i = 0; i < answers[q_key].length; i++ ) {
			// the initial part of the answer should be something similar to "A: "
			String a_init = new StringBuilder().append(a_letter).toString();
			// let's put together the initial part and the actual answer such as "A: The answer is..."
			String a_complete = a_init+": "+answers[q_key][i];
			// and print it to output
			System.out.println(a_complete);

			// let's increase the initial letter, A -> B, before starting the loop again
			a_letter++;
		}

		// return the correct letter based on the key of the correct answer:
		return a_acceptables[answers_correct[q_key]];
	}

	/*
	 * this function needs the scanner as the parameter,
	 * it asks, receive and validate the user input (asking again indefinitely until a good input is received)
	 * it returns the validated user input
	 */
	public static char a_get(Scanner s) {
		boolean correct = false;
		char a_guessed = 'a';
		while(correct == false) {
			System.out.println("What is your answer?");
			try {
				// get only the first characater of the string without throwing exception
				if(s.hasNextLine()) a_guessed = s.next().charAt(0);
				// check that character is in the acceptable answers:
				correct = (new String(a_acceptables).indexOf(a_guessed) == -1) ? false : true;

			} catch (Exception e ) {
				System.out.println("exception: "+e);
			}
			// if it is not correct remind the User to only use maiusc characters
			if(!correct) System.out.println("You must insert a single MAIUSC character");
		}

		return a_guessed;
	}

	/*
	 * simple function that UPDATE the score
	 */
	public static void score_update(boolean is_guess_correct) {
		// if "guessed correctly? True" then increase the count of correct score
		if (is_guess_correct) {
			q_correct++;
		}
		// in any case let's increase the cunt of questions answered
		q_tot++;
	}

	/*
	 * simple function that PRINT and RESET the score
	 */
	public static void score_print() {
		// Let's print and reset the score
		System.out.println("\n You guessed "+q_correct+" out of "+q_tot+" total questions, so your score is "+(q_correct*100.0f/q_tot)+"%");
		q_correct=0;
		q_tot=0;
	}

	/*
	 * helpful while debugging
	 */
	public static void sleep() {
		try {
		  Thread.sleep(2000);
		} catch (InterruptedException e) {
		  Thread.currentThread().interrupt();
		}
	}
}
