package com.octocat.mew.sugoi.api;

import com.octocat.mew.sugoi.api.definition.IIndexApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexApplication implements IIndexApplication{

    @Override
    public String index() {
        return "Greeting from Octocat!";
    }
}
