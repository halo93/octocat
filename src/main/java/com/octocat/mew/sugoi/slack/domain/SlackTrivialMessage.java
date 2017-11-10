package com.octocat.mew.sugoi.slack.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
public class SlackTrivialMessage extends SlackMessageTemplate {

    private String messageContent;

    @Override
    protected String messageContent() {
        return messageContent;
    }
}
