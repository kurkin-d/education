package ru.g4.education.spring.axon.api;

public class RiseAlarmEvent {
  private final String alarmName;
  
  public String getAlarmName() {
    return alarmName;
  }
  
  public RiseAlarmEvent(String alarmName) {
    super();
    this.alarmName = alarmName;
  }
}
