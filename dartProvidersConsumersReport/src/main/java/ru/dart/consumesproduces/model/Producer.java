package ru.dart.consumesproduces.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Producer {

    private Address address;

    private String title;

    private Collection<Offerts> offerts;

    public Producer() {
    }

    public Collection<Offerts> getOffertsByProduct(Product product) {
	List<Offerts> result = new ArrayList<Offerts>();
	for (Offerts offert : offerts) {
	    if (offert.getProduct().equals(product)) {
		result.add(offert);
	    }
	}
	return result;
    }

    public Collection<Offerts> getOfferts() {
	return offerts;
    }

    public Address getAddress() {
	return address;
    }

    public void setAddress(Address address) {
	this.address = address;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public void setOfferts(Collection<Offerts> offerts) {
	this.offerts = offerts;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((address == null) ? 0 : address.hashCode());
	result = prime * result + ((offerts == null) ? 0 : offerts.hashCode());
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
	Producer other = (Producer) obj;
	if (address == null) {
	    if (other.address != null)
		return false;
	} else if (!address.equals(other.address))
	    return false;
	if (offerts == null) {
	    if (other.offerts != null)
		return false;
	} else if (!offerts.equals(other.offerts))
	    return false;
	if (title == null) {
	    if (other.title != null)
		return false;
	} else if (!title.equals(other.title))
	    return false;
	return true;
    }

}
