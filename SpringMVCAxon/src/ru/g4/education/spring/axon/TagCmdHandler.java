package ru.g4.education.spring.axon;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ru.g4.education.spring.axon.api.CreateTagCmd;
import ru.g4.education.spring.axon.api.DeleteTagCmd;
import ru.g4.education.spring.axon.api.NewTagValCmd;

@Component
public class TagCmdHandler {
  
  @Autowired
  @Qualifier("TagRepository")
  private Repository<TagAggr> repo;
  
  public TagCmdHandler() {
  }
  
  public TagCmdHandler(Repository<TagAggr> repo) {
    this.repo = repo;
  }
  
  @CommandHandler
  public void onCreateCmd(CreateTagCmd cmd) {
    TagAggr aggr = new TagAggr(cmd.getName());
    repo.add(aggr);
  }
  
  @CommandHandler
  public void onNewTagCmd(NewTagValCmd cmd) {
    TagAggr aggr = repo.load(cmd.getName());
    if (aggr != null) {
      aggr.setValue(cmd.getValue());
    }
  }
  
  @CommandHandler
  public void onDeleteTag(DeleteTagCmd cmd) {
    TagAggr aggr = repo.load(cmd.getName());
    if (aggr != null) {
      aggr.forget();
    }
  }
  
}
