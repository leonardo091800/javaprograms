package vehicles;

import java.util.Scanner;

/**
 * interface for Cars with specific attributes: [nDoors = number of doors; fuel = type of fuel]
 * and their relative get methods, set methods and update method
 */	
interface MotorVehicle {
	Scanner s = Utils.getScanner();

	// getter methods
	int getNWheels();
	String getType();

	// setter methods
	void setNWheels(int nWheels);
	void setType(String type);

	/**
	 * ask and update custom attributes
	 */
	default void update() {
		String tmp_s;
		int tmp_i;

		System.out.print("Insert the number of wheels (0 for the default value): ");
		tmp_i = vehicles.Utils.getValidInt();
		if(tmp_i != 0) setNWheels(tmp_i);
		System.out.print("Insert the type of motorbike (leave empty for the default value): ");
		tmp_s = s.nextLine().toUpperCase();
		if(tmp_s != "") setType(tmp_s);
	}
}
