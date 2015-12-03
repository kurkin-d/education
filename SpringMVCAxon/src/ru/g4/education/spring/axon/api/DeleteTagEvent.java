package ru.g4.education.spring.axon.api;

public class DeleteTagEvent {
  private final String name;
  
  public String getName() {
    return name;
  }
  
  public DeleteTagEvent(String name) {
    super();
    this.name = name;
  }
}
