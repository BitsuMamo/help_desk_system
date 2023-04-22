package view;

import controller.TicketController;
import model.Customer;
import model.Ticket;

import java.util.List;
import java.util.Scanner;

public class CustomerView {
    private final Scanner keyboard = new Scanner(System.in);
    private final Customer cust;
    private final TicketController ticketController = new TicketController();
    CustomerView(Customer cust){
        this.cust = cust;
    }
    public void showCustomerView(){
        int input;
        Util.printGreeting(cust);

        do {
            Util.printMenu(
                    "Create Ticket",
                    "View Active Tickets",
                    "Delete Active Ticket",
                    "View Resolved Tickets",
                    "Exit"
            );
            input = keyboard.nextInt();
            keyboard.nextLine();

            switch (input) {
                case 1 -> createTicket();
                case 2 -> viewActiveTickets();
                case 3 -> deleteActiveTicket();
                case 4 -> viewResolvedTickets();
                case 5 -> new AuthenticationView().showAuthScreen();
                default -> System.out.println("Wrong Input!");
            }
        }while(input != 5);

    }

    private void viewResolvedTickets() {
        List<Ticket> tickets = ticketController.getByCustomer(cust.getId()).stream()
                .filter(Ticket::isFixed)
                .toList();
        Util.printTickets(tickets);

    }

    private void deleteActiveTicket() {
        viewActiveTickets();
        int id;
        id = Util.getId(keyboard, "Ticket");
        ticketController.delete(id);
    }

    private void viewActiveTickets() {
        List<Ticket> tickets = ticketController.getByCustomer(cust.getId());
        Util.printTickets(tickets);

    }

    private void createTicket() {
        String title, desc;

        System.out.println("+------------------------------------------------");
        System.out.printf("|%12s >    ", "Enter Title");
        title = keyboard. nextLine();
        System.out.println("+------------------------------------------------");
        System.out.printf("|%12s >    ", "Enter Desc");
        desc = keyboard.nextLine();
        System.out.println("+------------------------------------------------");

        Ticket newTicket = new Ticket(title, desc, cust);
        ticketController.create(newTicket);
    }
}
