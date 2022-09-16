package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MainDao;
import dao.MainDaoImpl;

@WebServlet("/main")
public class Controller extends HttpServlet {
	private MainDao mainDao = MainDaoImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("interestList", mainDao.selectInterestViewFitVideo());
		request.setAttribute("partList", mainDao.selectPartfitVideo());

		request.getRequestDispatcher("/main.jsp").forward(request, response);
	}
}
