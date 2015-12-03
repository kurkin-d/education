package ru.g4.education.spring.axon.web;

import java.security.Principal;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.g4.education.spring.axon.ReadModelDAO;
import ru.g4.education.spring.axon.Tag;
import ru.g4.education.spring.axon.api.CreateTagCmd;
import ru.g4.education.spring.axon.api.NewTagValCmd;

@Controller
@RequestMapping("/tags")
public class TagRestController {
  
  private ReadModelDAO dao;
  
  private CommandGateway cGateway;
  
  @Autowired
  public TagRestController(ReadModelDAO dao, CommandGateway cGateway) {
    super();
    this.dao = dao;
    this.cGateway = cGateway;
  }
  
  @RequestMapping(value = "/{tagName}", method = RequestMethod.POST, consumes = "application/json")
  @ResponseBody
  public ResponseEntity<String> addTagValue(@PathVariable String tagName, @RequestBody NewTagValCmd cmd) {
    try {
      cGateway.sendAndWait(cmd);
      System.out.println("пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ");
      return new ResponseEntity<String>(HttpStatus.OK);
    } catch (Exception ex) {
      ex.printStackTrace();
      System.out.println("пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ 500 " + ex.getMessage());
      return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
  @ResponseBody
  public ResponseEntity<String> addTag(@RequestBody CreateTagCmd cmd) {
    System.out.println("пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ " + cmd);
    try {
      cGateway.sendAndWait(cmd);
      System.out.println("пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ");
      return new ResponseEntity<String>(HttpStatus.OK);
    } catch (Exception ex) {
      ex.printStackTrace();
      System.out.println("пїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ 500 " + ex.getMessage());
      return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @RequestMapping(value = "/{tagName}", produces = "application/json", method = RequestMethod.GET)
  public @ResponseBody Tag getTag(@PathVariable String tagName, Principal user) {
    
    System.out.println("запрос тега " + tagName + " " + user.getName());
    return dao.getTag(tagName);
  }
}
