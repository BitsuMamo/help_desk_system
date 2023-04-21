package dao;

import model.Customer;

import java.util.List;
import java.util.Optional;

public class CustomerDao implements IDao<Customer>, IDummyData {
    @Override
    public List<Customer> getAll() {
        return dm.usersData
                .values().stream()
                .filter(user -> user.getUserType().equals("CUSTOMER"))
                .map(user -> (Customer)user)
                .toList();
    }

    @Override
    public Optional<Customer> getById(Integer id) {
        return Optional.of((Customer) dm.usersData.get(id));
    }

    @Override
    public Customer create(Customer data) {
        dm.usersData.put(data.getId(), data);
        return (Customer) dm.usersData.get(data.getId());
    }

    @Override
    public Customer delete(Integer id) {
        Customer user = (Customer) dm.usersData.get(id);
        dm.usersData.remove(id);
        return user;
    }
}
