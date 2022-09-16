package dao;

import dto.User;

public interface UserDao {

	public void signUp(User user);

	public User getUser(String userId, String password);

	public String getUserId(int userSeq);
}
