package ru.g4.education.spring.axon.web;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ru.g4.education.spring.axon.ReadModelDAO;
import ru.g4.education.spring.axon.Tag;

@Controller
@RequestMapping({"/taglist"})
public class TagListFormController {
  
  public ReadModelDAO dao;
  
  @Autowired
  public TagListFormController(ReadModelDAO dao) {
    super();
    this.dao = dao;
  }
  
  @RequestMapping(value = "/{tagName}", method = RequestMethod.GET)
  public String getTag(@PathVariable String tagName, Model model) {
    Tag t = dao.getTag(tagName);
    if (t != null) {
      model.addAttribute("tag", t);
    }
    System.out.println("Вынули тег " + t);
    return "tagPage";
  }
  
  
  @RequestMapping(method = RequestMethod.GET)
  public String getTagByRequestParam(@RequestParam String tagName, @RequestParam(required = false,
      defaultValue = "false") Boolean scroll, Model model) {
    Tag t = dao.getTag(tagName);
    if (t != null) {
      model.addAttribute("tag", t);
    }
    System.out.println("Вынули тег " + t + " scroll=" + scroll);
    return "tagPage";
  }
}
