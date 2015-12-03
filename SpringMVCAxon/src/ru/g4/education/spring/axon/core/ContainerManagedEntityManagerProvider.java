package ru.g4.education.spring.axon.core;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.axonframework.common.jpa.EntityManagerProvider;

public class ContainerManagedEntityManagerProvider implements EntityManagerProvider {
  
  private EntityManager entityManager;
  
  @Override
  public EntityManager getEntityManager() {
    return entityManager;
  }
  
  /**
   * Sets the container managed entityManager to use. Is generally injected by the application container.
   *
   * @param entityManager the entityManager to return upon request.
   */
  @PersistenceContext(unitName = "axonSpringAggr")
  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;
  }
}
