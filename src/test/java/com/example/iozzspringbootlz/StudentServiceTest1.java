package com.example.iozzspringbootlz;

import com.example.iozzspringbootlz.db.ScoreRepository;
import com.example.iozzspringbootlz.db.StudentRepository;
import org.junit.After;
import org.junit.Test;
import io.vavr.collection.List;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest1 {
	@Autowired
	private StudentRepository repository;

	@Autowired
	private ScoreRepository scoreRepository;
	@Test
	public void getEmptyList()
	{
		final StudentService service = new StudentService(repository, scoreRepository);
		List<Student> students = service.getStudents();
		assertTrue(students.isEmpty());
	}
	@Test
	public void addStudent()
	{
		final StudentService service = new StudentService(repository, scoreRepository);
		final Student created = service.addStudent(
				new NewStudent("Student1", "1-2", "IP"));
		assertNotNull(created);
	}

	@Test
	public void addStudentIsReturned()
	{
		final StudentService service = new StudentService(repository, scoreRepository);
		final NewStudent createdNew = new NewStudent("Student1", "1-2", "IP");
		final Student created = service.addStudent(createdNew);
		final List<Student> students = service.getStudents();
		assertTrue(createdNew.name==students.get(0).name);

	}
	@Test
	public void addStudentHasNewId()
	{
		final StudentService service = new StudentService(repository, scoreRepository);
		final NewStudent createdNew1 = new NewStudent("Student1", "1-2", "IP");
		final NewStudent createdNew2 = new NewStudent("Student2", "2-2", "IP");
		service.addStudent(createdNew1);
		service.addStudent(createdNew2);
		final List<Student> students = service.getStudents();
		assertEquals(2,students.size());
		assertNotEquals(students.get(0).id,students.get(1).id);
	}
	@After
	public void cleanAfterTest()
	{
		this.repository.deleteAll();
	}

}