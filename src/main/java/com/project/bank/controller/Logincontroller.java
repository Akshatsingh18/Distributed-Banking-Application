package com.project.bank.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.project.bank.Interface.LoginInterface;
import com.project.bank.Interface.TransactionInterface;
import com.project.bank.model.AccountModel;
import com.project.bank.model.LoginModel;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class Logincontroller {
	
	 @Autowired
     LoginInterface repo;
	 
	 @Autowired
	 TransactionInterface AccountRepo;
	 

	 @GetMapping("/custom-login")
	 public String customLoginPage() {
		 System.out.println("Inside getCustomLogin");
	     return "Login.jsp"; 
	 }
	    
	    
	    @GetMapping("/register")
	    public String showRegisterPage() {
	        return "RegisterPage.jsp";
	    }


	
	    @PostMapping("/custom-login")
	    public ModelAndView login(
	            @RequestParam String Username,
	            @RequestParam String Password,
	            HttpServletResponse response,
	            HttpServletRequest request) 
	    {
	        ModelAndView mv = new ModelAndView();

	        List<LoginModel> logs = repo.findByusername(Username);

	        if (!logs.isEmpty()) {
	            LoginModel user = logs.get(0);

	            if (user.getPassword().equals(Password)) {
	                System.out.println("Login Successful");
	                System.out.println("Session User: " + request.getSession().getAttribute("loggedUser"));
	                request.getSession().setAttribute("loggedUser", user);
	                System.out.println("Session User: " + request.getSession().getAttribute("loggedUser"));
	                mv.setViewName("Home.jsp");
	            } else {
	                System.out.println("Wrong Password");
	                mv.setViewName("Login.jsp");
	                request.getSession().setAttribute("LoginStatus", "Invalid password");
	            }
	        } else {
	            System.out.println("User Not Found");
	            mv.setViewName("Login.jsp");
                request.getSession().setAttribute("LoginStatus", "User not found");
	        }

	        return mv;
	    }

	
	    @PostMapping("/register")
	    public String registerUser(@RequestParam String Fullname,
	                               @RequestParam String username,
	                               @RequestParam String password) {
	        LoginModel user = new LoginModel();
	        AccountModel account = new AccountModel();

	        // ✅ Correct field mappings
	        user.setFullname(Fullname);  
	        user.setUsername(username);
	        user.setPassword(password); 
	        
	        
	        account.setCustomerName(Fullname);
	        account.setBalance(0);

	        repo.save(user);
	        AccountRepo.save(account);
	        

	        System.out.println("✅ Account Created for: " + username);

	        // ✅ Redirect to login page with success message
	        return "redirect:/custom-login?registered=true";
	    }
	    
	    
	    @PostMapping("/log-out")
	    public String logout(HttpServletRequest request)
	    {
	    	request.getSession().removeAttribute("loggedUser");
	    	return "Login.jsp";
	    }

	

}
