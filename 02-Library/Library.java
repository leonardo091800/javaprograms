import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.print.event.PrintServiceAttributeListener;

/*
 * overview:
 *   while(true)
 *     getAction
 *       add -> add(false)
 *       borrow -> borrow()
 *       return -> add(true)
 *       exit -> exit
 *
 *  book_exist()
 *    t = getTitle
 *    a = getAuthor
 *    q = getQuantity
 *    t_keys = t ? in titles
 *    while(t_keys)
 *      if(a == authors[t_key])
 *        return t_key
 *      return -1
 *
 *  add(boolean return)
 *    key = book_exists()
 *    if key != -1
 *      if(return) -> error
 *      else add_existing(key, q)
 *    else add_new(t, a, q)
 *       
 *  borrow()
 *    key = book_exists())
 *    if key == -1 -> error
 *    else add_existing(key, q)
 *
 *
 */
public class Library {
	/* 
	 * Internal Arrays of books stored in the Library
	 * Each book is identified by a unique key,
	 * For each book the program fetchs title[key], author[key], quantity[key]
	 */
	static List<String> title = new ArrayList<>(Arrays.asList("It", "Dune", "Jaws", "Macbeth"));
	static List<String> author = new ArrayList<>(Arrays.asList("Stephen King", "Frank Herbert", "Peter Benchley", "William Shakespeare"));
	static List<Integer> quantity = new ArrayList<>(Arrays.asList(3, 5, 14, 1));

	/*
	 * These are placeholders,
	 * they will be overwritten every time an action is taken, 
	 * they are used by multiple functions in this class
	 */
	static String action = "";	// action to take
	static int book_key = -1;	// key of the book the user is working on
	static String t = "";		// title of the book the user is working on
	static String a = "";		// author of the book the user is working on
	static int q = -1;		// quantity of the book the user is working on

	static Scanner s = new Scanner(System.in);

	/*
	 * keep asking what to do, (through getAction method)
	 * for each action reset the commoon variables (t, a, q) and call the appropriate metohds based on action given
	 */
	public static void main(String[] args) {
		mainLoop:
		while(true) {
			// cleaning the common variables for every action taken:
			action = "";
			book_key = -1;
			t = "";	 
			a = "";	 
			q = -1;	 

			// ask the user to decide on an action to take
			getAction();
			// to better the output
			System.out.println();
			switch(action) {
				case "consult":
					consult();
					break;
				case "add":
					add(false);
					break;
				case "borrow":
					borrow();
					break;
				case "return":
					add(true);
					break;
				case "exit":
					break mainLoop;
				default:
					print_err("error, this shouldn't have happened inform admin of possible bug...");
			}
		}
	}

	/*
	 * print the possible actions, read & validate user input, save the command
	 */
	private static void getAction() {
		// local dictionary with possible action and explanation
		Dictionary<String, String> actions = new Hashtable<>();
		actions.put("consult", "consult the library");
		actions.put("add", "Add a book in the Library");
		actions.put("borrow", "Borrow a book from the Library");
		actions.put("return", "Return a book taken from the Library");
		actions.put("exit", "exit");
		Enumeration<String> keys = actions.keys();	// needed to loop between the actions

		// array with accepted actions
		String[] actions_accepted = {"add", "borrow", "return", "exit", "consult"};
		// boolean value default to check, if it does not turn true the program will keep asking a valid action:
		boolean action_is_accepted = false;
		// tmp variable to store user input
		String action_tmp = "";
		while(!action_is_accepted) {
			// program will print the possible actions
			System.out.println("\nWrite what you want this program to do (e.g. add )");
			while(keys.hasMoreElements()) {
				String key = keys.nextElement();
				System.out.println(" - "+key+": "+actions.get(key));
			}
			// read the user input
			action_tmp = s.nextLine();
			// if the input is valid action_is_accepted becomes true
			action_is_accepted = ( Arrays.asList(actions_accepted).contains(action_tmp) ) ? true : false;
			// if it is not true informs the user and repeat the loop.
			if(!action_is_accepted) System.out.println("Your input is not valid!"); 
		}

		// if program arrives here the action is valid so we can save it
		action = action_tmp;
	}

	/*
	 * print all the books in the library
	 */
	private static void consult() {
		for(int i = 0; i<title.size(); i++) {
			System.out.println(""+Integer.toString(quantity.get(i))+" books with title: '"+title.get(i)+"', by author: '"+author.get(i)+"'");
		}
	}

	/*
	 * Call function book_exists to store title (t), author (a) and quantity (g) and get the key
	 * key = -1 if book does not exist.
	 * if book does not exist and action is to return print error, add the new book otherwise (action is to add)
	 * if book exist update the quantity (both with action return and action add)
	 */
	private static void add(boolean is_return) {
		// get the dictionary key of the book (the function also stores t, a, q in global variables)
		book_key = book_exists();

		// if book does not exist:
		if ( book_key < 0 ) {
			// and the action is return, then print an error message
			if( is_return ) {
				print_err("This book was not taken from this Library!");
			} 
			// if action is to add, then add a new book
			else {
				add_new();
				print_success("New book added successfully");
			}
		} 
		// if book exists update its quantity:
		else {
			update_quantity();
			if( is_return ) {
				print_success("Book returned successfully");
			} else {
				print_success("Book added successfully");
			}
		}
	}

	/*
	 * Call function book_exists to store title (t), author (a) and quantity (g) and get the key
	 * key = -1 if book does not exist.
	 * if book does not exist print error and exit,
	 *   if quantity is positive (books remaining in the library) call update_quantity()
	 *   else inform user that borrow action is not possible
	 */
	private static void borrow() {
		// get the dictionary key of the book (the function also stores t, a, q in global variables)
		book_key = book_exists();

		// if book does not exist:
		if ( book_key < 0 ) {
			print_err("This book does not exist in this Library!");
		} else {
			if ( q < 0 ) {
				print_err("Sorry, the amount you want to borrow is more than what we have");
				System.out.println(quantity.get(book_key)+" books '"+title.get(book_key)+"' made by '"+author.get(book_key));
			} else {
				print_success("Books borrowed successfully");
				update_quantity();
			}
		}
	}

	/*
	 * ask the user the title, author and quantity of the desired book
	 */
	private static int book_exists() {
		// get the book details
		// title
		System.out.print("\nPlease insert the title of the book: ");
		t = s.nextLine();
		// author (only if add command)
		if (action.equals("add")) {
			System.out.print("Please insert the author of the book: ");
			a = s.nextLine();
		}
		// quantity
		System.out.print("Please insert the quantity of the book: ");
		// loop until quantity is a valid positive number
		while(true) {
			// making sure it is an integer
			try {
				q = s.nextInt();
			} catch (Exception e) {
				print_err("Please insert an integer number: ");
				s.nextLine(); // consume newline character
			}
			// making sure it is positive
			if (q < 1) {
				print_err("Please insert a valid number: ");
			} else {
				break;
			}
		}
		s.nextLine(); // consume newline character

//		helpful for debugging:
//		System.out.println(q+" books with title '"+t+"', by author '"+a+"'");

		// in order to check if title of book exists lets loop through all the book
		for ( int i = 0; i < title.size(); i++) {
//			helpful for debugging:
//			System.out.println("checking book "+i+" "+title.get(i)+" a = "+author.get(i));
			// if both author and title coincide 
			if ( t.equals(title.get(i)) ) {
//				helpful for debugging:
//				System.out.println("action = "+action);
				// update the quantity
				q = (action.equals("borrow")) ? quantity.get(i) - q : q + quantity.get(i);

				// return the key of that book
				return i;
			}
		}

		// if function has not returned anything yet, book does not exist
		return -1;
	}
	/*
	 * add the variables to the db
	 * since the db is made by 3 internal arrays this function is quite simple.
	 */
	private static void add_new() {
		title.add(t);
		author.add(a);
		quantity.add(q);
	}

	/*
	 * update the quantity of the desired book retrieved by key
	 * since the db is made by 3 internal arrays this function is quite simple.
	 */
	private static void update_quantity() {
//		helpful for debugging:
//		System.out.println("book_key = "+book_key+" and q to add = "+q);
		// check that the quantity is positive
		quantity.set(book_key, q);
	}

	/*
	 * simple function that prints to console in green
	 */
	private static void print_success(String s) {
		final String ANSI_RESET = "\u001B[0m";
		final String ANSI_GREEN = "\u001B[32m";
		System.out.println(ANSI_GREEN + s + ANSI_RESET);
	}

	/*
	 * simple function that prints to console in red
	 */
	private static void print_err(String s) {
		final String ANSI_RESET = "\u001B[0m";
		final String ANSI_RED = "\u001B[31m";
		System.out.println(ANSI_RED + s + ANSI_RESET);
	}
}
