package com.octocat.mew.sugoi.slack.domain.message;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import me.ramswaroop.jbot.core.slack.models.Attachment;

@Builder
@Getter
public class AttachmentContent {
    private String channel;
    private String type;
    
    @Singular
    private List<Attachment> attachments;
}
