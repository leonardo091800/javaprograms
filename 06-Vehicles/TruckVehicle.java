package vehicles;

import java.util.Scanner;

/**
 * interface for Cars with specific attributes: [nDoors = number of doors; fuel = type of fuel]
 * and their relative get methods, set methods and update method
 */	
interface TruckVehicle {
	Scanner s = Utils.getScanner();

	// getter methods
	int getCapacity();
	String getTransmission();

	// setter methods
	void setCapacity(int capacity);
	void setTransmission(String transmission);

	/**
	 * ask and update custom attributes
	 */
	default void update() {
		String tmp_s;
		int tmp_i;

		System.out.print("Insert the capacity of the truck in tons (0 for the default value): ");
		tmp_i = vehicles.Utils.getValidInt();
		if(tmp_i != 0) setCapacity(tmp_i);
		System.out.print("Insert the transmission type (leave empty for the default value): ");
		tmp_s = s.nextLine().toUpperCase();
		if(tmp_s != "") setTransmission(tmp_s);
	}
}
