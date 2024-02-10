package com.aglet.priority.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aglet.priority.entity.Faculty;


public interface FcRepository extends JpaRepository<Faculty,Integer>
{
	public Faculty findByRoll(String roll);

	@Query("SELECT f.roll FROM Faculty f WHERE f.roll = :roll")
    String findRollByRoll(@Param("roll") String roll);
   

}


