package ru.g4.education.spring.axon.api;

public class CreateAlarmEvent {
  private String alarmName;
  
  private String tagName;
  
  public CreateAlarmEvent(String alarmName, String tagName) {
    super();
    this.alarmName = alarmName;
    this.tagName = tagName;
  }
  
  public String getAlarmName() {
    return alarmName;
  }
  
  public void setAlarmName(String alarmName) {
    this.alarmName = alarmName;
  }
  
  public String getTagName() {
    return tagName;
  }
  
  public void setTagName(String tagName) {
    this.tagName = tagName;
  }
}
