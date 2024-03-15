package vehicles;

import java.util.UUID;

import vehicles.Utils.*; // why is it not working?

/**
 * class representing Cars with
 * unique id, make, model, year of manufacture (common of each vehicle)
 * number of doors, fuel type (unique of car class)
 */
public class Motor implements Vehicle, MotorVehicle {
	private String vehicleType = this.getClass().getSimpleName();
	private String id;
	private String make;
	private String model;
	private int yearOfManufacture;
	private int nWheels;
	private Type type;
	enum Type { SPORT, CRUISER, OFFROAD }

	// constructor
	public Motor() {
		this.id = UUID.randomUUID().toString();
		this.make = "test";
		this.model = "test";
		this.yearOfManufacture = 1950;
		this.nWheels = 2;
		this.type = Type.SPORT;
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
	public int getNWheels() {
		return this.nWheels;
	}
	public String getType() {
		return this.type.name();
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
	public void setNWheels(int nWheels) {
		this.nWheels = nWheels;
	}
	public void setType(String type) {
		try {
			this.type = Type.valueOf(type);
		} catch (Exception e) {
			Utils.print_err("Sorry, '"+type+"' is not recognised as a valid Motor type (SPORT, CRUISER, OFFROAD), no changes made for type");
		}
	}

	@Override
	public void update() {
		Vehicle.super.update();
		MotorVehicle.super.update();
	}

	// vehicls should be convertible into string format
	public String toString() {
		return "id: "+this.getId()+" - "+this.getVehicleType()+" made by '"+this.getMake()+"' in year '"+this.getYearOfManufacture()+"' with model '"+this.getModel()+"', it has '"+this.getNWheels()+"' wheels and the type is '"+this.getType()+"'";
	}
}
