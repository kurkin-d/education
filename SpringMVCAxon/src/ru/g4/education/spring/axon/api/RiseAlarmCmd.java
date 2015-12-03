package ru.g4.education.spring.axon.api;

public class RiseAlarmCmd {
  private final String alarmName;
  
  public String getAlarmName() {
    return alarmName;
  }
  
  public RiseAlarmCmd(String alarmName) {
    super();
    this.alarmName = alarmName;
  }
  
}
