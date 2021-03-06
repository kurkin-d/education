package ru.dart.consumesproduces.model;

public class Needs {

    private Product product;

    private long count;

    public Needs() {
    }

    public Product getProduct() {
	return product;
    }

    public void setProduct(Product product) {
	this.product = product;
    }

    public long getCount() {
	return count;
    }

    public void setCount(long count) {
	this.count = count;
    }

    public Needs(Product product, long count) {
	super();
	this.product = product;
	this.count = count;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (int) (count ^ (count >>> 32));
	result = prime * result + ((product == null) ? 0 : product.hashCode());
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
	Needs other = (Needs) obj;
	if (count != other.count)
	    return false;
	if (product == null) {
	    if (other.product != null)
		return false;
	} else if (!product.equals(other.product))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Needs [product=" + product + ", count=" + count + "]";
    }

}
