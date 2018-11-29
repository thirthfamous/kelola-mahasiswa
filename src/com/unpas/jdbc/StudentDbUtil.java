package com.unpas.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


public class StudentDbUtil {
	private DataSource dataSource;
	
	public StudentDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Mahasiswa> getStudents() throws Exception {
		List<Mahasiswa> listMhs = new ArrayList<Mahasiswa>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "SELECT * FROM  student order by namabelakang";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				// retrieve data from result set row
				int id = myRs.getInt("id");
				String namadepan = myRs.getString("namadepan");
				String namabelakang = myRs.getString("namabelakang");
				String email = myRs.getString("email");
				
				//create new student object
				listMhs.add(new Mahasiswa(id, namadepan, namabelakang, email));
			}
			
			return listMhs;
		} finally {
			// close JDBC objects
			close(myConn, myRs, myStmt);
		}
	}
	
	public void addStudent(Mahasiswa mhs) {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet rs = null;
		try {
		// Koneksi db
		myConn = dataSource.getConnection();
			
		// insert sql
		String sql = "INSERT INTO student"
					 +"(namadepan, namabelakang, email)"
					 +"values (?, ?, ?)";
		myStmt = myConn.prepareStatement(sql);
				
		// set parameter untuk mahasiswa
		myStmt.setString(1, mhs.getNamadepan());
		myStmt.setString(2, mhs.getNamabelakang());
		myStmt.setString(3, mhs.getEmail());
		
		System.out.println(mhs +"hahahaha ");
		// eksekusi sql
		myStmt.executeUpdate();
		
		
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// tutup
			close(myConn, null, myStmt);
		}
	}

	private void close(Connection myConn, ResultSet myRs, Statement myStmt) {
		try{
			if (myConn != null) {
				myConn.close();
			}
			
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void updateStudent(Mahasiswa mhs) {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
		// Koneksi db
		myConn = dataSource.getConnection();
			
		// insert sql
		
		
		String sql = "UPDATE student SET namadepan=?, namabelakang=?, email=? where id=?";
		myStmt = myConn.prepareStatement(sql);
				
		// set parameter untuk mahasiswa
		myStmt.setString(1, mhs.getNamadepan());
		myStmt.setString(2, mhs.getNamabelakang());
		myStmt.setString(3, mhs.getEmail());
		myStmt.setInt(4, mhs.getId());
		
		// eksekusi sql
		myStmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// tutup
			close(myConn, null, myStmt);
		}
	}

	public Mahasiswa getStudent(String mhsId) {
		Mahasiswa mhs = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int id;
		
		try {
			// convert student id to int
			id = Integer.parseInt(mhsId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "SELECT * FROM student WHERE id=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, id);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if(myRs.next()) {
				String namadepan = myRs.getString("namadepan");
				String namabelakang = myRs.getString("namabelakang");
				String email = myRs.getString("email");
				
				mhs = new Mahasiswa(id, namadepan, namabelakang, email);
			} else {
				throw new Exception("ngga ada student id "+id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mhs;
	}

	public void deleteStudent(String id) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert mhs id to int
			int mhsId = Integer.parseInt(id);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete mhs
			String sql = "delete from student where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, mhsId);
			
			// execute sql statement
			myStmt.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
	}

	
}
