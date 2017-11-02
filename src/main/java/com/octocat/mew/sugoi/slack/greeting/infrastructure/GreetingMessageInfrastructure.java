package com.octocat.mew.sugoi.slack.greeting.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GreetingMessageInfrastructure {
    @Autowired
    private GreetingMessages greetingMessages;
    
    public String greeting(String username) {
        return greetingMessages.greetingFromTheOtherSide(username);
    }
    
    public String thank() {
        return greetingMessages.thankful();
    }
}
