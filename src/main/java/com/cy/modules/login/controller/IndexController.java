package com.cy.modules.login.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@RequestMapping(value = {"/showIndex",""})
	public String toIndex(HttpServletRequest request,Model model){
		System.out.println("11111111111111");
		
		return "user/homePage";
	}
}
