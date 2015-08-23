package com.student.service;

import java.util.List;
import java.util.Queue;

import com.student.model.Student;

public interface IStudentService {

	public void addNewStudent(Queue<Student> queue);

	public List<Student> printStudent();

}
