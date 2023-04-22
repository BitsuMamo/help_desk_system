package controller;

import dao.TicketDao;
import model.Customer;
import model.Servicer;
import model.Ticket;

import java.util.List;
import java.util.Optional;

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

}
