package com.ruoyi.dylive.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dylive/test")
public class DyTestController {

    @RequestMapping(value = "/1", method = RequestMethod.GET)
    public String test() {
        return "test";
    }

}
