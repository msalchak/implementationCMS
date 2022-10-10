package com.implementationcms.interfaces.rest;

import com.implementationcms.utils.ModelMapperUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    private final ModelMapper modelMapper = ModelMapperUtil.getModelMapper();

    @PostMapping
    public SimpleContentResponse createContent(@RequestBody CreateContentCommand createContentCommand) {
        Content content = contentService.create(createContentCommand);
        return modelMapper.map(content, SimpleContentResponse.class);
    }

    @GetMapping
    public Page<SimpleContentResponse> getAllContents(@PageableDefault Pageable pageable) {
        Page<Content> contents = contentService.getAll(pageable);
        return contents.map(content -> modelMapper.map(content, SimpleContentResponse.class));
    }

    @GetMapping("/{id}")
    public DetailedAdminContentResponse getContent(@PathVariable Long id) {
        Content content = contentService.get(id);
        return modelMapper.map(content, DetailedAdminContentResponse.class);
    }

    @GetMapping("/{id}/customer")
    public DetailedCustomerContentResponse viewContent(@PathVariable Long id) {
        Content content = contentService.view(id);
        return modelMapper.map(content, DetailedCustomerContentResponse.class);
    }

    @PostMapping("/{id}/publish")
    public SimpleContentResponse publishContent(@PathVariable Long id) {
        Content content = contentService.publish(id);
        return modelMapper.map(content, SimpleContentResponse.class);
    }

    @PostMapping("/{id}/edit")
    public SimpleContentResponse editContent(@PathVariable Long id, @RequestBody EditContentCommand editContentCommand) {
        Content content = contentService.edit(id, editContentCommand);
        return modelMapper.map(content, SimpleContentResponse.class);
    }
}
