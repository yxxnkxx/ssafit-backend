package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReviewDaoImpl;
import dto.Review;

/**
 * Servlet implementation class ReviewController
 */
@WebServlet("/Review")
public class ReviewController extends HttpServlet {
	private static Map<String, List<Review>> reviews;
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		process(request, response);

	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		switch (action) {
		case "list":
			doList(request, response);
			break;
		case "write":
			doWrite(request, response);
			break;
		}
		
	}

	private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String youtubeId = request.getParameter("youtubeId");
		
		List<Review> temp = new ArrayList<>();
		for (int i = 0 ; i < reviews.get(youtubeId).size(); i++) {
			temp.add((Review) reviews.get(youtubeId));
		}
		
		request.setAttribute("reviewList", temp);
		RequestDispatcher disp = request.getRequestDispatcher("/list.jsp");
		disp.forward(request, response);
	}

	private void doWrite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String youtubeId = request.getParameter("youtubeId");
		String title = request.getParameter("title");
		int reviewId = Integer.parseInt(request.getParameter("reviewId"));
		int viewCnt = Integer.parseInt(request.getParameter("viewCnt"));
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");

		SimpleDateFormat formatter = new SimpleDateFormat();
		
		Date regDate = null;
		try {
			regDate = formatter.parse(request.getParameter("regDate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		Review review = new Review(reviewId, title, content, viewCnt, regDate, writer, youtubeId);
		ReviewDaoImpl.getInstance().addReview(review);
		
		RequestDispatcher disp = request.getRequestDispatcher("/list.jsp");
		disp.forward(request, response);
		
	}

}
