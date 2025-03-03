package org.example.services;

import lombok.Getter;
import org.example.dto.NewsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Getter
public class NewsCRUDService implements CRUDService<NewsDTO>{

    private final ConcurrentHashMap<Long, NewsDTO> storage = new ConcurrentHashMap<>();

    @Override
    public NewsDTO getById(long id) {
        System.out.println("Get by ID: " + id);
        if(!storage.containsKey(id)) {
            throw new RuntimeException("Новость с ID " + id + " не найдена.");
        }
        return storage.get(id);
    }

    @Override
    public Collection<NewsDTO> getAll() {
        System.out.println("Get all");
        return storage.values();
    }

    @Override
    public ResponseEntity create(NewsDTO item) {
        System.out.println("Create");

        long maxId = storage.keySet().stream().mapToLong(id -> id).max().orElse(0);
        long nextId = maxId + 1;
        item.setId(nextId);
        storage.put(nextId, item);

        return new ResponseEntity(item, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity update(long id, NewsDTO item) {
        if(!storage.containsKey(id)) {
            return returnInvalidIdResponse(id);
        }
        item.setId(id);
        storage.put(id, item);
        return new ResponseEntity(item, HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteById(long id) {
        if(!storage.containsKey(id)) {
            return returnInvalidIdResponse(id);
        }
        storage.remove(id);
        System.out.println("Delete " + id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity returnInvalidIdResponse(long id) {
        String message = "Новость с ID " + id + " не найдена.";
        HashMap<String, String> response = new HashMap<>();
        response.put("message", message);
        System.out.println(message);
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }
}
