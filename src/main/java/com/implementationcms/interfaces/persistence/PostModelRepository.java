package com.implementationcms.interfaces.persistence;

import com.implementationcms.domain.model.PostModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface PostModelRepository extends CrudRepository<PostModel, Long> {
    Page<PostModel> findAll(Pageable pageable);
}
