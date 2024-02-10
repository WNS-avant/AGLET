package com.aglet.priority.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.aglet.priority.entity.Student;
import com.aglet.priority.services.Services;


@Controller
public class StController {

	@Autowired
	Services stserv;
	
	@PostMapping("/success")
	public String newStud(@ModelAttribute Student s)
	{
		boolean status = stserv.checkId(s.getRoll());
		if(status==false)
		{
			stserv.create(s);
			stserv.addDeposit(s.getRoll(),s.getAmount());
			
			System.out.println("student is added");
			return "createsuccess";
		}
		else return "alreadyExists";	
	}
}
