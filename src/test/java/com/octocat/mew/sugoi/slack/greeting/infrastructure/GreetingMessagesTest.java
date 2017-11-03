package com.octocat.mew.sugoi.slack.greeting.infrastructure;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class GreetingMessagesTest {

    @Tested GreetingMessages greetingMessage;
    
    @Test
    public void testGreetingFromTheOtherSide(@Mocked Random random) throws Exception {
        new Expectations(greetingMessage) {{
            greetingMessage.getSalute(); result = Arrays.asList("Hi, I am %s, your super assistant.", "%s");
            new Random(); result = random;
            random.nextInt(); result = 0;
        }};
        assertThat(greetingMessage.greetingFromTheOtherSide("octocat"), is("Hi, I am octocat, your super assistant."));
    }
    
    @Test
    public void testThank(@Mocked Random random) throws Exception {
        new Expectations(greetingMessage) {{
            greetingMessage.getThank(); result = Arrays.asList("Glad to hear that!", "Well, that what you said");
            new Random(); result = random;
            random.nextInt(); result = 1;
        }};
        assertThat(greetingMessage.thankful(), is("Well, that what you said"));
    }
}
