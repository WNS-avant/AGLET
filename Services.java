package com.aglet.priority.services;

import java.util.List;



import com.aglet.priority.entity.Student;

public interface Services {
	
	public String create(Student s);
	
	public boolean checkId(String roll);
	
	public boolean takeorder(String roll);

	public List<Student> display();
	
	public String getUserOrder(String roll);
	
	public int addAmount(int price,String roll);

	String addDeposit(String roll, int amount);

	void deleteStud(String stdRoll);
	

	
}	
