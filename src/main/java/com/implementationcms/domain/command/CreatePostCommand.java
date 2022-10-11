package com.implementationcms.domain.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePostCommand extends AbstractPostCommand {
    private String postType;
}
