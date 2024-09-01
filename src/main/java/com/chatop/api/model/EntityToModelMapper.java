package com.chatop.api.model;

/**
 * Generic entity to model interface.
 */
public interface EntityToModelMapper<T, S> {
  public S entityToModel(T entity);
}
