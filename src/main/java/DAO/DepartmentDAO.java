package DAO;

import DTO.Department;
import DTO.Position;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentDAO extends DAO<Department> {

    int getBossId(String department) throws SQLException;

    List<String> getPositions(String department) throws SQLException;
    List<String> getDepartmentsName() throws SQLException;

    int getIdByName(String name) throws SQLException;

    int getMaxId() throws SQLException;

}
