package ru.dart.consumesproduces.model;

import java.util.List;

public class Consumer {

    private String title;

    private Address address;

    private List<Offerts> offerts;

    public Consumer() {
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public Address getAddress() {
	return address;
    }

    public void setAddress(Address address) {
	this.address = address;
    }

    public List<Offerts> getOfferts() {
	return offerts;
    }

    public void setOfferts(List<Offerts> offerts) {
	this.offerts = offerts;
    }

}
