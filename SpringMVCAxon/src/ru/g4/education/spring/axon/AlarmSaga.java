package ru.g4.education.spring.axon;

import javax.persistence.Transient;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.saga.annotation.AbstractAnnotatedSaga;
import org.axonframework.saga.annotation.SagaEventHandler;
import org.axonframework.saga.annotation.StartSaga;
import org.springframework.beans.factory.annotation.Autowired;

import ru.g4.education.spring.axon.api.BackAlarmCmd;
import ru.g4.education.spring.axon.api.CreateAlarmEvent;
import ru.g4.education.spring.axon.api.NewValueEvent;
import ru.g4.education.spring.axon.api.RiseAlarmCmd;

public class AlarmSaga extends AbstractAnnotatedSaga {
  
  @Transient
  protected transient CommandBus bus;
  
  private String alarmName;
  
  @Autowired
  public void setCommandBus(CommandBus bus) {
    this.bus = bus;
  }
  
  public CommandBus getCommandBus() {
    return bus;
  }
  
  /**
   * 
   */
  private static final long serialVersionUID = -4399796205844488602L;
  
  @StartSaga
  @SagaEventHandler(associationProperty = "alarmName")
  public void onCreateAlarm(CreateAlarmEvent event) {
    associateWith("name", event.getTagName());
    this.alarmName = event.getAlarmName();
  }
  
  @SagaEventHandler(associationProperty = "name")
  public void onNewTagValue(NewValueEvent event) {
    if (event.getValue().equals("много"))
      bus.dispatch(new GenericCommandMessage<>(new RiseAlarmCmd(alarmName)));
    else
      bus.dispatch(new GenericCommandMessage<>(new BackAlarmCmd(alarmName)));
  }
}
