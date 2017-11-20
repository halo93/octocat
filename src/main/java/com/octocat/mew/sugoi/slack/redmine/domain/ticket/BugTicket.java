package com.octocat.mew.sugoi.slack.redmine.domain.ticket;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BugTicket {

    private static final String REDMINE_DOMAIN_URL = "http://speeda-issuetracker.uzabase.com/redmine/issues";
    private String ticketNumber;
    
    public String url() {
        return String.format("%s/%s", REDMINE_DOMAIN_URL, ticketNumber);
    }
    
    public String ticketNumber() {
        return this.ticketNumber;
    }
}
