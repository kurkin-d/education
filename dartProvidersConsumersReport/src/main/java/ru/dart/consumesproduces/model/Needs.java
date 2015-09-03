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
  
  
}
