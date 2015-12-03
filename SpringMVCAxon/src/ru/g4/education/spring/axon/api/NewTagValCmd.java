package ru.g4.education.spring.axon.api;

public class NewTagValCmd {
  private String name;
  
  private String value;
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setValue(String value) {
    this.value = value;
  }
  
  public NewTagValCmd() {
  }
  
  public NewTagValCmd(String name, String value) {
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
