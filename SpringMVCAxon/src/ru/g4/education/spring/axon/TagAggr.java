package ru.g4.education.spring.axon;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import ru.g4.education.spring.axon.api.CreateTagEvent;
import ru.g4.education.spring.axon.api.DeleteTagEvent;
import ru.g4.education.spring.axon.api.NewValueEvent;

@Entity
public class TagAggr extends AbstractAnnotatedAggregateRoot<String> {
  
  @AggregateIdentifier
  @Id
  private String tagName;
  
  private String value;
  
  public void setValue(String value) {
    apply(new NewValueEvent(tagName, value));
  }
  
  public TagAggr(String tagName) {
    apply(new CreateTagEvent(tagName));
  }
  
  public void forget() {
    apply(new DeleteTagEvent(tagName));
  }
  
  public TagAggr() {
    // TODO Auto-generated constructor stub
  }
  
  @EventHandler
  public void onCreateEvent(CreateTagEvent event) {
    this.tagName = event.getName();
  }
  
  @EventHandler
  public void onUpdateValue(NewValueEvent event) {
    this.value = event.getValue();
  }
  
  @EventHandler
  public void onDeleteValue(DeleteTagEvent event) {
    markDeleted();
  }
}
