package com.team.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wang0 on 2016/9/12.
 */

@Controller
public class Controll {

    @RequestMapping("/")
    public String view() {
        return "view";
    }

}
