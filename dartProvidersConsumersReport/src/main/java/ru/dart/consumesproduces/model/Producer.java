package ru.dart.consumesproduces.model;

import java.util.Collection;
import java.util.List;

public class Producer {
  
  private Address address;
  
  private String title;
  
  private List<Needs> needs;
  
  public Producer() {
  }
  
  public Collection<Needs> getNeedsBy(Product product) {
    return null;
  }
  
  public List<Needs> getNeeds() {
    return needs;
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
  
  public void setNeeds(List<Needs> needs) {
    this.needs = needs;
  }
  
  
  
}
