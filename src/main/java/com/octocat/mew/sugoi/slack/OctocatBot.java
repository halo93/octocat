package com.octocat.mew.sugoi.slack;

import java.util.regex.Matcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import com.octocat.mew.sugoi.slack.greeting.application.GreetingApplication;
import com.octocat.mew.sugoi.slack.redmine.application.RedmineBugTicketApplication;

import lombok.Getter;
import me.ramswaroop.jbot.core.slack.Bot;
import me.ramswaroop.jbot.core.slack.Controller;
import me.ramswaroop.jbot.core.slack.EventType;
import me.ramswaroop.jbot.core.slack.models.Event;
import me.ramswaroop.jbot.core.slack.models.Message;

/**
 * A Slack Bot sample. You can create multiple bots by just
 * extending {@link Bot} class like this one.
 *
 * @author ramswaroop
 * @version 1.0.0, 05/06/2016
 */
@Component
public class OctocatBot extends Bot {

    private static final Logger logger = LoggerFactory.getLogger(OctocatBot.class);

    /**
     * Slack token from application.properties file. You can get your slack token
     * next <a href="https://my.slack.com/services/new/bot">creating a new bot</a>.
     */
    @Value("${slackBotToken}")
    @Getter
    private String slackToken;
    
    @Autowired 
    private RedmineBugTicketApplication redmineBugTicketApplication;
    
    @Autowired
    private GreetingApplication greetingApplication;
    
    @Override
    public Bot getSlackBot() {
        return this;
    }

    /**
     * Invoked when an item is pinned in the channel.
     *
     * @param session
     * @param event
     */
    @Controller(events = EventType.PIN_ADDED)
    public void onPinAdded(WebSocketSession session, Event event) {
        reply(session, event, new Message("Thanks for the pin! You can find all pinned items under channel details."));
    }

    /**
     * Invoked when bot receives an event of type file shared.
     * NOTE: You can't reply to this event as slack doesn't send
     * a channel id for this event type. You can learn more about
     * <a href="https://api.slack.com/events/file_shared">file_shared</a>
     * event from Slack's Api documentation.
     *
     * @param session
     * @param event
     */
    @Controller(events = EventType.FILE_SHARED)
    public void onFileShared(WebSocketSession session, Event event) {
        logger.info("File shared: {}", event);
    }

    @Controller(pattern = "(?i)(bug[0-9\\s]*)")
    public void grabBugTicket(WebSocketSession session, Event event, Matcher matcher) {
        redmineBugTicketApplication
            .grabBugTicket(matcher)
            .forEach(message -> reply(session, event, message));
    }
    
    @Controller(events = {EventType.DIRECT_MENTION, EventType.DIRECT_MESSAGE}, pattern="(?i)(bug[0-9\\s]*)")
    public void grabBugTicketDM(WebSocketSession session, Event event, Matcher matcher) {
        redmineBugTicketApplication.grabBugTicket(matcher).forEach(message -> reply(session, event, message));
    }
    
    @Controller(events = {EventType.DIRECT_MENTION, EventType.DIRECT_MESSAGE}, pattern="(?i)(thank)")
    public void greetingtDM(WebSocketSession session, Event event) {
        reply(session, event, greetingApplication.thank());
    }
    
    /**
     * Invoked when the bot receives a direct mention (@botname: message)
     * or a direct message. NOTE: These two event types are added by jbot
     * to make your task easier, Slack doesn't have any direct way to
     * determine these type of events.
     *
     * @param session
     * @param event
     */
    @Controller(events = {EventType.DIRECT_MENTION, EventType.DIRECT_MESSAGE}, pattern="(?i)(hello)")
    public void greeting(WebSocketSession session, Event event) {
        reply(session, event, greetingApplication.greeting(slackService.getCurrentUser().getName()));
    }
    
    @Controller(events = {EventType.DIRECT_MENTION, EventType.DIRECT_MESSAGE}, pattern="(?i)(who are you)")
    public void greetingHi(WebSocketSession session, Event event) {
        greeting(session, event);
    }
}
