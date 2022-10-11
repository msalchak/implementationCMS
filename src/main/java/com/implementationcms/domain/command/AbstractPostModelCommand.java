package com.implementationcms.domain.command;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class AbstractPostModelCommand {
    private String name;

    private Map<String, String> attributes;
}
