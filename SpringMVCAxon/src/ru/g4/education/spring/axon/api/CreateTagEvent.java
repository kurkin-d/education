package ru.g4.education.spring.axon.api;

public class CreateTagEvent {
  private final String name;
  
  public String getName() {
    return name;
  }
  
  public CreateTagEvent(String name) {
    super();
    this.name = name;
  }
}
