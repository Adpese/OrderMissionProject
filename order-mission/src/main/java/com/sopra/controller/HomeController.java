package com.sopra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	
	@RequestMapping("/home")
	public String home() {
		return "index";
	}
	
	@RequestMapping("/addcolb")
	public String addcolb() {
		return "indexgg";
	}
	
	@RequestMapping("/listar")
	public String listar() {
		return "list";
	}

}
