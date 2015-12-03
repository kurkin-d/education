package ru.g4.education.spring.axon.core;

import javax.persistence.EntityManager;

import ru.g4.education.spring.axon.TagAggr;

// @Repository("testSpr")
public class TestDAO {
  
  // @PersistenceContext
  private EntityManager em;
  
  public void addTag(TagAggr tag) {
    em.persist(tag);
  }
}
