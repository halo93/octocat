package com.octocat.mew.sugoi.slack.greeting.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.octocat.mew.sugoi.slack.greeting.infrastructure.GreetingMessageInfrastructure;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class GreetingMessageRepositoryTest {

    @Tested GreetingMessageRepository repository;
    @Injectable GreetingMessageInfrastructure infrastructure;
    
    @Test
    public void testGreeting() throws Exception {
        new Expectations() {{
            infrastructure.greeting("Octocat"); result = "uhhu";
        }};
        
        assertThat(repository.greeting("Octocat"), is("uhhu"));
    }
    
    @Test
    public void testThank() throws Exception {
        new Expectations() {{
            infrastructure.thank(); result = "You're welcome";
        }};
        
        assertThat(repository.thank(), is("You're welcome"));
    }
    
}
