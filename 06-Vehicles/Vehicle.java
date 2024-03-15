package vehicles;

import java.util.Scanner;

/**
 * interface for vehicles with custom attirbutes: [id = unique identifier of vehicle; model = model of vehicle; yearOfManufacture = uear of manufacture}
 * and their relative get methods, set methods, and update method
 */
interface Vehicle {
	Scanner s = Utils.getScanner();

	// get methods
	String getVehicleType();	
	String getId();	
	String getModel();	
	int getYearOfManufacture();

	// set methods
	void setMake(String m);	
	void setModel(String m);	
	void setYearOfManufacture(int y);

	// shortcut to set the year
	default void setYear(int y) {
		setYearOfManufacture(y);
	}

	/**
	 * for each private attribute ask if want to change it or not, in case change it 
	 */
	default public void update() {
		String tmp_s;
		int tmp_i;

		System.out.print("Insert the make (leave empty for default value): ");
		tmp_s = s.nextLine();
		if(tmp_s != "") setMake(tmp_s);
		System.out.print("Insert the model (leave empty for default value): ");
		tmp_s = s.nextLine();
		if(tmp_s != "") setModel(tmp_s);
		System.out.print("Insert the Year of Manufacture (0 for default value): ");
		tmp_i = vehicles.Utils.getValidInt();
		if(tmp_i != 0) setYear(tmp_i);
	}
}
