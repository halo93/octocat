package com.octocat.mew.sugoi.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexApplication {

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        return "Greeting from Octocat!";
    }
}
