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

	@RequestMapping(value = "/students/{id}/number/{number}",
	method = RequestMethod.POST,
	produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Student setNumber(@PathVariable("id") long id, @PathVariable("number") String number)
	{
		return this.service.changeNumber(id, number).orElseThrow(
				() -> new NoStudentException(id));
	}

	@RequestMapping(value = "/students/{id}/scores", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public int addScore(@PathVariable("id") long id, @RequestBody Score score)
	{
		return this.service.addScore(id, score)
				.orElseThrow(
						()->new NoStudentException(id));
	}
}
