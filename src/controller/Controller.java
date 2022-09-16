package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MainDao;
import dao.MainDaoImpl;
import dto.Review;
import dto.Video;

@WebServlet("/main")
public class Controller extends HttpServlet {
	private MainDao mainDao = MainDaoImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		process(request, response);

	}
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("interestList", mainDao.selectInterestViewFitVideo());
		String action = request.getParameter("action");
		if (action != null) {
			switch (action) {
			case "select":
				selectPartList(request, response);
				break;
			case "list":
				doList(request, response);
				break;
			case "write":
				doWrite(request, response);
				break;
			case "detail":
				doDetail(request, response);
				break;
			}
		} else {
		request.getRequestDispatcher("/main.jsp").forward(request, response);			
		}

	}

	private void doDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reviewId = Integer.parseInt(request.getParameter("reviewId"));
		String youtubeId = request.getParameter("youtubeId");
		List<Review> reviews = mainDao.selectReviewByYoutubeId(youtubeId);
		for (Review r : reviews) {
			if (r.getReviewId() == reviewId) {
				request.setAttribute("review", r);				
				request.getRequestDispatcher("detail.jsp").forward(request, response);
				return;
			}
		}
	}


	private void selectPartList(HttpServletRequest request, HttpServletResponse response) {
		String part = request.getParameter("part");

		request.setAttribute("partList", mainDao.selectPartfitVideo(part));
	}

	private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String youtubeId = request.getParameter("youtubeId");
		
		request.setAttribute("youtubeId", youtubeId);

		// 조회수 1 증가
		List<Video> videoList = mainDao.selectAllVideo();
		for (int i = 0; i < videoList.size(); i++) {
			if (videoList.get(i).getYoutubeId().equals(youtubeId)) {
				videoList.get(i).setViewCnt(videoList.get(i).getViewCnt() + 1);
			}
		}


		List<Review> reviewList = mainDao.selectReviewByYoutubeId(youtubeId);

		request.setAttribute("reviewList", reviewList);

		RequestDispatcher disp = request.getRequestDispatcher("/list.jsp");
		disp.forward(request, response);
	}

	private void doWrite(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String youtubeId = request.getParameter("youtubeId");
		
		Video video = MainDaoImpl.getInstance().selectVideoByYoutubeId(youtubeId);
		
		String title = request.getParameter("title");
//		int reviewId = Integer.parseInt(request.getParameter("reviewId"));
//		int viewCnt = Integer.parseInt(request.getParameter("viewCnt"));
		String writer = "ssafy";
		String content = request.getParameter("content");

//		SimpleDateFormat formatter = new SimpleDateFormat();
//
//		Date regDate = null;
//		try {
//			regDate = formatter.parse(request.getParameter("regDate"));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		Review review = new Review(0, title, content, 0, new Date(), writer, youtubeId);
		System.out.println(title + " " + content);
		
		mainDao.addReview(review);

		RequestDispatcher disp = request.getRequestDispatcher("/main?action=list");
		disp.forward(request, response);

	}
}
