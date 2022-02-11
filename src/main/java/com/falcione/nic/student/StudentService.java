package com.falcione.nic.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	public void addStudent(Student student) throws IllegalStateException {
		Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());

		if (studentByEmail.isPresent()) {
			throw new IllegalStateException("Email already taken.");
		}
		studentRepository.save(student);
	}

	public void deleteStudentById(Long id) throws IllegalStateException {
		if (!studentRepository.existsById(id)) {
			throw new IllegalStateException(String.format("Student with id %s does not exist!", id.toString()));
		}
		studentRepository.deleteById(id);
	}

	@Transactional
	public void updateStudent(Long id, String name, String email) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException(String.format("Student with id %s does not exist!", id)));

		if (name != null && !name.isEmpty() && !Objects.equals(student.getName(), name)) {
			student.setName(name);
		}

		if (email != null && !email.isEmpty() && !Objects.equals(student.getEmail(), email)) {
			student.setEmail(email);
		}
	}
}