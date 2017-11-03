package com.octocat.mew.sugoi.slack.greeting.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.octocat.mew.sugoi.slack.greeting.repository.GreetingMessageRepository;

import me.ramswaroop.jbot.core.slack.models.Message;

@Controller
public class GreetingApplication {

    @Autowired
    private GreetingMessageRepository repository;
    
    public Message greeting(String username) {
        return new Message(repository.greeting(username));
    }
    
    public Message thank() {
        return new Message(repository.thank());
    }
}
