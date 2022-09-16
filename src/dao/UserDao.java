package dao;

import java.util.List;

import dto.User;

public interface UserDao {

	public void signUp(User user);

	public User getUser(String userId, String password);

	public String getUserId(int userSeq);

	public List<User> getUserList();
}
