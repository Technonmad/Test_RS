package DAO;

import DTO.Position;

import java.sql.SQLException;

public interface PositionDAO extends DAO<Position>{

    int getSalary(String position) throws SQLException;
    int getIdByName(String name) throws SQLException;
    int getMaxId() throws SQLException;

}
