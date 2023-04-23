package view;

import controller.TicketController;
import model.Servicer;
import model.Ticket;

import java.util.List;
import java.util.Scanner;

public class ServicerView {
    private final Scanner keyboard = new Scanner(System.in);
    private final TicketController ticketController = new TicketController();
    private final Servicer servicer;
    ServicerView(Servicer servicer){
        this.servicer = servicer;
    }

    public void showServicerView(){
        int input;

        Util.printGreeting(servicer);

        do{
            Util.printMenu("Show Active Tickets", "Show Resolved Ticket", "Resolve Ticket", "Exit");
            input = keyboard.nextInt();

            switch (input) {
                case 1 -> showActiveTickets();
                case 2 -> showResolvedTickets();
                case 3 -> resolveTicket();
                case 4 -> new AuthenticationView().showAuthScreen();
            }


        }while (input != 4);
    }

    private void showActiveTickets() {
        Util.printTickets(ticketController.showActiveTickets(servicer));
    }

    private void resolveTicket() {
        showActiveTickets();
        int id = Util.getId(keyboard, "Ticket");
        ticketController.updateStatus(id);
    }

    private void showResolvedTickets() {
        Util.printTickets(ticketController.getFixedTicket(servicer.getId()));
    }
}
