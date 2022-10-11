package com.implementationcms.domain.model;

import com.implementationcms.domain.command.PostStatus;
import com.implementationcms.domain.exception.DraftNotFoundException;
import com.implementationcms.domain.exception.PublishNotFoundException;
import com.implementationcms.utils.ModelMapperUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table (name = "POSTS")
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
    @Enumerated(value = EnumType.STRING)
    private PostStatus status;

    private String type;

    @OneToMany(mappedBy = "post")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<PostVersion> postVersion;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    public Post(String postType, List<PostAttribute> postAttributeList) {
        this.type = postType;
        PostVersion postVersion = new PostVersion(this, postAttributeList).create();
        this.postVersion = Collections.singletonList(postVersion);
    }

    public Post create() {
        this.status = PostStatus.DRAFT;
        this.createdTime = LocalDateTime.now();
        this.updatedTime = LocalDateTime.now();
        return this;
    }

    public void update(List<PostAttribute> postAttributeList) {
        this.updatedTime = LocalDateTime.now();
        this.mustGetDraft().update(postAttributeList);
        if (PostStatus.PUBLISHED.equals(this.getStatus())) {
            this.setStatus(PostStatus.CHANGED);
        }
    }

    public void publish() {
        this.setStatus(PostStatus.PUBLISHED);
        this.setUpdatedTime(LocalDateTime.now());
        this.getOptionalPublished().ifPresent(publishedPost -> publishedPost.setStatus(PostStatus.ACHIVED));
        List<PostAttribute> postAttributeList = populatePostAttributeList(this.mustGetDraft().getPostAttributeList());
        PostVersion postVersion = new PostVersion(this, postAttributeList).create();
        postVersion.setStatus(PostStatus.PUBLISHED);
        this.getPostVersion().add(postVersion);
    }

    public void discard() {
        List<PostAttribute> postAttributeList = populatePostAttributeList(this.mustGetPublished().getPostAttributeList());
        this.mustGetDraft().update(postAttributeList);
        this.updatedTime = LocalDateTime.now();
        this.status = PostStatus.PUBLISHED;
    }

    public void archive() {
        this.status = PostStatus.ACHIVED;
        this.updatedTime = LocalDateTime.now();
    }

    public PostVersion mustGetDraft() {
        return this.getPostVersion().stream()
                .filter(postVersion -> PostStatus.DRAFT.equals(postVersion.getStatus()))
                .findFirst()
                .orElseThrow(() -> new DraftNotFoundException(this.getId()));
    }

    public PostVersion mustGetPublished() {
        return this.getPostVersion().stream()
                .filter(postVersion -> PostStatus.PUBLISHED.equals(postVersion.getStatus()))
                .findFirst()
                .orElseThrow(() -> new PublishNotFoundException(this.getId()));
    }

    private Optional<PostVersion> getOptionalPublished() {
        return this.getPostVersion().stream()
                .filter(postVersion -> PostStatus.PUBLISHED.equals(postVersion.getStatus()))
                .findFirst();
    }

    private List<PostAttribute> populatePostAttributeList(List<PostAttribute> postAttributeList) {
        ModelMapper modelMapper = ModelMapperUtil.getModelMapper();
        List<PostAttribute> newPostAttributeList = new ArrayList<>();
        postAttributeList.forEach(attribute ->
                newPostAttributeList.add(modelMapper.map(attribute, PostAttribute.class)));
        return newPostAttributeList;
    }


}
