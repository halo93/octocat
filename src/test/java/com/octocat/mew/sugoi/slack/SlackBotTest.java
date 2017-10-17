package com.octocat.mew.sugoi.slack;

import me.ramswaroop.jbot.core.slack.Bot;
import me.ramswaroop.jbot.core.slack.Controller;
import me.ramswaroop.jbot.core.slack.SlackService;
import me.ramswaroop.jbot.core.slack.models.Event;
import me.ramswaroop.jbot.core.slack.models.User;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Arrays;
import java.util.regex.Matcher;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class SlackBotTest {
    @Mock
    private WebSocketSession session;

    @Mock
    private SlackService slackService;

    @InjectMocks
    private TestBot bot;

    @Rule
    public OutputCapture capture = new OutputCapture();

    @Before
    public void init(){
        User user = new User();
        user.setName("Pham Hung");
        user.setId("CHUNCHUN");
        when(slackService.getDmChannels()).thenReturn(Arrays.asList("CHUNCHUN1", "CHUNCHUN2"));
        when(slackService.getCurrentUser()).thenReturn(user);
        when(slackService.getWebSocketUrl()).thenReturn("");
    }

    public static class TestBot extends Bot {
        @Override public String getSlackToken() {
            return "slackToken";
        }

        @Override public Bot getSlackBot() {
            return this;
        }

        @Controller(pattern = "^\\(bug\\) [0-9]*$", next = "askBugTicket")
        public void grabBugTicket(WebSocketSession session, Event event, Matcher matcher){
            System.out.println(matcher.group(0));
        }
    }

    @Test
    public void grabBugTicketTest() throws Exception {
        bot.handleTextMessage(session, new TextMessage());
        assertThat(capture.toString(), containsString("sdfsdf"));
    }
}
