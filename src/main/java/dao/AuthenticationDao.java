package dao;

import model.User;

import java.util.Optional;

public class AuthenticationDao implements IDummyData {
    public Optional<User> logIn(String userName, String password){
        return dm.usersData.values().stream()
                .filter(user -> user.getUserName().equals(userName) && user.getPassword().equals(password))
                .findFirst();
    }
}
