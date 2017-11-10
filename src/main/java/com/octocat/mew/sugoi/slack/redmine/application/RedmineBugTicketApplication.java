package com.octocat.mew.sugoi.slack.redmine.application;

import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.octocat.mew.sugoi.slack.redmine.domain.bot_argument.implement.BugTicketArgumentResolution;
import com.octocat.mew.sugoi.slack.redmine.repository.RedmineBugRepository;

import me.ramswaroop.jbot.core.slack.models.Message;

@Controller
public class RedmineBugTicketApplication {

    @Autowired 
    private RedmineBugRepository redmineBugRepository;

    public List<Message> grabBugTicket(Matcher matcher) {
        return redmineBugRepository
                .fetchBugTickets(new BugTicketArgumentResolution(matcher).resolve())
                .stream()
                .map(e -> "Here's you are:\n>" + e)
                .map(Message::new)
                .collect(Collectors.toList());
    }
}
