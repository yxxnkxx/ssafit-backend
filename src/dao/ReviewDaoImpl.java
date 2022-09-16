package dao;

import java.util.List;
import java.util.Map;

import dto.Review;

public class ReviewDaoImpl implements ReviewDao {
	private static ReviewDaoImpl instance = new ReviewDaoImpl();
	private static Map<String, List<Review>> reviews;
	private int reviewId;
	
	private ReviewDaoImpl() {

	}
	
	public static ReviewDaoImpl getInstance() {
		return instance;
	}
	
	
	@Override
	public List<Review> selectReviewByYoutubeId(String youtubeId) {
		return reviews.get(youtubeId);
	}

	@Override
	public void addReview(Review review) {
		reviews.get(review.getYoutubeId()).add(review);
	}

	@Override
	public void removeReview(String youtubeId, int reviewId) {
		
	}


}
