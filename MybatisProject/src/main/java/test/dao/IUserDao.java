package test.dao;

import test.vo.User;

import java.util.ArrayList;

public interface IUserDao {

    ArrayList<User> findAllUsers();

    User findUserByName(String name);

    void insertUser(User user);

    void updateUser(User user);

    void deleteUserByName(String name);
}
