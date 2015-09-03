package ru.dart.consumesproduces.model;

public class Offerts {
  
  private Product product;
  
  private long count;
  
  public Offerts(Product product, long count) {
    super();
    this.product = product;
    this.count = count;
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
  
  public Offerts() {
  }
  
  
  
}
