package com.octocat.mew.sugoi.slack.redmine.domain.ticket;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BugTicket {

    private static final String REDMINE_DOMAIN_URL = "http://speeda-issuetracker.uzabase.com/redmine/issues";
    private String ticketNumber;
    
    public String getUrl() {
        return String.format("%s/%s", REDMINE_DOMAIN_URL, ticketNumber);
    }
}
