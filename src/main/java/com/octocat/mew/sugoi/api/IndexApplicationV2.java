package com.octocat.mew.sugoi.api;

import com.octocat.mew.sugoi.api.definition.IIndexApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v2")
public class IndexApplicationV2 implements IIndexApplication{

    @Override
    public String index() {
        return "Greeting from Octocat! Chunchun barkbark";
    }
}
