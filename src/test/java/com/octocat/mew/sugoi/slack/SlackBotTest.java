package com.octocat.mew.sugoi.slack;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.regex.Matcher;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import me.ramswaroop.jbot.core.slack.Bot;
import me.ramswaroop.jbot.core.slack.Controller;
import me.ramswaroop.jbot.core.slack.SlackService;
import me.ramswaroop.jbot.core.slack.models.Event;
import me.ramswaroop.jbot.core.slack.models.User;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
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

    @Test
    public void grabBugTicketTest() throws Exception {
        TextMessage textMessage = new TextMessage("{\"type\": \"message\"," +
                "\"ts\": \"1358878749.000002\"," +
                "\"channel\": \"A1E78BACV\"," +
                "\"user\": \"U023BECGF\"," +
                "\"text\": \"Bug 101\"}");
        bot.handleTextMessage(session, textMessage);
        assertThat(capture.toString(), is("Bug 101\n"));
    }
    
    @Test
    public void grabBugTicketNormalCaseTest() throws Exception {
        TextMessage textMessage = new TextMessage("{\"type\": \"message\"," +
                "\"ts\": \"1358878749.000002\"," +
                "\"channel\": \"A1E78BACV\"," +
                "\"user\": \"U023BECGF\"," +
                "\"text\": \"bug 101\"}");
        bot.handleTextMessage(session, textMessage);
        assertThat(capture.toString(), is("bug 101\n"));
    }
    
    @Test
    public void grabBugTicketNotCatchTest() throws Exception {
        TextMessage textMessage = new TextMessage("{\"type\": \"message\"," +
                "\"ts\": \"1358878749.000002\"," +
                "\"channel\": \"A1E78BACV\"," +
                "\"user\": \"U023BECGF\"," +
                "\"text\": \"I want to introduce you the bug 101 102  103\"}");
        bot.handleTextMessage(session, textMessage);
        assertThat(capture.toString(), is("bug 101 102  103\n"));
    }
    
    public static class TestBot extends Bot {
        @Override public String getSlackToken() {
            return "slackToken";
        }

        @Override public Bot getSlackBot() {
            return this;
        }

        @Controller(pattern = "(?i)(bug[0-9\\s]*)", next = "askBugTicket")
        public void grabBugTicket(WebSocketSession session, Event event, Matcher matcher){
            System.out.println(matcher.group(0));
        }
    }

}
