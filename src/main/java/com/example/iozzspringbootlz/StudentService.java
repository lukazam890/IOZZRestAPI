package com.example.iozzspringbootlz;

import io.vavr.collection.List;

public class StudentService {
	private List<Student> students = List.empty();

	List<Student> getStudents()
	{
		return students;
	}

	Student addStudent(final NewStudent student)
	{
		Student created = new Student(students.size()+1, student.name, student.number, student.grupa);
		students = students.prepend(created);
		return created;
	}

}
