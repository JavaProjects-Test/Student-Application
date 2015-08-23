package com.student.service;

import java.util.Iterator;
import java.util.List;
import java.util.Queue;

import com.student.daothread.StudentDaoThread;
import com.student.model.Student;

public class StudentServiceImpl implements IStudentService {

	public void addNewStudent(Queue<Student> queue) {
		Iterator<Student> itr = queue.iterator();
		while (itr.hasNext()) {
			Student objStudent = itr.next();
			if (objStudent.getRollNumber().isEmpty()
					|| objStudent.getFirstName().isEmpty()
					|| objStudent.getLastName().isEmpty()
					|| objStudent.getBranch().isEmpty()) {
				System.out.println("Field can not be blank....");
			} else {
				StudentDaoThread objectThread = new StudentDaoThread(queue);
				objectThread.run();
			}
		}

	}

	public List<Student> printStudent() {
		StudentDaoThread objDaoThread = new StudentDaoThread();
		return objDaoThread.printStudents();
	}

}
