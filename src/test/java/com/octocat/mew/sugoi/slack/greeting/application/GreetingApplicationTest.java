package com.octocat.mew.sugoi.slack.greeting.application;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.octocat.mew.sugoi.slack.domain.SlackTrivialMessage;
import com.octocat.mew.sugoi.slack.greeting.repository.GreetingMessageRepository;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class GreetingApplicationTest {

    @Tested GreetingApplication application;
    @Injectable GreetingMessageRepository repository;
    
    @Test
    public void testGreeting() throws Exception {
        new Expectations() {{
            repository.greeting("Octocat"); result = "Hello Octocat";
        }};
        
        assertThat(application.greeting("Octocat", "hung_channel"), is(new SlackTrivialMessage("Hello Octocat", "hung_channel")));
    }
    
    @Test
    public void testThank() throws Exception {
        new Expectations() {{
            repository.thank(); result = "Say it with your mom with gratitude";
        }};
        
        assertThat(application.thank("hung_channel"), is(new SlackTrivialMessage("Say it with your mom with gratitude", "hung_channel")));
    }
}
