package DAO;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T>{

    T get(int id) throws SQLException;
    List<T> getAll() throws SQLException;
    int insert(T expression) throws SQLException;
    int update(T expression, int id) throws SQLException;
    int delete(T expression) throws SQLException;

}
