package com.aglet.priority.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aglet.priority.entity.Faculty;
import com.aglet.priority.repository.FcRepository;

@Service
public class FcServices implements Services_fc{

	@Autowired
	FcRepository frep;
	
	@Override
	public String create(Faculty fac) {
		frep.save(fac);
		return "done";
	}

	@Override
	public boolean checkIdFac(String roll) {
		if(frep.findByRoll(roll)==null)
		{
			return false;
		}
		
		else {return true;}
	}

	@Override
	public boolean factakeorder(int price, String roll) {
	  Faculty fac = frep.findByRoll(roll);
	  String userRoll = fac.getRoll();
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
	public List<Faculty> displayfac() {
		List<Faculty> fc = frep.findAll();
		return fc;
	}

	@Override
	public String getUserFaculty(String roll) {
		String f = frep.findRollByRoll(roll);
		return f;
	}

	@Override
	public int addAmount(int price, String roll) {
	    Faculty f = frep.findByRoll(roll);
		    if(f.getCurrent()==0)
	    {
	    	return 0;
	    }
	    int currentDeposit = f.getCurrent(); 

	    if (currentDeposit != 0 && currentDeposit < price ) 
	    {
	        System.out.println("Insufficient balance"+currentDeposit);
	        return 1;
	    } 
	    else if (currentDeposit > price) 
	    {
	        currentDeposit = currentDeposit - price;
	        f.setCurrent(currentDeposit);	       
	        frep.save(f);
	        return currentDeposit;
	    } 
	    else if (price == currentDeposit) {
	    	f.setCurrent(0);
	        frep.save(f);
	        System.out.println("You have used up your balance");
	        return currentDeposit;
	    }
	
	    return currentDeposit;
	}

	public String addDeposit(String facRoll, int amount) {
		 Faculty f = frep.findByRoll(facRoll);
		 int current = f.getCurrent();
		 if (f != null) {
		        f.setCurrent(current+amount);
		        f.setAmount(amount);
		        int newTotal = f.getTotal() + amount;
		        System.out.println("New total: " + newTotal);
		        f.setTotal(newTotal);
		        frep.save(f);
		    } 
		  return "selectauser";
		
		}

	@Override
	public void deleteFac(String facRoll) {
		Faculty f = frep.findByRoll(facRoll);
		if(f!=null)
		{
			frep.delete(f);
		}
	}
		
	
	
}
