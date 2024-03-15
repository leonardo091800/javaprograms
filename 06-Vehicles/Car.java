package vehicles;

import java.util.UUID;

import vehicles.Utils.*; // why is it not working?

/**
 * class representing Cars with
 * unique id, make, model, year of manufacture (common of each vehicle)
 * number of doors, fuel type (unique of car class)
 */
public class Car implements Vehicle, CarVehicle {
	private String vehicleType = this.getClass().getSimpleName();
	private String id;
	private String make;
	private String model;
	private int yearOfManufacture;
	private int nDoors;
	private Fuel fuel;
	enum Fuel { PETROL, DIESEL, ELECTRIC }

	// constructor
	public Car() {
		this.id = UUID.randomUUID().toString();
		this.make = "test";
		this.model = "test";
		this.yearOfManufacture = 1950;
		this.nDoors = 4;
		this.fuel = Fuel.ELECTRIC;
	}

	// get methods
	public String getVehicleType() {
		return this.vehicleType;
	}
	public String getId() {
		return this.id;
	}
	public String getMake() {
		return this.make;
	}
	public String getModel() {
		return this.model;
	}
	public int getYearOfManufacture() {
		return this.yearOfManufacture;
	}
	public int getNDoors() {
		return this.nDoors;
	}
	public String getFuel() {
		return this.fuel.name();
	}

	// set methods
	public void setMake(String make) {
		this.make = make;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setYearOfManufacture(int year) {
		this.yearOfManufacture = year;
	}
	public void setNDoors(int nDoors) {
		this.nDoors = nDoors;
	}
	public void setFuel(String fuel) {
		try {
			this.fuel = Fuel.valueOf(fuel);
		} catch (Exception e) {
			Utils.print_err("Sorry, '"+fuel+"' is not recognised as a valid Fuel type (ELECTRIC, DIESEL, PETROL), no changes made for fuel");
		}
	}

	@Override
	public void update() {
		Vehicle.super.update();
		CarVehicle.super.update();
	}

	// vehicls should be convertible into string format
	public String toString() {
		return "id: "+this.getId()+" - "+this.getVehicleType()+" made by '"+this.getMake()+"' in year '"+this.getYearOfManufacture()+"' with model '"+this.getModel()+"', it has '"+this.getNDoors()+"' doors and the fuel is '"+this.getFuel()+"'";
	}
}
