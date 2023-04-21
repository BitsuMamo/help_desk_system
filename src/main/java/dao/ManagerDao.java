package dao;

import model.Customer;
import model.Manager;

import java.util.List;
import java.util.Optional;

public class
ManagerDao implements IDao<Manager>, IDummyData {
    @Override
    public List<Manager> getAll() {
        return dm.usersData
                .values().stream()
                .filter(user -> user.getUserType().equals("MANAGER"))
                .map(user -> (Manager)user)
                .toList();
    }

    @Override
    public Optional<Manager> getById(Integer id) {
        return Optional.of((Manager) dm.usersData.get(id));
    }

    @Override
    public Manager create(Manager data) {
        dm.usersData.put(data.getId(), data);
        return (Manager) dm.usersData.get(data.getId());
    }

    @Override
    public Manager delete(Integer id) {
        Manager user = (Manager) dm.usersData.get(id);
        dm.usersData.remove(id);
        return user;
    }
}
