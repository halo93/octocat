package com.octocat.mew.sugoi.slack.redmine.domain.ticket;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class BugTicketTest {

    @Tested BugTicket bugticket;
    @Injectable String ticketNumber = "2345";
    
    @Test
    public void testGetUrl() throws Exception {
        assertThat(bugticket.getUrl(), is("http://speeda-issuetracker.uzabase.com/redmine/issues/2345"));
    }
}
