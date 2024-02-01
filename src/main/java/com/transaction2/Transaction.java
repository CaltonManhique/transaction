package com.transaction2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Transaction {

	public void createTransaction() {
		Connection connection = DBConnection.getConnection();
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {

			DataManagement.sqlCreateTable(stmt);
			DataManagement.sqlCreateWorkTable(stmt);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBConnection.closeConnection(connection);
		}

	}

	public void insertTransaction() {
		try (Connection connection = DBConnection.getConnection();
				Statement stmt = connection.createStatement();
			) {

			DataManagement.insertDataIntoTable(stmt);
			System.out.println("rows: " + DataManagement.getAffectedRows());
			
			if (DataManagement.getAffectedRows() >= 3) {
				DataManagement.insertDataIntoWorkTable(stmt);
				connection.setAutoCommit(true);
			} else {
				connection.rollback();
				System.out.println("rolling-back");
			}
			
			connection.setAutoCommit(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Person> readTransaction() {

		ArrayList<Person> people = new ArrayList<Person>();

		try (Connection connection = DBConnection.getConnection(); Statement stmt = connection.createStatement()) {
			people = DataManagement.getDataFromTable(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return people;
	}

	public void displayResult() {
		Transaction transaction = new Transaction();
		ArrayList<Person> result = transaction.readTransaction();

		result.forEach(System.out::println);
	}
}
