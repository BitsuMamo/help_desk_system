package view;

import controller.AuthenticationController;
import model.Customer;
import model.Manager;
import model.Servicer;
import model.User;

import java.util.Optional;
import java.util.Scanner;

public class AuthenticationView {
    private final Scanner keyboard = new Scanner(System.in);
    private final AuthenticationController authController = new AuthenticationController();
    public void showAuthScreen(){

        Optional<User> user = Optional.empty();
        while(user.isEmpty()){
            System.out.println("+-----+");
            System.out.println("|Login|");
            System.out.println("+-----+--------------------+");
            System.out.printf("|%-10s>    ", "UserName");
            String userName = keyboard.nextLine().trim();
            System.out.println("+--------------------------+");
            System.out.printf("|%-10s>    ", "Password");
            String password = keyboard.nextLine().trim();
            System.out.println("+--------------------------+");


            user = authController.logIn(userName, password);
            if(user.isEmpty()) {
                System.out.println("+-----------------+");
                System.out.println("|Wrong Credentials|");
                System.out.println("+-----------------+");
            }
        }

        switch (user.get().getUserType()) {
            case "MANAGER" -> new ManagerView((Manager) user.get()).showManagerView();
            case "CUSTOMER" -> new CustomerView((Customer) user.get()).showCustomerView();
            case "SERVICER" -> new ServicerView((Servicer) user.get()).showServicerView();
        }


    }
}
