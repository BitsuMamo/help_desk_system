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
        do {
            System.out.println("1. Create Ticket");
            System.out.println("2. View Active Tickets");
            System.out.println("3. Delete Active Ticket");
            System.out.println("4. View Resolved Tickets");
            System.out.println("5. Exit");
            input = keyboard.nextInt();

            switch (input){
                case 1:
                    createTicket();
                    break;
                case 2:
                    viewActiveTickets();
                    break;
                case 3:
                    deleteActiveTicket();
                    break;
                case 4:
                    viewResolvedTickets();
                    break;
                case 5:
                    new AuthenticationView().showAuthScreen();
                    break;
                default:
                    System.out.println("Wrong Input!");
                    break;
            }
        }while(input != 5);

    }

    private void viewResolvedTickets() {
        List<Ticket> tickets = ticketController.getByCustomer(cust);
        tickets.stream()
                .filter(Ticket::isFixed)
                .forEach(System.out::println);
    }

    private void deleteActiveTicket() {
        viewActiveTickets();
        int id;
        System.out.print("Enter ID: ");
        id = keyboard.nextInt();
        ticketController.delete(id);
    }

    private void viewActiveTickets() {
        List<Ticket> tickets = ticketController.getByCustomer(cust);
        Util.printTickets(tickets);

    }

    private void createTicket() {
        int id;
        String title, desc;

        System.out.print("Enter ID: ");
        id = keyboard.nextInt();
        keyboard.nextLine();
        System.out.print("Enter Title: ");
        title = keyboard. nextLine();
        System.out.print("Enter Desc: ");
        desc = keyboard.nextLine();

        Ticket newTicket = new Ticket(id, title, desc, cust);
        ticketController.create(newTicket);
    }
}
