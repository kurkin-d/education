package ru.dart.consumesproduces.model;

public class Address {

    private String region;

    private String town;

    private String street;

    private int houseNumber;

    public Address(String region, String town, String street, int houseNumber) {
	super();
	this.region = region;
	this.town = town;
	this.street = street;
	this.houseNumber = houseNumber;
    }

    public String getRegion() {
	return region;
    }

    public String getTown() {
	return town;
    }

    public String getStreet() {
	return street;
    }

    public int getHouseNumber() {
	return houseNumber;
    }

}
