package com.octocat.mew.sugoi.slack.domain;

import org.springframework.web.socket.TextMessage;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface SlackMessage {
    TextMessage toSendableMessage(String channelId) throws JsonProcessingException;
}
