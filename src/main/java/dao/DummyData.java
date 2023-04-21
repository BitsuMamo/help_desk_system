package dao;

import model.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DummyData {

    List<User> users;

    List<Ticket> tickets;

    HashMap<Integer, User> usersData = new HashMap<>();
    HashMap<Integer, Ticket> ticketData = new HashMap<>();


     DummyData(){
        users = List.of(
                new Customer(1, "Tanvir", "tanvir", "hello"),
                new Customer(2, "Suraphel", "sura", "123"),
                new Customer(3, "Amenssisa", "amen", "456"),
                new Manager(4, "Admin", "admin", "admin"),
                new Servicer(5, "Kalkidan", "kal", "000"),
                new Servicer(6, "Santa", "clause", "001"),
                new Servicer(7, "Hell", "hel", "002")
        );

        tickets = List.of(
                new Ticket(1, "help", "me", (Customer) users.get(0)),
                new Ticket(2, "help", "me", (Customer) users.get(1)),
                new Ticket(3, "help", "me", (Customer) users.get(2)),
                new Ticket(4, "help", "me", (Customer) users.get(0)),
                new Ticket(1, "help", "me", (Customer) users.get(2))
        );

        users.forEach(user -> usersData.put(user.getId(), user));
        tickets.forEach(ticket -> ticketData.put(ticket.getId(), ticket));

    }

//    static List<>

}
