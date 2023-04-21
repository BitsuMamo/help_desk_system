package controller;


import java.util.List;
import java.util.Optional;

public interface ICommon<T> {
    List<T> getAll();
    Optional<T> getById(Integer id);

    T create(T data);

    T delete(Integer id);
}
