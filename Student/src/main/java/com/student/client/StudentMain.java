package com.student.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

import com.student.model.Student;
import com.student.service.IStudentService;
import com.student.service.StudentServiceImpl;

public class StudentMain {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Student objectStudent = new Student();
		IStudentService objectService = new StudentServiceImpl();
		while (true) {
			System.out.println("Enter Your Choice: ");
			System.out.println("1. Add new Student");
			System.out.println("2. Print all Students");

			try {
				switch (Integer.parseInt(br.readLine())) {

				case 1: {
					System.out.println("Enter Roll Number: ");
					objectStudent.setRollNumber(br.readLine());
					System.out.println("Enter First Name: ");
					objectStudent.setFirstName(br.readLine());
					System.out.println("Enter Last Name: ");
					objectStudent.setLastName(br.readLine());
					System.out.println("Enter Branch: ");
					objectStudent.setBranch(br.readLine());

					Queue<Student> queue = new ArrayDeque<Student>();
					queue.add(objectStudent);

					objectService.addNewStudent(queue);
					break;
				}

				case 2: {
					List<Student> list = objectService.printStudent();
					Iterator<Student> itr = list.iterator();
					while (itr.hasNext()) {

						Student objStudent = itr.next();
						System.out.println("Roll Number: "
								+ objStudent.getRollNumber());
						System.out.println("First Name: "
								+ objStudent.getFirstName());
						System.out.println("Last Name: "
								+ objStudent.getLastName());
						System.out
								.println("Branch : " + objStudent.getBranch());
						System.out
								.println("---------------------------------------------");
					}
					break;
				}
				default: {
					System.out.println("Wrong Choice!!");
				}

				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
