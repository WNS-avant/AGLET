package com.aglet.priority.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aglet.priority.entity.Student;


public interface StRepository extends JpaRepository<Student,Integer>
{
	public Student findByRoll(String roll);	

	@Query("SELECT s.roll FROM Student s WHERE s.roll = :roll")
    String findRollByRoll(@Param("roll") String roll);
	

}
