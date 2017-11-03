package com.octocat.mew.sugoi.slack.greeting.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.octocat.mew.sugoi.slack.greeting.infrastructure.GreetingMessageInfrastructure;

@Repository
public class GreetingMessageRepository {
    
    @Autowired
    private GreetingMessageInfrastructure infrastructure;
    
    public String greeting(String username) {
        return infrastructure.greeting(username);
    }
    
    public String thank() {
        return infrastructure.thank();
    }
}
