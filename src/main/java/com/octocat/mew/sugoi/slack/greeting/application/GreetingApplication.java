package com.octocat.mew.sugoi.slack.greeting.application;

import org.springframework.stereotype.Controller;

import me.ramswaroop.jbot.core.slack.models.Message;

@Controller
public class GreetingApplication {

    public Message greeting(String username) {
        return new Message(String.format("Hi, I am %s, and I am your super assistant.", username));
    }
    
    public Message thank() {
        return new Message("You're welcome, bro!");
    }
}
