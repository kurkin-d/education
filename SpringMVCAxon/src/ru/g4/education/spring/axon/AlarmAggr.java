package ru.g4.education.spring.axon;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import ru.g4.education.spring.axon.api.BackAlarmEvent;
import ru.g4.education.spring.axon.api.CreateAlarmEvent;
import ru.g4.education.spring.axon.api.RiseAlarmEvent;

@Entity
public class AlarmAggr extends AbstractAnnotatedAggregateRoot<String> {
  
  /**
   * 
   */
  private static final long serialVersionUID = -8161926129155965725L;
  
  @Id
  @AggregateIdentifier
  private String alarmName;
  
  private String tagName;
  
  protected AlarmAggr() {
    
  }
  
  public String getAlarmName() {
    return alarmName;
  }
  
  public AlarmAggr(String alarmName, String tagName) {
    apply(new CreateAlarmEvent(alarmName, tagName));
  }
  
  @EventHandler
  public void onAlarmCreate(CreateAlarmEvent event) {
    this.alarmName = event.getAlarmName();
    this.tagName = event.getTagName();
  }
  
  public String getTagName() {
    return tagName;
  }
  
  
  public String getState() {
    return state;
  }
  
  public void riseAlarm(String state) {
    apply(new RiseAlarmEvent(state));
  }
  
  @EventHandler
  public void onRiseEvent(RiseAlarmEvent event) {
    this.state = "rised";
  }
  
  @EventHandler
  public void onBackEvent(BackAlarmEvent event) {
    this.state = "back to normal";
  }
  
  public void backAlarm(String state) {
    apply(new BackAlarmEvent(state));
  }
  
  private String state;
  
  
}
