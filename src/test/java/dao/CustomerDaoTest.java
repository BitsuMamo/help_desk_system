package dao;

import model.Customer;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDaoTest {

    @Test
    void getAll() {
        CustomerDao dao = new CustomerDao();
        List<Customer> custs = dao.getAll();

        System.out.println(custs.size());

        assertEquals(4, custs.size());
    }
    @Test
    void getAllCheckInstance() {
        CustomerDao dao = new CustomerDao();
        List<Customer> custs = dao.getAll();

        custs.forEach(
                customer -> {
                    assertEquals(Customer.class, customer.getClass());
                }
        );
    }

    @Test
    void getByValidId() {
        CustomerDao dao = new CustomerDao();
        Optional<Customer> cust = dao.getById(1);
        Customer expected = new Customer(1, "John Doe", "johndoe", "password1");

        assertTrue(cust.isPresent());
        assertEquals(expected, cust.get());
    }
    @Test
    void getByInValidId() {
        CustomerDao dao = new CustomerDao();
        Optional<Customer> cust = dao.getById(1000);

        assertFalse(cust.isPresent());
    }

    @Test
    void create() {
        CustomerDao dao = new CustomerDao();
        Customer newCust = new Customer("test", "test", "test");

        Customer returnCust = dao.create(newCust);

        assertNotNull(returnCust);
        assertEquals(returnCust.getName(), newCust.getName());
        assertEquals(returnCust.getUserName(), newCust.getUserName());
        assertEquals(returnCust.getPassword(), newCust.getPassword());
        assertEquals(returnCust.getUserType(), newCust.getUserType());

        dao.delete(returnCust.getId());
    }

    @Test
    void delete() {
        CustomerDao dao = new CustomerDao();

        Customer newCust = new Customer("test", "test", "test");

        Customer returnCust = dao.delete(dao.create(newCust).getId());

        assertNotNull(returnCust);
        assertEquals("test", returnCust.getName());
        assertEquals("test", returnCust.getUserName());
        assertEquals("test",returnCust.getPassword());
        assertEquals("CUSTOMER",returnCust.getUserType());
    }
    @Test
    void deleteInvalid() {
        CustomerDao dao = new CustomerDao();
        Customer returnCust = dao.delete(1000);

        assertNull(returnCust);

    }
}