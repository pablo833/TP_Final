package DAOS;

import EXCEPTIONS.RadioException;

import java.util.List;

public interface DAO<T> {

    void insert(T t) throws RadioException;

    void update(T t) throws RadioException;

    T get(int codigo) throws RadioException, UnsupportedOperationException;

    void delete(int codigo) throws RadioException;

    List<T> getAll() throws RadioException;

    T getByInternalID(T t) throws RadioException;
}
