package com.transaction2.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.transaction2.DBConnection;
import com.transaction2.Transaction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionTest {
	@Test
	public void test_EX_1() {
		Transaction transaction = new Transaction();
		transaction.createTransaction();
		transaction.insertTransaction();
		transaction.displayResult();
		int total = countRecords();
		Assertions.assertEquals(4, total);

	}

	private int countRecords() {
		int total = 0;
		try (Connection connection = DBConnection.getConnection(); Statement stmt = connection.createStatement()) {
			String sql = "SELECT COUNT(*) AS total FROM Clients ";
			ResultSet count = stmt.executeQuery(sql);
			while (count.next()) {
				total = count.getInt("total");
			}
			return total;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}

}
