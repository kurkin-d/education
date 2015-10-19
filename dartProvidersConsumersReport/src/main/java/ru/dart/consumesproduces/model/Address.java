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

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + houseNumber;
	result = prime * result + ((region == null) ? 0 : region.hashCode());
	result = prime * result + ((street == null) ? 0 : street.hashCode());
	result = prime * result + ((town == null) ? 0 : town.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Address other = (Address) obj;
	if (houseNumber != other.houseNumber)
	    return false;
	if (region == null) {
	    if (other.region != null)
		return false;
	} else if (!region.equals(other.region))
	    return false;
	if (street == null) {
	    if (other.street != null)
		return false;
	} else if (!street.equals(other.street))
	    return false;
	if (town == null) {
	    if (other.town != null)
		return false;
	} else if (!town.equals(other.town))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Address [region=" + region + ", town=" + town + ", street="
		+ street + ", houseNumber=" + houseNumber + "]";
    }

}
