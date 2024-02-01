package com.transaction2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataManagement {

	private static int affectedRows = 0;

	public static int getAffectedRows() {
		return affectedRows;
	}

	public static void sqlCreateTable(Statement statement) throws SQLException {
		dropTable(statement);
		String sql = "CREATE TABLE IF NOT EXISTS Clients(id INT primary key auto_increment, name VARCHAR(60), "
				+ "lastName VARCHAR(60), age INT, address varchar(200), email varchar(100))";
		statement.executeUpdate(sql);
	}

	public static void sqlCreateWorkTable(Statement statement) throws SQLException {
		dropWorkTable(statement);
		String sql = "CREATE TABLE IF NOT EXISTS Work(work_id INT primary key auto_increment, name VARCHAR(60), "
				+ "description varchar(200))";
		statement.executeUpdate(sql);
	}

	public static void insertDataIntoTable(Statement statement) throws SQLException {
		try {
			String sql = "INSERT INTO Clients (name, lastName, age, address, email) VALUES ('John', 'Deep', 50, 'USA', 'john@deep.com'),"
					+ "('Maria','Madalena',40, 'Jerusalem', 'maria@god.com'),('Joseph','Meyer',26,'EUROPA','joseph@meyer.pt')"
					+ ",('Sali','Abdul',36,'EUROPA','sali@abd.com');";
			affectedRows = statement.executeUpdate(sql);

		} catch (SQLException s) {
			s.printStackTrace();
		}

	}

	public static void insertDataIntoWorkTable(Statement statement) throws SQLException {
		try {
			String sql = "INSERT INTO Work (name, description) VALUES ('Assembly table', 'Done'),"
					+ "('Assembly bed','20 minutes'),('Assembly chairs','2 hours'),('Assembly wardrobe','1 hour'),"
					+ "('Assembly kitchen', '5 hours');";
			statement.executeUpdate(sql);

		} catch (SQLException s) {
			s.printStackTrace();
		}

	}

	private static void dropTable(Statement statement) throws SQLException {
		String sql = "DROP TABLE IF EXISTS Clients";
		statement.executeUpdate(sql);
	}

	private static void dropWorkTable(Statement statement) throws SQLException {
		String sql = "DROP TABLE IF EXISTS Work";
		statement.executeUpdate(sql);
	}

	public static ArrayList<Person> getDataFromTable(Statement statement) throws SQLException {
		String sql = "select id, name, lastName, age, address,email from Clients";

		ResultSet rs = statement.executeQuery(sql);

		ArrayList<Person> peopleList = new ArrayList<Person>();
		while (rs.next()) {

			int id = rs.getInt("id");
			String name = rs.getString("name");
			String lastName = rs.getString("lastName");
			int age = rs.getInt("age");
			String address = rs.getString("address");
			String email = rs.getString("email");

			peopleList.add(new Person(id, name, lastName, age, address, email));

		}

		rs.close();
		return peopleList;
	}
}
