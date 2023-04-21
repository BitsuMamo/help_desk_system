package controller;

import dao.IDao;
import dao.IDummyData;
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

    public List<Ticket> getByCustomer(Customer customer){
        return ticketDao.getByCustomer(customer);
    }

    public List<Ticket> getByServicer(Servicer servicer){
        return ticketDao.getByServicer(servicer);
    }

    public void updateStatus(Integer id){
        Optional<Ticket> ticket = ticketDao.getById(id);
        ticket.get().setFixed(true);
    }

    public void updateServicer(Integer id, Servicer servicer){
        ticketDao.getById(id).get().setServicer(servicer);
    }

}
