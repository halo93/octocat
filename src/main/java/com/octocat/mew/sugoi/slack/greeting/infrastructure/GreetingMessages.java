package com.octocat.mew.sugoi.slack.greeting.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@PropertySource("classpath:com/octocat/mew/sugoi/slack/greeting/infrastructure/greeting.properties")
@ConfigurationProperties("greeting")
@Getter
public class GreetingMessages {
    
    private List<String> thank = new ArrayList<>();
    private List<String> salute = new ArrayList<>();
    
    public String greetingFromTheOtherSide(String username) {
        List<String> salute = getSalute();
        return String.format(salute.get(randomNumber(salute.size())), username);
    }
    
    public String thankful() {
        List<String> thank = getThank();
        return thank.get(randomNumber(thank.size()));
    }

    private int randomNumber(int range) {
        return Math.abs(new Random().nextInt()) % range;
    }
}
