package controller;

import dao.TicketDao;
import model.Customer;
import model.Servicer;
import model.Ticket;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class TicketController implements ICommon<Ticket>{
    TicketDao ticketDao = new TicketDao();
    @Override
    public List<Ticket> getAll() {
        return ticketDao.getAll();
    }

    @Override
    public Optional<Ticket> getById(Integer id) {
        return ticketDao.getById(id);
    }

    @Override
    public Ticket create(Ticket data) {
        return ticketDao.create(data);
    }

    @Override
    public Ticket delete(Integer id) {
        return ticketDao.delete(id);
    }

    public List<Ticket> getByCustomer(Integer customer_id){
        return ticketDao.getByCustomer(customer_id);
    }

    public List<Ticket> getByServicer(Integer servicer_id){
        return ticketDao.getByServicer(servicer_id);
    }

    public void updateStatus(Integer id){
        ticketDao.resolveTicket(id);
    }

    public void updateServicer(Integer id, Servicer servicer){
        Integer servicer_id = servicer == null ? null : servicer.getId();
        ticketDao.addServicer(id, servicer_id);
    }


    public List<Ticket> getUnAssignedTicket() {
        List<Ticket> tickets = getAll();

        return tickets.stream()
                .filter(ticket -> ticket.getServicer() == null)
                .toList();
    }

    public List<Ticket> getFixedTicket(Integer id){

        Stream<Ticket> custList = getByCustomer(id).stream().filter(Ticket::isFixed);
        Stream<Ticket> servList = getByServicer(id).stream().filter(Ticket::isFixed);

        return Stream.concat(custList, servList).toList();
    }

    public List<Ticket> showActiveTickets(Servicer servicer){
        return getByServicer(servicer.getId()).stream()
                .filter(ticket -> !ticket.isFixed())
                .toList();
    }
}
