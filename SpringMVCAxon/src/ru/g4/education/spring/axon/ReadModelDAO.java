package ru.g4.education.spring.axon;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional("readModelTsManager")
public class ReadModelDAO {
  
  @PersistenceContext(unitName = "axonSpringReadModel")
  private EntityManager em;
  
  public void add(Tag tag) {
    em.persist(tag);
  }
  
  public void update(Tag tag) {
    em.merge(tag);
  }
  
  public Tag getTag(String tagName) {
    return em.find(Tag.class, tagName);
  }
}
