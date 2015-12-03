package ru.g4.education.spring.axon;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ru.g4.education.spring.axon.api.BackAlarmCmd;
import ru.g4.education.spring.axon.api.CreateAlarmCmd;
import ru.g4.education.spring.axon.api.RiseAlarmCmd;

@Component
public class AlarmCmdHandler {
  
  @Autowired
  @Qualifier("AlarmRepository")
  private Repository<AlarmAggr> repo;
  
  public AlarmCmdHandler() {
  }
  
  public AlarmCmdHandler(Repository<AlarmAggr> repo) {
    super();
    this.repo = repo;
  }
  
  @CommandHandler
  public void onCreateCmd(CreateAlarmCmd cmd) {
    AlarmAggr aggr = new AlarmAggr(cmd.getAlarmName(), cmd.getTagName());
    repo.add(aggr);
  }
  
  @CommandHandler
  public void onRiseCmd(RiseAlarmCmd cmd) {
    AlarmAggr aggr = repo.load(cmd.getAlarmName());
    if (aggr != null) {
      aggr.riseAlarm("rise");
    }
  }
  
  @CommandHandler
  public void onBackCmd(BackAlarmCmd cmd) {
    AlarmAggr aggr = repo.load(cmd.getAlarmName());
    if (aggr != null) {
      aggr.backAlarm("back");
    }
  }
}
