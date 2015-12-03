package ru.g4.education.spring.axon;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

import ru.g4.education.spring.axon.api.CreateTagEvent;
import ru.g4.education.spring.axon.api.NewValueEvent;

public class TagEventListener {
  
  private ReadModelDAO dao;
  
  public ReadModelDAO getDao() {
    return dao;
  }
  
  @Autowired
  public void setDao(ReadModelDAO dao) {
    this.dao = dao;
  }
  
  @EventHandler
  public void onCreate(CreateTagEvent event) {
    System.out.println("Услышал новое событие. Новый тег" + event.getName());
    Tag t = new Tag();
    t.setTagName(event.getName());
    dao.add(t);
  }
  
  @EventHandler
  public void onNewValue(NewValueEvent event) {
    System.out.println("Услышал новое событие. Новое значение " + event.getName() + " " + event.getValue());
    Tag t = new Tag();
    t.setTagName(event.getName());
    t.setTagValue(event.getValue());
    dao.update(t);
  }
}
