package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "news")
@Getter
@Setter
@RequiredArgsConstructor
public class News {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @CreationTimestamp
    @Column(name = "date")
    private Instant date;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
