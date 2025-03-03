package org.example.controllers;

import org.example.dto.NewsDTO;
import org.example.services.CRUDService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final CRUDService<NewsDTO> newsService;

    public NewsController(CRUDService<NewsDTO> newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/{id}")
    public NewsDTO getNewsById(@PathVariable Long id) {
        return newsService.getById(id);
    }

    @GetMapping
    public Collection<NewsDTO> getAllNews() {
        return newsService.getAll();
    }

    @PostMapping
    public ResponseEntity createNews(@RequestBody NewsDTO news) {
        return newsService.create(news);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateNews(@PathVariable Long id, @RequestBody NewsDTO news) {
        return newsService.update(id, news);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteNews(@PathVariable Long id) {
        return newsService.deleteById(id);
    }
}
