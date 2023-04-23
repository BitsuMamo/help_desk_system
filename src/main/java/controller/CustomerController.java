package controller;

import dao.CustomerDao;
import dao.IDao;
import model.Customer;
import model.Ticket;

import java.util.List;
import java.util.Optional;

public class CustomerController implements ICommon<Customer> {

    IDao<Customer> customerDao = new CustomerDao();
    @Override
    public List<Customer> getAll() {
        return customerDao.getAll();
    }

    @Override
    public Optional<Customer> getById(Integer id) {
        return customerDao.getById(id);
    }

    @Override
    public Customer create(Customer data) {
        return customerDao.create(data);
    }

    @Override
    public Customer delete(Integer id) {
        TicketController ticketController = new TicketController();
        List<Ticket> tickets = ticketController.getByCustomer(id);
        tickets.forEach(ticket -> ticketController.delete(ticket.getId()));

        return customerDao.delete(id);
    }

}
