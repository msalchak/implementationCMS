package com.implementationcms.domain.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.implementationcms.domain.command.PostStatus;
import com.implementationcms.domain.model.Post;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.PropertyMap;

import java.time.LocalDateTime;

@Getter
@Setter
public class SimplePostResponse {
    private Long id;

    private String name;

    private PostStatus status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedTime;

    public static PropertyMap<Post, SimplePostResponse> PostToSimplePostDtoMap = new PropertyMap<Post, SimplePostResponse>() {

        @Override
        protected void configure() {
            map().setName(source.mustGetDraft().getName());
        }
    };
}
