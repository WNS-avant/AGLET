package com.aglet.priority.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Faculty 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int sr_no;
	String roll;
	String name;
	int year;
	int price;
	int amount;
	int total;
	int current;
	public Faculty() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Faculty(int sr_no, String roll, String name, int year, int price, int amount, int total,
			int current) {
		super();
		this.sr_no = sr_no;
		this.roll = roll;
		this.name = name;
		this.year = year;
		this.price = price;
		this.amount = amount;
		this.total = total;
		this.current = current;
	}
	public int getId() {
		return sr_no;
	}
	public void setId(int sr_no) {
		this.sr_no = sr_no;
	}
	public String getRoll() {
		return roll;
	}
	public void setRoll(String roll) {
		this.roll = roll;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
	@Override
	public String toString() {
		return "Faculty [sr_no=" + sr_no + ", roll=" + roll + ", name=" + name + ", year=" + year 
				+ ", price=" + price + ", amount=" + amount +", total=" + total + ", current=" + current + "]";
	}
	
}