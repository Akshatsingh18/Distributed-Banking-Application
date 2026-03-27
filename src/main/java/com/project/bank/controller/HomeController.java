package com.project.bank.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.bank.model.LoginModel;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	
	
	
	@GetMapping("/")
	public ModelAndView home(Model model , HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
	    System.out.println("Session User: " + request.getSession().getAttribute("loggedUser"));
	    
	    LoginModel username = (LoginModel) session.getAttribute("loggedUser");
	    System.out.println("loggedUser:" + username);
	    if(username != null)
	    {
	    	mv.setViewName("Home.jsp");
	    }
	    else
	    {
	    	mv.setViewName("Login.jsp");
	    }
	    
	    return mv;
	}
   
	
}
