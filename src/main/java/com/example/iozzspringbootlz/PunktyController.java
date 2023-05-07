package com.example.iozzspringbootlz;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/punkty")
public class PunktyController {

	private final StudentService service;

	public PunktyController(StudentService service) {
		this.service = service;
	}

	@RequestMapping(value = "/students", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	List<Student> getUsers()
	{
		return service.getStudents().asJava();
	}
	@RequestMapping(value = "/students", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	Student AddUser(@RequestBody NewStudent student)
	{
		return service.addStudent(student);
	}
}
