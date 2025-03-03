package org.example.services;

import org.springframework.http.ResponseEntity;

import java.util.Collection;

public interface CRUDService<T> {
    T getById(long id);
    Collection<T> getAll();
    ResponseEntity create(T item);
    ResponseEntity update(long id, T item);
    ResponseEntity deleteById(long id);
}
