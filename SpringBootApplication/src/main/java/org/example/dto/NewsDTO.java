package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
public class NewsDTO {
    private Long id;
    private String title;
    private String text;
    private Instant date;

    public NewsDTO(Long id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = Instant.now();
    }
}