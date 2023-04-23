package controller;

import dao.IDao;
import dao.ServicerDao;
import model.Servicer;
import model.Ticket;

import java.util.List;
import java.util.Optional;

public class ServicerController implements ICommon<Servicer> {




    IDao<Servicer> servicerDao = new ServicerDao();
    @Override
    public List<Servicer> getAll() {
        return servicerDao.getAll();
    }

    @Override
    public Optional<Servicer> getById(Integer id) {
        return servicerDao.getById(id);
    }

    @Override
    public Servicer create(Servicer data) {
        return servicerDao.create(data);
    }

    @Override
    public Servicer delete(Integer id) {
        TicketController ticketController = new TicketController();

        List<Ticket> tickets = ticketController.getByServicer(id);
        tickets.forEach(ticket -> ticketController.updateServicer(ticket.getId(), null));

        return servicerDao.delete(id);
    }


}
