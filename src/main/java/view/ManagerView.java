package view;

import controller.ManagerController;
import controller.TicketController;
import model.Manager;
import model.Ticket;

import java.util.List;
import java.util.Scanner;

public class ManagerView {
    private final Scanner keyboard = new Scanner(System.in);
    private final Manager manager;

    ManagerView(Manager manager){
        this.manager = manager;
    }
    private final TicketController ticketController = new TicketController();
    public void showManagerView(){
        int input;
        do{
            System.out.println("1. Create User");
            System.out.println("2. Create Servicer");
            System.out.println("3. View Tickets");
            System.out.println("4. Assign Ticket to Servicer");
            System.out.println("5. Exit");
            System.out.print(">");
            input = keyboard.nextInt();

            switch (input){
                case 1:
                    createUser();
                    break;
                case 2:
                    createServicer();
                    break;
                case 3:
                    viewTickets();
                    break;
                case 4:
                    assignTicket();
                    break;
                case 5:
                    new AuthenticationView().showAuthScreen();
                    break;
            }

        }while(input != 5);
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
        System.out.println("Enter ID: ");
        int id = keyboard.nextInt();
        ticketController.updateStatus(id);
    }
}
