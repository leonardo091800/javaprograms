package vehicles;

import java.util.UUID;

import vehicles.Utils.*; // why is it not working?

/**
 * class representing Trucks with
 * unique id, make, model, year of manufacture (common of each vehicle)
 * cargo capacity in tons, transmission type manual or automatic (unique of car class)
 */
public class Truck implements Vehicle, TruckVehicle {
	private String vehicleType = this.getClass().getSimpleName();
	private String id;
	private String make;
	private String model;
	private int yearOfManufacture;
	private int capacity;	// in tons
	private Transmission transmission;
	enum Transmission { MANUAL, AUTOMATIC }

	// constructor
	public Truck() {
		this.id = UUID.randomUUID().toString();
		this.make = "test";
		this.model = "test";
		this.yearOfManufacture = 1950;
		this.capacity = 30;
		this.transmission = Transmission.AUTOMATIC;
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
	public int getCapacity() {
		return this.capacity;
	}
	public String getTransmission() {
		return this.transmission.name();
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
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public void setTransmission(String transmission) {
		try {
			this.transmission = Transmission.valueOf(transmission);
		} catch (Exception e) {
			Utils.print_err("Sorry, '"+transmission+"' is not recognised as a valid transmission type (AUTOMATIC, MANUAL), no changes made for transmission");
		}
	}

	@Override
	public void update() {
		Vehicle.super.update();
		TruckVehicle.super.update();
	}

	// vehicls should be convertible into string format
	public String toString() {
		return "id: "+this.getId()+" - "+this.getVehicleType()+" made by '"+this.getMake()+"' in year '"+this.getYearOfManufacture()+"' with model '"+this.getModel()+"', it has '"+this.getCapacity()+"' tons of capacity and the tranmission is '"+this.getTransmission()+"'";
	}
}
