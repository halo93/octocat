package com.octocat.mew.sugoi.slack.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class RedmineBugRepository {

    public List<String> fetchBugTicket(String... bugNumber) {
        return Arrays
            .asList(bugNumber)
            .stream()
            .map(e -> String.format("speeda-issuetracker.uzabase.com/redmine/issues/%s", e))
            .collect(Collectors.toList());
    }
}
