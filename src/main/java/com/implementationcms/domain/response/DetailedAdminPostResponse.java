package com.implementationcms.domain.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.implementationcms.domain.command.PostStatus;
import com.implementationcms.domain.model.Post;
import com.implementationcms.domain.model.PostAttribute;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.PropertyMap;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class DetailedAdminPostResponse {
    private Long id;

    private String name;

    private PostStatus status;

    private List<PostAttribute> postAttributeList;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedTime;

    public static PropertyMap<Post, DetailedAdminPostResponse> DetailedAdminPostDtoMap = new PropertyMap<Post, DetailedAdminPostResponse>() {

        @Override
        protected void configure() {
            map().setName(source.mustGetDraft().getName());
            map().setPostAttributeList(source.mustGetDraft().getPostAttributeList());
        }
    };

    public static PropertyMap<Post, DetailedAdminPostResponse> CustomerDetailedPostDtoMap = new PropertyMap<Post, DetailedAdminPostResponse>() {

        @Override
        protected void configure() {
            map().setName(source.mustGetPublished().getName());
            map().setPostAttributeList(source.mustGetPublished().getPostAttributeList());
        }
    };
}
