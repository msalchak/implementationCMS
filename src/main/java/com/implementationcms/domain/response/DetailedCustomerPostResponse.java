package com.implementationcms.domain.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.implementationcms.domain.model.Post;
import com.implementationcms.domain.model.PostAttribute;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.PropertyMap;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class DetailedCustomerPostResponse {
    private Long id;

    private String name;

    private List<PostAttribute> postAttributeList;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedTime;

    public static PropertyMap<Post, DetailedCustomerPostResponse> DetailedCustomerPostDtoMap = new PropertyMap<Post, DetailedCustomerPostResponse>() {

        @Override
        protected void configure() {
            map().setName(source.mustGetPublished().getName());
            map().setPostAttributeList(source.mustGetPublished().getPostAttributeList());
        }
    };
}
