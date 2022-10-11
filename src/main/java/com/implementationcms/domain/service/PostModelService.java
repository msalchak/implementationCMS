package com.implementationcms.domain.service;

import com.implementationcms.domain.command.AbstractPostModelCommand;
import com.implementationcms.domain.exception.PostModelNotFoundException;
import com.implementationcms.domain.model.PostModel;
import com.implementationcms.interfaces.persistence.PostModelRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.DataException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostModelService {
    private final PostModelRepository postModelRepository;
    @Transactional(readOnly = true)
    public PostModel view(Long id) {
        return postModelRepository.findById(id).orElseThrow(() -> new PostModelNotFoundException(id));
    }

    @Transactional(rollbackFor = DataException.class)
    public PostModel create(AbstractPostModelCommand postModelCommand) {
        PostModel postModel = new PostModel(postModelCommand.getName(), postModelCommand.getAttributes()).create();
        postModelRepository.save(postModel);
        return postModel;
    }

    @Transactional(rollbackFor = DataException.class)
    public PostModel edit(Long id, AbstractPostModelCommand postModelCommand) {
        PostModel postModel = postModelRepository.findById(id).orElseThrow(() -> new PostModelNotFoundException(id));
        postModel.update(postModelCommand.getAttributes());
        return postModel;
    }

    @Transactional(readOnly = true)
    public Page<PostModel> getAll(Pageable pageable) {
        return postModelRepository.findAll(pageable);
    }
}
