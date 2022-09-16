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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserId(int userSeq) {
		// TODO Auto-generated method stub
		return null;
	}

}
