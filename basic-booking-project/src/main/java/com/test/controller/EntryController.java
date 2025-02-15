package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class EntryController {
	
	@GetMapping("/index.html")
    public String goToIndex() {
		return "index";
    }
	
	@GetMapping("/login")
    public String goToLogin() {
        return "login";
    }
}
