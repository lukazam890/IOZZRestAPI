package com.example.iozzspringbootlz;

import com.example.iozzspringbootlz.db.StudentRepository;
import com.example.iozzspringbootlz.db.StudentRow;
import io.vavr.collection.List;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class StudentService {
	private final StudentRepository repository;

	List<Student> getStudents()
	{
		return List.ofAll(this.repository.findAll())
				.map(getStudentRowStudentFunction());
	}

	private static Function<StudentRow, Student> getStudentRowStudentFunction() {
		return db ->
				new Student(
						db.getId(),
						db.getName(),
						db.getNumber(),
						db.getGrupa()
				);
	}

	Student addStudent(final NewStudent student)
	{
		StudentRow created = this.repository.save(new StudentRow(student.name,student.number,student.grupa));
		return getStudentRowStudentFunction().apply(created);
	}

	public StudentService(StudentRepository repository) {
		this.repository = repository;
	}
}
