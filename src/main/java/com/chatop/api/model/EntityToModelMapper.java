package com.chatop.api.model;

public interface EntityToModelMapper<T,S> {
    public S entityToModel(T entity);
}
