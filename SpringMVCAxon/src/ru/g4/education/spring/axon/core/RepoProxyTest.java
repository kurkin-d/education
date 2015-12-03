package ru.g4.education.spring.axon.core;

import ru.g4.education.spring.axon.TagAggr;


public class RepoProxyTest {
  
  public TestDAO dao;
  
  public RepoProxyTest() {
  }
  
  
  public void addProxy(TagAggr aggr) {
    dao.addTag(aggr);
  }
}
