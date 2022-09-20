package DAO;

import DTO.Employee;

import java.sql.SQLException;

public interface EmployeeDAO extends DAO<Employee>{

    int getMaxId() throws SQLException;

}
