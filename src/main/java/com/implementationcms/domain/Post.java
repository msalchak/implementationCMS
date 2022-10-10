package com.implementationcms.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column
    private String title;

    @Lob
    @Column
    @NotEmpty
    private String content;

    @Column
    private Boolean postStatus;

    @Column
    private LocalDateTime createdTime;

    @Column
    private LocalDateTime updatedTime;


}
