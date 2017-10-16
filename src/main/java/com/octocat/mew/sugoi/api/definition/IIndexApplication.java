package com.octocat.mew.sugoi.api.definition;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("api")
public interface IIndexApplication {
    @RequestMapping(value = "index", method = RequestMethod.GET)
    String index();
}
