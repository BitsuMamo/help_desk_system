package controller;

import dao.AuthenticationDao;
import model.User;

import java.util.Optional;

public class AuthenticationController {
    AuthenticationDao authDao = new AuthenticationDao();
    public Optional<User> logIn(String userName, String password){
        return authDao.logIn(userName, password);
    }
}
