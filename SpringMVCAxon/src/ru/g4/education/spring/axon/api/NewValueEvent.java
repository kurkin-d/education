package ru.g4.education.spring.axon.api;

public class NewValueEvent {
  private final String name;
  
  private final String value;
  
  public NewValueEvent(String name, String value) {
    super();
    this.name = name;
    this.value = value;
  }
  
  public String getName() {
    return name;
  }
  
  public String getValue() {
    return value;
  }
}
