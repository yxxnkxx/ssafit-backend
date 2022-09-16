package dao;

import java.util.List;

import dto.Review;

public interface ReviewDao {
	public List<Review> selectReviewByYoutubeId(String youtubeId);
	public void addReview(Review review);
	public void removeReview(String youtubeId, int reviewId);
	
}
