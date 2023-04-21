package view;

import controller.CustomerController;
import controller.ManagerController;
import controller.ServicerController;
import controller.TicketController;
import model.Manager;
import model.Ticket;

import java.util.List;
import java.util.Scanner;

public class ManagerView {
    private final Scanner keyboard = new Scanner(System.in);
    private final Manager manager;

    private final TicketController ticketController = new TicketController();
    private final CustomerController customerController = new CustomerController();
    private final ServicerController servicerController = new ServicerController();
    ManagerView(Manager manager){
        this.manager = manager;
    }
    public void showManagerView(){
        int input;
        do{
            System.out.println("1. Create User");
            System.out.println("2. Create Servicer");
            System.out.println("3. View Tickets");
            System.out.println("4. Assign Ticket to Servicer");
            System.out.println("5. View Customers");
            System.out.println("6. Exit");
            System.out.print(">");
            input = keyboard.nextInt();

            switch (input) {
                case 1 -> createUser();
                case 2 -> createServicer();
                case 3 -> viewTickets();
                case 4 -> assignTicket();
                case 5 -> viewCustomers();
                case 6 -> new AuthenticationView().showAuthScreen();
            }

        }while(input != 6);
    }

    private void viewCustomers() {
        customerController.getAll().forEach(System.out::println);
    }

    //TODO Finish
    private void createServicer() {
    }

    //TODO Finish
    private void createUser() {

    }

    public void viewTickets(){
        List<Ticket> tickets = ticketController.getAll();
        Util.printTickets(tickets);
    }

    public void assignTicket(){
        viewTickets();
        System.out.println("Enter Ticket ID: ");
        int id = keyboard.nextInt();

        Util.printServicers(servicerController.getAll());
        System.out.println("Enter Servicer ID");
        int id2 = keyboard.nextInt();

        ticketController.updateServicer(id, servicerController.getById(id2).get());
    }
}
