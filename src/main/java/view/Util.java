package view;

import model.Servicer;
import model.Ticket;
import model.User;

import java.util.List;

public class Util {
    public static String createSeparator(String sep, String start, String end, Integer size){
        StringBuilder sb = new StringBuilder();
        sb.append(start);
        sb.append(String.valueOf(sep).repeat(Math.max(0, size - 1)));
        sb.append(end);
        return sb.toString();
    }
    public static String createSeparatorInternal(String sep, String middle, Integer middleSize, Integer repeat){
        StringBuilder sb = new StringBuilder();
        StringBuilder internalSb = new StringBuilder();
        internalSb.append(middle);
        internalSb.append(String.valueOf(sep).repeat(Math.max(0, middleSize)));
        sb.append(internalSb.toString().repeat(Math.max(0, repeat)));
        sb.append(middle);
        return sb.toString();
    }

    public static void printTickets(List<Ticket> tickets){
        System.out.println(createSeparator("-", "+", "+", 300 + 6));
        System.out.printf(
                "|%-50s|%50s|%50s|%50s|%50s|%50s|%n",
                "ID", "Created At", "Title", "Desc", "Cust", "Servicer"
        );


        tickets.forEach((x) -> {
            System.out.println(createSeparatorInternal("-", "+", 50, 6));
            System.out.println(x);
        });

        System.out.println(createSeparator("-", "+", "+", 300 + 6));

    }

    public static void printUser(List<? extends User> users){
        System.out.println(createSeparator("-", "+", "+", 42));
        System.out.printf(
                "|%-20s|%20s|%n",
                "ID", "Name"
        );

        users.forEach((x) -> {
            System.out.println(createSeparatorInternal("-", "+", 20, 2));
            System.out.println(x);
        });

        System.out.println(createSeparator("-", "+", "+", 42));
    }
}
