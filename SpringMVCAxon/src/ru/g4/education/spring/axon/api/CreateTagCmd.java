package ru.g4.education.spring.axon.api;

public class CreateTagCmd {
  private String name;
  
  public CreateTagCmd() {
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public CreateTagCmd(String name) {
    super();
    this.name = name;
  }
  
}
