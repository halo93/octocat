package com.octocat.mew.sugoi.slack.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import me.ramswaroop.jbot.core.slack.models.RichMessage;

/**
 * The wrapper of {@link me.ramswaroop.jbot.core.slack.models.Message}
 * 
 * @author library
 */
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
public class SlackRichMessage extends SlackMessageTemplate {

    private RichMessage richMessage;

    @Override
    protected String messageContent() {
        try {
            return new ObjectMapper().writeValueAsString(richMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return richMessage.getText();
        }
    }
}
