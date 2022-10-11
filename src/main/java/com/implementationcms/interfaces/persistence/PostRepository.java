package com.implementationcms.interfaces.persistence;

import com.implementationcms.domain.command.PostStatus;
import com.implementationcms.domain.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Long> {
    @Query(value = "from Post c where c.id = :id and c.status <> :postStatus")
    Optional<Post> findPostByIdAndPostStatus(Long id, PostStatus postStatus);

    @Query(value = "from Post c where c.id = :id and c.status in :postStatusList")
    Optional<Post> findPostByIdAndPostStatus(Long id, List<PostStatus> postStatusList);

    @Query(value = "from Post c where c.status <> :postStatus")
    Page<Post> findPostPageByPostStatus(Pageable pageable, PostStatus postStatus);
}
