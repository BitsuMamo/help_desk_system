package controller;

import dao.IDao;
import dao.TicketDao;
import model.Ticket;

import java.util.List;
import java.util.Optional;

public class TicketController implements ICommon<Ticket> {
    IDao<Ticket> ticketDao = new TicketDao();
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
}
