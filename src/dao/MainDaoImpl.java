package dao;

import java.util.ArrayList;
import java.util.List;

import dto.Video;

public class MainDaoImpl implements MainDao {
	
	private List<Video> list;
	private static MainDaoImpl instance;
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
		
	}
	
	public static MainDao getInstance() {
		if (instance==null) instance = new MainDaoImpl();
		return instance;
	}
	

	@Override
	public List<Video> selectInterestViewFitVideo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Video> selectPartfitVideo(String partname) {
		// TODO Auto-generated method stub
		return null;
	}

}
