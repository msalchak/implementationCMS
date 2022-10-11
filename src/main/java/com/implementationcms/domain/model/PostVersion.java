package com.implementationcms.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.implementationcms.domain.command.PostStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@NoArgsConstructor
public class PostVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Post post;

    private Long version;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private PostStatus status;

    @ElementCollection
    @CollectionTable(
            name = "post_attribute",
            joinColumns = @JoinColumn(name = "version_id")
    )
    @Cascade(CascadeType.ALL)
    private List<PostAttribute> postAttributeList;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    public PostVersion(Post post, List<PostAttribute> PostAttributeList) {
        this.post = post;
        this.postAttributeList = postAttributeList;
    }

    public PostVersion create() {
        this.version = 1L;
        this.status = PostStatus.DRAFT;
        this.createdTime = LocalDateTime.now();
        this.updatedTime = LocalDateTime.now();
        this.updateName();
        return this;
    }

    public void update(List<PostAttribute> postAttributeList) {
        this.version++;
        this.postAttributeList = postAttributeList;
        this.updateName();
        this.updatedTime = LocalDateTime.now();
    }

    private void updateName() {
        this.postAttributeList.stream()
                .filter(postAttribute -> "name".equals(postAttribute.getKey()))
                .findAny()
                .ifPresent(postAttribute -> this.name = postAttribute.getValue());
    }
}
