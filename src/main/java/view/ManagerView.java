package view;

import controller.CustomerController;
import controller.ServicerController;
import controller.TicketController;
import model.*;

import java.util.List;
import java.util.Optional;
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
        Util.printGreeting(manager);
        do{
            Util.printMenu(
                    "Create Customer",
                    "Create Servicer",
                    "View Tickets",
                    "Assign Ticket to Servicer",
                    "View Customers",
                    "View Servicers",
                    "View Tickets By Customer",
                    "Delete Customer",
                    "Delete Servicer",
                    "Delete Ticket",
                    "Exit"
            );
            input = keyboard.nextInt();
            keyboard.nextLine();

            switch (input) {
                case 1 -> createCustomer();
                case 2 -> createServicer();
                case 3 -> viewTickets();
                case 4 -> assignTicket();
                case 5 -> viewCustomers();
                case 6 -> viewServicers();
                case 7 -> viewTicketByCustomer();
                case 8 -> deleteCustomer();
                case 9-> deleteServicer();
                case 10 -> deleteTicket();
                case 11 -> new AuthenticationView().showAuthScreen();
            }

        }while(input != 11);
    }

    private void deleteTicket() {
        viewTickets();
        int ticket_id = Util.getId(keyboard, "Ticket");

        ticketController.delete(ticket_id);
    }

    private void deleteServicer() {
        viewServicers();
        int servicer_id = Util.getId(keyboard, "Servicer");

        List<Ticket> tickets = ticketController.getByServicer(servicer_id);
        tickets.forEach(ticket -> ticketController.updateServicer(ticket.getId(), null));

        servicerController.delete(servicer_id);
    }

    private void deleteCustomer() {
        viewCustomers();
        int customer_id = Util.getId(keyboard, "Customer");


        List<Ticket> tickets = ticketController.getByCustomer(customer_id);
        tickets.forEach(ticket -> ticketController.delete(ticket.getId()));

        customerController.delete(customer_id);
    }

    private void viewTicketByCustomer() {
        viewCustomers();
        int customer_id = Util.getId(keyboard, "Customer");
        List<Ticket> tickets = ticketController.getByCustomer(customer_id);
        Util.printTickets(tickets);
    }

    private void viewServicers() {
        List<Servicer> servicers = servicerController.getAll();
        Util.printUser(servicers);
    }

    private void viewCustomers() {
       List<Customer> customers = customerController.getAll();
       Util.printUser(customers);
    }

    private void createServicer() {
        String name, userName, password;
        System.out.println("+--------------------------------------------");
        System.out.printf("|%-20s >    ", "Enter Name: ");
        name = keyboard.nextLine();
        System.out.println("+--------------------------------------------");
        System.out.printf("|%-20s >    ", "Enter User Name: ");
        userName = keyboard.nextLine();
        System.out.println("+--------------------------------------------");
        System.out.printf("|%-20s >    ", "Enter Password: ");
        password = keyboard.nextLine();
        System.out.println("+--------------------------------------------");

        Servicer newServicer = new Servicer(name, userName, password);
        servicerController.create(newServicer);
    }

    //TODO Finish
    private void createCustomer() {
        String name, userName, password;
        System.out.println("+--------------------------------------------");
        System.out.printf("|%-20s >    ", "Enter Name: ");
        name = keyboard.nextLine();
        System.out.println("+--------------------------------------------");
        System.out.printf("|%-20s >    ", "Enter User Name: ");
        userName = keyboard.nextLine();
        System.out.println("+--------------------------------------------");
        System.out.printf("|%-20s >    ", "Enter Password: ");
        password = keyboard.nextLine();
        System.out.println("+--------------------------------------------");

        Customer newCustomer = new Customer(name, userName, password);
        customerController.create(newCustomer);
    }

    public void viewTickets(){
        List<Ticket> tickets = ticketController.getAll();
        Util.printTickets(tickets);
    }

    public void assignTicket(){
        List<Ticket> tickets = ticketController.getAll();

        List<Ticket> unAssignedTicket = tickets.stream()
                .filter(ticket -> ticket.getServicer() == null)
                .toList();

        Util.printTickets(unAssignedTicket);

        int id = Util.getId(keyboard, "Ticket");

        int id2 = Util.getId(keyboard, "Servicer");

        Optional<Servicer> servicer = servicerController.getById(id2);
        ticketController.updateServicer(id, servicer.orElse(null));
    }
}
