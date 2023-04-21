package controller;

import dao.IDao;
import dao.ServicerDao;
import model.Servicer;

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
        return servicerDao.delete(id);
    }
}
