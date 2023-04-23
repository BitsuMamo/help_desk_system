package controller;

import dao.IDao;
import dao.ManagerDao;
import model.Manager;

import java.util.List;
import java.util.Optional;

public class ManagerController implements ICommon<Manager> {
    IDao<Manager> managerDao = new ManagerDao();
    @Override
    public List<Manager> getAll() {
        return managerDao.getAll();
    }

    @Override
    public Optional<Manager> getById(Integer id) {
        return managerDao.getById(id);
    }

    @Override
    public Manager create(Manager data) {
        return managerDao.create(data);
    }

    @Override
    public Optional<Manager> delete(Integer id) {
        return managerDao.delete(id);
    }
}
