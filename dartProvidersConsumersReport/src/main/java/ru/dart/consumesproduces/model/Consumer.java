package ru.dart.consumesproduces.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Consumer {

    private String title;

    private Address address;

    private Collection<Needs> needs;

    public Consumer(String title, Address address, Collection<Needs> needs) {
	super();
	this.title = title;
	this.address = address;
	this.needs = needs;
    }

    public Consumer(String title, Address address) {
	super();
	this.title = title;
	this.address = address;
    }

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

    public Collection<Needs> getNeedsByProduct(Product product) {
	List<Needs> result = new ArrayList<Needs>();
	for (Needs need : needs) {
	    if (need.getProduct().equals(product)) {
		result.add(need);
	    }
	}
	return result;
    }

    public Collection<Needs> getNeeds() {
	return needs;
    }

    public void setNeeds(Collection<Needs> offerts) {
	this.needs = offerts;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((address == null) ? 0 : address.hashCode());
	result = prime * result + ((needs == null) ? 0 : needs.hashCode());
	result = prime * result + ((title == null) ? 0 : title.hashCode());
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
	Consumer other = (Consumer) obj;
	if (address == null) {
	    if (other.address != null)
		return false;
	} else if (!address.equals(other.address))
	    return false;
	if (needs == null) {
	    if (other.needs != null)
		return false;
	} else if (!needs.equals(other.needs))
	    return false;
	if (title == null) {
	    if (other.title != null)
		return false;
	} else if (!title.equals(other.title))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Consumer [title=" + title + ", address=" + address
		+ ", offerts=" + needs + "]";
    }

}
