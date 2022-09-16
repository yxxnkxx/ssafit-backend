package dto;

import java.util.ArrayList;
import java.util.List;

public class User {

	private int userSeq;
	private String id;
	private String password;
	private String name;
	private String email;
	private List<Video> likeList;

	public User(int userSeq, String id, String password, String name, String email) {
		super();
		this.userSeq = userSeq;
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		likeList = new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}

	public List<Video> getLikeList() {
		return likeList;
	}

	public void setLikeList(List<Video> likeList) {
		this.likeList = likeList;
	}

}
