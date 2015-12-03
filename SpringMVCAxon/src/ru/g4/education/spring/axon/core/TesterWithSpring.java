package ru.g4.education.spring.axon.core;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.g4.education.spring.axon.api.CreateAlarmCmd;
import ru.g4.education.spring.axon.api.CreateTagCmd;
import ru.g4.education.spring.axon.api.NewTagValCmd;

public class TesterWithSpring {
  public static void main(String[] args) {
    ApplicationContext applicationContext =
        new ClassPathXmlApplicationContext("simpleConfig.xml", TesterWithSpring.class);
    CommandGateway cGateway = (CommandGateway) applicationContext.getBean(CommandGateway.class);
    cGateway.send(new CreateTagCmd("SagaTestTag_1"));
    cGateway.send(new CreateAlarmCmd("SagaTestAlarm_1", "SagaTestTag_1"));
    cGateway.send(new NewTagValCmd("SagaTestTag_1", "мало"));
    cGateway.send(new NewTagValCmd("SagaTestTag_1", "много"));
    // cGateway.send(new NewTagValCmd("SagaTestTag_1", "мало"));
    // RepoProxyTest repo = applicationContext.getBean(RepoProxyTest.class);;
    // repo.addProxy(new TagAggr("aaa"));
    ((ConfigurableApplicationContext) applicationContext).close();
  }
}
