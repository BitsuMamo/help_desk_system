package dao;

import model.Customer;
import model.Manager;
import model.Servicer;
import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationDaoTest {

    @Test
    void logInWithValidCustomer() {
        AuthenticationDao authDao = new AuthenticationDao();
        Optional<User> user = authDao.logIn("bobsmith", "password3");

        assertTrue(user.isPresent());
        assertEquals(Customer.class, user.get().getClass());
    }

    @Test
    void logInWithValidManager() {
        AuthenticationDao authDao = new AuthenticationDao();
        Optional<User> user = authDao.logIn("chrislee", "password6");

        assertTrue(user.isPresent());
        assertEquals(Manager.class, user.get().getClass());
    }

    @Test
    void logInWithValidServicer() {
        AuthenticationDao authDao = new AuthenticationDao();
        Optional<User> user = authDao.logIn("marysmith", "password9");

        assertTrue(user.isPresent());
        assertEquals(Servicer.class, user.get().getClass());
    }

    @Test
    void logInWithInValidCreds() {
        AuthenticationDao authDao = new AuthenticationDao();
        Optional<User> user = authDao.logIn("hello", "world");

        assertFalse(user.isPresent());
    }
}