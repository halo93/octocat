package com.octocat.mew.sugoi.slack.redmine.domain.bot_argument.implement;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.regex.Matcher;

import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class BugTicketArgumentResolutionTest {

    @Tested BugTicketArgumentResolution ticketResolution;
    @Injectable Matcher matcher;
    
    @Test
    public void testResolveInTheNormalCase() throws Exception {
        expectation("bug 123 456   789");
        assertThat(ticketResolution.resolve(), is(new String[] {"123", "456", "789"}));
    }

    @Test
    public void testResolveInRealWorld() throws Exception {
        expectation("I want to get the bugs 123 456   789");
        assertThat(ticketResolution.resolve(), is(new String[] {"123", "456", "789"}));
    }

    private void expectation(String groupResult) {
        new Expectations() {{
            matcher.group(0); result = groupResult;
        }};
    }
}
