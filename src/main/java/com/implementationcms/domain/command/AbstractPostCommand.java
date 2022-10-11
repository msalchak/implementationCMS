package com.implementationcms.domain.command;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AbstractPostCommand {
    private List<CreatePostAttributeCommand> attributes;
}
