package DAO;

import java.sql.SQLException;

public interface DAO<T>{

    int get(T expression) throws SQLException;
    int insert(T expression) throws SQLException;
    int update(T expression) throws SQLException;
    int delete(T expression) throws SQLException;

}
