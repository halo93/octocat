package com.octocat.mew.sugoi.slack.redmine.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.octocat.mew.sugoi.slack.redmine.domain.ticket.BugTicket;

@Repository
public class RedmineBugRepository {

    public List<String> fetchBugTickets(String... bugNumber) {
        return Arrays
            .asList(bugNumber)
            .stream()
            .map(e -> new BugTicket(e).url())
            .collect(Collectors.toList());
    }
}
