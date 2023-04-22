package view;

import controller.ServicerController;
import controller.TicketController;
import model.Servicer;
import model.Ticket;

import java.util.List;
import java.util.Scanner;

public class ServicerView {
    private final Scanner keyboard = new Scanner(System.in);
//    private final ServicerController servicerController = new ServicerController();
    private final TicketController ticketController = new TicketController();
    private final Servicer servicer;
    ServicerView(Servicer servicer){
        this.servicer = servicer;
    }

    public void showServicerView(){
        int input;
        do{
            System.out.println("1. Show Active Tickets");
            System.out.println("2. Show Ticket");
            System.out.println("3. Resolve Ticket");
            System.out.println("4. Exit");
            input = keyboard.nextInt();

            switch (input) {
                case 1 -> showActiveTickets();
                case 2 -> showTickets();
                case 3 -> resolveTicket();
                case 4 -> new AuthenticationView().showAuthScreen();
            }


        }while (input != 3);
    }

    private void showActiveTickets() {
        List<Ticket> tickets = ticketController.getByServicer(servicer).stream()
                .filter(ticket -> !ticket.isFixed())
                .toList();
        Util.printTickets(tickets);
    }

    private void resolveTicket() {
        showActiveTickets();
        System.out.println("Enter ID: ");
        int id = keyboard.nextInt();
        ticketController.updateStatus(id);
    }

    private void showTickets() {
        List<Ticket> tickets = ticketController.getByServicer(servicer);
        Util.printTickets(tickets);
    }
}
