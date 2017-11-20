package com.octocat.mew.sugoi.slack.domain;

import org.springframework.web.socket.TextMessage;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.octocat.mew.sugoi.slack.domain.message.AttachmentContent;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * The wrapper of {@link me.ramswaroop.jbot.core.slack.models.Message}
 * 
 * @author library
 */
@EqualsAndHashCode
@AllArgsConstructor
public class SlackRichMessage implements SlackMessage {

    private AttachmentContent attachmentMessage;

    @Override
    public TextMessage toSendableMessage() throws JsonProcessingException {
        return new TextMessage(messageContent());
    }

    protected String messageContent() {
        try {
            return new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                .setSerializationInclusion(Include.NON_NULL)
                .writeValueAsString(attachmentMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Opps! Something went wrong.";
        }
    }
}
