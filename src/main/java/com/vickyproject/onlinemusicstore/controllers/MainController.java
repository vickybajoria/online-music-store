package com.vickyproject.onlinemusicstore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
	
	@RequestMapping("online-music-store")
	public String homePage()
	{
		return "home";
	}

}
