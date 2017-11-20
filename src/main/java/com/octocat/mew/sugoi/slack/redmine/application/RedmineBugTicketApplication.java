package com.octocat.mew.sugoi.slack.redmine.application;

import java.util.regex.Matcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.octocat.mew.sugoi.slack.domain.SlackMessage;
import com.octocat.mew.sugoi.slack.domain.SlackRichMessage;
import com.octocat.mew.sugoi.slack.domain.message.AttachmentContent;
import com.octocat.mew.sugoi.slack.domain.message.AttachmentContent.AttachmentContentBuilder;
import com.octocat.mew.sugoi.slack.redmine.domain.bot_argument.implement.BugTicketArgumentResolution;
import com.octocat.mew.sugoi.slack.redmine.domain.ticket.BugTicket;
import com.octocat.mew.sugoi.slack.redmine.repository.RedmineBugRepository;

import me.ramswaroop.jbot.core.slack.models.Attachment;

@Controller
public class RedmineBugTicketApplication {

    @Autowired private RedmineBugRepository redmineBugRepository;

    public SlackMessage grabBugTicket(Matcher matcher) {
        AttachmentContentBuilder attachmentContentBuilder = AttachmentContent.builder();
        redmineBugRepository
            .fetchBugTickets(new BugTicketArgumentResolution(matcher).resolve())
            .stream()
            .map(this::createAttachment)
            .forEach(attachmentContentBuilder::attachment);
        return new SlackRichMessage(attachmentContentBuilder.build());
    }

    private Attachment createAttachment(BugTicket ticket) {
        Attachment attachment = new Attachment();
        attachment.setFallback("Redmine ticket");
        attachment.setColor("good");
        attachment.setPretext("Here you are");
        attachment.setAuthorName("Octocat");
        attachment.setTitle("Ticket #" + ticket.ticketNumber());
        attachment.setTitleLink(ticket.url());
        attachment.setText("URL: " + ticket.url());
        return attachment;
    }
}
