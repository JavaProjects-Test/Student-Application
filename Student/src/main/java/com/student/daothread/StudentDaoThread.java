package com.student.daothread;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

import com.student.model.Student;

public class StudentDaoThread extends Thread {

	Queue<Student> queue = new ArrayDeque<Student>();

	public StudentDaoThread() {
		System.out.println("List:");
	}

	public StudentDaoThread(Queue<Student> queue) {
		this.queue = queue;
	}

	public Connection getConnection() throws ClassNotFoundException,
			SQLException {

		Class.forName("com.mysql.jdbc.Driver");

		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3307/studentsdb", "root", "12345");

		return connection;
	}

	public boolean addNewStudent(Queue<Student> queue) {
		Connection connection;
		Student student = null;
		if (queue.size() == 1) {
			try {
				connection = getConnection();
				Iterator<Student> itr = queue.iterator();
				while (itr.hasNext()) {
					student = itr.next();

				}

				PreparedStatement preparedStatement = connection
						.prepareStatement("insert into student values(?,?,?,?)");

				preparedStatement.setString(1, student.getRollNumber());
				preparedStatement.setString(2, student.getFirstName());
				preparedStatement.setString(3, student.getLastName());
				preparedStatement.setString(4, student.getBranch());

				int numberOfRecords = preparedStatement.executeUpdate();
				if (numberOfRecords == 1) {
					return true;
				}

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;

	}

	public List<Student> printStudents() {
		List<Student> list = new ArrayList<Student>();
		Connection connection;
		try {
			connection = getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from student");
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Student objStudent = new Student();
				objStudent.setRollNumber(resultSet.getString(1));
				objStudent.setFirstName(resultSet.getString(2));
				objStudent.setLastName(resultSet.getString(3));
				objStudent.setBranch(resultSet.getString(4));
				list.add(objStudent);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

	public void run() {
		boolean status = addNewStudent(queue);
		if (status) {
			System.out.println("Added SuccessFully");
		} else {
			System.out.println("Not Added!!");
		}

	}

}
