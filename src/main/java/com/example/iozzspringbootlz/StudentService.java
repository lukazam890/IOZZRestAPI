package com.example.iozzspringbootlz;

import com.example.iozzspringbootlz.db.ScoreRepository;
import com.example.iozzspringbootlz.db.ScoreRow;
import com.example.iozzspringbootlz.db.StudentRepository;
import com.example.iozzspringbootlz.db.StudentRow;
import io.vavr.collection.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StudentService {
	private final StudentRepository studentRepository;
	private final ScoreRepository scoreRepository;

	List<Student> getStudents()
	{
		return List.ofAll(this.studentRepository.findAll())
				.map(StudentRow::toStudent);
	}


	Student addStudent(final NewStudent student)
	{
		StudentRow created = this.studentRepository.save(new StudentRow(student.name,student.number,student.grupa));
		return created.toStudent();
	}

	public StudentService(StudentRepository repository, ScoreRepository scoreRepository) {
		this.studentRepository = repository;
		this.scoreRepository = scoreRepository;
	}

	@Transactional
	public Optional<Student> changeNumber (long studentId, String newNumber)
	{
		final Optional<StudentRow> student=this.studentRepository.findById(studentId);
		return student.map(c -> {
			c.setNumber(newNumber);
			return c.toStudent();
		});
	}

	@Transactional
	public Optional<Integer> addScore(final long studentId, final Score score) {
		final Optional<StudentRow> student =
				this.studentRepository.findById(studentId);
		return student.map(c->{
			int existingScore=List.ofAll(c.getScores())
					.foldLeft(0,(p,s)->p+s.getScore());
			final ScoreRow newScore=new ScoreRow(score.score,score.comment,c);
			this.scoreRepository.save(newScore);
			return existingScore+score.score;});}
}
