package com.implementationcms.domain.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePostAttributeCommand {
    private String key;

    private String value;

    private String type;
}
