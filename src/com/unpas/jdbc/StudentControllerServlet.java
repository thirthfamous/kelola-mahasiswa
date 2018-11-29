package com.unpas.jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDbUtil studentDbUtil;
	
	@Resource(name="jdbc/mahasiswa")
    private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		try {
			studentDbUtil = new StudentDbUtil(dataSource);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
		String command = req.getParameter("command");
		
		if(command == null) {
			command = "LIST";
		}
		
		switch (command) {
		case "LIST":
			listMahasiswa(req, resp);
			break;
		case "ADD":
			addMahasiswa(req, resp);
			break;
		case "UPDATE":
			updateMahasiswa(req, resp);
			break;
		case "LOAD":
			loadMahasiswa(req, resp);
			break;
		default:
			listMahasiswa(req, resp);
			break;
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}



	private void loadMahasiswa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// read student id
		String mhsId = req.getParameter("id");
		
		// get student from database
		Mahasiswa mhs = studentDbUtil.getStudent(mhsId);
		
		// place student in the request attribute
		req.setAttribute("mahasiswa", mhs);
		
		// forward ke jsp
		RequestDispatcher dispatcher = req.getRequestDispatcher("update-student-form.jsp");
		dispatcher.forward(req, resp);
		
	}



	private void updateMahasiswa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// read student info from form data
		String namadepan = req.getParameter("namadepan");
		String namabelakang = req.getParameter("namabelakang");
		String email = req.getParameter("email");
		String id = req.getParameter("id");
		
		// buat objek mahasiswa
		Mahasiswa mhs = new Mahasiswa(Integer.parseInt(id), namadepan, namabelakang, email);
		
		// masukkan ke db
		studentDbUtil.updateStudent(mhs);
		
		// kembali ke list-mahasiswa
		listMahasiswa(req, resp);
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String command = request.getParameter("command");
			
			if(command == null) {
				command = "LIST";
			}
			
			switch (command) {
			case "LIST":
				listMahasiswa(request, response);
				break;
			case "ADD":
				addMahasiswa(request, response);
				break;
			case "UPDATE":
				updateMahasiswa(request, response);
				break;
			case "LOAD":
				loadMahasiswa(request, response);
				break;
			case "DELETE":
				deleteMahasiswa(request, response);
				break;

	
			default:
				listMahasiswa(request, response);
				break;
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	private void deleteMahasiswa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		studentDbUtil.deleteStudent(id);
		
		// kembali ke list-mahasiswa
		listMahasiswa(request, response);
	}



	private void addMahasiswa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read student info from form data
		String namadepan = request.getParameter("namadepan");
		String namabelakang = request.getParameter("namabelakang");
		String email = request.getParameter("email");
		
		// buat objek mahasiswa
		Mahasiswa mhs = new Mahasiswa(namadepan, namabelakang, email);
		
		// masukkan ke db
		studentDbUtil.addStudent(mhs);
		
		// kembali ke list-mahasiswa
		listMahasiswa(request, response);
		
	}



	private void listMahasiswa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get mahasiswa dari studentdbutil
		List<Mahasiswa> listMhs = studentDbUtil.getStudents();
		
		// add mahasiswa ke request
		request.setAttribute("list_mahasiswa", listMhs);
		
		// forward ke JSP
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-mahasiswa.jsp");
		dispatcher.forward(request, response);
		
	}

}
