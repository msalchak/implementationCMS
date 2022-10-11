package com.implementationcms.domain.service;

import com.implementationcms.domain.command.AbstractPostCommand;
import com.implementationcms.domain.command.CreatePostCommand;
import com.implementationcms.domain.command.EditPostCommand;
import com.implementationcms.domain.command.PostStatus;
import com.implementationcms.domain.exception.PostNotFoundException;
import com.implementationcms.domain.model.Post;
import com.implementationcms.domain.model.PostAttribute;
import com.implementationcms.interfaces.persistence.PostRepository;
import com.implementationcms.utils.ModelMapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.exception.DataException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper = ModelMapperUtil.getModelMapper();
    @Transactional(rollbackFor = DataException.class)
    public Post create(CreatePostCommand createPostCommand) {
        List<PostAttribute> postAttributeList = populatePostAttributeList(createPostCommand);
        Post post = new Post(createPostCommand.getPostType(), postAttributeList).create();
        postRepository.save(post);
        return post;
    }

    @SneakyThrows
    @Transactional(readOnly = true)
    public Post get(Long id) {
        return postRepository.findPostByIdAndPostStatus(id, PostStatus.ACHIVED).orElseThrow(() -> new PostNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public Post view(Long id) {
        return postRepository.findPostByIdAndPostStatus(id, Arrays.asList(PostStatus.PUBLISHED, PostStatus.CHANGED)).orElseThrow(() -> new PostNotFoundException(id));
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @SneakyThrows
    public Post edit(Long id, EditPostCommand editPostCommand) {
        List<PostAttribute> postAttributeList = populatePostAttributeList(editPostCommand);
        Post post = postRepository.findPostByIdAndPostStatus(id, PostStatus.ACHIVED).orElseThrow(() -> new PostNotFoundException(id));
        post.update(postAttributeList);
        return post;
    }

    @SneakyThrows
    @Transactional(rollbackFor = RuntimeException.class)
    public Post publish(Long id) {
        Post post = postRepository.findPostByIdAndPostStatus(id, PostStatus.ACHIVED).orElseThrow(() -> new PostNotFoundException(id));
        post.publish();
        return post;
    }

    @SneakyThrows
    @Transactional(rollbackFor = RuntimeException.class)
    public Post discard(Long id) {
        Post post = postRepository.findPostByIdAndPostStatus(id, PostStatus.ACHIVED).orElseThrow(() -> new PostNotFoundException(id));
        post.discard();
        return post;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public Post archive(Long id) {
        Post post = postRepository.findPostByIdAndPostStatus(id, PostStatus.ACHIVED).orElseThrow(() -> new PostNotFoundException(id));
        post.archive();
        return post;
    }

    @Transactional(readOnly = true)
    public Page<Post> getAll(Pageable pageable) {
        return postRepository.findPostPageByPostStatus(pageable, PostStatus.ACHIVED);
    }

    private List<PostAttribute> populatePostAttributeList(AbstractPostCommand postCommand) {
        List<PostAttribute> postAttributeList = new ArrayList<>();
        postCommand.getAttributes().forEach(attribute ->
                postAttributeList.add(modelMapper.map(attribute, PostAttribute.class)));
        return postAttributeList;
    }
}
