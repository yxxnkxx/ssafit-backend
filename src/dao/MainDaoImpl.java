package dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.Review;
import dto.Video;

public class MainDaoImpl implements MainDao {

	private List<Video> list;
	private static Map<String, List<Review>> reviews = new HashMap<>();
	private static MainDaoImpl instance;
	private static Map<String, Integer> reviewSeq = new HashMap<>();

	private MainDaoImpl() {
		list = new ArrayList<Video>();
		list.add(new Video("전신 다이어트 최고의 운동 [칼소폭 찐 핵핵매운맛]", "전신", "gMaB-fG4u4g", "ThankyouBUBU", 0));
		list.add(new Video("하루 15분! 전신 칼로리 불태우는 다이어트 운동", "전신", "swRNeYw1JkY", "ThankyouBUBU", 0));
		list.add(new Video("상체 다이어트 최고의 운동 BEST [팔뚝살/겨드랑이살/등살/가슴어깨라인]", "상체", "54tTYO-vU2E", "ThankyouBUBU", 0));
		list.add(new Video("상체비만 다이어트 최고의 운동 [상체 핵매운맛]", "상체", "QqqZH3j_vH0", "ThankyouBUBU", 0));
		list.add(new Video("하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]", "하체", "tzN6ypk6Sps", "김강민", 0));
		list.add(new Video("저는 하체 식주의자 입니다", "하체", "u5OgcZdNbMo", "GYM종국", 0));
		list.add(new Video("11자복근 복부 최고의 운동 [복근 핵매운맛]", "복부", "PjGcOP-TQPE", "ThankyouBUBU", 0));
		list.add(new Video("(Sub)누워서하는 5분 복부운동!! 효과보장! (매일 2주만 해보세요!)", "복부", "7TLk7pscICk", "SomiFit", 0));

		for (int i = 0; i < list.size(); i++) {
			reviews.put(list.get(i).getYoutubeId(), new ArrayList<Review>());
			reviewSeq.put(list.get(i).getYoutubeId(), 0);
		}

	}

	public static MainDao getInstance() {
		if (instance == null)
			instance = new MainDaoImpl();
		return instance;
	}

	@Override
	public List<Video> selectInterestViewFitVideo() {
		List<Video> interestList = new ArrayList<>();
		Collections.sort(list, new Comparator<Video>() {

			@Override
			public int compare(Video o1, Video o2) {
				return o2.getViewCnt() - o1.getViewCnt();
			} // 내림차순정렬
		});

		for (int i = 0; i < 3; i++) {
			interestList.add(list.get(i));
		}

		return interestList;
	}

	@Override
	public List<Video> selectPartfitVideo(String partname) {
		List<Video> partList = new ArrayList<>();

		if (!partname.equals(null)) {
			for (int i = 0; i < list.size(); i++)
				if (list.get(i).getFitPartName().equals(partname))
					partList.add(list.get(i));

		}
		return partList;
	}

	@Override
	public List<Review> selectReviewByYoutubeId(String youtubeId) {
		return reviews.get(youtubeId);
	}

	@Override
	public void addReview(Review review) {
		List<Review> temp = reviews.get(review.getYoutubeId());

		temp.add(review);
		reviews.put(review.getYoutubeId(), temp);
	}

	@Override
	public void removeReview(String youtubeId, int reviewId) {
		List<Review> temp = reviews.get(youtubeId);
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getReviewId() == reviewId) {
				temp.remove(i);
			}
		}
		reviews.put(youtubeId, temp);
	}

	@Override
	public Video selectVideoByYoutubeId(String youtubeId) {
		Video video = null;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getYoutubeId().equals(youtubeId))
				video = list.get(i);
		}
		return video;
	}

	@Override
	public List<Video> selectAllVideo() {
		return list;
	}

	@Override
	public int selectReviewSeq(String youtubeId) {
		int nowCnt = reviewSeq.get(youtubeId);
		reviewSeq.put(youtubeId, ++nowCnt);
		return nowCnt;
	}

}
