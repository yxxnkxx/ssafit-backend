package dao;

import java.util.List;

import dto.Review;
import dto.Video;

public interface MainDao {

	public List<Video> selectInterestViewFitVideo();

	public List<Video> selectPartfitVideo(String partname);

	public List<Review> selectReviewByYoutubeId(String youtubeId);

	public Video selectVideoByYoutubeId(String youtubeId);

	public List<Video> selectAllVideo();

	public void addReview(Review review);

	public void removeReview(String youtubeId, int reviewId);
	
	public int selectReviewSeq(String youtubeId);
}
