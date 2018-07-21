package com.example.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedisWebController {

	@Autowired
	private StudentRepository studentRepository;

	@RequestMapping("/redisTest")
	public String redisTest() {
		// save
		Student student = new Student("Eng2015001", "John Doe", Student.Gender.MALE, 1);
		studentRepository.save(student);
		// find
		Student retrievedStudent = studentRepository.findById("Eng2015001").get();
		System.out.println(retrievedStudent.getName());
		// update
		retrievedStudent.setName("Richard Watson");
		studentRepository.save(student);
		// delete
		studentRepository.deleteById(student.getId());
		return "redisTest";
	}

}
