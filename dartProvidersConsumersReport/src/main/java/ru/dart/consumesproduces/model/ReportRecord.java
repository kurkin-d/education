package ru.dart.consumesproduces.model;

public class ReportRecord {

    private String region;
    private Product product;
    private Producer producer;
    private Consumer consumer;

    public ReportRecord() {
    }

    public ReportRecord(String region, Product product, Producer producer,
	    Consumer consumer) {
	super();
	this.region = region;
	this.product = product;
	this.producer = producer;
	this.consumer = consumer;
    }

    public String getRegion() {
	return region;
    }

    public void setRegion(String region) {
	this.region = region;
    }

    public Product getProduct() {
	return product;
    }

    public void setProduct(Product product) {
	this.product = product;
    }

    public Producer getProducer() {
	return producer;
    }

    public void setProducer(Producer producer) {
	this.producer = producer;
    }

    public Consumer getConsumer() {
	return consumer;
    }

    public void setConsumer(Consumer consumer) {
	this.consumer = consumer;
    }

    @Override
    public String toString() {
	return "ReportRecord [region=" + region + ", product=" + product
		+ ", producer=" + producer + ", consumer=" + consumer + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((consumer == null) ? 0 : consumer.hashCode());
	result = prime * result
		+ ((producer == null) ? 0 : producer.hashCode());
	result = prime * result + ((product == null) ? 0 : product.hashCode());
	result = prime * result + ((region == null) ? 0 : region.hashCode());
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
	ReportRecord other = (ReportRecord) obj;
	if (consumer == null) {
	    if (other.consumer != null)
		return false;
	} else if (!consumer.equals(other.consumer))
	    return false;
	if (producer == null) {
	    if (other.producer != null)
		return false;
	} else if (!producer.equals(other.producer))
	    return false;
	if (product == null) {
	    if (other.product != null)
		return false;
	} else if (!product.equals(other.product))
	    return false;
	if (region == null) {
	    if (other.region != null)
		return false;
	} else if (!region.equals(other.region))
	    return false;
	return true;
    }

}
