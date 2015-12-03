package ru.g4.education.spring.axon.core;

import java.io.File;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AnnotationCommandHandlerAdapter;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventhandling.annotation.AnnotationEventListenerAdapter;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.fs.FileSystemEventStore;
import org.axonframework.eventstore.fs.SimpleEventFileResolver;

import ru.g4.education.spring.axon.TagAggr;
import ru.g4.education.spring.axon.TagCmdHandler;
import ru.g4.education.spring.axon.TagEventListener;
import ru.g4.education.spring.axon.api.CreateTagCmd;

public class Tester {
  
  public static void main(String[] args) {
    CommandBus cb = new SimpleCommandBus();
    CommandGateway cGateway = new DefaultCommandGateway(cb);
    
    EventStore evStore =
        new FileSystemEventStore(new SimpleEventFileResolver(new File("." + File.separator + "repo" + File.separator
            + "events")));
    EventBus evBus = new SimpleEventBus();
    EventSourcingRepository<TagAggr> r = new EventSourcingRepository<TagAggr>(TagAggr.class, evStore);
    r.setEventBus(evBus);
    TagEventListener tagListener = new TagEventListener();
    evBus.subscribe(AnnotationEventListenerAdapter.subscribe(tagListener, evBus));
    TagCmdHandler cmdHandler = new TagCmdHandler(r);
    AnnotationCommandHandlerAdapter.subscribe(cmdHandler, cb);
    cGateway.send(new CreateTagCmd("testTag2"));
  }
  
}
