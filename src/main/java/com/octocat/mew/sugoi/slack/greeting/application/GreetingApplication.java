package com.octocat.mew.sugoi.slack.greeting.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.octocat.mew.sugoi.slack.domain.SlackMessage;
import com.octocat.mew.sugoi.slack.domain.SlackTrivialMessage;
import com.octocat.mew.sugoi.slack.greeting.repository.GreetingMessageRepository;

@Controller
public class GreetingApplication {

    @Autowired
    private GreetingMessageRepository repository;
    
    public SlackMessage greeting(String username, String channelId) {
        return new SlackTrivialMessage(repository.greeting(username), channelId);
    }
    
    public SlackMessage thank(String channelId) {
        return new SlackTrivialMessage(repository.thank(), channelId);
    }
}
