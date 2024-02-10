package com.aglet.priority.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.aglet.priority.entity.Faculty;
import com.aglet.priority.services.Services_fc;

@Controller
public class FcController 
{
	@Autowired
	Services_fc fcserv;
	
	
	
	@PostMapping("/facsuccess")
	public String newFac(@ModelAttribute Faculty fac)
	{
		boolean status = fcserv.checkIdFac(fac.getRoll());
		if(status==false)
		{
			fcserv.create(fac);
			fcserv.addDeposit(fac.getRoll(),fac.getAmount());
			System.out.println("Faculty is added");
			return "faccreatesuccess";
		}
		else return "alreadyExists";
	}
}

