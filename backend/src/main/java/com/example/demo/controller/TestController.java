package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping("/api")
public class TestController {
    @GetMapping("/testSession")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody String getSession(HttpSession session) {
        String state = (String) session.getAttribute("state");
        return state;
    }
}
