package ru.g4.education.spring.axon.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping({"/client"})
public class SocketClientContainer {
  
  public SocketClientContainer() {
    // TODO Auto-generated constructor stub
  }
  
  @RequestMapping(method = RequestMethod.GET)
  public String getClientPage(Map<String, Object> model) {
    System.out.println("поймали запрос клиентской страницы");
    return "client";
  }
  
}
