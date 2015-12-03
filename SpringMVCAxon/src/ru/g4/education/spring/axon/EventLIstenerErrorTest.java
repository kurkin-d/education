package ru.g4.education.spring.axon;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.eventhandling.annotation.EventHandler;

import ru.g4.education.spring.axon.api.CreateTagCmd;
import ru.g4.education.spring.axon.api.CreateTagEvent;

public class EventLIstenerErrorTest {
  
  private int counter = 0;
  
  private CommandBus commandBus;
  
  public CommandBus getCommandBus() {
    return commandBus;
  }
  
  public void setCommandBus(CommandBus commandBus) {
    this.commandBus = commandBus;
  }
  
  @EventHandler
  public void onCreate(CreateTagEvent event) {
    counter++;
    if (counter % 4 == 0) {
      throw new RuntimeException("из вредности метнули на событие " + event.getName());
    }
    System.out.println("Услышал новое событие. Новый тег" + event.getName());
    if (!event.getName().endsWith("_Inner")) {
      System.out.println("жахаем внутреннюю");
      commandBus.dispatch(new GenericCommandMessage<>(new CreateTagCmd(event.getName() + "_Inner")));
    }
  }
}
