package dao;

import java.util.List;

import dto.Video;

public interface MainDao {

	public List<Video> selectInterestViewFitVideo();
	
	public List<Video> selectPartfitVideo();
}
