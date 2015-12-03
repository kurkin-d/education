package ru.g4.education.spring.axon.api;

public class BackAlarmCmd {
  private final String alarmName;
  
  public String getAlarmName() {
    return alarmName;
  }
  
  public BackAlarmCmd(String alarmName) {
    super();
    this.alarmName = alarmName;
  }
}
