package view;

import model.Ticket;
import model.User;

import java.util.List;
import java.util.Scanner;

public class Util {
    public static String createSeparator(String sep, String start, String end, Integer size){
        return start +
                String.valueOf(sep).repeat(Math.max(0, size - 1)) +
                end;
    }
    public static String createSeparatorInternal(String sep, String middle, Integer middleSize, Integer repeat){
        StringBuilder sb = new StringBuilder();
        String internalSb = middle +
                String.valueOf(sep).repeat(Math.max(0, middleSize));
        sb.append(internalSb.repeat(Math.max(0, repeat)));
        sb.append(middle);
        return sb.toString();
    }

    public static void printTickets(List<Ticket> tickets){
        System.out.println(createSeparator("-", "+", "+", 240 + 6));
        System.out.printf(
                "|%40s|%40s|%-40s|%-40s|%-40s|%-40s|%n",
                "ID", "Created At", "Title", "Desc", "Cust", "Servicer"
        );


        tickets.forEach((x) -> {
            System.out.println(createSeparatorInternal("-", "+", 40, 6));
            System.out.println(x);
        });

        System.out.println(createSeparator("-", "+", "+", 240 + 6));

    }

    public static void printUser(List<? extends User> users){
        System.out.println(createSeparator("-", "+", "+", 63));
        System.out.printf(
                "|%20s|%-20s|%-20s|%n",
                "ID", "Name", "Type"
        );

        users.forEach((x) -> {
            System.out.println(createSeparatorInternal("-", "+", 20, 3));
            System.out.println(x);
        });

        System.out.println(createSeparator("-", "+", "+", 63));
    }

    public static void printGreeting(User user){
        System.out.println(Util.createSeparatorInternal("-", "+", 20, 3));
        System.out.printf("|%20s|%20s|%20s|%n","WELCOME" ,user.getUserType(), user.getName());
        System.out.println(Util.createSeparatorInternal("-", "+", 20, 3));
        System.out.println();
    }

    public static void printMenu(String... menuItems){
        System.out.println("+-----+");
        System.out.println("|MENU |");
        System.out.println("+-----+-------------------------+");
        for(int i = 0; i < menuItems.length; i++){
            System.out.printf("|%-5d|%-25s|%n", i + 1, menuItems[i]);
        }
        System.out.println("+-----+-------------------------+");

        System.out.printf("|%-5s|", ">");
    }

    public static int getId(Scanner keyboard, String type){
        System.out.println("+---------------------------------");
        System.out.printf("|%20s>    ","Enter "+ type +" ID: ");
        int id = keyboard.nextInt();
        System.out.println("+---------------------------------");
        return id;
    }

}
