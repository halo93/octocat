package com.octocat.mew.sugoi.slack.repository;

import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import me.ramswaroop.jbot.core.slack.models.Message;

@Controller
public class RedmineBugTicketApplication {

    @Autowired 
    private RedmineBugRepository redmineBugRepository;

    public List<Message> grabBugTicket(Matcher matcher) {
        return redmineBugRepository
                .fetchBugTicket(matcher.group(0).replaceAll("bug", "").split(" *"))
                .stream()
                .map(Message::new)
                .collect(Collectors.toList());
    }
}
