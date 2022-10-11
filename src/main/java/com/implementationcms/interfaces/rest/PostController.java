package com.implementationcms.interfaces.rest;

import com.implementationcms.domain.command.CreatePostCommand;
import com.implementationcms.domain.command.EditPostCommand;
import com.implementationcms.domain.model.Post;
import com.implementationcms.domain.response.DetailedAdminPostResponse;
import com.implementationcms.domain.response.DetailedCustomerPostResponse;
import com.implementationcms.domain.response.SimplePostResponse;
import com.implementationcms.domain.service.PostService;
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
    public SimplePostResponse createContent(@RequestBody CreatePostCommand createPostCommand) {
        Post post = postService.create(createPostCommand);
        return modelMapper.map(post, SimplePostResponse.class);
    }

    @GetMapping
    public Page<SimplePostResponse> getAllPosts(@PageableDefault Pageable pageable) {
        Page<Post> posts = postService.getAll(pageable);
        return posts.map(post -> modelMapper.map(post, SimplePostResponse.class));
    }

    @GetMapping("/{id}")
    public DetailedAdminPostResponse getPost(@PathVariable Long id) {
        Post post = postService.get(id);
        return modelMapper.map(post, DetailedAdminPostResponse.class);
    }

    @GetMapping("/{id}/customer")
    public DetailedCustomerPostResponse viewPost(@PathVariable Long id) {
        Post post = postService.view(id);
        return modelMapper.map(post, DetailedCustomerPostResponse.class);
    }

    @PostMapping("/{id}/publish")
    public SimplePostResponse publishPost(@PathVariable Long id) {
        Post post = postService.publish(id);
        return modelMapper.map(post, SimplePostResponse.class);
    }

    @PostMapping("/{id}/edit")
    public SimplePostResponse editPost(@PathVariable Long id, @RequestBody EditPostCommand editPostCommand) {
        Post post = postService.edit(id, editPostCommand);
        return modelMapper.map(post, SimplePostResponse.class);
    }
}
