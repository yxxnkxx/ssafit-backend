package controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MainDao;
import dao.MainDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import dto.Review;
import dto.User;
import dto.Video;

@WebServlet("/main")
public class Controller extends HttpServlet {
	private MainDao mainDao = MainDaoImpl.getInstance();
	private static UserDao userDao = UserDaoImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		process(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("interestList", mainDao.selectInterestViewFitVideo());
		String action = request.getParameter("action");
		if (action != null) {
			switch (action) {

			// video
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
			case "update":
				doUpdate(request, response);
				break;
			case "remove":
				doRemove(request, response);
				break;

			// user
			case "signup":
				doSignup(request, response);
				break;
			case "login":
				doLogin(request, response);
				break;
			case "logout":
				doLogout(request, response);
				break;

			// 찜
			case "likeList":
				doLikeList(request, response);
				break;
			case "like":
				doLike(request, response);
				break;
			}
		} else {
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}

	}

	private void doRemove(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String youtubeId = request.getParameter("youtubeId");
		int reviewId = Integer.parseInt(request.getParameter("reviewId"));

		mainDao.removeReview(youtubeId, reviewId);
		response.sendRedirect(request.getContextPath() + "/main?action=list&youtubeId=" + youtubeId);

	}

	private void doUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String youtubeId = request.getParameter("youtubeId");
		int reviewId = Integer.parseInt(request.getParameter("reviewId"));

		List<Review> reviewList = mainDao.selectReviewByYoutubeId(youtubeId);
		request.getParameter("content");

		for (Review r : reviewList) {
			if (r.getReviewId() == reviewId) {
				r.setTitle(request.getParameter("title"));
				r.setContent(request.getParameter("content"));
				request.setAttribute("review", r);
				request.getRequestDispatcher("detail.jsp").forward(request, response);
				return;
			}
		}

	}

	private void doDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int reviewId = Integer.parseInt(request.getParameter("reviewId"));
		String youtubeId = request.getParameter("youtubeId");
		List<Review> reviewList = mainDao.selectReviewByYoutubeId(youtubeId);
		for (Review r : reviewList) {
			if (r.getReviewId() == reviewId) {

				int nowView = r.getViewCnt();
				r.setViewCnt(++nowView);
				request.setAttribute("review", r);

				request.getRequestDispatcher("detail.jsp").forward(request, response);
				return;
			}
		}
	}

	private void selectPartList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String part = request.getParameter("part");
		request.setAttribute("partList", mainDao.selectPartfitVideo(part));
		request.getRequestDispatcher("/main.jsp").forward(request, response);
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

		String title = request.getParameter("title");
		int reviewId = mainDao.selectReviewSeq(youtubeId);

		String writer = "";
		HttpSession session = request.getSession();
		if (session.getAttribute("loginUser") == null) {
			writer = "guest";
		} else {
			User user = (User) session.getAttribute("loginUser");
			writer = user.getId();
		}

		String content = request.getParameter("content");

		Review review = new Review(reviewId, title, content, 0, new Date(), writer, youtubeId);

		mainDao.addReview(review);

		RequestDispatcher disp = request.getRequestDispatcher("/main?action=list");
		disp.forward(request, response);

	}

	private void doLike(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String youtubeId = request.getParameter("youtubeId");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");

		Video video = mainDao.selectVideoByYoutubeId(youtubeId);
		System.out.println(user.getLikeList());
		for (int i = 0; i < user.getLikeList().size(); i++) {
			if (user.getLikeList().get(i).getYoutubeId().equals(youtubeId)) {
				request.setAttribute("msg", "이미 찜한 영상입니다.");
				request.getRequestDispatcher("user/fail.jsp").forward(request, response);
				return;
			}
		}

		user.getLikeList().add(video);
		response.sendRedirect(request.getContextPath() + "/main?action=likeList");

	}

	private void doLikeList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");

		List<Video> likeList = user.getLikeList();

		request.setAttribute("likeList", likeList);

		RequestDispatcher disp = request.getRequestDispatcher("/user/like.jsp");
		disp.forward(request, response);

	}

	private void doSignup(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String passwordCheck = request.getParameter("passwordCheck");
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");

		// id 중복체크
		boolean idCheck = true;
		List<User> userList = userDao.getUserList();
		for (User u : userList)
			if (userId.equals(u.getId()))
				idCheck = false;

		if (password.equals(passwordCheck) && idCheck) {
			User user = new User(0, userId, password, userName, email);
			userDao.signUp(user);

			// 로그인 처리
			// session에 loginUser를 저장.
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", userDao.getUser(userId, password));
			response.sendRedirect(request.getContextPath() + "/main");
			return;
			// 페이지 이동하는 2가지 방식
			// 1. redirect: 새로운 페이지를 요청(기존의 request, response가 아닌),단절
			// 2. forward: 현재 request, response를 가지고 요청
		} else {
			request.setAttribute("msg", "회원가입 실패");
			request.getRequestDispatcher("user/fail.jsp").forward(request, response);
		}

	}

	private void doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect(request.getContextPath() + "/main");

	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		User user = userDao.getUser(userId, password);
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
			response.sendRedirect(request.getContextPath() + "/main");
		} else {
			request.setAttribute("msg", "로그인 실패");
			request.getRequestDispatcher("user/fail.jsp").forward(request, response);
		}
	}

}
