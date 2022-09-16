package dao;

import java.util.ArrayList;
import java.util.List;

import dto.User;

public class UserDaoImpl implements UserDao {

	private static UserDaoImpl instance = new UserDaoImpl();
	private int userSeq;
	private List<User> userList;

	private UserDaoImpl() {
		userList = new ArrayList<>();
	}

	public static UserDaoImpl getInstance() {
		return instance;
	}

	@Override
	public void signUp(User user) {
		user.setUserSeq(userSeq++);
		userList.add(user);

	}

	@Override
	public User getUser(String userId, String password) {
		for (int i = 0; i < userList.size(); i++) {
			User temp = userList.get(i);
			if (temp.getId().equals(userId) && temp.getPassword().equals(password)) {
				return temp;
			}
		}
		return null;
	}

	@Override
	public String getUserId(int userSeq) {
		for (int i = 0; i < userList.size(); i++) {
			User temp = userList.get(i);
			if (temp.getUserSeq() == userSeq) {
				return temp.getId();
			}
		}
		return null;
	}

	public List<User> getUserList() {
		return userList;
	}

}
