package com.octocat.mew.sugoi.slack.redmine.domain.bot_argument.implement;

import java.util.regex.Matcher;

import com.octocat.mew.sugoi.slack.redmine.domain.bot_argument.BotEntryPointArgumentResolution;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BugTicketArgumentResolution implements BotEntryPointArgumentResolution<String[]> {

    private Matcher matcher;

    @Override
    public String[] resolve() {
        return matcher.group(0).replaceAll("(?i).*bug[^0-9]*", "").trim().split(" +");
    }
    
}
