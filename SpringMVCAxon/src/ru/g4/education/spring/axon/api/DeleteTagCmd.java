package ru.g4.education.spring.axon.api;

public class DeleteTagCmd {
  private final String name;
  
  public String getName() {
    return name;
  }
  
  public DeleteTagCmd(String name) {
    super();
    this.name = name;
  }
}
