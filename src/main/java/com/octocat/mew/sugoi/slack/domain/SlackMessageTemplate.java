package com.octocat.mew.sugoi.slack.domain;

import org.springframework.web.socket.TextMessage;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.EqualsAndHashCode;
import me.ramswaroop.jbot.core.slack.EventType;
import me.ramswaroop.jbot.core.slack.models.Message;

@EqualsAndHashCode
abstract class SlackMessageTemplate implements SlackMessage {
    
    @Override
    public TextMessage toSendableMessage(String channelId) throws JsonProcessingException {
        return new TextMessage(configureMessage(channelId).toJSONString());
    }

    protected abstract String messageContent();

    private Message configureMessage(String channelId) {
        Message message = new Message(messageContent());
        message.setType(EventType.MESSAGE.name().toLowerCase());
        message.setText(encode(message.getText()));
        if (message.getChannel() == null) {
            message.setChannel(channelId);
        }
        return message;
    }

    private String encode(String message) {
        return message == null ? null : message.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}
