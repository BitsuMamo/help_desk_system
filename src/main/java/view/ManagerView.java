package view;

import controller.CustomerController;
import controller.ServicerController;
import controller.TicketController;
import model.*;

import java.util.HashMap;
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

        Optional<Ticket> ticket = ticketController.delete(ticket_id);
        if(ticket.isPresent()){
            Util.printMsg("DELETED");
            Util.printTickets(List.of(ticket.get()));
            return;
        }
        Util.printMsg("Ticket Doesn't Exits");

    }

    private void deleteServicer() {
        viewServicers();
        int servicer_id = Util.getId(keyboard, "Servicer");

        Optional<Servicer> servicer = servicerController.delete(servicer_id);
        if (servicer.isPresent()){
            Util.printMsg("DELETED");
            Util.printUsers(List.of(servicer.get()));
            return;
        }
        Util.printMsg("Servicer Doesn't Exist");
    }

    private void deleteCustomer() {
        viewCustomers();
        int customer_id = Util.getId(keyboard, "Customer");

        Optional<Customer> customer = customerController.delete(customer_id);
        if(customer.isPresent()){
            Util.printMsg("DELETED");
            Util.printUsers(List.of(customer.get()));
            return;
        }
        Util.printMsg("Customer Doesn't Exist");
    }

    private void viewTicketByCustomer() {
        viewCustomers();
        int customer_id = Util.getId(keyboard, "Customer");
        List<Ticket> tickets = ticketController.getByCustomer(customer_id);
        Util.printTickets(tickets);
    }

    private void viewServicers() {
        List<Servicer> servicers = servicerController.getAll();
        Util.printUsers(servicers);
    }

    private void viewCustomers() {
       List<Customer> customers = customerController.getAll();
       Util.printUsers(customers);
    }

    private void createServicer() {
        HashMap<String, String> input = new HashMap<>();
        getUserInput(input);

        Servicer newServicer = new Servicer(input.get("NAME"), input.get("USERNAME"), input.get("PASSWORD"));
        Util.printMsg("CREATED");
        Servicer servicer = servicerController.create(newServicer);
        Util.printUsers(List.of(servicer));
    }

    private void createCustomer() {
        HashMap<String, String> input = new HashMap<>();
        getUserInput(input);

        Util.printMsg("CREATED");
        Customer newCustomer =  new Customer(input.get("NAME"), input.get("USERNAME"), input.get("PASSWORD"));
        Customer customer = customerController.create(newCustomer);
        Util.printUsers(List.of(customer));
    }

    private void viewTickets(){
        List<Ticket> tickets = ticketController.getAll();
        Util.printTickets(tickets);
    }

    private void assignTicket(){
       List<Ticket> unAssignedTicket = ticketController.getUnAssignedTicket();
        Util.printTickets(unAssignedTicket);

        int ticketId = Util.getId(keyboard, "Ticket");

        viewServicers();

        int servicerId = Util.getId(keyboard, "Servicer");

        Optional<Servicer> servicer = servicerController.getById(servicerId);
        ticketController.updateServicer(ticketId, servicer.orElse(null));
    }

    private void getUserInput(HashMap<String, String> input){
        System.out.println("+--------------------------------------------");
        System.out.printf("|%-20s >    ", "Enter Name: ");
        input.put("NAME", keyboard.nextLine());
        System.out.println("+--------------------------------------------");
        System.out.printf("|%-20s >    ", "Enter User Name: ");
        input.put("USERNAME", keyboard.nextLine());
        System.out.println("+--------------------------------------------");
        System.out.printf("|%-20s >    ", "Enter Password: ");
        input.put("PASSWORD", keyboard.nextLine());
        System.out.println("+--------------------------------------------");
    }
}
