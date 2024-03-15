package vehicles;

import java.util.Scanner;

/**
 * interface for Cars with specific attributes: [nDoors = number of doors; fuel = type of fuel]
 * and their relative get methods, set methods and update method
 */	
interface CarVehicle {
	Scanner s = Utils.getScanner();

	// getter methods
	int getNDoors();
	String getFuel();

	// setter methods
	void setNDoors(int nDoors);
	void setFuel(String fuel);

	/**
	 * ask and update custom attributes
	 */
	default void update() {
		String tmp_s;
		int tmp_i;

		System.out.print("Insert the number of doors (0 for the default value): ");
		tmp_i = vehicles.Utils.getValidInt();
		if(tmp_i != 0) setNDoors(tmp_i);
		System.out.print("Insert the fuel type (leave empty for the default value): ");
		tmp_s = s.nextLine().toUpperCase();
		if(tmp_s != "") setFuel(tmp_s);
	}
}
