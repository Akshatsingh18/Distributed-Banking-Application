package com.project.bank.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.bank.Interface.LoginInterface;
import com.project.bank.Interface.StatementInterface;
import com.project.bank.Interface.TransactionInterface;
import com.project.bank.model.AccountModel;
import com.project.bank.model.LoginModel;
import com.project.bank.model.StatementModel;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class TransactionController {
	
	@Autowired
	LoginInterface repo;
	@Autowired
	TransactionInterface transactionRepo;
	
	@Autowired
	StatementInterface statementRepo;
	
    @GetMapping("/transaction")
    public String showTransactionPage() {
        return "Transaction.jsp";
    }
	
	@PostMapping("/transaction")
	public ModelAndView transaction(@RequestParam String CName,
                                    @RequestParam int Amount,
                                    HttpServletRequest request,
                                    Model model){
		ModelAndView mv = new ModelAndView();
		LoginModel loggedUser = (LoginModel) request.getSession().getAttribute("loggedUser");
		double balance = 0.0;
		if (loggedUser != null) {
		    String username = loggedUser.getUsername();
			List<LoginModel> logs = repo.findByusername(CName);
			List<AccountModel> transactionList = transactionRepo.findByCustomerName(CName);
	    	List<AccountModel> ClientName = transactionRepo.findByCustomerName(username);
	
			if(!logs.isEmpty())
			{
			    System.out.println("Customer Found");
		    	StatementModel stat = new StatementModel();
			    if ("a".equals(username) && "a".equals(CName))
			    { 
				    AccountModel customer = transactionList.get(0);
				    AccountModel client = ClientName.get(0);
				    System.out.println("adminAccount");	
				    Amount = Amount + customer.getBalance();
				    customer.setBalance(Amount);
				    transactionRepo.save(customer);
				    System.out.println("Money Added to account " + customer.getBalance());
				    balance = client.getBalance();
				    stat.setStatus('S');
				 }
			    else
			    {
				    AccountModel customer = transactionList.get(0);
				    AccountModel client = ClientName.get(0);
				    System.out.println("adminAccount");	
			        AccountModel account = new AccountModel();
			        if(Amount <= client.getBalance())
			        {
				        int CustomerCurrentBalance = Amount + customer.getBalance(); 
				        int ClientCurrentBalance = client.getBalance() - Amount;   
				        customer.setBalance(CustomerCurrentBalance);
					    balance = customer.getBalance(); 
				        client.setBalance(ClientCurrentBalance);
				        transactionRepo.save(customer);
				        transactionRepo.save(client);
				        System.out.println("Transaction is successful");
				        stat.setStatus('S');
			        }
			        else
			        {
			        	System.out.println("Not enough Money");
			        	stat.setStatus('E');
			        }
				    balance = client.getBalance(); 
			        account.setBalance(0);
			    }
			    System.out.println("Record starting to added");	   
	        	stat.setName(username);
	        	stat.setCustname(CName);
	        	stat.setAmount(Amount);
	        	LocalDate today = LocalDate.now();
	        	stat.setDate(today);
	        	Random ran = new Random();
	            int randomNum = ran.nextInt(100) + 1;
	        	String ref = today + String.valueOf(randomNum);;
	        	stat.setContract_ref_no(ref);
	        	statementRepo.save(stat);
			    System.out.println("Record added");	   
			}
			else
			{
			    AccountModel client = ClientName.get(0);
			    System.out.println("Customer Not Found");
			    balance = client.getBalance(); 
			    mv.setViewName("Transaction.jsp");
			}
		    AccountModel client = ClientName.get(0);
		    balance = client.getBalance(); 
	        mv.setViewName("Transaction.jsp"); 
		}
		
		mv.addObject("balance", balance);
		return mv;
	}
}
