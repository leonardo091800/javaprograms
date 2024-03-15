package vehicles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import vehicles.Utils.*;

/**
 * Class that manages the vehicles, it is possible to add, view, update, delete vehicles
 * the view command display the details of each vehicle
 * the add command prompt the user with a sub menu asking him to choose the type of vehicle
 *   it then creates and add to the internal List the specified vehicle, 
 *   it also calls the "update" method of the newly created object to let the user update its initial values
 *   (this flow was chosen in order to increase code re-usability and easiness of maintainance)
 * the update command asks for the id of the vehicle, it then calls the update method of the object.
 * the delete command asks for the id of the vehicle, it removes the vehicle from the internal program list
 */
public class VehicleManager {
	static Scanner s = Utils.getScanner();

	// variables used accross the different functions
	static String action = "";

	// list of students
	private static List<Vehicle> vehicles = new ArrayList<Vehicle>();

	/**
	 * get the action through getValidAction() 
	 * and call different functions based on action
	 */
	public static void main(String[] args) {
		mainLoop:
		while (true) {
			// get the action
			action = getValidAction("mainAction");

			// depending on the action call desired methods
			switch (action) {
				case "view":
					getVehicles();
					break;
				case "add":
					// Declaration of the new vehicle
					Vehicle newVehicle;
					boolean isVehicleCreated = true;	// tmp parameter to check correct creation of vehicle

					// loop to get a valid type of vehicle
					addVehicleLoop: while(true) {
						action = getValidAction("addVehicle");

						// initialization of the new vehicle based on the type of vehicle
						switch (action) {
							case "car":
								newVehicle = new Car();
								break addVehicleLoop;
							case "motorcycle":
								newVehicle = new Motor();
								break addVehicleLoop;
							case "truck":
								newVehicle = new Truck();
								break addVehicleLoop;
							case "back":
								newVehicle = new Car(); // this is just to get rid of the error "variable might not been initialised", it is not going to be used anyway
								isVehicleCreated = false; // if user goes back new vehicle does not need to be added to list
								break addVehicleLoop;
							default:
								Utils.print_err("Something is not right, please try again");
								break;
						}
					}
					if(isVehicleCreated) {
						// add the vehicle to the list right after the last element of same vehicleType
						vehicles.add(getIndexOfLastVehicleType(newVehicle.getVehicleType()), newVehicle);
						// call the update method to let the user initialize the newly created vehicle
						newVehicle.update();
					}
					break;
				case "delete":
					v_rm();
					break;
				case "update":
					v_update();
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


	/* actual methods to manage Vehicles */

	/**
	 * print all the vehicles currently created
	 */
	static void getVehicles() {
		// check if there are vehicles...
		if(vehicles.size() > 0) {
			// if so call the toString method
			for (int i = 0; i<vehicles.size(); i++) {
				System.out.println(vehicles.get(i));
			}
		}
		else {
			// if not print error message
			Utils.print_err("no vehicles yet, please add them");
		}
	}

	/**
	 * asks for the vehicle (calling getObjectFromID()) and calls vehicle.update()
	 */
	static void v_update() {
		// get vehicle
		Vehicle v = getObjectFromID();

		// call the update function of the vehicle if not null
		if(v != null) {
			v.update();
		}
	}

	/**
	 * asks for the vehicle (calling getObjectFromID()) and calls the v_rm(vehicle)
	 */
	static void v_rm() {
		// get vehicle
		Vehicle v = getObjectFromID();

		// if vehicle exist, call the function with the vehicle as parameter
		if(v != null) v_rm(v);
	}
	/**
	 * remove vehicle from List
	 * @param Vehicle to be removed
	 */
	static void v_rm(Vehicle v) {
		vehicles.remove(v);
		Utils.print_err("vechicle "+v+" removed");
	}


	/* Validation and Input Management */

	/**
	 * create Map of valid actions, based on action given as parameter
	 * ask for further action (different action for each possible parameter)
	 * as an infinite loop until the action is considered valid (must match the key of the Map created previously)
	 * @param a_type Action Type: either mainAction or specificAction 
	 * @return validated action as a String
	 */
	private static String getValidAction(String a_type) {
		Map<String, String> actions = new HashMap<String, String>();

		switch (a_type) {
			case "mainAction":
				actions.put("view", "View all the vehicles");
				actions.put("add", "Add a vehicle");
				actions.put("update", "Update the vehicle details");
				actions.put("delete", "Delete a vehicle");
				actions.put("exit", "exit");
				break;
			case "addVehicle":
				actions.put("car", "Add a car");
				actions.put("truck", "Add a truck");
				actions.put("motorcycle", "Add a motorcycle");
				actions.put("back", "Go back");
				break;
			default:
				return "";
		}

		// tmp values
		boolean action_is_accepted = false;
		String action_tmp = "";

		do {
			// program will print the possible actions
			System.out.println("\nWrite one of the following commands in lowercase: ");
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
				Utils.print_err("Your input is not valid!");
		} while (!action_is_accepted);

		// if program arrives here the action is valid so we can save it
		return action_tmp;
	}

	/**
	 * ask the user ONCE the ID
	 * if ID in array, return object, else return null and print error
	 * @return vehicle object OR null
	 */
	private static Vehicle getObjectFromID() {
		// get the ID
		System.out.print("\nPlease insert the ID of the vehicle: ");
		String id_tmp = s.nextLine();

		// check the ID through the List
		for ( int i = 0; i < vehicles.size(); i++) {
			if(vehicles.get(i).getId().equals(id_tmp)) {
				// if ID match return the object
				return vehicles.get(i);
			}
		}

		// if for loop ended without finding ID...
		Utils.print_err("Vehicle with ID="+id_tmp+" not found");
		return null;
	}

	/**
	 * get the index of the last vehicle of a specified type,
	 * it is used for sorting the vehicles, it will check in order:
	 * car, motorcycles and lastly truck
	 */
	private static int getIndexOfLastVehicleType(String VehicleType) {
		if(vehicles.size() < 1 ) return 0;

		switch (VehicleType) {
			case "Car":
				// the for loop will end when the vehicle in the list is not a car anymore 
				for(int i = 0; i < vehicles.size(); i++) {
					if(!vehicles.get(i).getVehicleType().equals("Car")) return i;
				}
				// if list only made by cars let's put it at the end
				return vehicles.size();
			case "Motor":
				// the for loop will start from the last position of the car end when the vehicle in the list is not a motor anymore 
				for(int i = getIndexOfLastVehicleType("Car"); i < vehicles.size(); i++) {
					if(!vehicles.get(i).getVehicleType().equals("Motor")) return i;
				}
				// if there are no trucks in the list let's put it at the end
				return vehicles.size();
			default:
				// if it is a truck just put it at the end of the list
				return vehicles.size();
		}
	}
}
