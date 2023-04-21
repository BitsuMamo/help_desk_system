package dao;

import model.Customer;
import model.Servicer;

import java.util.List;
import java.util.Optional;

public class ServicerDao implements IDao<Servicer>, IDummyData {

    @Override
    public List<Servicer> getAll() {
        return dm.usersData
                .values().stream()
                .filter(user -> user.getUserType().equals("SERVICER"))
                .map(user -> (Servicer)user)
                .toList();
    }

    @Override
    public Optional<Servicer> getById(Integer id) {
        return Optional.of((Servicer) dm.usersData.get(id));
    }

    @Override
    public Servicer create(Servicer data) {
        dm.usersData.put(data.getId(), data);
        return (Servicer) dm.usersData.get(data.getId());
    }

    @Override
    public Servicer delete(Integer id) {
        Servicer user = (Servicer) dm.usersData.get(id);
        dm.usersData.remove(id);
        return user;
    }
}
