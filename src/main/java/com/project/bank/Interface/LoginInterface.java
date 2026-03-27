package com.project.bank.Interface;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.bank.model.LoginModel;

public interface LoginInterface extends JpaRepository<LoginModel,Integer>{
	List<LoginModel> findByusername(String username);
}
