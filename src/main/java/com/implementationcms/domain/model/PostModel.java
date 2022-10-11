package com.implementationcms.domain.model;

import com.implementationcms.domain.converter.HashMapConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class PostModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Convert(converter = HashMapConverter.class)
    private Map<String, String> attributes;

    private LocalDateTime updatedTime;

    private LocalDateTime createdTime;

    public PostModel(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    public PostModel create() {
        this.createdTime = LocalDateTime.now();
        this.updatedTime = LocalDateTime.now();
        return this;
    }

    public void update(Map<String, String> attributes) {
        this.attributes = attributes;
    }
}
