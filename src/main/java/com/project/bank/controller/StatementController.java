package com.project.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.bank.model.AccountModel;
import com.project.bank.model.LoginModel;
import com.project.bank.model.StatementModel;

import jakarta.servlet.http.HttpServletRequest;

import com.project.bank.Interface.StatementInterface;


@Controller
public class StatementController {
	
	@Autowired
	StatementInterface statementRepo;
    
	
	@GetMapping("/statement")
	public String GetStatement()  
	{
		return "Statement.jsp";
	}
	
	
	@PostMapping("/statement")
	public ModelAndView Statement(HttpServletRequest request)  
	{
		ModelAndView mv = new ModelAndView();
		LoginModel loggedUser = (LoginModel) request.getSession().getAttribute("loggedUser");
	    String username = loggedUser.getUsername();
    	List<StatementModel> clientStatements = statementRepo.findByname(username);
    	for (StatementModel s : clientStatements) {
    	    System.out.println("Name: " + s.getName());
    	    System.out.println("Amount: " + s.getAmount());
    	    System.out.println("Date: " + s.getDate());
    	    System.out.println("Reference No: " + s.getContract_ref_no());
    	    System.out.println("------");
    	}
    	mv.addObject("statements", clientStatements);
    	mv.setViewName("Statement.jsp");
	    return mv;
	}
	
}
