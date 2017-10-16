package com.octocat.mew.sugoi.slack;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.ramswaroop.jbot.core.slack.models.Attachment;
import me.ramswaroop.jbot.core.slack.models.RichMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BugLinkSlashCommand {
    private static final Logger logger = LoggerFactory.getLogger(BugLinkSlashCommand.class);

    @Value("${slashCommandToken}")
    private String slashCommandToken;

    @RequestMapping(value = "bug_ticket",
        method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public RichMessage onReceiveBugTicket(@RequestParam("token") String token,
            @RequestParam("team_id") String teamId,
            @RequestParam("team_domain") String teamDomain,
            @RequestParam("channel_id") String channelId,
            @RequestParam("channel_name") String channelName,
            @RequestParam("user_id") String userId,
            @RequestParam("user_name") String userName,
            @RequestParam("command") String command,
            @RequestParam("text") String text,
            @RequestParam("response_url") String responseUrl) throws JsonProcessingException {
        if (!token.equals(slashCommandToken)){
            return new RichMessage("Octocat denied to communicate with you, bastard!~");
        }
        RichMessage responseMess = new RichMessage();
        responseMess.setResponseType("in_channel");
        Attachment[] attachments = new Attachment[1];
        attachments[0] = new Attachment();
        attachments[0].setText("Hello boy, do you wanna have some fun?" + text);
        responseMess.setAttachments(attachments);
        logger.debug("Reply (RichMessage): {}", new ObjectMapper().writeValueAsString(responseMess));
        return responseMess.encodedMessage();
    }

}
