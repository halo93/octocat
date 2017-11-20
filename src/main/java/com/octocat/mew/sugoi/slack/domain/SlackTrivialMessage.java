package com.octocat.mew.sugoi.slack.domain;

import org.springframework.web.socket.TextMessage;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import me.ramswaroop.jbot.core.slack.EventType;
import me.ramswaroop.jbot.core.slack.models.Message;

@EqualsAndHashCode
@AllArgsConstructor
public class SlackTrivialMessage implements SlackMessage {

    private String messageContent;
    private String channelId;

    @Override
    public TextMessage toSendableMessage() throws JsonProcessingException {
        return new TextMessage(configureMessage(channelId).toJSONString());
    }

    private Message configureMessage(String channelId) {
        Message message = new Message(messageContent);
        message.setType(EventType.MESSAGE.name().toLowerCase());
        message.setText(encode(message.getText()));
        message.setChannel(channelId);
        return message;
    }

    private String encode(String message) {
        return message == null ? null : message.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}
