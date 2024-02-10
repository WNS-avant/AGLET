package com.aglet.priority.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aglet.priority.entity.Student;
import com.aglet.priority.repository.StRepository;

@Service
public class StServices implements Services
{

	@Autowired
	StRepository srep;

	@Override
	public String create(Student s) {
		srep.save(s);
		return "all Set";
	}

	@Override
	public boolean checkId(String roll) {
		if(srep.findByRoll(roll)==null)
		{
			return false;
		}
		
		else {return true;}
	}

	@Override
	public boolean takeorder(String roll) 
	{
		Student s = srep.findByRoll(roll);
		String userRoll = s.getRoll();
		if(userRoll.equals(roll))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public List<Student> display() {
		List<Student> st = srep.findAll();
		return st;
	}

	@Override
	public String getUserOrder(String roll) {
		String s = srep.findRollByRoll(roll);
		return s;
	}

	@Override
	public int addAmount(int price, String roll) {
	    Student s = srep.findByRoll(roll);
		    if(s.getCurrent()==0)
	    {
	    	return 0;
	    }
	    int currentDeposit = s.getCurrent(); 

	    if (currentDeposit != 0 && currentDeposit < price ) 
	    {
	        System.out.println("Insufficient balance"+currentDeposit);
	        return 1;
	    } 
	    else if (currentDeposit > price) 
	    {
	        currentDeposit = currentDeposit - price;
	        s.setCurrent(currentDeposit);	       
	        srep.save(s);
	        return currentDeposit;
	    } 
	    else if (price == currentDeposit) {
	        s.setCurrent(0);
	        srep.save(s);
	        System.out.println("You have used up your balance");
	        return currentDeposit;
	    }
	
	    return currentDeposit;
	}

	@Override
	public String addDeposit(String roll, int amount) {
		    Student student = srep.findByRoll(roll);
		   int current = student.getCurrent();
		    if (student != null) {
		    	 student.setAmount(amount);
		         student.setCurrent(current+amount);
		         
		         int newTotal = student.getTotal() + amount;
		         System.out.println("New total: " + newTotal);
		         student.setTotal(newTotal);
		         
		         srep.save(student);
		    }
		    return "selectauser";
		    
		}


	@Override
	public void deleteStud(String stdRoll) {
		// TODO Auto-generated method stub
		Student s= srep.findByRoll(stdRoll);
				if(s!=null)
				{
					srep.delete(s);
				}
	}

}

	
