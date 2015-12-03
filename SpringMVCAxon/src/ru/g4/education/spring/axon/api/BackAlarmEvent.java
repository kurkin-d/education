package ru.g4.education.spring.axon.api;

public class BackAlarmEvent {
  private final String alarmName;
  
  public String getAlarmName() {
    return alarmName;
  }
  
  public BackAlarmEvent(String alarmName) {
    super();
    this.alarmName = alarmName;
  }
}
