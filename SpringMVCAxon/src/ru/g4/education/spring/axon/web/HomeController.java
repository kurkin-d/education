package ru.g4.education.spring.axon.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/helloWorld"})
public class HomeController {
  
  @RequestMapping(method = RequestMethod.GET)
  public String getMainPage(Map<String, Object> model) {
    System.out.println("Поймали запрос в контроллер главной формы " + model);
    model.put("name", "axon scada");
    return "home";
  }
}
