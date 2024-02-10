package com.aglet.priority.services;

import java.util.List;

import com.aglet.priority.entity.Faculty;

public interface Services_fc 
{
public String create(Faculty fac);
	
	public boolean checkIdFac(String roll);
	
	public boolean factakeorder(int price, String roll);

	public List<Faculty> displayfac();
	
	public String getUserFaculty(String roll);

	public String addDeposit(String roll, int amount);

	int addAmount(int price, String roll);

	void deleteFac(String facRoll);
}

