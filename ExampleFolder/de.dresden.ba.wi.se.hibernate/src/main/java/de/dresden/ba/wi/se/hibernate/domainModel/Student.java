package de.dresden.ba.wi.se.hibernate.domainModel;

public class Student {
	// Private fields
	private int _id;
	private String _firstName;
	private String _lastName;
	private String _registrationNumber;
	
	// Getters & Setters (REQUIRED for private fields!)
	public int getId() {
		return _id;
	}

	public void setId(int id) {
		this._id = id;
	}

	public String getFirstName() {
		return _firstName;
	}

	public void setFirstName(String firstName) {
		this._firstName = firstName;
	}

	public String getLastName() {
		return _lastName;
	}

	public void setLastName(String lastName) {
		this._lastName = lastName;
	}

	public String getRegistrationNumber() {
		return _registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this._registrationNumber = registrationNumber;
	}

	// Default Constructor and further Constructors
	public Student() { }
	
	public Student(String firstName, String lastName, String registrationNumber) {
		this._firstName = firstName;
		this._lastName = lastName;
		this._registrationNumber = registrationNumber;
	}
	
	// Methods
	public String getFullInfo() {
		String fullInfo = this._lastName + ", " + this._firstName + " (" + this._registrationNumber + ") -- OID: " + this._id;
		
		return fullInfo;
	}
}
