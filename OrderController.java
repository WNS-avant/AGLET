package com.aglet.priority.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aglet.priority.entity.Faculty;
import com.aglet.priority.entity.Student;
import com.aglet.priority.services.FcServices;
import com.aglet.priority.services.StServices;

@Controller
public class OrderController 
{
	@Autowired
	StServices stserv;
	
	@Autowired
	FcServices fcserv;
	
	@GetMapping("/map-entry")
	public String addStud()
	{
		return "studententry";
	}
	
	@GetMapping("/map-facentry")
	public String addfaculty()
	{
		return "facultyentry";
	}
	
	@GetMapping("/map-index")
	public String returnToMain()
	{
		return "index";
	}
	
	@GetMapping("/map-topup")
	public String topup()
	{
		
		return "newDeposit";
	}
	
	@GetMapping("/map-display")
	public String mapDisplay(Model model) {
	    List<Student> studList = stserv.display();
	    List<Faculty> facList = fcserv.displayfac();
	    model.addAttribute("studList", studList);
	    model.addAttribute("facList", facList);
	    return "adminpage";   
	}
	
	@PostMapping("/map-delete")
	public String mapDelete(Model model,@ModelAttribute Student s, @ModelAttribute Faculty f,@RequestParam String roll,@RequestParam String name, String deletedUserType)
	{
		String stdRoll = stserv.getUserOrder(s.getRoll());
	    String facRoll = fcserv.getUserFaculty(f.getRoll());
	    if(roll.equals(stdRoll))
	    {
	    	deletedUserType = "Student";
	    	stserv.deleteStud(stdRoll);
	        System.out.println("Student deleted");
	        model.addAttribute("deletedUserName", name);
	        model.addAttribute("deletedUserRoll", roll);
	        model.addAttribute("deletedUserType", deletedUserType);
	    }
	    else if(roll.equals(facRoll))
	    {
	    	deletedUserType = "Faculty";
	    	fcserv.deleteFac(facRoll);
	        System.out.println("Faculty deleted");
	        model.addAttribute("deletedUserName", name);
	        model.addAttribute("deletedUserRoll", roll);
	        model.addAttribute("deletedUserType", deletedUserType);
	    }
		return "delUser";
	}
	
	@PostMapping("/placeorder")
	public String userOrder(@ModelAttribute Student s, @ModelAttribute Faculty f,
	                        @ModelAttribute ("user") String user, @RequestParam String roll, @RequestParam int price) 
	{
		 System.out.println("User: " + user);
		    System.out.println("Roll: " + roll);

	    String stdRoll = stserv.getUserOrder(s.getRoll());
	    String facRoll = fcserv.getUserFaculty(f.getRoll());

	    if ("Student".equals(user) && roll.equals(stdRoll)) 
	    {
	    	boolean credential = stserv.takeorder(roll);
			if(credential == true)	
			{
			int value = stserv.addAmount(price,roll);
			System.out.println("value" +value);
				if(value==1)
				{
					return "topup";
				}
				else if(value==0)
				{
					return "balancenil";
				}
			
				else
				return "orderplaced";
				
			}
			else
			{
				return "usernotexist";
			}
		}
		
	    
	    else if("Faculty".equals(user) && roll.equals(facRoll))
	    {

			boolean credential = fcserv.factakeorder(price, roll);
			if(credential == true)	
			{
				int value = fcserv.addAmount(price,roll);
				System.out.println("value" +value);
				if(value==1)
				{
					return "topup";
				}
				if(value==0)
				{
					return "balancenil";
				}
			
				else
				return "orderplaced";
				
			}
			else
			{
				return "usernotexist";
			}
	    }
	   
	     return "selectauser";
	   
	}
	
	@PostMapping("/add-deposit")
	public String addDeposit(@ModelAttribute Student s, @ModelAttribute Faculty f,
            Model model,@RequestParam("user") String user,
	                         @RequestParam("roll") String roll,
	                         @RequestParam("amount") int amount) 
	{
	    String stdRoll = stserv.getUserOrder(s.getRoll());
	    String facRoll = fcserv.getUserFaculty(f.getRoll());
		 if ("Student".equals(user) && roll.equals(stdRoll)) 
		 {
	            stserv.addDeposit(roll, amount);
	            System.out.println("deposit added");
	        } 
		 else if ("Faculty".equals(user) && roll.equals(facRoll)) 
	        {
	            fcserv.addDeposit(roll, amount);
	            System.out.println("deposit added");
	        }
		 else {
			 return"usernotexist";
		 }
		 
	        model.addAttribute("amount", amount);
	        model.addAttribute("roll", roll);
	        
	        return "depositAdded";
	}
}
