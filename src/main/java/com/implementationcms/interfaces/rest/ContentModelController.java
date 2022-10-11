package com.implementationcms.interfaces.rest;

import com.implementationcms.domain.command.AbstractPostModelCommand;
import com.implementationcms.domain.model.PostModel;
import com.implementationcms.domain.response.SimplePostModelResponse;
import com.implementationcms.domain.service.PostModelService;
import com.implementationcms.utils.ModelMapperUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contentModels")
@RequiredArgsConstructor
public class ContentModelController {
    private final PostModelService postModelService;

    private final ModelMapper modelMapper = ModelMapperUtil.getModelMapper();

    @PostMapping
    public SimplePostModelResponse createPostModel(@RequestBody AbstractPostModelCommand postModelCommand) {
        PostModel postModel = postModelService.create(postModelCommand);
        return modelMapper.map(postModel, SimplePostModelResponse.class);
    }

    @GetMapping
    public Page<SimplePostModelResponse> getAllPostModels(@PageableDefault Pageable pageable) {
        Page<PostModel> postModels = postModelService.getAll(pageable);
        return postModels.map(postModel -> modelMapper.map(postModel, SimplePostModelResponse.class));
    }

    @GetMapping("/{id}")
    public SimplePostModelResponse viewPostModel(@PathVariable Long id) {
        PostModel postModel = postModelService.view(id);
        return modelMapper.map(postModel, SimplePostModelResponse.class);
    }

    @PostMapping("/{id}/edit")
    public SimplePostModelResponse editPostModel(@PathVariable Long id, @RequestBody AbstractPostModelCommand postModelCommand) {
        PostModel postModel =  postModelService.edit(id, postModelCommand);
        return modelMapper.map(postModel, SimplePostModelResponse.class);
    }
}
